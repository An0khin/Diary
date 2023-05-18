package com.home.viewmodel.listeners;

import com.home.viewmodel.Diary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingButtonListener implements ActionListener {

	private Diary diary;
	
	public SettingButtonListener(Diary diary) {
		this.diary = diary;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			
		JPanel mySqlSetting = new JPanel(new BorderLayout());
		JLabel mySqlText = new JLabel("Use MySQL");
		JButton mySqlButton = new JButton("OFF");
		
		JButton resetDataButton = new JButton("RESET ALL DATA");
		
		mySqlButton.addActionListener((ev) -> {
			diary.executeUseMySql();
			if(mySqlButton.getText().equals("OFF"))
				mySqlButton.setText("ON");
			else
				mySqlButton.setText("OFF");
		});
		
		resetDataButton.addActionListener((ev) -> {
			diary.resetAllData();
		});
		
		mySqlSetting.add(mySqlText, BorderLayout.WEST);
		mySqlSetting.add(mySqlButton, BorderLayout.EAST);
		
		Object[] message = {
				mySqlSetting,
				resetDataButton
		};
		
		JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE);
		JDialog dialog = pane.createDialog("SETTINGS");
		
		pane.remove(pane.getComponents().length - 1);
		dialog.setVisible(true);	
	}
}