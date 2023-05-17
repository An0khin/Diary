package com.home.viewmodel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class DiarySettings {
	private boolean useMySql;
	
	private File fileIni;
	
	private boolean titleCol;
	private boolean lastUpdCol;
	private boolean descriptionCol;
	
	public DiarySettings(File fileIni) {
		this.fileIni = fileIni;
		
		if(fileIni.exists()) {
			
			load();
			
		} else {
						
			try {
				fileIni.createNewFile();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			reset();
			
		}
	}
	
	public void executeUseMySql() {
		useMySql = !useMySql;
		save();
	}
	
	public  void setColumns(boolean[] columns) {
		//0 - title; 1 - lastUpd; 2 - description
		
		titleCol = columns[0];
		lastUpdCol = columns[1];
		descriptionCol = columns[2];
		
		save();
	}
	
	public boolean getUseMySql() {
		return useMySql;
	}
	
	public boolean[] getColumns() {
		//0 - title; 1 - lastUpd; 2 - description
		boolean[] columns = new boolean[3];
		
		columns[0] = titleCol;
		columns[1] = lastUpdCol;
		columns[2] = descriptionCol;
		
		return columns;
	}
	
	//Load and save settings
	public void load() {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(fileIni));
			
			useMySql = new Boolean(prop.getProperty("useMySql"));
			titleCol = new Boolean(prop.getProperty("titleCol"));
			lastUpdCol = new Boolean(prop.getProperty("lastUpdCol"));
			descriptionCol = new Boolean(prop.getProperty("descriptionCol"));
			
		} catch(Exception ex) {
			ex.printStackTrace();
			
			reset();
		}
	}
	
	public void save() {
		Properties prop = new Properties();
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileIni))) {			
			prop.setProperty("useMySql", String.valueOf(useMySql));
			prop.setProperty("titleCol", String.valueOf(titleCol));
			prop.setProperty("lastUpdCol", String.valueOf(lastUpdCol));
			prop.setProperty("descriptionCol", String.valueOf(descriptionCol));
			
			prop.store(new FileWriter(fileIni), "");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//Reset
	public void reset() {
		useMySql = false;
		titleCol = true;
		lastUpdCol = true;
		descriptionCol = true;
		
		save();
	}
 }
