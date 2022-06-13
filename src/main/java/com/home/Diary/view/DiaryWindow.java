package com.home.Diary.view;

import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Mon Jun 13 23:55:52 YEKT 2022
 */



/**
 * @author get
 */
public class DiaryWindow  {

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		Diary = new JFrame();
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

		//======== Diary ========
		{
			Diary.setTitle("My Diary");
			Container DiaryContentPane = Diary.getContentPane();
			DiaryContentPane.setLayout(new BorderLayout());

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
			Diary.setJMenuBar(menuBar1);

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
			DiaryContentPane.add(mainPanel, BorderLayout.CENTER);
			Diary.pack();
			Diary.setLocationRelativeTo(Diary.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JFrame Diary;
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
