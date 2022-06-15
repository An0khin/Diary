package com.home.Diary.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordList {
	private List<Record> records;
	
	public RecordList() {
		records = new ArrayList<>();
	}
	
	public List<Record> getList() {
		return records;
	}
	
	public void addRecord(Record rec) {
		records.add(rec);
	}
	
	public Record getRecordByDate(Date date) {
		Record record;
		
		for(int i = 0; i < records.size(); i++) {
			
			record = records.get(i);
			
			if(record.getDate().equals(date)) {
				return record;
			}
			
		}
		
		return null;
	}
}
