package com.home.Diary.view.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JLabel;
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
import com.home.Diary.view.DiaryWindow;
import com.home.Diary.viewmodel.Diary;

public class EditButtonListener implements ActionListener {

	Diary diary;
	Record record;
		
	public EditButtonListener(Diary diary) {
		super();
		this.diary = diary;
	}
	
	public void setCurrentRecord(Record record) {
		this.record = record;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(record != null) {
			JPanel upperPanel = new JPanel(new BorderLayout());
				
			SpinnerModel modelCurDate = new SpinnerDateModel();
			modelCurDate.setValue(record.getDate());
				
			JSpinner spinnerForDate = new JSpinner(modelCurDate);
			JComponent editor = new JSpinner.DateEditor(spinnerForDate, "EEE MMM dd HH:mm:ss z yyyy");
			((DefaultEditor) editor).getTextField().setEditable(false); //disable editing field of the date by own hands
			spinnerForDate.setEditor(editor);
			    
				
//				JTextField dateLbl = new JTextField(record.getDate().toString());
				
			JTextField titleField = new JTextField(record.getTitle(), 40);
			titleField.setHorizontalAlignment(JTextField.CENTER);
				
			JLabel lastUpdateLbl = new JLabel(record.getLastUpdate().toString());
				
			upperPanel.add(spinnerForDate, BorderLayout.WEST);
			upperPanel.add(titleField, BorderLayout.CENTER);
			upperPanel.add(lastUpdateLbl, BorderLayout.EAST);
				
				
			JTextArea descriptionArea = new JTextArea(record.getDescription(), 5, 10);
			descriptionArea.setLineWrap(true);
			JScrollPane descriptionPane = new JScrollPane(descriptionArea);
			descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
			JTextArea contentArea = new JTextArea(record.getContent(), 40, 40);
			contentArea.setLineWrap(true);
			JScrollPane contentPane = new JScrollPane(contentArea);
			contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
			Object[] message = {
					upperPanel,
					"Description", descriptionPane,
					"Content", contentPane
			};
				
			int option = JOptionPane.showConfirmDialog(null, message, "New record", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE);
				
			if(option == JOptionPane.OK_OPTION) {
				int optionSave = JOptionPane.showConfirmDialog(null, "Do you want to save the changes?", "Save?", JOptionPane.YES_NO_OPTION);
					
				if(optionSave == JOptionPane.YES_OPTION) {
					Date date = (Date) spinnerForDate.getValue();
					String title = titleField.getText();
					String description = descriptionArea.getText();
					String content = contentArea.getText();
					Date lastUpdate = Calendar.getInstance().getTime();
						
					diary.editRecord(record, date, title, lastUpdate, description, content);
				}
			}
		}
	}
}