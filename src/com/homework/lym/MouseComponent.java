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
    private EllipseNode current;

    MouseComponent(){
        nodes = new ArrayList<>();
        current = null;
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
        setBackground(Color.white);
    }

    public void addCom(NodeGraph nodeGraph) {
        for (Node node : nodeGraph.getNodeList()) {
            if (!isContainNode(node, nodes)) {
                EllipseNode ellipseNode = new EllipseNode(node);
                nodes.add(ellipseNode);
            }
        }

        for (EllipseNode ellipseNode : nodes) {
            ellipseNode.gridPosUpdate();
        }

        repaint();
    }
    private boolean isContainNode(Node node, ArrayList<EllipseNode> nodes) {
        for (EllipseNode node1 : nodes) {
            if (node1.getNodeId() == node.getId())
                return true;
        }
        return false;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Paint p = g2d.getPaint();
        g2d.setPaint(Color.decode(JAPAN_COLOR[4]));

        for (EllipseNode t : nodes) {
            g2d.fill(t);
        }
        g2d.setPaint(p);

        for(EllipseNode t : nodes){

            g2d.draw(t);
            String drawString = String.valueOf(t.getNodeId());

            //测量消息大小
            FontRenderContext context = g2d.getFontRenderContext();
            Rectangle2D bounds = g2d.getFont().getStringBounds(drawString, context);

            //set(x,y)
            double x = (float)t.getX()+(SIDELENGTH+12 - bounds.getWidth()) / 2;
            double y = (float)t.getY()+(SIDELENGTH) / 2;

            g2d.drawString(drawString,(float)x,(float)y);
        }

    }

    //找到第一个含有p的square
    private EllipseNode find(Point2D p){
        for(EllipseNode t : nodes){
            if(t.contains(p))return t;
        }
        return null;
    }

    //从collection中移动一个Node
    private void remove(Ellipse2D.Double s){
        if(s == null)return;
        if(s == current)current = null;
        nodes.remove(s);
        repaint();
    }

    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent event){
            current = find(event.getPoint());
        }

        public void mouseClicked(MouseEvent event){
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
                current.updatePos(x, y);
                repaint();
            }
        }
    }

}
