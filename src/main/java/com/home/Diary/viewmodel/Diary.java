package com.home.Diary.viewmodel;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import com.home.Diary.model.Record;
import com.home.Diary.model.RecordList;

public class Diary extends Observable {
	RecordList records;
	
	public void change() {
		setChanged();
		notifyObservers();
	}
	
	public void setModel(RecordList records) {
		this.records = records;
		change();
	}
	
	public List<Record> getList() {
		return records.getList();
	}
	
	public void addRecord(Record rec) {
		records.addRecord(rec);
		change();
	}
	
	public Record getRecordByDate(Date date) {
		return records.getRecordByDate(date);
	}
}
