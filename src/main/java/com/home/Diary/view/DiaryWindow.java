package com.home.Diary.view;

import java.awt.*;
import java.util.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.*;

import com.home.Diary.model.Record;
import com.home.Diary.view.listeners.NewButtonListener;
import com.home.Diary.viewmodel.Diary;

import javax.swing.*;
/*
 * Created by JFormDesigner on Mon Jun 13 23:55:52 YEKT 2022
 */



/**
 * @author get
 */
public class DiaryWindow implements Observer{
	
	private Diary diary;
	private DefaultTableModel  tableModel;
	private NewButtonListener newButtonListener;
	
	public DiaryWindow(Diary diary) {
		this.diary = diary;
		diary.addObserver(this);
		
		newButtonListener = new NewButtonListener(diary);
		
		initComponents();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.removeRow(i);
		}
		for(Record rec : diary.getList()) {
			tableModel.addRow(new Object[] {rec.getDate(), rec.getTitle()});
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		diaryWindow = new JFrame();
		menuBar1 = new JMenuBar();
		menuFile = new JMenu();
		menuItem1 = new JMenuItem();
		menuFileItemSave = new JMenuItem();
		menuEdit = new JMenu();
		menuItem2 = new JMenuItem();
		menuItem3 = new JMenuItem();
		menuItem4 = new JMenuItem();
		menuFind = new JMenu();
		menuView = new JMenu();
		mainPanel = new JPanel();
		panelRecords = new JPanel();
		scrollPaneRecordsTable = new JScrollPane();
		recordsTable = new JTable();
		panelInfo = new JPanel();
		buttonNew = new JButton();

		//======== diaryWindow ========
		{
			diaryWindow.setTitle("My Diary");
			diaryWindow.setVisible(true);
			Container diaryWindowContentPane = diaryWindow.getContentPane();
			diaryWindowContentPane.setLayout(new BorderLayout());

			//======== menuBar1 ========
			{

				//======== menuFile ========
				{
					menuFile.setText("File");

					//---- menuItem1 ----
					menuItem1.setText("Open");
					menuFile.add(menuItem1);

					//---- menuFileItemSave ----
					menuFileItemSave.setText("Save");
					menuFile.add(menuFileItemSave);
				}
				menuBar1.add(menuFile);

				//======== menuEdit ========
				{
					menuEdit.setText("Edit");

					//---- menuItem2 ----
					menuItem2.setText("New");
					menuEdit.add(menuItem2);

					//---- menuItem3 ----
					menuItem3.setText("Edit");
					menuEdit.add(menuItem3);

					//---- menuItem4 ----
					menuItem4.setText("Delete");
					menuEdit.add(menuItem4);
				}
				menuBar1.add(menuEdit);

				//======== menuFind ========
				{
					menuFind.setText("Find");
				}
				menuBar1.add(menuFind);

				//======== menuView ========
				{
					menuView.setText("View");
				}
				menuBar1.add(menuView);
			}
			diaryWindow.setJMenuBar(menuBar1);

			//======== mainPanel ========
			{
				mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

				//======== panelRecords ========
				{
					panelRecords.setLayout(new BoxLayout(panelRecords, BoxLayout.X_AXIS));

					//======== scrollPaneRecordsTable ========
					{

						//---- recordsTable ----
						recordsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
						recordsTable.setModel(new DefaultTableModel(
							new Object[][] {
								{null, ""},
								{null, null},
							},
							new String[] {
								"DATE", "TITLE"
							}
						) {
							Class<?>[] columnTypes = new Class<?>[] {
								Date.class, String.class
							};
							@Override
							public Class<?> getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
						scrollPaneRecordsTable.setViewportView(recordsTable);
					}
					panelRecords.add(scrollPaneRecordsTable);

					//======== panelInfo ========
					{
						panelInfo.setMaximumSize(new Dimension(33000, 0));
						panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
					}
					panelRecords.add(panelInfo);
				}
				mainPanel.add(panelRecords);

				//---- buttonNew ----
				buttonNew.setText("New");
				buttonNew.setMaximumSize(new Dimension(33000, 25));
				buttonNew.setHideActionText(true);
				buttonNew.setMinimumSize(new Dimension(33000, 25));
				mainPanel.add(buttonNew);
			}
			diaryWindowContentPane.add(mainPanel, BorderLayout.CENTER);
			diaryWindow.pack();
			diaryWindow.setLocationRelativeTo(diaryWindow.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		tableModel = new DefaultTableModel(null, new String[] {"DATE", "TITLE"}) {
			@Override
			public boolean isCellEditable(int row, int column) { //using for disable editable rows
				return false;
			}
		};
		
		recordsTable.setModel(tableModel);
		buttonNew.addActionListener(newButtonListener);
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JFrame diaryWindow;
	private JMenuBar menuBar1;
	private JMenu menuFile;
	private JMenuItem menuItem1;
	private JMenuItem menuFileItemSave;
	private JMenu menuEdit;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenuItem menuItem4;
	private JMenu menuFind;
	private JMenu menuView;
	private JPanel mainPanel;
	private JPanel panelRecords;
	private JScrollPane scrollPaneRecordsTable;
	private JTable recordsTable;
	private JPanel panelInfo;
	private JButton buttonNew;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
