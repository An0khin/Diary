package com.home.viewmodel.listeners;

import com.home.viewmodel.Diary;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class TableSelectionListener extends MouseAdapter {
    private final Diary diary;
    private final JPopupMenu popupFrame;

    public TableSelectionListener(Diary diary, JPopupMenu popupFrame) {
        this.diary = diary;
        this.popupFrame = popupFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        popupFrame.setVisible(false);

        JTable table = (JTable) (e.getSource());
        Point p = e.getPoint();
        int row = table.rowAtPoint(p);
        table.setRowSelectionInterval(row, row);

        int index = table.getSelectedRow();

        if(index != -1) {
            TableModel tableModel = table.getModel();
            Date currentDate = Diary.stringToDate((String) tableModel.getValueAt(index, 0));
            diary.updateCurrentRecordByDate(currentDate);
        }

        if(e.getButton() == MouseEvent.BUTTON3) {
            showWindowByRightClick(e);
        } else if(e.getButton() == MouseEvent.BUTTON1) {
            if(e.getClickCount() >= 2) {
                diary.openChoiceCurrentRecord();
            }
        }
    }

    private void showWindowByRightClick(MouseEvent event) {
        Point mousePos = event.getLocationOnScreen();
        mousePos.move((int) mousePos.getX() - 5, (int) mousePos.getY() - 5);
        popupFrame.setLocation(mousePos);
        popupFrame.setVisible(true);
    }
}