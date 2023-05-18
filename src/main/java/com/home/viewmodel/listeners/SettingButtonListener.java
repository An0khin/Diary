package com.home.viewmodel.listeners;

import com.home.viewmodel.Diary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingButtonListener implements ActionListener {
    private final Diary diary;

    public SettingButtonListener(Diary diary) {
        this.diary = diary;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] message = setupMessage();

        openDialog(message);
    }

    private Object[] setupMessage() {
        JButton resetDataButton = setupResetButton();

        return new Object[] {
                resetDataButton
        };
    }

    private JButton setupResetButton() {
        JButton resetDataButton = new JButton("RESET ALL DATA");

        resetDataButton.addActionListener((ev) -> {
            diary.resetAllData();
        });

        return resetDataButton;
    }

    private void openDialog(Object[] message) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = pane.createDialog("SETTINGS");

        pane.remove(pane.getComponents().length - 1);
        dialog.setVisible(true);
    }
}