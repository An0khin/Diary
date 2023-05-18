package com.home.model;

import com.home.Nodeable;

import java.util.*;

public class Record implements Nodeable {
    private Date date;
    private String title;
    private Date lastUpdate;
    private String description;
    private String content;

    public Record(Date date, String title, Date lastUpdate, String description, String content) {
        this.date = date;
        this.title = title;
        this.lastUpdate = lastUpdate;
        this.description = description;
        this.content = content;
    }

    public Record clone() {
        return new Record(date, title, lastUpdate, description, content);
    }

    public static Record getNull() {
        return new Record(null, null, null, null, null);
    }

    @Override
    public Map<String, String> getValues() {
        Map<String, String> elements = new LinkedHashMap<>();

        elements.put("title", title);
        elements.put("lastUpdate", lastUpdate == null ? "" : lastUpdate.toString());
        elements.put("description", description);
        elements.put("content", content);

        return elements;
    }

    @Override
    public String[] getId() {
        if(date == null) {
            return new String[] {"date"};
        }

        return new String[] {"date", date.toString()};
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
