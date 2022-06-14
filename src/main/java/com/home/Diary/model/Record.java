package com.home.Diary.model;

import java.util.Date;

public class Record {
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
	
}
