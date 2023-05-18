package com.home.viewmodel;

import com.home.DefaultXMLManager;
import com.home.XMLManager;
import com.home.model.Record;
import com.home.model.RecordList;
import com.home.view.*;
import com.home.viewmodel.listeners.*;

import javax.swing.table.DefaultTableModel;
import javax.xml.transform.TransformerConfigurationException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Diary {
    private final DiaryWindow diaryWindow;
    private final DiarySettings diarySettings;
    private final DiaryTable diaryTable;
    private final SideRecord sideRecord;
    private final Popup popupFrame;

    private XMLManager xmlManager;
    private final RecordList recordList;
    private Record currentRecord;

    private NewButtonListener newListener;
    private OpenButtonListener openListener;
    private EditButtonListener editListener;
    private DeleteButtonListener deleteListener;

    private static final String DOCUMENTS_PATH = System.getProperty("user.home") + "/Documents/Diary/";

    public static Date stringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            return new Date(format.parse(dateString).getTime());
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Diary(DiaryWindow diaryWindow) {
        this.diaryWindow = diaryWindow;
        this.diarySettings = setupSettings();
        this.recordList = new RecordList();
        this.diaryTable = new DiaryTable(diarySettings, diaryWindow, recordList);
        this.popupFrame = new Popup();
        this.sideRecord = new SideRecord(diaryWindow.infoPanel);

        setListeners();
        setupXml();

        changeTable();
    }

    private DiarySettings setupSettings() {
        String fileIniPath = DOCUMENTS_PATH + "settings.ini";
        File fileIni = new File(fileIniPath);

        return new DiarySettings(fileIni);
    }
    private void setListeners() {
        setupMainListeners();
        setPopupListeners();

        setFirstListeners();

        setMenuListeners();
    }
    private void setupMainListeners() {
        newListener = new NewButtonListener(this);
        openListener = new OpenButtonListener();
        editListener = new EditButtonListener(this);
        deleteListener = new DeleteButtonListener(this);
    }
    private void setPopupListeners() {
        popupFrame.openButton.addActionListener(openListener);
        popupFrame.deleteButton.addActionListener(deleteListener);
        popupFrame.editButton.addActionListener(editListener);
    }
    private void setFirstListeners() {
        diaryWindow.recordsTable.addMouseListener(new TableSelectionListener(this, popupFrame));
        diaryWindow.buttonNew.addActionListener(newListener);
    }
    private void setMenuListeners() {
        setMenuFileListeners();
        setMenuEditListeners();
        setMenuFindListeners();
        setMenuViewListeners();
    }
    private void setMenuEditListeners() {
        diaryWindow.menuEditItemNew.addActionListener(newListener);
        diaryWindow.menuEditItemEdit.addActionListener(editListener);
        diaryWindow.menuEditItemDelete.addActionListener(deleteListener);
    }
    private void setMenuFileListeners() {
        diaryWindow.menuFileItemLoad.addActionListener(new LoadButtonListener(this));
        diaryWindow.menuFileItemSave.addActionListener(new SaveButtonListener(this));
        diaryWindow.menuFileItemSettings.addActionListener(new SettingButtonListener(this));
    }
    private void setMenuViewListeners() {
        diaryWindow.menuViewItemColumns.addActionListener(e -> new ColumnsSettingWindow(this));
    }

    private void setMenuFindListeners() {
        diaryWindow.menuFindItemFind.addActionListener(e -> new FindSettingWindow(this));
    }
    private void setupXml() {
        File diaryFolder = new File(DOCUMENTS_PATH);
        diaryFolder.mkdir();

        String xmlFilePath = DOCUMENTS_PATH + "data.xml";
        setXml(xmlFilePath);
    }

    private void setXml(String filePath) {
        try {
            xmlManager = new DefaultXMLManager(filePath);

            if(!new File(filePath).exists()) {
                xmlManager.createXml("DataBase");
                xmlManager.addElement("Records", "DataBase");
            }
        } catch(TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void changeTable() {
        currentRecord = null;

        updateListFromXML();

        diaryTable.updateTableModel();
        updateListeners();
    }

    private void updateListFromXML() { //adding records from XML to List
        recordList.clear();
        fillList();
    }

    private void fillList() {
        List<String[]> recordsXml = xmlManager.getListOf("Records", "Record", Record.getNull());

        for(String[] array : recordsXml) {
            Record rec = new Record(stringToDate(array[0]), array[1], stringToDate(array[2]), array[3], array[4]);
            recordList.addRecord(rec);
        }
    }

    private void updateListeners() {
        openListener.updateCurrentRecord(currentRecord);
        deleteListener.updateCurrentRecord(currentRecord);
        editListener.updateCurrentRecord(currentRecord);
    }

    public void updateCurrentRecordByDate(Date date) {
        currentRecord = recordList.getRecordByDate(date);

        sideRecord.viewRecord(currentRecord);

        updateListeners();
    }

    public void addRecord(Record rec) {
        recordList.addRecord(rec);
        xmlManager.addNode(rec.clone(), "Record", "Records");

        changeTable();
    }

    public void editRecord(Record rec, Date date, String title, Date lastUpd, String descr, String content) {
        Record newRecord = new Record(date, title, lastUpd, descr, content);
        xmlManager.editNode(rec.clone(), newRecord.clone(), "Record", "Records");

        rec.setDate(date);
        rec.setTitle(title);
        rec.setLastUpdate(lastUpd);
        rec.setDescription(descr);
        rec.setContent(content);

        changeTable();
    }

    public void deleteRecord(Record rec) {
        xmlManager.removeNode(rec.clone(), "Record", "Records");
        recordList.delete(rec);

        changeTable();
    }

    public boolean[] getColumns() {
        return diarySettings.getColumns();
    }

    public void setColumns(boolean[] columns) {
        diarySettings.setColumns(columns);
        changeTable();
    }

    public void selectRecordsByFind(String dateString, String title, String description) {
        diaryWindow.recordsTable.clearSelection();

        Record[] records = recordList.findRecords(dateString, title, description);

        selectRecords(records);
    }
    private void selectRecords(Record[] records) {
        DefaultTableModel tableModel = diaryWindow.tableModel;
        int size = tableModel.getRowCount();

        for(int i = 0; i < size; i++) {
            Date date = stringToDate((String) tableModel.getValueAt(i, 0));
            Record record = recordList.getRecordByDate(date);

            for(Record value : records) {
                if(record.equals(value)) {
                    diaryWindow.recordsTable.addRowSelectionInterval(i, i);
                    break;
                }
            }
        }
    }

    public void openChoiceCurrentRecord() {
        OpenDialog.open(currentRecord, openListener, editListener);
    }

    public void saveXml(File toPath) {
        xmlManager.saveXml(toPath);
    }

    public void loadXml(File fromPath) {
        xmlManager.loadXml(fromPath);

        updateListFromXML();
        changeTable();
    }

    public void resetAllData() {
        diarySettings.reset();
        xmlManager.clear("Records");
        recordList.clear();

        changeTable();
    }
}
