package com.example;

import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

public class PrioPanel extends JFrame {
    private DefaultTableModel tableModel;
    private JTable dataTable;

    public PrioPanel() {
        setTitle("Spark Sched");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon logo = new ImageIcon("src/SparkSchedLogo.png");
        setIconImage(logo.getImage());

        HighlightPanel taskSummary = new HighlightPanel();
        taskSummary.setBounds(50, 80, 340, 33);

        JLabel label = new JLabel("T A S K  S U M M A R Y");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Canva Sans", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(10, 20, 280, 20);
        taskSummary.add(label);
        add(taskSummary);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("Time");
        tableModel.addColumn("Task");

        dataTable = new JTable(tableModel);
        dataTable.setBounds(50, 130, 400, 200);

        JTableHeader header = dataTable.getTableHeader();
        header.setBackground(new Color(190, 182, 168));
        header.setForeground(Color.WHITE);

        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.getTableHeader().setResizingAllowed(false);

        TableColumnModel columnModel = dataTable.getColumnModel();
        TableColumn columnDate = columnModel.getColumn(0);
        TableColumn columnTime = columnModel.getColumn(1);
        TableColumn columnName = columnModel.getColumn(2);

        columnDate.setPreferredWidth(50);
        columnTime.setPreferredWidth(50);
        columnName.setPreferredWidth(150);

        dataTable.getTableHeader().setPreferredSize(new Dimension(dataTable.getTableHeader().getWidth(), 30));
        dataTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(50, 135, taskSummary.getWidth(), 400);

        JPanel tableInfo = new JPanel();
        tableInfo.setBackground(getContentPane().getBackground());
        tableInfo.setBounds(-10, 545, taskSummary.getWidth(), taskSummary.getHeight()+ 20);

        JLabel infoLabel = new JLabel("<html><div style='text-align: left;'><table><tr><td style='vertical-align: top;'>&#8226;</td><td>This table displays a summary of tasks</td></tr><tr><td></td><td>sorted by most recent additions</td></tr></table></div></html>");
        infoLabel.setForeground(Color.decode("#737373"));
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        tableInfo.add(infoLabel);

        add(tableInfo);
        add(scrollPane);

        // RIGHT First Task panel
        JPanel firstTaskPanel = new JPanel();
        firstTaskPanel.setBounds(426, 50, 790, 70);
        //firstTaskPanel.setBackground(Color.ORANGE); // To show panel dimensions only ദ്ദി(˵ •̀ ᴗ - ˵ ) ✧
        firstTaskPanel.setLayout(null);
        add(firstTaskPanel);

        // First Task label
        JLabel firstTaskLabel = new JLabel("What task would you like to start?");
        firstTaskLabel.setFont(new Font("Canva Sans", Font.PLAIN, 20));
        firstTaskLabel.setBounds(10, 0, 305, 30);
        firstTaskPanel.add(firstTaskLabel);

        // First Task textfield
        CurvedTextField firstTaskTextField = new CurvedTextField();
        firstTaskTextField.setText("Enter task here");
        firstTaskTextField.setFont(new Font("Canva Sans", Font.PLAIN, 13));
        firstTaskTextField.setForeground(Color.decode("#737373"));
        firstTaskTextField.setBounds(firstTaskLabel.getX() + firstTaskLabel.getWidth() + 10, firstTaskLabel.getY(), 465, 30);
        firstTaskPanel.add(firstTaskTextField);

        // First Task button
        RoundedButtonPanel firstTaskButton = new RoundedButtonPanel("Enter");
        firstTaskButton.setBounds(firstTaskPanel.getWidth()-100, firstTaskTextField.getY()+ 30 + 10, 100, 30);
        firstTaskPanel.add(firstTaskButton);

        // First Task info label
        JLabel firstTaskInfoLabel = new JLabel("<html>&#8226; Select task from 'Task Summary' to begin scheduling.</html>");
        firstTaskInfoLabel.setBounds(10, firstTaskButton.getY(), firstTaskPanel.getWidth()-firstTaskButton.getWidth(), 30);
        firstTaskInfoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        firstTaskInfoLabel.setForeground(Color.decode("#737373"));
        firstTaskPanel.add(firstTaskInfoLabel);

        // Sched Summary panel
        JPanel schedSummaryPanel = new JPanel();
        schedSummaryPanel.setBounds(426, firstTaskPanel.getY() + firstTaskPanel.getHeight() + 15, 790, 100);
        schedSummaryPanel.setBackground(Color.decode("#D1CBBC"));
        schedSummaryPanel.setLayout(null);
        add(schedSummaryPanel);

        // Sched Summary label
        JLabel schedSummaryLabel = new JLabel("<html>&#8226; Start your day by accomplishing <br>" +
                "<br>&#8226; The task is scheduled to start at </html>");
        schedSummaryLabel.setBounds(20, 10, 250, 80);
        schedSummaryLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        schedSummaryPanel.add(schedSummaryLabel);


        // Sched Highlight for "Start your day by accomplishing"
        HighlightPanel schedSummaryHighlight1 = new HighlightPanel();
        schedSummaryHighlight1.setBounds(schedSummaryLabel.getX() + schedSummaryLabel.getWidth() + 10, 15, 500, 30);
        schedSummaryPanel.add(schedSummaryHighlight1);

        // Sched Highlight for "The task is scheduled to start at"
        HighlightPanel schedSummaryHighlight2 = new HighlightPanel();
        schedSummaryHighlight2.setBounds(schedSummaryLabel.getX() + schedSummaryLabel.getWidth() + 10, 55, 300, 30);
        schedSummaryPanel.add(schedSummaryHighlight2);

        // Top Priorities Panel
        HighlightPanel topPrioritiesPanel = new HighlightPanel();
        topPrioritiesPanel.setBounds(426, schedSummaryPanel.getY() + schedSummaryPanel.getHeight() + 30, 790, 33);
        add(topPrioritiesPanel);

        // Top Priorities label
        JLabel topPrioritiesLabel = new JLabel("S C H E D U L E  R O U T I N E");
        topPrioritiesLabel.setForeground(Color.WHITE);
        topPrioritiesLabel.setFont(new Font("Canva Sans", Font.BOLD, 14));
        topPrioritiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPrioritiesLabel.setBounds(10, 20, 280, 20);
        topPrioritiesPanel.add(topPrioritiesLabel);

        // Top Priorities Table / TPT
        DefaultTableModel topPrioritiesTableModel = new DefaultTableModel();
        topPrioritiesTableModel.addColumn("Date");
        topPrioritiesTableModel.addColumn("Time");
        topPrioritiesTableModel.addColumn("Task");

        JTable topPrioritiesTable = new JTable(topPrioritiesTableModel);
        topPrioritiesTable.setBounds(426, topPrioritiesPanel.getY() + topPrioritiesPanel.getHeight() + 20, 790, 250);

        // TPT: Set column header attributes
        JTableHeader topPrioritiesHeader = topPrioritiesTable.getTableHeader();
        topPrioritiesHeader.setBackground(new Color(190, 182, 168));
        topPrioritiesHeader.setForeground(Color.WHITE);
        topPrioritiesHeader.setReorderingAllowed(false);
        topPrioritiesHeader.setResizingAllowed(false);

        // TPT: Set preferred widths for columns
        TableColumnModel topPrioritiesColumnModel = topPrioritiesTable.getColumnModel();
        TableColumn topPrioritiesColumnDate = topPrioritiesColumnModel.getColumn(0);
        TableColumn topPrioritiesColumnTime = topPrioritiesColumnModel.getColumn(1);
        TableColumn topPrioritiesColumnName = topPrioritiesColumnModel.getColumn(2);

        topPrioritiesColumnDate.setPreferredWidth(30);
        topPrioritiesColumnTime.setPreferredWidth(30);
        topPrioritiesColumnName.setPreferredWidth(180);

        topPrioritiesTable.getTableHeader().setPreferredSize(new Dimension(topPrioritiesTable.getTableHeader().getWidth(), 30));
        topPrioritiesTable.setRowHeight(25);

        // TPT: Add the Top Priorities table to the frame
        JScrollPane topPrioritiesScrollPane = new JScrollPane(topPrioritiesTable);
        topPrioritiesScrollPane.setBounds(426, topPrioritiesPanel.getY() + topPrioritiesPanel.getHeight() + 20, 790, 250);
        add(topPrioritiesScrollPane);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(426, 720 - 150 + 10, 790, 50);
        //bottomPanel.setBackground(Color.PINK);
        bottomPanel.setLayout(null);
        add(bottomPanel);

        JLabel bottomPanelLabel = new JLabel("Would you like to overview the whole schedule routine?");
        bottomPanelLabel.setFont(new Font("Canva Sans", Font.PLAIN, 20));
        bottomPanelLabel.setBounds(0, 10, 600, 30);
        bottomPanel.add(bottomPanelLabel);

        RoundedButtonPanel routineButton = new RoundedButtonPanel("Yes");
        routineButton.setBounds(bottomPanelLabel.getWidth() - 100 + 10, 10, 70, 30 );
        bottomPanel.add(routineButton);


        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrioPanel::new);
    }
}
