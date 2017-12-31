package com.homework.front;

import com.homework.core.Node;
import com.homework.core.NodeGraph;
import com.homework.utils.ProbabilityManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

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
    private ProbabilityManager manager;
    private Map<String, Double> probabilityMap;

    private JPopupMenu menu;
    MouseComponent(NodeGraph graph){
        manager = new ProbabilityManager(graph);
        nodes = new ArrayList<>();
        current = null;
        this.nodeGraph = graph;
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
        initMouseMenu();
        probabilityMap = manager.getProbabilityMap();
    }

    void init() {
        nodes.clear();
        repaint();
    }

    void initMouseMenu() {
        menu = new JPopupMenu();
        JMenuItem info = new JMenuItem("查看");
        JMenuItem delete = new JMenuItem("删除");
        menu.add(info);
        menu.add(delete);
        info.addActionListener((e -> {
            manager.showDialog(current.getNodeId());
            updateCom();
        }));

        delete.addActionListener(e -> {
            nodeGraph.deleteNode(current.getNodeId());
            updateCom();
        });
    }
    //考虑撤销的问题
    void updateCom() {
        probabilityMap = manager.getProbabilityMap();
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

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        //画线
        if(nodes.size()>=2){
            for(EllipseNode thisNode: nodes){
                Point2D outPoint = new Point2D.Double(thisNode.getCenterX(), thisNode.getCenterY());
                ArrayList<Node> list = nodeGraph.getInEdgesNode(thisNode.getNode());
                //设置线条粗细
                g2d.setStroke(new BasicStroke(2.5f));
                g2d.setColor(LINECOLOR);

                for(Node inNode : list){
                    EllipseNode ellipseNode = getEllipseNodeByNodeID(inNode.getId());
                    Point2D inPoint = new Point2D.Double(ellipseNode.getCenterX(), ellipseNode.getCenterY());

                    //判断斜率是否为-1~1
                    double k = (outPoint.getY()-inPoint.getY()) / (outPoint.getX()-inPoint.getX());
                    if (k >= -2 && k <= 2) {
                        if (outPoint.getX() > inPoint.getX()) {
                            drawAL((int)inPoint.getX()+26,(int)inPoint.getY(),(int)outPoint.getX()-26,(int)outPoint.getY(),String.format("%.2f", probabilityMap.get(inNode.getId()+"->"+thisNode.getNodeId())),g2d);
                        } else {
                            drawAL((int)inPoint.getX()-26,(int)inPoint.getY(),(int)outPoint.getX()+26,(int)outPoint.getY(),String.format("%.2f", probabilityMap.get(inNode.getId()+"->"+thisNode.getNodeId())),g2d);
                        }
                    } else {
                        if (outPoint.getY() > inPoint.getY()) {
                            drawAL((int)inPoint.getX(),(int)inPoint.getY()+14,(int)outPoint.getX(),(int)outPoint.getY()-19,String.format("%.2f", probabilityMap.get(inNode.getId()+"->"+thisNode.getNodeId())),g2d);
                        } else {
                            drawAL((int)inPoint.getX(),(int)inPoint.getY()-19,(int)outPoint.getX(),(int)outPoint.getY()+14,String.format("%.2f", probabilityMap.get(inNode.getId()+"->"+thisNode.getNodeId())),g2d);
                        }
                    }

                }
            }
        }
        //画椭圆
        for (EllipseNode t : nodes) {
            g2d.setColor(Color.decode(JAPAN_COLOR[t.getDepth()%5]));
            g2d.fill(t);
            if (t == current) {
                g2d.setColor(Color.gray);
                g2d.draw(current);
            }
        }
        g2d.setColor(Color.white);

       //画字
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
    }

    private EllipseNode getEllipseNodeByNodeID(int id) {
        for (EllipseNode node : nodes) {
            if (node.getNodeId() == id)
                return node;
        }
        return null;
    }
    private static void drawAL(int sx, int sy, int ex, int ey, String p, Graphics2D g2)
    {
        double H = 8; // 箭头高度
        double L = 5; // 底边的一半

        double awrad = Math.atan(L / H); // 箭头角度
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];
        // 画线
        double proportion = 1-7/Math.sqrt((ex-sx)*(ex-sx) + (ey-sy)*(ey-sy));
        g2.drawLine(sx, sy, (int)(proportion*ex+(1-proportion)*sx), (int)(proportion*ey+(1-proportion)*sy));
        g2.drawString(p,(sx+ex)/2,(sy+ey)/2);
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo((int)x_3, (int)y_3);
        triangle.lineTo((int)x_4, (int)y_4);
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
        public void mousePressed(MouseEvent event) {
            current = find(event.getPoint());
            repaint();
            if (event.getButton() == MouseEvent.BUTTON3 && current != null) {
                menu.show(MouseComponent.this, event.getX(), event.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //若移动到线上，显示概率
            super.mouseMoved(e);
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
