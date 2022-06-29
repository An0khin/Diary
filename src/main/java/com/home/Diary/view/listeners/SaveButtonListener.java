package com.home.Diary.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.home.Diary.viewmodel.Diary;

public class SaveButtonListener implements ActionListener {
	
	Diary diary;
	
	public SaveButtonListener(Diary diary) {
		super();
		this.diary = diary;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser fc = new JFileChooser();
		
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Extensible Markup Language", "xml");
		fc.setFileFilter(filter);
		
		if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			diary.saveXml(fc.getSelectedFile());
		}
	}

}
