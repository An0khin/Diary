package com.home.Diary.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import javax.swing.table.*;
import javax.swing.text.JTextComponent;

import com.home.Diary.model.Record;
import com.home.Diary.view.listeners.DeleteButtonListener;
import com.home.Diary.view.listeners.EditButtonListener;
import com.home.Diary.view.listeners.LoadButtonListener;
import com.home.Diary.view.listeners.NewButtonListener;
import com.home.Diary.view.listeners.OpenButtonListener;
import com.home.Diary.view.listeners.SaveButtonListener;
import com.home.Diary.view.listeners.SettingButtonListener;
import com.home.Diary.viewmodel.Diary;

import javax.swing.*;
/*
 * Created by JFormDesigner on Mon Jun 13 23:55:52 YEKT 2022
 */



/**
 * @author get
 */
public class DiaryWindow extends JFrame implements Observer{
	
	private Diary diary;
	private DefaultTableModel tableModel;
	private OpenButtonListener openButtonListener;
	private NewButtonListener newButtonListener;
	private EditButtonListener editButtonListener;
	private DeleteButtonListener deleteButtonListener;
	private SettingButtonListener settingButtonListener;
	private SaveButtonListener saveButtonListener;
	private LoadButtonListener loadButtonListener;
	private Record currentRecord;
	private Popup popupFrame;
		
	public DiaryWindow(Diary diary) {
		this.diary = diary;
		diary.addObserver(this);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createListeners();
		
		createPopup();
		
		initComponents();
		
		createModel(diary.getColumns());
		
		diary.change();
	}
	
	public void createPopup() {
		List<JButton> components = new ArrayList<>();
		
		JButton openButton = new JButton("OPEN");
		openButton.addActionListener(openButtonListener);
		
		JButton editButton = new JButton("EDIT");
		editButton.addActionListener(editButtonListener);
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(deleteButtonListener);
		
		components.add(openButton);
		components.add(editButton);
		components.add(deleteButton);
		
		popupFrame = new Popup(components);
	}
	
	public void createListeners() {
		openButtonListener = new OpenButtonListener(diary);
		newButtonListener = new NewButtonListener(diary);
		editButtonListener = new EditButtonListener(diary);
		deleteButtonListener = new DeleteButtonListener(diary);
		settingButtonListener = new SettingButtonListener(diary);
		saveButtonListener = new SaveButtonListener(diary);
		loadButtonListener = new LoadButtonListener(diary);
	}
	
	public void updateListeners() {
		openButtonListener.setCurrentRecord(currentRecord);
		editButtonListener.setCurrentRecord(currentRecord);
		deleteButtonListener.setCurrentRecord(currentRecord);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		currentRecord = null;
//		System.out.println(tableModel.getRowCount());
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.removeRow(i);
		}
		
		tableModel.setRowCount(0);

		//0 - title; 1 - lastUpd; 2 - description
		boolean[] columns = diary.getColumns();
		
		createModel(columns);
		
		for(Record rec : diary.getList()) {
			List<Object> row = new ArrayList<>();
			row.add(rec.getDate());
			
			if(columns[0]) {
//				System.out.println("TITLE");
				row.add(rec.getTitle());
			}
			
			if(columns[1]) {
//				System.out.println("LAST UPDATE");
				row.add(rec.getLastUpdate());
			}
			
			if(columns[2]) {
//				System.out.println("DESCRIPTION");
				row.add(rec.getDescription());
			}
			
			
			tableModel.addRow(row.toArray());
		}
		
		panelInfo.removeAll();
		panelInfo.updateUI();
		
