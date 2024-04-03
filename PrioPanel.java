package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class PrioPanel extends JFrame {
    private DefaultTableModel tableModel;
    private JTable dataTable;
    private JTextField startInputField, startInputField2, firstTaskTextField;
    private DefaultTableModel topPrioritiesTableModel;
    private Map<String, List<String>> taskGraph;
    private Set<String> visitedNodes;

    public PrioPanel(FrameController controller, Stack<String[]> taskTableData) {
        taskGraph = new HashMap<>();
        visitedNodes = new HashSet<>();
        setTitle("Spark Sched");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon logo = new ImageIcon(getClass().getResource("SparkSchedLogo.png"));
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

        for (Object[] rowData : taskTableData) {
            tableModel.addRow(rowData);
        }

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
        tableInfo.setBounds(-10, 545, taskSummary.getWidth(), taskSummary.getHeight() + 20);

        JLabel infoLabel = new JLabel("<html><div style='text-align: left;'><table><tr><td style='vertical-align: top;'>&#8226;</td><td>This table displays a summary of tasks</td></tr><tr><td></td><td>sorted by most recent additions</td></tr></table></div></html>");
        infoLabel.setForeground(Color.decode("#737373"));
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        tableInfo.add(infoLabel);

        add(tableInfo);
        add(scrollPane);

        // RIGHT First Task panel
        JPanel firstTaskPanel = new JPanel();
        firstTaskPanel.setBounds(426, 50, 790, 70);
        firstTaskPanel.setLayout(null);
        add(firstTaskPanel);

        // First Task label
        JLabel firstTaskLabel = new JLabel("What task would you like to start?");
        firstTaskLabel.setFont(new Font("Canva Sans", Font.PLAIN, 20));
        firstTaskLabel.setBounds(10, 0, 305, 30);
        firstTaskPanel.add(firstTaskLabel);

        // First Task textfield
        firstTaskTextField = new JTextField();
        firstTaskTextField.setText("Enter task here");
        firstTaskTextField.setFont(new Font("Canva Sans", Font.PLAIN, 13));
        firstTaskTextField.setForeground(Color.decode("#737373"));
        firstTaskTextField.setBounds(firstTaskLabel.getX() + firstTaskLabel.getWidth() + 10, firstTaskLabel.getY(), 465, 30);
        firstTaskPanel.add(firstTaskTextField);

        // First Task button
        RoundedButtonPanel firstTaskButton = new RoundedButtonPanel("Enter");
        firstTaskButton.setBounds(firstTaskPanel.getWidth() - 100, firstTaskTextField.getY() + 30 + 10, 100, 30);
        firstTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredText = firstTaskTextField.getText();
                boolean textFound = false;
                int selectedIndex = -1;

                // Find the selected task in the dataTable
                for (int i = 0; i < dataTable.getRowCount(); i++) {
                    String task = (String) dataTable.getValueAt(i, 2);
                    if (enteredText.equals(task)) {
                        textFound = true;
                        selectedIndex = i;
                        break;
                    }
                }

                if (textFound) {
                    firstTaskTextField.setForeground(Color.BLACK);
                    startInputField.setText(enteredText);
                    updateStartInputField();

                    // Clear the topPrioritiesTableModel before adding the selected task
                    topPrioritiesTableModel.setRowCount(0);

                    // Add the selected task to the top of the topPrioritiesTableModel
                    Object[] rowData = new Object[tableModel.getColumnCount()];
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        rowData[j] = tableModel.getValueAt(selectedIndex, j);
                    }
                    topPrioritiesTableModel.addRow(rowData);

                    // Copy data from tableModel to topPrioritiesTableModel for other tasks
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if (i != selectedIndex) {
                            rowData = new Object[tableModel.getColumnCount()];
                            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                                rowData[j] = tableModel.getValueAt(i, j);
                            }
                            topPrioritiesTableModel.addRow(rowData);
                        }
                    }

                    // Call DFS with the selected task as the starting node
                    dfs(enteredText);

                } else {
                    JOptionPane.showMessageDialog(PrioPanel.this, "Enter a task from the table", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        firstTaskPanel.add(firstTaskButton);

        // First Task info label
        JLabel firstTaskInfoLabel = new JLabel("<html>&#8226; Select task from 'Task Summary' to begin scheduling.</html>");
        firstTaskInfoLabel.setBounds(10, firstTaskButton.getY(), firstTaskPanel.getWidth() - firstTaskButton.getWidth(), 30);
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

        startInputField = new JTextField(20);
        startInputField.setOpaque(false);
        startInputField.setBorder(BorderFactory.createEmptyBorder());
        startInputField.setHorizontalAlignment(SwingConstants.CENTER);
        startInputField.setFont(new Font("Arial", Font.PLAIN, 15));
        startInputField.setForeground(Color.WHITE);
        startInputField.setEditable(false);
        schedSummaryHighlight1.add(startInputField);

        schedSummaryPanel.add(schedSummaryHighlight1);

        // Sched Highlight for "The task is scheduled to start at"
        HighlightPanel schedSummaryHighlight2 = new HighlightPanel();
        schedSummaryHighlight2.setBounds(schedSummaryLabel.getX() + schedSummaryLabel.getWidth() + 10, 55, 300, 30);

        startInputField2 = new JTextField(20);
        startInputField2.setOpaque(false);
        startInputField2.setBorder(BorderFactory.createEmptyBorder());
        startInputField2.setHorizontalAlignment(SwingConstants.CENTER);
        startInputField2.setFont(new Font("Arial", Font.PLAIN, 15));
        startInputField2.setForeground(Color.WHITE);
        startInputField2.setEditable(false);
        schedSummaryHighlight2.add(startInputField2);

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
        topPrioritiesTableModel = new DefaultTableModel();
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
        bottomPanel.setLayout(null);
        add(bottomPanel);

        JLabel bottomPanelLabel = new JLabel("Would you like to overview the whole schedule routine?");
        bottomPanelLabel.setFont(new Font("Canva Sans", Font.PLAIN, 20));
        bottomPanelLabel.setBounds(0, 10, 600, 30);
        bottomPanel.add(bottomPanelLabel);

        RoundedButtonPanel routineButton = new RoundedButtonPanel("Proceed");
        routineButton.setBounds(bottomPanelLabel.getWidth() - 100 + 10, 10, 100, 30);
        routineButton.addActionListener(e -> controller.switchToSchedRoutineSummary());
        bottomPanel.add(routineButton);

        setVisible(true);
    }

    private void dfs(String startTask) {
        Stack<String> stack = new Stack<>();
        stack.push(startTask);
        visitedNodes.add(startTask);
    
        while (!stack.isEmpty()) {
            String currentTask = stack.pop();
            List<String> neighbors = taskGraph.getOrDefault(currentTask, new ArrayList<>());
    
            for (String neighbor : neighbors) {
                if (!visitedNodes.contains(neighbor)) {
                    stack.push(neighbor);
                    visitedNodes.add(neighbor);
                    dfs(neighbor); // Recursive call to visit neighbors of the current neighbor
                }
            }
        }
    }

    private void updateStartInputField() {
        String enteredText = firstTaskTextField.getText();
        for (int i = 0; i < dataTable.getRowCount(); i++) {
            String task = (String) dataTable.getValueAt(i, 2);
            if (enteredText.equals(task)) {
                String date = (String) dataTable.getValueAt(i, 0);
                String time = (String) dataTable.getValueAt(i, 1);
                startInputField2.setText(date + " and " + time);
                break;
            }
        }
    }
}
