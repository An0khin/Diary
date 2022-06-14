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
}
