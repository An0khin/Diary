package com.home.Diary.view.listeners;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			
			int widthFields = gd.getDisplayMode().getWidth() / 15;
			int heightFields = gd.getDisplayMode().getHeight() / 20;
			
			JPanel upperPanel = new JPanel(new BorderLayout());
			
			JTextField dateField = new JTextField(record.getDate().toString());
			dateField.setEditable(false);
				
			JTextField titleField = new JTextField(record.getTitle());
			titleField.setHorizontalAlignment(SwingConstants.CENTER);
			titleField.setEditable(false);
				
			upperPanel.add(dateField, BorderLayout.WEST);
			upperPanel.add(titleField, BorderLayout.CENTER);
			
			JTextArea contentArea = new JTextArea(record.getContent(), heightFields, widthFields);
			contentArea.setLineWrap(true);
			contentArea.setEditable(false);
			JScrollPane contentPane = new JScrollPane(contentArea);
			contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			Object[] message = {
					upperPanel,
					contentPane
			};
			
			JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE);
			JDialog dialog = pane.createDialog("Reading record");
			
			pane.remove(pane.getComponents().length - 1);
			dialog.setVisible(true);
		}
	}

}
