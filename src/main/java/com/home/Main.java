package com.home;

import com.home.view.DiaryWindow;
import com.home.viewmodel.Diary;

public class Main {
    public static void main(String[] args) {
        DiaryWindow diaryWindow = new DiaryWindow();

        Diary diary = new Diary(diaryWindow);
    }
}
