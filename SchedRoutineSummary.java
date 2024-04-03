package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SchedRoutineSummary extends JFrame {

    private DefaultTableModel tableModel;
    private JTable dataTable;
    private JLabel infoLabel;

    public SchedRoutineSummary(FrameController controller) {
        setTitle("Spark Sched");
        setSize(460, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon logo = new ImageIcon(getClass().getResource("SparkSchedLogo.png"));
        setIconImage(logo.getImage());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        HighlightPanel taskSummary = new HighlightPanel();
        taskSummary.setPreferredSize(new Dimension(340, 33));

        JLabel label = new JLabel("R O U T I N E  S U M M A R Y");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Canva Sans", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        taskSummary.add(label);
        mainPanel.add(taskSummary, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("Time");
        tableModel.addColumn("Task");

        dataTable = new JTable(tableModel);
        JTableHeader header = dataTable.getTableHeader();
        header.setBackground(new Color(190, 182, 168));
        header.setForeground(Color.WHITE);
        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.getTableHeader().setResizingAllowed(false);

        TableColumnModel columnModel = dataTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(30);
        columnModel.getColumn(2).setPreferredWidth(190);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);


        JPanel taskSummaryInfo = new JPanel();
        taskSummaryInfo.setPreferredSize(new Dimension(340, 50));
        //taskSummaryInfo.setBackground(Color.PINK);
        taskSummaryInfo.setLayout(null); // Set layout to null
        mainPanel.add(taskSummaryInfo, BorderLayout.SOUTH);

        infoLabel = new JLabel("<html>&#8226; This table utilizes <b>Depth-First Search (DFS)</b>, " +
                "the task routine is efficiently sorted starting based on your chosen task, " +
                "ensuring tasks are sequenced in a manner that respects their dependencies and maximizes efficiency.<br></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setForeground(Color.decode("#737373"));
        infoLabel.setPreferredSize(new Dimension(466, 80));
        taskSummaryInfo.add(infoLabel);

        // Add a ComponentListener to the frame to listen for resize eventszzzz
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                // Resize the label dynamically based on the new size of the frame
                Dimension size = taskSummaryInfo.getSize();
                infoLabel.setBounds(0, 0, size.width, size.height);
            }
        });

        // Add window listener to handle window close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                controller.schedRoutineSummaryClosed();
            }
        });




        setVisible(true);
    }

    public void setDataTable(DefaultTableModel model) {
        tableModel = model;
        dataTable.setModel(tableModel);
    }

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SchedRoutineSummary::new);
    }
     */
}
