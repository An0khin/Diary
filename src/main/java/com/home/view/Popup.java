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
        List<JButton> components = new ArrayList<>();

        openButton = new JButton("OPEN");
        editButton = new JButton("EDIT");
        deleteButton = new JButton("DELETE");

        components.add(openButton);
        components.add(editButton);
        components.add(deleteButton);

        for(JButton comp : components) {
            comp.addActionListener((e) -> {
                this.setVisible(false);
            });
            add(comp);
        }
        this.addMouseListener(new PopupMouseListener());
    }
    private class PopupMouseListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            ((Popup) e.getSource()).setVisible(false);
        }
    }
}
