package com.example;

import javax.swing.*;
import java.awt.*;

public class LaunchPad {
    public static void main(String[] args) {
      
        JFrame frame = new JFrame("Spark Schedule");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE); 
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("SPARK SCHEDULE", SwingConstants.CENTER);
        label.setFont(new Font("MV Boli", Font.PLAIN, 40)); //di ko pa mahatak yung hey gotcha temporary lang
        label.setForeground(new Color(54, 54, 54));

        JPanel underlinePanel = new JPanel();
        underlinePanel.setBackground(new Color(54, 54, 54));

        JLabel textLabel = new JLabel("<html>turning plans in the spark<br>of success.</html>");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 
        textLabel.setForeground(new Color(54, 54, 54)); 

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(frame.getBackground());

        //SINCE HINDI SYMMETRICAL YUNG DESIGN NATIN, GAMIT TAYO GRIDBAG PARA SA MORE CONTROL NG COMPONENTS

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(20, 0, 20, 0); 
        mainPanel.add(label, gbc);

        underlinePanel.setPreferredSize(new Dimension(1, 2)); 
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(underlinePanel, gbc);

        gbc.gridx = 1; 
        gbc.insets = new Insets(20, 10, 20, 0);
        mainPanel.add(textLabel, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        mainPanel.add(emptyPanel, gbc);

        RoundedButtonPanel button = new RoundedButtonPanel("Create Schedule");
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 14)); 
        button.setForeground(new Color(54, 54, 54));
        button.setBackground(Color.WHITE); 
      
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        gbc.insets = new Insets(20, 10, 0, 0); 
        mainPanel.add(button, gbc);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
}
