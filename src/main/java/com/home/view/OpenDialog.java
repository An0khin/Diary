package com.home.view;

import com.home.model.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OpenDialog {
    public JButton openButton;
    public JButton editButton;

    public OpenDialog(Record record, ActionListener openListener, ActionListener editListener) {
        if(record != null) {
            JPanel upperPanel = new JPanel(new BorderLayout());

            JTextField dateField = new JTextField(record.getDate().toString());
            dateField.setEditable(false);

            JTextField titleField = new JTextField(record.getTitle(), 40);
            titleField.setHorizontalAlignment(SwingConstants.CENTER);
            titleField.setEditable(false);

            JTextField lastUpdateLbl = new JTextField(record.getLastUpdate().toString());
            lastUpdateLbl.setEditable(false);

            upperPanel.add(dateField, BorderLayout.WEST);
            upperPanel.add(titleField, BorderLayout.CENTER);
            upperPanel.add(lastUpdateLbl, BorderLayout.EAST);

            JTextArea descriptionArea = new JTextArea(record.getDescription(), 5, 40);
            descriptionArea.setLineWrap(true);
            descriptionArea.setEditable(false);
            JScrollPane descriptionPane = new JScrollPane(descriptionArea);
            descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            JPanel lowerPanel = new JPanel(new BorderLayout());

            openButton = new JButton("OPEN");
            openButton.addActionListener(openListener);
            editButton = new JButton("EDIT");
            editButton.addActionListener(editListener);

            lowerPanel.add(openButton, BorderLayout.WEST);
            lowerPanel.add(editButton, BorderLayout.EAST);

            Object[] message = {
                    upperPanel,
                    descriptionPane,
                    lowerPanel
            };

            JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
            JDialog dialog = pane.createDialog("HOP");

            openButton.addActionListener((e) -> dialog.setVisible(false));
            editButton.addActionListener((e) -> dialog.setVisible(false));

            pane.remove(pane.getComponents().length - 1);
            dialog.setVisible(true);
        }
    }
}
