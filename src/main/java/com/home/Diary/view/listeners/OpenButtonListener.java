package com.home.Diary.view.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner.DefaultEditor;

import com.home.Diary.model.Record;
import com.home.Diary.viewmodel.Diary;

public class OpenButtonListener implements ActionListener {
	
	Diary diary;
	Record record;
		
	public OpenButtonListener(Diary diary) {
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
			
			JTextField dateField = new JTextField(record.getDate().toString());
			dateField.setEditable(false);
				
			JTextField titleField = new JTextField(record.getTitle(), 40);
			titleField.setHorizontalAlignment(SwingConstants.CENTER);
			titleField.setEditable(false);
				
			upperPanel.add(dateField, BorderLayout.WEST);
			upperPanel.add(titleField, BorderLayout.CENTER);
			
			JTextArea contentArea = new JTextArea(record.getContent(), 40, 40);
			contentArea.setLineWrap(true);
			contentArea.setEditable(false);
			JScrollPane contentPane = new JScrollPane(contentArea);
			contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			JPanel lowerPanel = new JPanel(new BorderLayout());
						
			Object[] message = {
					upperPanel,
					contentPane
			};
			
			JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE);
			JDialog dialog = pane.createDialog("HOP");
			
			pane.remove(pane.getComponents().length - 1);
			dialog.setVisible(true);
		}
	}

}
