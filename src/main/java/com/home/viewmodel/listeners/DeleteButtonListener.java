package com.home.viewmodel.listeners;

import com.home.model.Record;
import com.home.viewmodel.Diary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonListener implements ActionListener {
    private final Diary diary;
    private Record record;

    public DeleteButtonListener(Diary diary) {
        super();
        this.diary = diary;
    }

    public void updateCurrentRecord(Record record) {
        this.record = record;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        diary.deleteRecord(record);
    }

}
