package com.home.Diary;

import com.home.Diary.model.RecordList;
import com.home.Diary.view.DiaryWindow;
import com.home.Diary.viewmodel.Diary;

public class Main {
	public static void main(String[] args) {
		Diary diary = new Diary();
		diary.setModel(new RecordList());
		
		DiaryWindow window = new DiaryWindow(diary);
	}
}