		recordsTable.updateUI();
		updateListeners();
		
	}
	
	public void viewRecord(Record record) { //Right panel with info about record
		panelInfo.removeAll();
		panelInfo.updateUI();
		
		Font font = new Font("a", Font.BOLD, 12);
		
		JTextField infoComp = new JTextField("INFO");
		infoComp.setHorizontalAlignment(JTextField.CENTER);
		infoComp.setFont(new Font("a", Font.BOLD, 18));
		
		JTextField titleComp = new JTextField("Title");
		titleComp.setHorizontalAlignment(JTextField.CENTER);
		titleComp.setFont(font);
		
		JTextField dateComp = new JTextField("Date");
		dateComp.setHorizontalAlignment(JTextField.CENTER);
		dateComp.setFont(font);
		
		JTextField lastUpdateComp = new JTextField("Last Update");
		lastUpdateComp.setHorizontalAlignment(JTextField.CENTER);
		lastUpdateComp.setFont(font);
			
				
		JTextComponent[] comps = {
				infoComp, 
				titleComp,
				new JTextField(record.getTitle()),
				dateComp,
				new JTextField(record.getDate().toString()),
				lastUpdateComp,
				new JTextField(record.getLastUpdate().toString()),
		};
				
		for(JTextComponent comp : comps) {
			comp.setEditable(false);
			panelInfo.add(comp);
		}
		
	}
		
	public void openChoiceRecord(Record record) { //Frame with info about record
		if(record != null) {
			JPanel upperPanel = new JPanel(new BorderLayout());
			
			JTextField dateField = new JTextField(record.getDate().toString());
			dateField.setEditable(false);
				
			JTextField titleField = new JTextField(record.getTitle(), 40);
			titleField.setHorizontalAlignment(SwingConstants.CENTER);
			titleField.setEditable(false);

			JTextField lastUpdateLbl = new JTextField(record.getLastUpdate().toString());
			lastUpdateLbl.setEditable(false);
				
			upperPanel.add(dateField, BorderLayout.WEST);
			upperPanel.add(titleField, BorderLayout.CENTER);
			upperPanel.add(lastUpdateLbl, BorderLayout.EAST);
			
			JTextArea descriptionArea = new JTextArea(record.getDescription(), 5, 40);
			descriptionArea.setLineWrap(true);
			descriptionArea.setEditable(false);
			JScrollPane descriptionPane = new JScrollPane(descriptionArea);
			descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			JPanel lowerPanel = new JPanel(new BorderLayout());
			
			JButton openButton = new JButton("OPEN");
			openButton.addActionListener(openButtonListener);
			
			JButton editButton = new JButton("EDIT");
			editButton.addActionListener(editButtonListener);
			
			lowerPanel.add(openButton, BorderLayout.WEST);
			lowerPanel.add(editButton, BorderLayout.EAST);
			
			Object[] message = {
					upperPanel,
					descriptionPane,
					lowerPanel
			};
			
			JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE);
			JDialog dialog = pane.createDialog("HOP");
			
			openButton.addActionListener((e) -> {dialog.setVisible(false);});
			editButton.addActionListener((e) -> {dialog.setVisible(false);});
			
			pane.remove(pane.getComponents().length - 1);
			dialog.setVisible(true);
		}
	}

	private class TableSelectionListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
