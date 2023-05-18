package com.home.view;

import com.home.model.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OpenDialog {
    private static JButton openButton;
    private static JButton editButton;

    public static void open(Record record, ActionListener openListener, ActionListener editListener) {
        if(record == null) {
            return;
        }

        Object[] message = setupMessage(record, openListener, editListener);
        openDialog(message);
    }

    private static Object[] setupMessage(Record record, ActionListener openListener, ActionListener editListener) {
        JPanel upperPanel = setupUpperPanel(record);
        JScrollPane descriptionPane = setupDescriptionPane(record);
        JPanel lowerPanel = setupLowerPanel(openListener, editListener);

        return new Object[] {
                upperPanel,
                descriptionPane,
                lowerPanel
        };
    }

    private static JPanel setupUpperPanel(Record record) {
        JPanel upperPanel = new JPanel(new BorderLayout());

        JTextField dateField = setupDateField(record.getDate().toString());
        JTextField titleField = setupTitleField(record.getTitle());
        JTextField lastUpdateLbl = setupLastUpdateLabel(record.getLastUpdate().toString());

        upperPanel.add(dateField, BorderLayout.WEST);
        upperPanel.add(titleField, BorderLayout.CENTER);
        upperPanel.add(lastUpdateLbl, BorderLayout.EAST);

        return upperPanel;
    }
    private static JTextField setupDateField(String dateString) {
        JTextField dateField = new JTextField(dateString);
        dateField.setEditable(false);

        return dateField;
    }
    private static JTextField setupTitleField(String title) {
        JTextField titleField = new JTextField(title, 40);
        titleField.setHorizontalAlignment(SwingConstants.CENTER);
        titleField.setEditable(false);

        return titleField;
    }
    private static JTextField setupLastUpdateLabel(String lastUpdateString) {
        JTextField lastUpdateLabel = new JTextField(lastUpdateString);
        lastUpdateLabel.setEditable(false);

        return lastUpdateLabel;
    }

    private static JScrollPane setupDescriptionPane(Record record) {
        JTextArea descriptionArea = setupDescriptionArea(record.getDescription());

        JScrollPane descriptionPane = new JScrollPane(descriptionArea);
        descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return descriptionPane;
    }
    private static JTextArea setupDescriptionArea(String description) {
        JTextArea descriptionArea = new JTextArea(description, 5, 40);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);

        return descriptionArea;
    }

    private static JPanel setupLowerPanel(ActionListener openListener, ActionListener editListener) {
        JPanel lowerPanel = new JPanel(new BorderLayout());

        openButton = new JButton("OPEN");
        openButton.addActionListener(openListener);

        editButton = new JButton("EDIT");
        editButton.addActionListener(editListener);

        lowerPanel.add(openButton, BorderLayout.WEST);
        lowerPanel.add(editButton, BorderLayout.EAST);

        return lowerPanel;
    }

    private static void openDialog(Object[] message) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = pane.createDialog("Open record");

        openButton.addActionListener((e) -> dialog.setVisible(false));
        editButton.addActionListener((e) -> dialog.setVisible(false));

        pane.remove(pane.getComponents().length - 1);
        dialog.setVisible(true);
    }
}
