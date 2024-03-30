
import java.awt.Color;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

public class PanelKeith extends JFrame {
    private JPanel taskPanel;

    public PanelKeith() {
        setTitle("PanelKeith");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        taskPanel = new JPanel();
        taskPanel.setBackground(getContentPane().getBackground());
        taskPanel.setBounds(425, 62, 325, 85);

        JLabel taskLabel = new JLabel("Enter number of task  ");
        taskLabel.setForeground(Color.BLACK);
        taskLabel.setFont(new Font("Canva Sans", Font.PLAIN, 17));
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
        button1Panel.setBounds(750, 60, 120, 50);

        RoundedButtonPanel button1 = new RoundedButtonPanel("Set");
        button1.setFont(new Font("Canva Sans", Font.PLAIN, 12));
        button1.setPreferredSize(new Dimension(80, 30));
        button1Panel.add(button1);
        add(button1Panel);

        JPanel actPanel = new JPanel();
        actPanel.setBackground(getContentPane().getBackground());
        actPanel.setBounds(425, 145, 530, 50);

        JLabel actLabel = new JLabel("Name of the activity  ");
        actLabel.setForeground(Color.BLACK);
        actLabel.setFont(new Font("Canva Sans", Font.PLAIN, 17));
        actLabel.setHorizontalAlignment(SwingConstants.LEFT);
        actPanel.add(actLabel);

        CurvedTextField actText = new CurvedTextField();
        actText.setPreferredSize(new Dimension(345,30));
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

        JPanel timePanel = new JPanel();
        timePanel.setBackground(getContentPane().getBackground());
        timePanel.setBounds(425, 202, 250, 40);

        JLabel timeLabel = new JLabel("Time ");
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Canva Sans", Font.PLAIN, 17));
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
        datePanel.setBounds(660, 202, 300, 50);

        JLabel dateLabel = new JLabel("Date ");
        dateLabel.setForeground(Color.BLACK);
        dateLabel.setFont(new Font("Canva Sans", Font.PLAIN, 17));
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
        extraPanel.setBackground(Color.CYAN);
        extraPanel.setPreferredSize(new Dimension(20, 0));
        datePanel.add(extraPanel);

        RoundedButtonPanel dateButton = new RoundedButtonPanel("Add task");
        dateButton.setFont(new Font("Canva Sans", Font.PLAIN, 12));
        dateButton.setPreferredSize(new Dimension(80, 30));
        datePanel.add(dateButton);
        add(datePanel);

        JPanel bulletPanel = new JPanel();
        bulletPanel.setBackground(getContentPane().getBackground());
        bulletPanel.setBounds(440, 240, 320, 50);

        JLabel bulletLabel = new JLabel("<html><div style='text-align: left;'><table><tr><td style='vertical-align: top;'>&#8226;</td><td>Specify the activity name, task time, and date of the task</td></tr><tr><td></td><td>you wish to add.</td></tr></table></div></html>");
        bulletLabel.setForeground(Color.GRAY);
        bulletLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        bulletPanel.add(bulletLabel);
        add(bulletPanel);


        JPanel outputPanel = new JPanel();
        outputPanel.setBackground(new Color(209, 203, 188));
        outputPanel.setForeground(Color.WHITE);
        outputPanel.setBounds(425, 335, 530, 75);

        JPanel outputPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        outputPanel2.setBackground(new Color(209, 203, 188));
        outputPanel2.setForeground(Color.WHITE);
        outputPanel2.setBounds(425, 335, 340, 75);

        HighlightPanel medPanel = new HighlightPanel();
        medPanel.setBounds(672, 340, 35, 28);

        HighlightPanel pivotPanel = new HighlightPanel();
        pivotPanel.setBounds(685, 373, 250, 28);


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

        setVisible(true);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new PanelKeith());
    }
}