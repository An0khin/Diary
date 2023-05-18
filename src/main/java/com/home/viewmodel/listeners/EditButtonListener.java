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

public class EditButtonListener implements ActionListener {
	private final Diary diary;
	private Record record;

	private int heightFields;
	private int widthFields;

	private JSpinner dateSpinner;
	private JTextField titleField;
	private JTextArea descriptionArea;
	private JTextArea contentArea;

	public EditButtonListener(Diary diary) {
		super();
		this.diary = diary;
	}
	public void updateCurrentRecord(Record record) {
		this.record = record;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(record == null) {
			return;
		}

		setFieldsSize();
		showEditWindow();
	}

	private void setFieldsSize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		widthFields = gd.getDisplayMode().getWidth() / 15;
		heightFields = gd.getDisplayMode().getHeight() / 60;
	}

	private void showEditWindow() {
		Object[] message = setupMessage();

		int option = JOptionPane.showConfirmDialog(null, message, "Edit record", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

		if(option == JOptionPane.OK_OPTION) {
			int optionSave = JOptionPane.showConfirmDialog(null, "Do you want to save the changes?", "Save?", JOptionPane.YES_NO_OPTION);

			if(optionSave == JOptionPane.YES_OPTION) {
				confirmEdit();
			}
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
		JPanel upperPanel = new JPanel(new BorderLayout());

		dateSpinner = setupDateSpinner();
		titleField = setupTitleField();
		JLabel lastUpdateLabel = new JLabel(record.getLastUpdate().toString());

		upperPanel.add(dateSpinner, BorderLayout.WEST);
		upperPanel.add(titleField, BorderLayout.CENTER);
		upperPanel.add(lastUpdateLabel, BorderLayout.EAST);

		return upperPanel;
	}
	private JSpinner setupDateSpinner() {
		SpinnerModel modelDateSpinner = setupModelDateSpinner();
		DefaultEditor editor = setupDateSpinnerEditor();

		JSpinner dateSpinner = new JSpinner(modelDateSpinner);
		dateSpinner.setEditor(editor);

		return dateSpinner;
	}
	private SpinnerModel setupModelDateSpinner() {
		SpinnerModel modelCurDate = new SpinnerDateModel();
		modelCurDate.setValue(record.getDate());

		return modelCurDate;
	}
	private DefaultEditor setupDateSpinnerEditor() {
		DefaultEditor editor = new JSpinner.DateEditor(dateSpinner, "EEE MMM dd HH:mm:ss z yyyy");
		editor.getTextField().setEditable(false);

		return editor;
	}
	private JTextField setupTitleField() {
		JTextField titleField = new JTextField(record.getTitle());
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
		JTextArea descriptionArea = new JTextArea(record.getDescription(), heightFields, widthFields);
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
		JTextArea contentArea = new JTextArea(record.getContent(), (int) (heightFields * 1.5), widthFields);
		contentArea.setLineWrap(true);

		return contentArea;
	}

	private void confirmEdit() {
		Date date = (Date) dateSpinner.getValue();
		String title = titleField.getText();
		String description = descriptionArea.getText();
		String content = contentArea.getText();
		Date lastUpdate = Calendar.getInstance().getTime();

		diary.editRecord(record, date, title, lastUpdate, description, content);
	}
}