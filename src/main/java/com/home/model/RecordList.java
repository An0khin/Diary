package com.home.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordList {
    private final List<Record> records;

    public RecordList() {
        records = new ArrayList<>();
    }

    public List<Record> getList() {
        return records;
    }

    public void addRecord(Record rec) {
        records.add(rec);
    }

    public void delete(Record rec) {
        records.remove(rec);
    }

    public Record getRecordByDate(Date date) {
        return records.stream()
                .filter(record -> record.getDate().equals(date))
                .findFirst()
                .orElse(null);
    }

    public Record[] findRecords(String date, String title, String description) {
        return records.stream()
                .filter(record -> record.getDate().toString().contains(date) &&
                        record.getTitle().contains(title) &&
                        record.getDescription().contains(description))
                .toArray(Record[]::new);
    }

    public void clear() {
        records.clear();
    }
}
