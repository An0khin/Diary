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
        panel.removeAll();
        panel.updateUI();

        Font font = new Font("a", Font.BOLD, 12);

        JTextField infoComp = new JTextField("INFO");
        infoComp.setHorizontalAlignment(JTextField.CENTER);
        infoComp.setFont(new Font("a", Font.BOLD, 18));

        JTextField titleComp = new JTextField("Title");
        titleComp.setHorizontalAlignment(JTextField.CENTER);
        titleComp.setFont(font);

        JTextField dateComp = new JTextField("Date");
        dateComp.setHorizontalAlignment(JTextField.CENTER);
        dateComp.setFont(font);

        JTextField lastUpdateComp = new JTextField("Last Update");
        lastUpdateComp.setHorizontalAlignment(JTextField.CENTER);
        lastUpdateComp.setFont(font);


        JTextComponent[] comps = {
                infoComp,
                titleComp,
                new JTextField(record.getTitle()),
                dateComp,
                new JTextField(record.getDate().toString()),
                lastUpdateComp,
                new JTextField(record.getLastUpdate().toString()),
        };

        for(JTextComponent comp : comps) {
            comp.setEditable(false);
            panel.add(comp);
        }
    }
}
