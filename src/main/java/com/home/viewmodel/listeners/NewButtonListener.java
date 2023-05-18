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

public class NewButtonListener implements ActionListener {
	private final Diary diary;

	int widthFields;
	int heightFields;

	private JSpinner dateSpinner;
	private JTextField titleField;
	private JTextArea descriptionArea;
	private JTextArea contentArea;
	
	public NewButtonListener(Diary diary) {
		this.diary = diary;

		setupSizeFields();
	}

	private void setupSizeFields() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		widthFields = gd.getDisplayMode().getWidth() / 15;
		heightFields = gd.getDisplayMode().getHeight() / 60;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] message = setupMessage();

		int option = JOptionPane.showConfirmDialog(null, message, "New record", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(option == JOptionPane.OK_OPTION) {
			confirmCreate();
		}
	}

	private Object[] setupMessage() {
		JPanel upperPanel = setupUpperPanel();
		JScrollPane descriptionPane = setupDescriptionPane();
		JScrollPane contentPane = setupContentPane();

		return new Object[] {
				upperPanel,
				"Description", descriptionPane,
				"Content", contentPane
		};
	}

	private JPanel setupUpperPanel() {
		dateSpinner = setupDateSpinner();
		titleField = setupTitleField();

		JPanel upperPanel = new JPanel(new BorderLayout());

		upperPanel.add(dateSpinner, BorderLayout.WEST);
		upperPanel.add(titleField, BorderLayout.CENTER);

		return upperPanel;
	}
	private JSpinner setupDateSpinner() {
		SpinnerModel modelDateSpinner = setupModelDateSpinner();

		dateSpinner = new JSpinner(modelDateSpinner);

		DefaultEditor editor = setupDateSpinnerEditor();
		dateSpinner.setEditor(editor);

		return dateSpinner;
	}
	private SpinnerModel setupModelDateSpinner() {
		Date date = Calendar.getInstance().getTime();

		SpinnerModel modelCurDate = new SpinnerDateModel();
		modelCurDate.setValue(date);

		return modelCurDate;
	}
	private DefaultEditor setupDateSpinnerEditor() {
		DefaultEditor editor = new JSpinner.DateEditor(dateSpinner, "EEE MMM dd HH:mm:ss z yyyy");
		editor.getTextField().setEditable(false);

		return editor;
	}
	private JTextField setupTitleField() {
		JTextField titleField = new JTextField();
		titleField.setHorizontalAlignment(JTextField.CENTER);

		return titleField;
	}

	private JScrollPane setupDescriptionPane() {
		descriptionArea = setupDescriptionArea();

		JScrollPane descriptionPane = new JScrollPane(descriptionArea);
		descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		return descriptionPane;
	}
	private JTextArea setupDescriptionArea() {
		JTextArea descriptionArea = new JTextArea(heightFields, widthFields);
		descriptionArea.setLineWrap(true);

		return descriptionArea;
	}

	private JScrollPane setupContentPane() {
		contentArea = setupContentArea();

		JScrollPane contentPane = new JScrollPane(contentArea);
		contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		return contentPane;
	}
	private JTextArea setupContentArea() {
		JTextArea contentArea = new JTextArea((int) (heightFields * 1.5), widthFields);
		contentArea.setLineWrap(true);

		return contentArea;
	}

	private void confirmCreate() {
		Date date = (Date) dateSpinner.getValue();
		String title = titleField.getText();
		String description = descriptionArea.getText();
		String content = contentArea.getText();

		diary.addRecord(new Record(date, title, date, description, content));
	}
}
