package com.home.viewmodel.listeners;

import com.home.viewmodel.Diary;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadButtonListener implements ActionListener {
	private final Diary diary;
	
	public LoadButtonListener(Diary diary) {
		super();
		this.diary = diary;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Extensible Markup Language", "xml");
		fc.setFileFilter(filter);
		
		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			diary.loadXml(fc.getSelectedFile());
		}
	}

}
