package com.home.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

public class Popup extends JPopupMenu {
	public Popup(List<JButton> elements) {
		for(JButton comp : elements) {
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
			// TODO Auto-generated method stub
			super.mouseExited(e);
			((Popup) e.getSource()).setVisible(false);
		}
	}
}
