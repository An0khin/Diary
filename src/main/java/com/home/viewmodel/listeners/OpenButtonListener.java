package com.home.viewmodel.listeners;

import com.home.model.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenButtonListener implements ActionListener {
    private Record record;

    private int widthFields;
    private int heightFields;

    public OpenButtonListener() {
        super();

        setupSizeFields();
    }

    public void updateCurrentRecord(Record record) {
        this.record = record;
    }

    private void setupSizeFields() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        widthFields = gd.getDisplayMode().getWidth() / 15;
        heightFields = gd.getDisplayMode().getHeight() / 20;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(record == null) {
            return;
        }

        Object[] message = setupMessage();
        openDialog(message);
    }

    private Object[] setupMessage() {
        JPanel upperPanel = setupUpperPanel();
        JScrollPane contentPane = setupContentPane();

        return new Object[] {
                upperPanel,
                contentPane
        };
    }

    private JPanel setupUpperPanel() {
        JPanel upperPanel = new JPanel(new BorderLayout());

        JTextField dateField = setupDateField();
        JTextField titleField = setupTitleField();

        upperPanel.add(dateField, BorderLayout.WEST);
        upperPanel.add(titleField, BorderLayout.CENTER);

        return upperPanel;
    }

    private JTextField setupDateField() {
        JTextField dateField = new JTextField(record.getDate().toString());
        dateField.setEditable(false);

        return dateField;
    }

    private JTextField setupTitleField() {
        JTextField titleField = new JTextField(record.getTitle());
        titleField.setHorizontalAlignment(SwingConstants.CENTER);
        titleField.setEditable(false);

        return titleField;
    }

    private JScrollPane setupContentPane() {
        JTextArea contentArea = setupContentArea();

        JScrollPane contentPane = new JScrollPane(contentArea);
        contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return contentPane;
    }

    private JTextArea setupContentArea() {
        JTextArea contentArea = new JTextArea(record.getContent(), heightFields, widthFields);
        contentArea.setLineWrap(true);
        contentArea.setEditable(false);

        return contentArea;
    }

    private void openDialog(Object[] message) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = pane.createDialog("Reading record");

        pane.remove(pane.getComponents().length - 1);
        dialog.setVisible(true);
    }
}
