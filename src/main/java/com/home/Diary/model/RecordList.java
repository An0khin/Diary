package com.home.Diary.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordList {
	private List<Record> records = new ArrayList<>();
	
	public RecordList() {
		Record rec1 = new Record();
		rec1.setDate(new Date(1000000000));
		rec1.setTitle("First");
		records.add(rec1);
		
		Record rec2 = new Record();
		rec2.setDate(new Date(1000050000));
		rec2.setTitle("Second");
		records.add(rec2);
	}
	
	public List<Record> getList() {
		return records;
	}
}
