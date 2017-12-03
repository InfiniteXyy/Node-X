package com.homework.lym;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

class MyJComponent extends JComponent {
    private String title;

    MyJComponent(String name) {
        this.title = name;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("console", Font.PLAIN, 14);
        g2.setFont(font);

        //测量消息大小
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(title, context);

        //set(x,y)
        double x = (getWidth() - bounds.getWidth()) / 2;
        double y = (getHeight() - bounds.getHeight()) / 2;

        //add ascent to y to reach the baseline
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        //draw the baseline
        g2.drawString(title, (int) x, (int) baseY);
    }
}