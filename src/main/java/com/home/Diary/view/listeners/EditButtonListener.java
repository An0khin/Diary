package com.home.Diary.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.home.Diary.model.Record;
import com.home.Diary.view.DiaryWindow;

public class EditButtonListener implements ActionListener {

	DiaryWindow diaryWindow;
	Record record;
		
	public EditButtonListener(DiaryWindow diaryWindow) {
		super();
		this.diaryWindow = diaryWindow;
	}
	
	public void setCurrentRecord(Record record) {
		this.record = record;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(record != null) {
			diaryWindow.openRecord(record);
		}
	}
}