//			popupFrame.setVisible(false);
			
			JTable table = (JTable)(e.getSource());
			Point p = e.getPoint();
			int row = table.rowAtPoint(p);
			table.setRowSelectionInterval(row, row);
			
			int index = recordsTable.getSelectedRow();
			
			if(index != -1) {
				currentRecord = diary.getRecordByDate((Date) tableModel.getValueAt(index, 0));

				viewRecord(currentRecord);
				
				updateListeners();
			}
			
			if(e.getButton() == MouseEvent.BUTTON3) {
				Point mousePos = e.getLocationOnScreen();
				mousePos.move((int) mousePos.getX() - 5, (int) mousePos.getY() - 5);
				popupFrame.setLocation(mousePos);
				popupFrame.setVisible(true);
			} else if(e.getButton() == MouseEvent.BUTTON1) {
				if(e.getClickCount() >= 2) {
					openChoiceRecord(currentRecord);
				}
			}
		}
	}
	
	private class FindButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JLabel labelDate = new JLabel("Enter date/title/description");
			JTextField searchFieldDate = new JTextField();
			JLabel labelTitle = new JLabel("Enter title");
			JTextField searchFieldTitle = new JTextField();
			JLabel labelDesc = new JLabel("Enter description");
			JTextField searchFieldDescription = new JTextField();
			
			Object[] message = {
					labelDate,
					searchFieldDate,
					labelTitle,
					searchFieldTitle,
					labelDesc,
					searchFieldDescription
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Search", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			recordsTable.clearSelection();
			
			if(option == JOptionPane.OK_OPTION) {
				Record[] records = diary.findRecords(searchFieldDate.getText(), searchFieldTitle.getText(), searchFieldDescription.getText());
				
				int size = tableModel.getRowCount();
				Record currentRecord;
				
				for(int i = 0; i < size; i++) {
					currentRecord = diary.getRecordByDate((Date) tableModel.getValueAt(i, 0));
					
					for(int j = 0; j < records.length; j++) {
						if(currentRecord.equals(records[j])) {
							recordsTable.addRowSelectionInterval(i, i);
							break;
						}
					}
				}
			}
		}
	}
	
	public class ColumnsButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox dateColumn = new JCheckBox("DATE", true); //Date always true
			dateColumn.setEnabled(false);
			
			boolean[] columns = diary.getColumns();
			
			JCheckBox titleColumn = new JCheckBox("TITLE", columns[0]);
			JCheckBox lastUpdColumn = new JCheckBox("LAST UPDATE", columns[1]);
			JCheckBox descriptionColumn = new JCheckBox("DESCRIPTION", columns[2]);		
			
			Object[] message = {
					dateColumn,
					titleColumn,
					lastUpdColumn,
					descriptionColumn
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Columns...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						
			if(option == JOptionPane.OK_OPTION) {	
				
			columns[0] = titleColumn.isSelected();
			columns[1] = lastUpdColumn.isSelected();
			columns[2] = descriptionColumn.isSelected();
				
				diary.setColumns(columns);
				createModel(columns);
				update(null, null);
			}
		}
	}
	
	public void createModel(boolean[] columns) {
		//0 - title; 1 - lastUpd; 2 - description
		List<String> columnsString = new ArrayList<>();
		columnsString.add("DATE");
		
		if(columns[0]) {
			columnsString.add("TITLE");
		}
		
		if(columns[1]) {
			columnsString.add("LAST UPDATE");
		}
		
		if(columns[2]) {
			columnsString.add("DESCRIPTION");
		}
		
		tableModel = new DefaultTableModel(null, columnsString.toArray(new String[0])) {
			@Override
			public boolean isCellEditable(int row, int column) { //using for disable editable rows
				return false;
			}
		};
		
		recordsTable.setModel(tableModel);
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		diaryWindow = new JFrame();
		menuBar1 = new JMenuBar();
		menuFile = new JMenu();
		menuFileItemSave = new JMenuItem();
		menuFileItemLoad = new JMenuItem();
		menuFileItemSettings = new JMenuItem();
		menuEdit = new JMenu();
		menuEditItemNew = new JMenuItem();
		menuEditItemEdit = new JMenuItem();
		menuEditItemDelete = new JMenuItem();
		menuFind = new JMenu();
		menuFindItemFind = new JMenuItem();
		menuView = new JMenu();
		menuViewItemColumns = new JMenuItem();
		mainPanel = new JPanel();
		panelRecords = new JPanel();
		scrollPaneRecordsTable = new JScrollPane();
		recordsTable = new JTable();
		panelInfo = new JPanel();
		buttonNew = new JButton();

		//======== diaryWindow ========
		{
			diaryWindow.setTitle("My Diary");
			diaryWindow.setVisible(true);
			diaryWindow.setMinimumSize(new Dimension(650, 525));
			Container diaryWindowContentPane = diaryWindow.getContentPane();
			diaryWindowContentPane.setLayout(new BorderLayout());

			//======== menuBar1 ========
			{

				//======== menuFile ========
				{
					menuFile.setText("File");

					//---- menuFileItemSave ----
					menuFileItemSave.setText("Save");
					menuFile.add(menuFileItemSave);

					//---- menuFileItemLoad ----
					menuFileItemLoad.setText("Load");
					menuFile.add(menuFileItemLoad);

					//---- menuFileItemSettings ----
					menuFileItemSettings.setText("Settings");
					menuFile.add(menuFileItemSettings);
				}
				menuBar1.add(menuFile);

				//======== menuEdit ========
				{
					menuEdit.setText("Edit");

					//---- menuEditItemNew ----
					menuEditItemNew.setText("New");
					menuEdit.add(menuEditItemNew);

					//---- menuEditItemEdit ----
					menuEditItemEdit.setText("Edit");
					menuEdit.add(menuEditItemEdit);

					//---- menuEditItemDelete ----
					menuEditItemDelete.setText("Delete");
					menuEdit.add(menuEditItemDelete);
				}
				menuBar1.add(menuEdit);

				//======== menuFind ========
				{
					menuFind.setText("Find");

					//---- menuFindItemFind ----
					menuFindItemFind.setText("Find...");
					menuFind.add(menuFindItemFind);
				}
				menuBar1.add(menuFind);

				//======== menuView ========
				{
					menuView.setText("View");

					//---- menuViewItemColumns ----
					menuViewItemColumns.setText("Columns...");
					menuView.add(menuViewItemColumns);
				}
				menuBar1.add(menuView);
			}
			diaryWindow.setJMenuBar(menuBar1);

			//======== mainPanel ========
			{
				mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

				//======== panelRecords ========
				{
					panelRecords.setLayout(new BoxLayout(panelRecords, BoxLayout.X_AXIS));

					//======== scrollPaneRecordsTable ========
					{
						scrollPaneRecordsTable.setViewportView(recordsTable);
					}
					panelRecords.add(scrollPaneRecordsTable);

					//======== panelInfo ========
					{
						panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
					}
					panelRecords.add(panelInfo);
				}
				mainPanel.add(panelRecords);

				//---- buttonNew ----
				buttonNew.setText("New");
				buttonNew.setMaximumSize(new Dimension(33000, 25));
				buttonNew.setHideActionText(true);
				buttonNew.setMinimumSize(new Dimension(33000, 25));
				mainPanel.add(buttonNew);
			}
			diaryWindowContentPane.add(mainPanel, BorderLayout.CENTER);
			diaryWindow.pack();
			diaryWindow.setLocationRelativeTo(diaryWindow.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		tableModel = new DefaultTableModel(null, new String[] {"DATE", "TITLE", "LAST UPDATE", "DESCRIPTION"}) {
			@Override
			public boolean isCellEditable(int row, int column) { //using for disable editable rows
				return false;
			}
		};
				
		recordsTable.setModel(tableModel);
		buttonNew.addActionListener(newButtonListener);
		menuEditItemNew.addActionListener(newButtonListener);
		menuEditItemEdit.addActionListener(editButtonListener);
		menuEditItemDelete.addActionListener(deleteButtonListener);
		recordsTable.addMouseListener(new TableSelectionListener());
		menuFileItemSettings.addActionListener(settingButtonListener);
		menuFileItemSave.addActionListener(saveButtonListener);
		menuFileItemLoad.addActionListener(loadButtonListener);
		menuFindItemFind.addActionListener(new FindButtonListener());
		menuViewItemColumns.addActionListener(new ColumnsButtonListener());
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JFrame diaryWindow;
	private JMenuBar menuBar1;
	private JMenu menuFile;
	private JMenuItem menuFileItemSave;
	private JMenuItem menuFileItemLoad;
	private JMenuItem menuFileItemSettings;
	private JMenu menuEdit;
	private JMenuItem menuEditItemNew;
	private JMenuItem menuEditItemEdit;
	private JMenuItem menuEditItemDelete;
	private JMenu menuFind;
	private JMenuItem menuFindItemFind;
	private JMenu menuView;
	private JMenuItem menuViewItemColumns;
	private JPanel mainPanel;
	private JPanel panelRecords;
	private JScrollPane scrollPaneRecordsTable;
	private JTable recordsTable;
	private JPanel panelInfo;
	private JButton buttonNew;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
