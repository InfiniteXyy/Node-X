package com.homework.lym;

import com.homework.xyy.Node;
import com.homework.xyy.NodeGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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
    private ArrayList<EllipseNode> nodes;
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
            EllipseNode ellipseNode = new EllipseNode(ellipse2D,node.getId());
            nodes.add(ellipseNode);
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

            g2d.fill(nodes.get(i).getG2());

        }
        g2d.setPaint(p);
        for(EllipseNode t : nodes){
            g2d.draw(t.getG2());
            String drawString = new String("Node"+String.valueOf(t.getNodeid()));

            //测量消息大小
            FontRenderContext context = g2d.getFontRenderContext();
            Rectangle2D bounds = g2d.getFont().getStringBounds(drawString, context);

            //set(x,y)
            double x = (float)t.getG2().getX()+(SIDELENGTH+12 - bounds.getWidth()) / 2;
            double y = (float)t.getG2().getY()+(SIDELENGTH- bounds.getHeight()) / 2;

            g2d.drawString(drawString,(float)x,(float)y);
        }
    }

    //找到第一个含有p的square
    private Ellipse2D.Double find(Point2D p){
        for(EllipseNode t : nodes){
            if(t.getG2().contains(p))return t.getG2();
        }
        return null;
    }

    /*//吧square添加到collection中去
    public void add(Point2D p){
        double x = p.getX();
        double y = p.getY();

        current = new Ellipse2D.Double(x - SIDELENGTH/2,y-SIDELENGTH/2,SIDELENGTH+12,SIDELENGTH);
        nodes.add(current);
        repaint();
    }*/

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
            if(current == null)/*add(event.getPoint())*/;
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
