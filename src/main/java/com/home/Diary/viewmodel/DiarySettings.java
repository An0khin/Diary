package com.home.Diary.viewmodel;

public class DiarySettings {
	private boolean useMySql;
	
	public DiarySettings() {
		useMySql = false;
	}
	
	public void executeUseMySql() {
		useMySql = !useMySql;
	}
	
	public boolean getUseMySql() {
		return useMySql;
	}
}
