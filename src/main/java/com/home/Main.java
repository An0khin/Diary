package com.home;

import com.home.model.RecordList;
import com.home.view.DiaryWindow;
import com.home.viewmodel.Diary;

public class Main {
	public static void main(String[] args) {
		Diary diary = new Diary();
		diary.setModel(new RecordList());
		
		DiaryWindow window = new DiaryWindow(diary);
	}
}
