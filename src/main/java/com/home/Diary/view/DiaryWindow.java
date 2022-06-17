package com.home.Diary.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.table.*;
import javax.swing.text.JTextComponent;

import com.home.Diary.model.Record;
import com.home.Diary.view.listeners.DeleteButtonListener;
import com.home.Diary.view.listeners.EditButtonListener;
import com.home.Diary.view.listeners.NewButtonListener;
import com.home.Diary.view.listeners.OpenButtonListener;
import com.home.Diary.viewmodel.Diary;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
/*
 * Created by JFormDesigner on Mon Jun 13 23:55:52 YEKT 2022
 */



/**
 * @author get
 */
public class DiaryWindow extends JFrame implements Observer{
	
	private Diary diary;
	private DefaultTableModel tableModel;
	private OpenButtonListener openButtonListener;
	private NewButtonListener newButtonListener;
	private EditButtonListener editButtonListener;
	private DeleteButtonListener deleteButtonListener;
	private Record currentRecord;
	
	public DiaryWindow(Diary diary) {
		this.diary = diary;
		diary.addObserver(this);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		openButtonListener = new OpenButtonListener(diary);
		newButtonListener = new NewButtonListener(diary);
		editButtonListener = new EditButtonListener(diary);
		deleteButtonListener = new DeleteButtonListener(diary);
		
		initComponents();
	}
	
	public void updateListeners() {
		openButtonListener.setCurrentRecord(currentRecord);
		editButtonListener.setCurrentRecord(currentRecord);
		deleteButtonListener.setCurrentRecord(currentRecord);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.removeRow(i);
		}
		for(Record rec : diary.getList()) {
			tableModel.addRow(new Object[] {rec.getDate(), rec.getTitle()});
		}
		panelInfo.removeAll();
		panelInfo.updateUI();
		
		currentRecord = null;
		updateListeners();
	}
	
	public void viewRecord(Record record) {
		panelInfo.removeAll();
		panelInfo.updateUI();
		
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
			panelInfo.add(comp);
		}
		
	}
		
	public void openChoiceRecord(Record record) {
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
			
			JButton openButton = new JButton("OPEN");
			openButton.addActionListener(openButtonListener);
			
			JButton editButton = new JButton("EDIT");
			editButton.addActionListener(editButtonListener);
			
			lowerPanel.add(openButton, BorderLayout.WEST);
			lowerPanel.add(editButton, BorderLayout.EAST);
			
			Object[] message = {
					upperPanel,
					descriptionPane,
					lowerPanel
			};
			
			JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE);
			JDialog dialog = pane.createDialog("HOP");
			
			openButton.addActionListener((e) -> {dialog.setVisible(false);});
			editButton.addActionListener((e) -> {dialog.setVisible(false);});
			
			pane.remove(pane.getComponents().length - 1);
			dialog.setVisible(true);
		}
	}

	private class TableSelectionListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			if(e.getClickCount() >= 2) {
				openChoiceRecord(currentRecord);
			}
			
			int index = recordsTable.getSelectedRow();
			
			if(index != -1) {
				currentRecord = diary.getRecordByDate((Date) tableModel.getValueAt(index, 0));

				viewRecord(currentRecord);
				
				updateListeners();
			}			
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		diaryWindow = new JFrame();
		menuBar1 = new JMenuBar();
		menuFile = new JMenu();
		menuFileItemSave = new JMenuItem();
		menuEdit = new JMenu();
		menuEditNew = new JMenuItem();
		menuEditEdit = new JMenuItem();
		menuEditDelete = new JMenuItem();
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
			diaryWindow.setMinimumSize(new Dimension(650, 525));
			Container diaryWindowContentPane = diaryWindow.getContentPane();
			diaryWindowContentPane.setLayout(new BorderLayout());

			//======== menuBar1 ========
			{

				//======== menuFile ========
				{
					menuFile.setText("File");

					//---- menuFileItemSave ----
					menuFileItemSave.setText("Save");
					menuFile.add(menuFileItemSave);
				}
				menuBar1.add(menuFile);

				//======== menuEdit ========
				{
					menuEdit.setText("Edit");

					//---- menuEditNew ----
					menuEditNew.setText("New");
					menuEdit.add(menuEditNew);

					//---- menuEditEdit ----
					menuEditEdit.setText("Edit");
					menuEdit.add(menuEditEdit);

					//---- menuEditDelete ----
					menuEditDelete.setText("Delete");
					menuEdit.add(menuEditDelete);
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
						scrollPaneRecordsTable.setViewportView(recordsTable);
					}
					panelRecords.add(scrollPaneRecordsTable);

					//======== panelInfo ========
					{
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
		menuEditNew.addActionListener(newButtonListener);
		menuEditEdit.addActionListener(editButtonListener);
		menuEditDelete.addActionListener(deleteButtonListener);
		recordsTable.addMouseListener(new TableSelectionListener());
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JFrame diaryWindow;
	private JMenuBar menuBar1;
	private JMenu menuFile;
	private JMenuItem menuFileItemSave;
	private JMenu menuEdit;
	private JMenuItem menuEditNew;
	private JMenuItem menuEditEdit;
	private JMenuItem menuEditDelete;
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
