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

        JLabel label = new JLabel("SPARK SCHED", SwingConstants.CENTER);
        label.setFont(new Font("SERIF", Font.PLAIN, 55)); 
        label.setForeground(new Color(54, 54, 54)); 

        //SINCE HINDI SYMMETRICAL YUNG DESIGN NATIN, GAMIT TAYO GRIDBAG PARA SA MORE CONTROL NG COMPONENTS

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(0, -80, 0, 0);
        mainPanel.add(label, gbc);

        JPanel underlinePanel = new JPanel();
        underlinePanel.setBackground(new Color(54, 54, 54)); 
        int underlineWidth = 280; 
        underlinePanel.setPreferredSize(new Dimension(underlineWidth, 9)); 

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 60, 0, 0);
        mainPanel.add(underlinePanel, gbc);
        
        JLabel textLabel = new JLabel("<html>Turning plans into the spark<br>of success.</html>");
        textLabel.setFont(new Font("Canva Sans", Font.PLAIN, 16)); 
        textLabel.setForeground(new Color(54, 54, 54)); 

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(17, 35, 0, 0); 
        mainPanel.add(textLabel, gbc);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(frame.getBackground());

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
        gbc.insets = new Insets(65, 50, 0, 0);
        mainPanel.add(button, gbc);

        frame.getContentPane().add(mainPanel);

        frame.setVisible(true);
    }
}
