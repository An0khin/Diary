package com.home.viewmodel.listeners;

import com.home.model.Record;
import com.home.viewmodel.Diary;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class EditButtonListener implements ActionListener, ListenerWithRecord {

	Diary diary;
	Record record;
		
	public EditButtonListener(Diary diary) {
		super();
		this.diary = diary;
	}

	@Override
	public void updateCurrentRecord(Record record) {
		this.record = record;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(record != null) {
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			
			int widthFields = gd.getDisplayMode().getWidth() / 15;
			int heightFields = gd.getDisplayMode().getHeight() / 60;
			
			JPanel upperPanel = new JPanel(new BorderLayout());
				
			SpinnerModel modelCurDate = new SpinnerDateModel();
			modelCurDate.setValue(record.getDate());
				
			JSpinner spinnerForDate = new JSpinner(modelCurDate);
			DefaultEditor editor = new JSpinner.DateEditor(spinnerForDate, "EEE MMM dd HH:mm:ss z yyyy");
			editor.getTextField().setEditable(false); //disable editing field of the date by own hands
			spinnerForDate.setEditor(editor);
			    
				
//				JTextField dateLbl = new JTextField(record.getDate().toString());
				
			JTextField titleField = new JTextField(record.getTitle());
			titleField.setHorizontalAlignment(JTextField.CENTER);
				
			JLabel lastUpdateLbl = new JLabel(record.getLastUpdate().toString());
				
			upperPanel.add(spinnerForDate, BorderLayout.WEST);
			upperPanel.add(titleField, BorderLayout.CENTER);
			upperPanel.add(lastUpdateLbl, BorderLayout.EAST);
			
			JTextArea descriptionArea = new JTextArea(record.getDescription(), heightFields, widthFields);
			descriptionArea.setLineWrap(true);
			JScrollPane descriptionPane = new JScrollPane(descriptionArea);
			descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
			JTextArea contentArea = new JTextArea(record.getContent(), (int) (heightFields * 1.5), widthFields);
			contentArea.setLineWrap(true);
			JScrollPane contentPane = new JScrollPane(contentArea);
			contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
			Object[] message = {
					upperPanel,
					"Description", descriptionPane,
					"Content", contentPane
			};
				
			int option = JOptionPane.showConfirmDialog(null, message, "Edit record", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
				
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