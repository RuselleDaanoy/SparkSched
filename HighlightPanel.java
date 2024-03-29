package com.example;

import java.awt.*;
import javax.swing.*;

public class HighlightPanel extends JPanel {
    private static final Color fixedColor = new Color(141, 131, 116);
    private static final int curveValue = 5;

    public HighlightPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(curveValue, curveValue);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(fixedColor);
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);

        //IF EVER PREFER NIYO YUNG MAY BORDER SIYA, KUNIN NIYO LANG TO
        //graphics.setColor(getForeground());
        //graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }
}

