package com.home.Diary.viewmodel;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.com.home.XMLManager;

import com.home.Diary.model.Record;
import com.home.Diary.model.RecordList;
import com.home.JDBCManager.JDBCManager;

public class Diary extends Observable {
	RecordList records;
	XMLManager xmlManager;
	JDBCManager jdbcManager;
	
	public Diary() {
		File filePath = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Diary");
		filePath.mkdir();
		try {
			Thread.sleep(1);
		} catch(Exception e) {e.printStackTrace();}
		
		filePath = new File(filePath.getPath() + File.separator + "data.xml");
		
		xmlManager = new XMLManager(filePath);
		xmlManager.buildElement("Records");
		
		jdbcManager = new JDBCManager("root", "1234", "jdbc:mysql://localhost:3306/diary_base");
	}
	
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
		xmlManager.addNode(rec.clone(), "Record", "Records");
		jdbcManager.insertRowToTable(rec.getFields(), "records");
		change();
	}
	
	public void editRecord(Record rec, Date date, String title, Date lastUpd, String descr, String content) {
		xmlManager.editNode(rec.clone(), new Record(date, title, lastUpd, descr, content), "Record", "Records");
		
		rec.setDate(date);
		rec.setTitle(title);
		rec.setLastUpdate(lastUpd);
		rec.setDescription(descr);
		rec.setContent(content);
				
		change();
	}
	
	public void deleteRecord(Record rec) {
		xmlManager.removeNode(rec.clone(), "Record", "Records");
		records.delete(rec);
		
		change();
	}
	
	public Record getRecordByDate(Date date) {
		return records.getRecordByDate(date);
	}
}
