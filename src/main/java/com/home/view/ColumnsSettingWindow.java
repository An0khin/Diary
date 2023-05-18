package com.home.view;

import com.home.viewmodel.Diary;

import javax.swing.*;

public class ColumnsSettingWindow {
    private Diary diary;
    public ColumnsSettingWindow(Diary diary) {
        this.diary = diary;

        show();
    }

    private void show() {
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
        }
    }
}
