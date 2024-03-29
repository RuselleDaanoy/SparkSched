package com.example;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class PrioPanel extends JFrame {
    private DefaultTableModel tableModel;
    private JTable dataTable;

    public PrioPanel() {
        setTitle("PrioPanel");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        HighlightPanel taskSummary = new HighlightPanel();
        taskSummary.setBounds(30, 80, 340, 33); 

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
        dataTable.setBounds(30, 130, 400, 200);

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

        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(30, 135, taskSummary.getWidth(), 400); 

        JPanel tableInfo = new JPanel();
        tableInfo.setBackground(getContentPane().getBackground()); 
        tableInfo.setBounds(-10, 545, taskSummary.getWidth(), taskSummary.getHeight()+ 20);

        JLabel infoLabel = new JLabel("<html><div style='text-align: left;'><table><tr><td style='vertical-align: top;'>&#8226;</td><td>This table displays a summary of tasks</td></tr><tr><td></td><td>sorted by most recent additions</td></tr></table></div></html>");
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tableInfo.add(infoLabel);

        add(tableInfo); 
        add(scrollPane); 

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrioPanel());
    }
}
