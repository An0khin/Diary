package com.home.Diary.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.com.home.NodeSync;

public class Record implements NodeSync{
	private Date date;
	private String title;
	private Date lastUpdate;
	private String description;
	private String content;
	
	public Record(Date date, String title, Date lastUpdate, String description, String content) {
		super();
		this.date = date;
		this.title = title;
		this.lastUpdate = lastUpdate;
		this.description = description;
		this.content = content;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Record clone() {
		return new Record(date, title, lastUpdate, description, content);
	}
	
	public List<String> getFields() {
		List<String> fields = new ArrayList<String>();
		
		fields.add(date.toString());
		fields.add(title);
		fields.add(lastUpdate.toString());
		fields.add(description);
		fields.add(content);
		
		return fields;
	}

	@Override
	public List<String> getIds() {
		List<String> ids = new ArrayList<>();
		
		ids.add("date");
		ids.add(date.toString());
		
		return ids;
	}

	@Override
	public HashMap<String, String> getMapElements() {
		HashMap<String, String> elements = new LinkedHashMap<>();
				
		elements.put("date", date.toString());
		elements.put("title", title);
		elements.put("lastUpdate", lastUpdate.toString());
		elements.put("description", description);
		elements.put("content", content);
		
		return elements;
	}
	
}
