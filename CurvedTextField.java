package com.example;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CurvedTextField extends JTextField {
    private static final int curvedEdge = 5; 

    public CurvedTextField() {
        super();
        setOpaque(false);
        setBorder(new EmptyBorder(0, 10, 0, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, curvedEdge, curvedEdge);

        super.paintComponent(g);

        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, curvedEdge, curvedEdge);

        g2d.dispose();
    }
}
