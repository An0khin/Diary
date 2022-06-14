package com.home.Diary.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.home.Diary.model.Record;
import com.home.Diary.viewmodel.Diary;

public class NewButtonListener implements ActionListener {

	Diary diary;
	
	public NewButtonListener(Diary diary) {
		this.diary = diary;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Date date = Calendar.getInstance().getTime();
		JTextField titleField = new JTextField(40);
		
		JTextArea descriptionField = new JTextArea(5, 10);
		descriptionField.setLineWrap(true);
		JScrollPane descriptionPane = new JScrollPane(descriptionField);
		descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JTextArea contentField = new JTextArea(40, 40);
		contentField.setLineWrap(true);
		JScrollPane contentPane = new JScrollPane(contentField);
		contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
		Object[] message = {
				"Date", date,
				"Title", titleField,
				"Description", descriptionPane,
				"Content", contentPane
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "New record", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(option == JOptionPane.OK_OPTION) {
			String title = titleField.getText();
			String description = descriptionField.getText();
			String content = contentField.getText();
			
			System.out.println("HOP");
			
			diary.addRecord(new Record(date, title, date, description, content));
		}
		
	}
}
