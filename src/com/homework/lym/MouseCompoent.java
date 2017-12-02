package com.homework.lym;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MouseCompoent extends JComponent{
    private static final int SIDELENGTH = 30;
    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;
    public MouseCompoent(){
        squares = new ArrayList<>();
        current = null;
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        for(Rectangle2D t :squares){
            g2.draw(t);
        }
    }

    //找到第一个含有p的square
    public Rectangle2D find(Point2D p){
        for(Rectangle2D t :squares){
            if(t.contains(p))return t;
        }
        return null;
    }

    //吧square添加到collection中去
    public void add(Point2D p){
        double x = p.getX();
        double y = p.getY();

        current = new Rectangle2D.Double(x - SIDELENGTH/2,y-SIDELENGTH/2,SIDELENGTH,SIDELENGTH);
        squares.add(current);
        repaint();
    }

    //从collection中移动一个Suqares
    public void remove(Rectangle2D s){
        if(s == null)return;
        if(s == current)current = null;
        squares.remove(s);
        repaint();
    }

    //square containing the mouse cursor
    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent event){
            //add a new square if the cursor isn't inside a square
            current = find(event.getPoint());
            if(current == null)add(event.getPoint());
        }

        public void mouseClicked(MouseEvent event){
            //remove the current square if double clicked
            current = find(event.getPoint());
            if(current != null&&event.getClickCount()>=2)remove(current);
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {
        public void mouseMoved(MouseEvent event){
            if(find((event.getPoint()))==null)setCursor(Cursor.getDefaultCursor());
            else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }

        public void mouseDragged(MouseEvent event){
            if(current != null){
                int x = event.getX();
                int y = event.getY();

                current.setFrame(x- SIDELENGTH/2,y-SIDELENGTH/2,SIDELENGTH,SIDELENGTH);
                repaint();
            }
        }
    }
}