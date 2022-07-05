package com.home.Diary.view.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner.DefaultEditor;

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
		
		JPanel upperPanel = new JPanel(new BorderLayout());
		
		SpinnerModel modelCurDate = new SpinnerDateModel();
		modelCurDate.setValue(date);
		
	    JSpinner spinnerForDate = new JSpinner(modelCurDate);
	    JComponent editor = new JSpinner.DateEditor(spinnerForDate, "EEE MMM dd HH:mm:ss z yyyy");
	    ((DefaultEditor) editor).getTextField().setEditable(false); //disable editing field of the date by own hands
	    spinnerForDate.setEditor(editor);
	    
		JTextField titleField = new JTextField();
		titleField.setHorizontalAlignment(JTextField.CENTER);
		
		upperPanel.add(spinnerForDate, BorderLayout.WEST);
		upperPanel.add(titleField, BorderLayout.CENTER);
		
		
		JTextArea descriptionField = new JTextArea(5, 20);
		descriptionField.setLineWrap(true);
		JScrollPane descriptionPane = new JScrollPane(descriptionField);
		descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JTextArea contentField = new JTextArea(5, 20);
		contentField.setLineWrap(true);
		JScrollPane contentPane = new JScrollPane(contentField);
		contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
		Object[] message = {
				upperPanel,
				"Description", descriptionPane,
				"Content", contentPane
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "New record", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(option == JOptionPane.OK_OPTION) {
			date = (Date) spinnerForDate.getValue();
			String title = titleField.getText();
			String description = descriptionField.getText();
			String content = contentField.getText();
			
//			System.out.println("HOP");
			
			diary.addRecord(new Record(date, title, date, description, content));
		}
		
	}
}
