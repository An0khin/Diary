package com.home.Diary.viewmodel;

import java.io.File;
import java.text.SimpleDateFormat;
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
	
	DiarySettings diarySettings;
	boolean useMySql;
	
	public Diary() {
		diarySettings = new DiarySettings();
		useMySql = diarySettings.getUseMySql();
		
		setXML();
		if(useMySql)
			setMySql();
		
	}
	
	public void setMySql() {
		jdbcManager = new JDBCManager("root", "1234", "jdbc:mysql://localhost:3306/diary_base");
	}
	
	public void setXML() {
		File filePath = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Diary");
		filePath.mkdir();
		try {
			Thread.sleep(1);
		} catch(Exception e) {e.printStackTrace();}
		
		filePath = new File(filePath.getPath() + File.separator + "data.xml");
		
		xmlManager = new XMLManager(filePath);
		
		if(!filePath.exists()) {
			xmlManager.createXML("DataBase");
			xmlManager.buildElement("Records");
		}
	}
	
	public void change() {	
		setChanged();
		notifyObservers();
	}
	
	public void setModel(RecordList records) {
		this.records = records;
		updateListFromXML(); 
		if(useMySql)
			updateMySqlFromList();
		change();
	}
	
	public void updateListFromXML() { //adding records from XML to List
		List<String[]> recordsList = xmlManager.getListOf("Records", "Record", Record.getNull());
		
		for(String[] array : recordsList) {
			Record rec = new Record(stringToDate(array[0]), array[1], stringToDate(array[2]), array[3], array[4]);
			records.addRecord(rec);
		}
	}
	
	public void updateMySqlFromList() {
		jdbcManager.clearTable("records");
		
		for(Record rec : records.getList()) {
			jdbcManager.insertRowToTable(rec.getFields(), "records");
		}
	}
	
	private Date stringToDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		try {
			return new Date(format.parse(dateString).getTime());
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public List<Record> getList() {
		return records.getList();
	}
	
	public void addRecord(Record rec) {
		records.addRecord(rec);
		xmlManager.addNode(rec.clone(), "Record", "Records");
		if(useMySql)
			jdbcManager.insertRowToTable(rec.getFields(), "records");
		change();
	}
	
	public void editRecord(Record rec, Date date, String title, Date lastUpd, String descr, String content) {
		Record newRecord = new Record(date, title, lastUpd, descr, content);
		xmlManager.editNode(rec.clone(), newRecord.clone(), "Record", "Records");
		if(useMySql)
			jdbcManager.editRowFromTable(rec.clone().getFields(), newRecord.clone().getFields(), "records");
		
		rec.setDate(date);
		rec.setTitle(title);
		rec.setLastUpdate(lastUpd);
		rec.setDescription(descr);
		rec.setContent(content);
	
		change();
	}
	
	public void deleteRecord(Record rec) {
		xmlManager.removeNode(rec.clone(), "Record", "Records");
		if(useMySql)
			jdbcManager.deleteRowFromTable(rec.getFields(), "records");
		records.delete(rec);
		
		change();
	}
	
	public Record getRecordByDate(Date date) {
		return records.getRecordByDate(date);
	}
	
	public Record[] findRecords(String date, String title, String description) {		
		return records.findRecords(date, title, description);
	}
	
	//COMMANDS OF SETTINGS
	public void executeUseMySql() {
		diarySettings.executeUseMySql();
		useMySql = diarySettings.getUseMySql();
		setMySql();
		updateMySqlFromList();
	}
	
	//Save and Load XML
	public void saveXml(File toPath) {
		xmlManager.saveXML(toPath);
	}
	
	public void loadXml(File fromPath) {
		xmlManager.loadXML(fromPath);
		
		updateListFromXML();
		if(useMySql)
			updateMySqlFromList();
		change();
	}
}
