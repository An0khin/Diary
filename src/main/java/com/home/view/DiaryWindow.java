package com.home.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DiaryWindow extends JFrame {
    public JFrame diaryWindow;
    public JPanel infoPanel;
    public JTable recordsTable;
    public DefaultTableModel tableModel;
    public JButton buttonNew;
    public JMenuItem menuEditItemNew;
    public JMenuItem menuEditItemEdit;
    public JMenuItem menuEditItemDelete;
    public JMenuItem menuFileItemSettings;
    public JMenuItem menuFileItemSave;
    public JMenuItem menuFileItemLoad;
    public JMenuItem menuFindItemFind;
    public JMenuItem menuViewItemColumns;

    public DiaryWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
    }
    private void initComponents() {
        diaryWindow = new JFrame("My Diary");

        JMenuBar menuBar = setupMenuBar();
        JPanel mainPanel = setupMainPanel();

        setDiaryWindowSettings();

        diaryWindow.setJMenuBar(menuBar);

        Container diaryWindowContentPane = diaryWindow.getContentPane();
        diaryWindowContentPane.add(mainPanel, BorderLayout.CENTER);
        diaryWindow.pack();
    }
    private void setDiaryWindowSettings() {
        diaryWindow.setVisible(true);
        diaryWindow.setMinimumSize(new Dimension(650, 525));
        Container diaryWindowContentPane = diaryWindow.getContentPane();
        diaryWindowContentPane.setLayout(new BorderLayout());
        diaryWindow.setLocationRelativeTo(diaryWindow.getOwner());
    }
    private JMenuBar setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = setupMenuFile();
        JMenu menuEdit = setupMenuEdit();
        JMenu menuFind = setupMenuFind();
        JMenu menuView = setupMenuView();

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFind);
        menuBar.add(menuView);

        return menuBar;
    }
    private JMenu setupMenuFile() {
        JMenu menuFile = new JMenu("File");

        menuFileItemSave = new JMenuItem("Save");
        menuFileItemLoad = new JMenuItem("Load");
        menuFileItemSettings = new JMenuItem("Settings");

        menuFile.add(menuFileItemSave);
        menuFile.add(menuFileItemLoad);
        menuFile.add(menuFileItemSettings);

        return menuFile;
    }
    private JMenu setupMenuEdit() {
        JMenu menuEdit = new JMenu("Edit");

        menuEditItemNew = new JMenuItem("New");
        menuEditItemEdit = new JMenuItem("Edit");
        menuEditItemDelete = new JMenuItem("Delete");

        menuEdit.add(menuEditItemNew);
        menuEdit.add(menuEditItemEdit);
        menuEdit.add(menuEditItemDelete);

        return menuEdit;
    }
    private JMenu setupMenuFind() {
        JMenu menuFind = new JMenu("Find");
        menuFindItemFind = new JMenuItem("Find...");

        menuFind.add(menuFindItemFind);

        return menuFind;
    }
    private JMenu setupMenuView() {
        JMenu menuView = new JMenu("View");
        menuViewItemColumns = new JMenuItem("Columns...");

        menuView.add(menuViewItemColumns);

        return menuView;
    }
    private JPanel setupMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel recordsPanel = setupRecordsPanel();
        buttonNew = setupButtonNew();

        mainPanel.add(recordsPanel);
        mainPanel.add(buttonNew);

        return mainPanel;
    }
    private JButton setupButtonNew() {
        JButton buttonNew = new JButton("New");

        buttonNew.setMaximumSize(new Dimension(33000, 25));
        buttonNew.setHideActionText(true);
        buttonNew.setMinimumSize(new Dimension(33000, 25));

        return buttonNew;
    }
    private JPanel setupRecordsPanel() {
        JPanel recordsPanel = new JPanel();
        recordsPanel.setLayout(new BoxLayout(recordsPanel, BoxLayout.X_AXIS));

        JScrollPane scrollPaneRecordsTable = setupScrollPaneRecordsTable();
        infoPanel = setupInfoPanel();

        recordsPanel.add(scrollPaneRecordsTable);
        recordsPanel.add(infoPanel);

        return recordsPanel;
    }
    private JScrollPane setupScrollPaneRecordsTable() {
        JScrollPane scrollPaneRecordsTable = new JScrollPane();

        recordsTable = setupRecordsTable();
        scrollPaneRecordsTable.setViewportView(recordsTable);

        return scrollPaneRecordsTable;
    }
    private JTable setupRecordsTable() {
        JTable recordsTable = new JTable();

        tableModel = setupTableModel();
        recordsTable.setModel(tableModel);

        return recordsTable;
    }
    private DefaultTableModel setupTableModel() {
        return new DefaultTableModel(
                null,
                new String[] {"DATE", "TITLE", "LAST UPDATE", "DESCRIPTION"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { //using for disable editable rows
                return false;
            }
        };
    }
    private JPanel setupInfoPanel() {
        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        return infoPanel;
    }
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        recordsTable.setModel(tableModel);
    }
}
