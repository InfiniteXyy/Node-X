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

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

class MouseComponent extends JComponent{
    private static final String[] JAPAN_COLOR = {
            "#7B2A3B",
            "#EA7661",
            "#F8C58C",
            "#F8E7A2",
            "#86DD82"
    };
    private static final int SIDELENGTH = 40;
    private static final Color LINECOLOR = new Color(131,175,155);
    private ArrayList<EllipseNode> nodes;
    private EllipseNode current;
    private NodeGraph nodeGraph;
    private GeneralPath path = new GeneralPath();
    MouseComponent(NodeGraph graph){
        nodes = new ArrayList<>();
        current = null;
        this.nodeGraph = graph;
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    void init() {
        nodes.clear();
        repaint();
    }

    //考虑撤销的问题
    void updateCom(NodeGraph nodeGraph) {
        nodes.clear();
        for (Node node : nodeGraph.getNodeList()) {
            EllipseNode ellipseNode = EllipseNode.FromNode(node);
            nodes.add(ellipseNode);
        }
        for (EllipseNode ellipseNode : nodes) {
            ellipseNode.gridPosUpdate();
        }
        repaint();
    }
//
//    private boolean isContainNode(Node node, ArrayList<EllipseNode> nodes) {
//        for (EllipseNode node1 : nodes) {
//            if (node1.getNodeId() == node.getId())
//                return true;
//        }
//        return false;
//    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        for (EllipseNode t : nodes) {
            g2d.setColor(Color.decode(JAPAN_COLOR[t.getDepth()%5]));
            g2d.fill(t);
        }
        g2d.setColor(Color.white);
        for(EllipseNode t : nodes){
            String drawString = String.valueOf(t.getNodeId());

            //测量消息大小
            FontRenderContext context = g2d.getFontRenderContext();
            Rectangle2D bounds = g2d.getFont().getStringBounds(drawString, context);

            //set(x,y)
            double x = (float)t.getX()+(SIDELENGTH+12 - bounds.getWidth()) / 2;
            double y = (float)t.getY()+(SIDELENGTH) / 2;

            g2d.drawString(drawString,(float)x,(float)y);
        }
        Point sx;
        Point ex;
        if(nodes.size()>=2){
            for(EllipseNode tp: nodes){
                sx = tp.getPoint();
                ArrayList<Node> list = new ArrayList();
                list = nodeGraph.getInEdgesNode(tp.getNode());
                for(Node tnode : list){
                    ex = nodes.get(tnode.getId()).getPoint();
                    g2d.setColor(LINECOLOR);
                    drawAL((int)ex.getX()+26,(int)ex.getY()+16,(int)sx.getX()+26,(int)sx.getY()+16,g2d);
                }
            }
        }
    }
    public static void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2)
    {

        double H = 10; // 箭头高度
        double L = 4; // 底边的一半
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // 箭头角度
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];

        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        // 画线
        g2.drawLine(sx, sy, ex, ey);
        //
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.closePath();
        //实心箭头
        g2.fill(triangle);
        //非实心箭头
        //g2.draw(triangle);

    }

    // 计算
    static double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen) {

        double mathstr[] = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
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
