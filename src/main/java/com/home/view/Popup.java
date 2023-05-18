package com.home.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Popup extends JPopupMenu {
    public JButton openButton;
    public JButton editButton;
    public JButton deleteButton;

    public Popup() {
        List<JButton> buttons = setupButtons();

        for(JButton button : buttons) {
            button.addActionListener((e) -> {
                this.setVisible(false);
            });
            add(button);
        }
        this.addMouseListener(new PopupMouseListener());
    }
    private List<JButton> setupButtons() {
        List<JButton> buttons = new ArrayList<>();

        openButton = new JButton("OPEN");
        editButton = new JButton("EDIT");
        deleteButton = new JButton("DELETE");

        buttons.add(openButton);
        buttons.add(editButton);
        buttons.add(deleteButton);

        return buttons;
    }

    private class PopupMouseListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            ((Popup) e.getSource()).setVisible(false);
        }
    }
}
