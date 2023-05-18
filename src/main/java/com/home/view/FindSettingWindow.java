package com.home.view;

import com.home.viewmodel.Diary;

import javax.swing.*;

public class FindSettingWindow {
    private final Diary diary;

    public FindSettingWindow(Diary diary) {
        this.diary = diary;

        show();
    }

    public void show() {
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

        if(option == JOptionPane.OK_OPTION) {
            diary.updateRecordsByFind(searchFieldDate.getText(), searchFieldTitle.getText(), searchFieldDescription.getText());
        }
    }
}
