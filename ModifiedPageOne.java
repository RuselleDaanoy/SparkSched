import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ModifiedPageOne extends JFrame{
    private JPanel leftPanel;
    private JTable table;
    private DefaultTableModel taskTable;

    public ModifiedPageOne(){
        setTitle("Spark Sched");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // main - for test only
        JPanel mainPanel = new JPanel(new BorderLayout());

        // left panel
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(425, 720));
        leftPanel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);

        // HEADER
        JLabel label2 = new JLabel("SPARK SCHED");
        label2.setFont(new Font("SERIF", Font.PLAIN, 35));
        label2.setForeground(new Color(84, 84, 84));

        GridBagConstraints gbcLabel2 = new GridBagConstraints();
        gbcLabel2.gridx = 0;
        gbcLabel2.gridy = 0;
        gbcLabel2.anchor = GridBagConstraints.WEST;
        gbcLabel2.insets = new Insets(50, 22, 0, 100);
        topPanel.add(label2, gbcLabel2);

        JPanel underlinePanel = new JPanel();
        underlinePanel.setPreferredSize(new Dimension(150, 5));
        underlinePanel.setBackground(new Color(84, 84, 84));

        GridBagConstraints gbcUnderline = new GridBagConstraints();
        gbcUnderline.gridx = 0;
        gbcUnderline.gridy = 1;
        gbcUnderline.anchor = GridBagConstraints.WEST;
        gbcUnderline.insets = new Insets(0, 22, 35, 0);
        topPanel.add(underlinePanel, gbcUnderline);

        JLabel textLabel1 = new JLabel("<html> Turning plans into sparks<br>of success");
        textLabel1.setFont(new Font("Canva Sans", Font.PLAIN, 14));
        textLabel1.setForeground(new Color(84, 84, 84));

        GridBagConstraints gbcTextLabel1 = new GridBagConstraints();
        gbcTextLabel1.gridx = 0;
        gbcTextLabel1.gridy = 1;
        gbcTextLabel1.anchor = GridBagConstraints.EAST;
        gbcTextLabel1.insets = new Insets(0, 50, 15, 10);
        topPanel.add(textLabel1, gbcTextLabel1);

        // TABLE
        HighlightPanel head = new HighlightPanel();
        head.setBounds(50, 145, 340, 33);

        JLabel label1 = new JLabel("T A S K  S U M M A R Y");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Canva Sans", Font.BOLD, 14));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setBounds(10, 20, 280, 20);
        head.add(label1);

        taskTable = new DefaultTableModel();
        taskTable.addColumn("DATE");
        taskTable.addColumn("TIME");
        taskTable.addColumn("TASK");

        table = new JTable(taskTable);
        table.setBounds(50, 190, 400, 200);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(190, 182, 168));
        header.setForeground(Color.WHITE);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        TableColumnModel columnModel = table.getColumnModel();
        TableColumn columnDate = columnModel.getColumn(0);
        TableColumn columnTime = columnModel.getColumn(1);
        TableColumn columnTask = columnModel.getColumn(2);

        columnDate.setPreferredWidth(50);
        columnTime.setPreferredWidth(50);
        columnTask.setPreferredWidth(150);

        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 195, 340, 400);


        // FOOTER
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(Color.WHITE);

        RoundedButtonPanel clearButton = new RoundedButtonPanel("Clear");
        clearButton.setFont(new Font("Canva Sans", Font.PLAIN, 14));
        clearButton.setForeground(Color.BLACK);
        clearButton.setPreferredSize(new Dimension(90, 30));

        GridBagConstraints gbcClearButton = new GridBagConstraints();
        gbcClearButton.gridx = 0;
        gbcClearButton.gridy = 0;
        gbcClearButton.anchor = GridBagConstraints.CENTER;
        gbcClearButton.insets = new Insets(0, 75, 45, -185);
        bottomPanel.add(clearButton, gbcClearButton);

        JLabel tableDesc = new JLabel("<html><div style='text-align:left;'>" +
                "<p>* The table above display the summary of your<br>" +
                "&nbsp;&nbsp; tasks sorted by the most recent additions.</p></div></html>");
        tableDesc.setForeground(new Color(84, 84, 84));
        tableDesc.setFont(new Font("Arial", Font.PLAIN, 11));

        GridBagConstraints gbcTableDesc = new GridBagConstraints();
        gbcTableDesc.gridx = 0;
        gbcTableDesc.gridy = 0;
        gbcTableDesc.anchor = GridBagConstraints.WEST;
        gbcTableDesc.insets = new Insets(0, -90, 52, 0);
        bottomPanel.add(tableDesc, gbcTableDesc);

        // left panel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.add(head, BorderLayout.CENTER);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(topPanel, BorderLayout.NORTH);
        leftPanel.add(bottomPanel, BorderLayout.SOUTH);

        // right side

        JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        taskPanel.setBackground(getContentPane().getBackground());
        taskPanel.setBounds(426, 50, 350, 65);

        JLabel taskLabel = new JLabel("Enter number of task  ");
        taskLabel.setForeground(Color.BLACK);
        taskLabel.setFont(new Font("Canva Sans", Font.PLAIN, 20));
        taskLabel.setHorizontalAlignment(SwingConstants.CENTER);
        taskPanel.add(taskLabel);

        CurvedTextField taskText = new CurvedTextField();
        taskText.setPreferredSize(new Dimension(140,30));
        taskText.setText("1-10");
        taskText.setForeground(Color.GRAY);
        taskText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (taskText.getText().equals("1-10")) {
                    taskText.setText("");
                    taskText.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (taskText.getText().isEmpty()) {
                    taskText.setForeground(Color.GRAY);
                    taskText.setText("1-10");
                }
            }
        });
        taskPanel.add(taskText);
        JLabel taskLabel2 = new JLabel("<html><div style='text-align: left;'><table><tr><td style='vertical-align: top;'>&#8226;</td><td>Indicate the number of tasks you wish to complete.</td></tr></table></div></html>");
        taskLabel2.setForeground(Color.GRAY);
        taskLabel2.setFont(new Font("Arial", Font.PLAIN, 12));
        taskPanel.add(taskLabel2);
        add(taskPanel);

        JPanel button1Panel = new JPanel();
        button1Panel.setBackground(getContentPane().getBackground());
        button1Panel.setBounds(800, 50, 120, 50);

        RoundedButtonPanel button1 = new RoundedButtonPanel("Set");
        button1.setFont(new Font("Canva Sans", Font.PLAIN, 12));
        button1.setPreferredSize(new Dimension(80, 30));

        button1Panel.add(button1);
        add(button1Panel);

        JPanel actPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actPanel.setBackground(getContentPane().getBackground());
        actPanel.setBounds(426, 125, 790, 40);

        JLabel actLabel = new JLabel("Name of the activity  ");
        actLabel.setForeground(Color.BLACK);
        actLabel.setFont(new Font("Canva Sans", Font.PLAIN, 20));
        actPanel.add(actLabel);

        CurvedTextField actText = new CurvedTextField();
        actText.setPreferredSize(new Dimension(590,30));
        actText.setText("Type name here");
        actText.setForeground(Color.GRAY);
        actText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (actText.getText().equals("Type name here")) {
                    actText.setText("");
                    actText.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (actText.getText().isEmpty()) {
                    actText.setForeground(Color.GRAY);
                    actText.setText("Type name here");
                }
            }
        });
        actPanel.add(actText);
        add(actPanel);

        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timePanel.setBackground(getContentPane().getBackground());
        timePanel.setBounds(426, 175, 250, 40);

        JLabel timeLabel = new JLabel("Time ");
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Canva Sans", Font.PLAIN, 20));
        timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        timePanel.add(timeLabel);

        CurvedTextField timeText = new CurvedTextField();
        timeText.setPreferredSize(new Dimension(110,30));
        timeText.setText("hh:mm");
        timeText.setForeground(Color.GRAY);
        timeText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (timeText.getText().equals("hh:mm")) {
                    timeText.setText("");
                    timeText.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (timeText.getText().isEmpty()) {
                    timeText.setForeground(Color.GRAY);
                    timeText.setText("hh:mm");
                }
            }
        });
        timePanel.add(timeText);
        String[] amPmOptions = {"AM", "PM"};
        JComboBox<String> timeComboBox = new JComboBox<>(amPmOptions);
        timeComboBox.setFont(new Font("Canva Sans", Font.PLAIN, 12));
        timeComboBox.setPreferredSize(new Dimension(60, 30));
        timeComboBox.setBackground(Color.WHITE);
        timePanel.add(timeComboBox);
        add(timePanel);

        JPanel datePanel = new JPanel();
        datePanel.setBackground(getContentPane().getBackground());
        datePanel.setBounds(750, 175, 300, 50);

        JLabel dateLabel = new JLabel("Date ");
        dateLabel.setForeground(Color.BLACK);
        dateLabel.setFont(new Font("Canva Sans", Font.PLAIN, 20));
        dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        datePanel.add(dateLabel);

        CurvedTextField dateText = new CurvedTextField();
        dateText.setPreferredSize(new Dimension(95,30));
        dateText.setText("YYYY-MM-DD");
        dateText.setForeground(Color.GRAY);
        dateText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dateText.getText().equals("YYYY-MM-DD")) {
                    dateText.setText("");
                    dateText.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dateText.getText().isEmpty()) {
                    dateText.setForeground(Color.GRAY);
                    dateText.setText("YYYY-MM-DD");
                }
            }
        });
        datePanel.add(dateText);

        JPanel extraPanel = new JPanel();
        extraPanel.setBackground(getContentPane().getBackground());
        extraPanel.setPreferredSize(new Dimension(20, 0));
        datePanel.add(extraPanel);

        RoundedButtonPanel dateButton = new RoundedButtonPanel("Add task");
        dateButton.setFont(new Font("Canva Sans", Font.PLAIN, 12));
        dateButton.setPreferredSize(new Dimension(80, 30));
        datePanel.add(dateButton);
        add(datePanel);

        JPanel bulletPanel = new JPanel();
        bulletPanel.setBackground(getContentPane().getBackground());
        bulletPanel.setBounds(426, 215, 320, 50);

        JLabel bulletLabel = new JLabel("<html><div style='text-align: left;'><table><tr><td style='vertical-align: top;'>&#8226;</td><td>Specify the activity name, task time, and date of the task</td></tr><tr><td></td><td>you wish to add.</td></tr></table></div></html>");
        bulletLabel.setForeground(Color.GRAY);
        bulletLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        bulletPanel.add(bulletLabel);
        add(bulletPanel);


        JPanel outputPanel = new JPanel();
        outputPanel.setBackground(new Color(209, 203, 188));
        outputPanel.setForeground(Color.WHITE);
        outputPanel.setBounds(426, 275, 790, 70);

        JPanel outputPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        outputPanel2.setBackground(new Color(209, 203, 188));
        outputPanel2.setForeground(Color.WHITE);
        outputPanel2.setBounds(426, 275, 340, 70);

        HighlightPanel medPanel = new HighlightPanel();
        medPanel.setBounds(672, 280, 37, 28);

        HighlightPanel pivotPanel = new HighlightPanel();
        pivotPanel.setBounds(685, 310, 380, 28);


        JLabel outputLabel = new JLabel("<html><div style='text-align: left;'><table><tr><td style='vertical-align: top;'>&#8226;</td><td>Your lists of priorities consists of  ..l...... tasks.</td></tr></table></div></html>");
        outputLabel.setForeground(Color.BLACK);
        outputLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        outputPanel2.add(outputLabel);
        JLabel outputLabel2 = new JLabel("<html><div style='text-align: left;'><table><tr><td style='vertical-align: top;'>&#8226;</td><td>The top task should be finished by </td></tr></table></div></html>");
        outputLabel2.setForeground(Color.BLACK);
        outputLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
        outputPanel2.add(outputLabel2);
        add(medPanel);
        add(pivotPanel);
        add(outputPanel2);
        add(outputPanel);

        HighlightPanel topPrioritiesPanel = new HighlightPanel();
        topPrioritiesPanel.setBounds(426, 365, 790, 33);
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
        topPrioritiesTable.setBounds(426, topPrioritiesPanel.getY() + topPrioritiesPanel.getHeight() + 20, 790, 210);

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
        topPrioritiesScrollPane.setBounds(426, topPrioritiesPanel.getY() + topPrioritiesPanel.getHeight() + 20, 790, 200);
        add(topPrioritiesScrollPane);

        JPanel proceedPanel = new JPanel();
        proceedPanel.setBounds(426, 620, 790, 50);
        //bottomPanel.setBackground(Color.PINK);
        proceedPanel.setLayout(null);
        add(proceedPanel);

        JLabel bottomPanelLabel = new JLabel("Would you like to arrange your schedule efficiently according to your chosen task?");
        bottomPanelLabel.setFont(new Font("Canva Sans", Font.PLAIN, 15));
        bottomPanelLabel.setBounds(5, 10, 600, 30);
        proceedPanel.add(bottomPanelLabel);

        RoundedButtonPanel routineButton = new RoundedButtonPanel("Yes");
        routineButton.setBounds(bottomPanelLabel.getWidth() - 100 + 70, 10, 70, 30 );
        proceedPanel.add(routineButton);


        add(scrollPane);
        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new ModifiedPageOne());
    }
}
