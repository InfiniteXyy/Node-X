package com.homework.lym;

import com.homework.xyy.Node;
import com.homework.xyy.NodeGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import java.awt.geom.Point2D;
import java.util.ArrayList;

class MouseComponent extends JComponent{
    private static final String[] JAPAN_COLOR = {
            "#7B2A3B",
            "#ES7661",
            "#F8C58C",
            "#F8E7A2",
            "#86DD82"
    };
    private static final int SIDELENGTH = 40;
    private ArrayList<Ellipse2D.Double> nodes;
    private Ellipse2D.Double current;

    MouseComponent(){
        nodes = new ArrayList<>();
        current = null;
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
        setBackground(Color.white);
    }

    public void addCom(NodeGraph nodeGraph) {
        for (Node node : nodeGraph.getNodeList()) {
            Ellipse2D.Double ellipse2D = new Ellipse2D.Double(100+ node.getPosX()*70 - SIDELENGTH/2,60+node.getPosY()*60-SIDELENGTH/2,SIDELENGTH+12,SIDELENGTH);
            nodes.add(ellipse2D);
            System.out.println("Node" + node.getId() + "：" + "x=" + node.getPosX() + "  y=" +node.getPosY());
        }
        repaint();


    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Paint p = g2d.getPaint();
        g2d.setPaint(Color.decode(JAPAN_COLOR[4]));
        for (int i = 0; i < 10; i++) {

            g2d.fill(nodes.get(i));

        }
        g2d.setPaint(p);
        for(Ellipse2D.Double t : nodes){
            g2d.draw(t);
        }
    }

    //找到第一个含有p的square
    private Ellipse2D.Double find(Point2D p){
        for(Ellipse2D.Double t : nodes){
            if(t.contains(p))return t;
        }
        return null;
    }

    //吧square添加到collection中去
    public void add(Point2D p){
        double x = p.getX();
        double y = p.getY();

        current = new Ellipse2D.Double(x - SIDELENGTH/2,y-SIDELENGTH/2,SIDELENGTH+12,SIDELENGTH);
        nodes.add(current);
        repaint();
    }

    //从collection中移动一个Suqares
    private void remove(Ellipse2D.Double s){
        if(s == null)return;
        if(s == current)current = null;
        nodes.remove(s);
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
            else setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        public void mouseDragged(MouseEvent event){
            if(current != null){
                int x = event.getX();
                int y = event.getY();
                current.setFrame(x- SIDELENGTH/2,y-SIDELENGTH/2,SIDELENGTH+12,SIDELENGTH);
                repaint();
            }
        }
    }

}
