package com.home.view;

import com.home.model.Record;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class SideRecord {
    JPanel panel;
    public SideRecord(JPanel panel) {
        this.panel = panel;
    }

    public void viewRecord(Record record) {
        clearPanel();

        JTextComponent[] jTextComponents = setupTextComponents(record);

        for(JTextComponent jTextComponent : jTextComponents) {
            jTextComponent.setEditable(false);
            panel.add(jTextComponent);
        }
    }
    private void clearPanel() {
        panel.removeAll();
        panel.updateUI();
    }
    private JTextComponent[] setupTextComponents(Record record) {
        Font font = new Font("a", Font.BOLD, 12);
        Font bigFont = new Font("a", Font.BOLD, 18);

        JTextField infoComp = setupTextField("INFO", bigFont);
        JTextField titleComp = setupTextField("Title", font);
        JTextField dateComp = setupTextField("Date", font);
        JTextField lastUpdateComp = setupTextField("Last Update", font);

        return new JTextComponent[] {
                infoComp,
                titleComp,
                new JTextField(record.getTitle()),
                dateComp,
                new JTextField(record.getDate().toString()),
                lastUpdateComp,
                new JTextField(record.getLastUpdate().toString()),
        };
    }
    private JTextField setupTextField(String text, Font font) {
        JTextField jTextField = new JTextField(text);
        jTextField.setHorizontalAlignment(JTextField.CENTER);
        jTextField.setFont(font);

        return jTextField;
    }

}
