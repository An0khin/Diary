package com.home.viewmodel;

import com.home.model.Record;
import com.home.model.RecordList;
import com.home.view.DiaryWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class DiaryTable {
    DiarySettings diarySettings;
    DiaryWindow diaryWindow;
    RecordList recordList;

    public DiaryTable(DiarySettings diarySettings, DiaryWindow diaryWindow, RecordList recordList) {
        this.diarySettings = diarySettings;
        this.diaryWindow = diaryWindow;
        this.recordList = recordList;
    }

    public void updateTableModel() {
        clearTableModel();

        //0 - title; 1 - lastUpd; 2 - description
        boolean[] columns = diarySettings.getColumns();
        DefaultTableModel tableModel = createModel(columns);

        fillTable(tableModel, columns);

        JPanel panelInfo = diaryWindow.infoPanel;

        panelInfo.removeAll();
        panelInfo.updateUI();

        tableModel.fireTableDataChanged();
    }
    private void fillTable(DefaultTableModel tableModel, boolean[] columns) {
        for(Record rec : recordList.getList()) {
            List<String> row = new ArrayList<>();
            row.add(rec.getDate().toString());

            if(columns[0]) {
                row.add(rec.getTitle());
            }

            if(columns[1]) {
                row.add(rec.getLastUpdate().toString());
            }

            if(columns[2]) {
                row.add(rec.getDescription());
            }

            tableModel.addRow(row.toArray());
        }
    }
    private void clearTableModel() {
        DefaultTableModel tableModel = diaryWindow.tableModel;
        int rowsCount = tableModel.getRowCount();

        if(rowsCount <= 0) {
            return;
        }

        for(int i = 0; i < rowsCount; i++) {
            tableModel.removeRow(0);
        }

        tableModel.setRowCount(0);

        tableModel.fireTableDataChanged();
    }
    public DefaultTableModel createModel(boolean[] columns) {
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

        DefaultTableModel tableModel = new DefaultTableModel(null, columnsString.toArray(new String[0])) {
            @Override
            public boolean isCellEditable(int row, int column) { //using for disable editable rows
                return false;
            }
        };

        diaryWindow.setTableModel(tableModel);

        return tableModel;
    }
}
