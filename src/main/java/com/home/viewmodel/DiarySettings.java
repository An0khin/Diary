package com.home.viewmodel;

import java.io.*;
import java.util.Properties;

public class DiarySettings {
    private final File fileIni;

    private boolean titleCol;
    private boolean lastUpdCol;
    private boolean descriptionCol;

    public DiarySettings(File fileIni) {
        this.fileIni = fileIni;

        if(fileIni.exists()) {
            load();
        } else {
            createFileIni(fileIni);
        }
    }
    private void load() {
        Properties prop = new Properties();
        try(FileReader fileReader = new FileReader(fileIni)) {
            prop.load(fileReader);

            titleCol = Boolean.parseBoolean(prop.getProperty("titleCol"));
            lastUpdCol = Boolean.parseBoolean(prop.getProperty("lastUpdCol"));
            descriptionCol = Boolean.parseBoolean(prop.getProperty("descriptionCol"));

        } catch(IOException ex) {
            ex.printStackTrace();

            reset();
        }
    }
    private void createFileIni(File fileIni) {
        try {
            fileIni.createNewFile();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        reset();
    }
    public void setColumns(boolean[] columns) {
        //0 - title; 1 - lastUpd; 2 - description
        titleCol = columns[0];
        lastUpdCol = columns[1];
        descriptionCol = columns[2];

        save();
    }
    public boolean[] getColumns() {
        //0 - title; 1 - lastUpd; 2 - description
        boolean[] columns = new boolean[3];

        columns[0] = titleCol;
        columns[1] = lastUpdCol;
        columns[2] = descriptionCol;

        return columns;
    }
    public void reset() {
        titleCol = true;
        lastUpdCol = true;
        descriptionCol = true;

        save();
    }
    private void save() {
        Properties prop = new Properties();
        try(FileWriter fileWriter = new FileWriter(fileIni)) {
            prop.setProperty("titleCol", String.valueOf(titleCol));
            prop.setProperty("lastUpdCol", String.valueOf(lastUpdCol));
            prop.setProperty("descriptionCol", String.valueOf(descriptionCol));

            prop.store(fileWriter, "");

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
