package com.home.Diary.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.home.Diary.model.Record;
import com.home.Diary.viewmodel.Diary;

public class DeleteButtonListener implements ActionListener {

	Diary diary;
	Record record;
		
	public DeleteButtonListener(Diary diary) {
		super();
		this.diary = diary;
	}
	
	public void setCurrentRecord(Record record) {
		this.record = record;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		diary.deleteRecord(record);
	}

}
