package com.homework.lym;

import com.homework.xyy.Node;

import java.awt.*;
import java.awt.geom.Ellipse2D;

class EllipseNode extends Ellipse2D.Double{
    private static final double WIDTH = 52;
    private static final double HEIGHT = 32.24;

    private Node node;
    private Point mypoint;

    private EllipseNode(Node node) {
        super(getPosX(node), getPosY(node), WIDTH, HEIGHT);
        mypoint = new Point();
        mypoint.setLocation(getPosX(node), getPosY(node));
        this.node = node;
    }

    int getNodeId() {
        return node.getId();
    }

    void updatePos(int x, int y) {
        setFrame(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
        mypoint.setLocation(x-WIDTH/2, y-HEIGHT/2);
    }

    void gridPosUpdate() {
        setFrame(getPosX(node), getPosY(node), WIDTH, HEIGHT);
    }

    static private double getPosX(Node node) {
        return 80+ node.getPosX()*70;
    }

    static private double getPosY(Node node) {
        return 30+node.getPosY()*60;
    }

    int getDepth() {
        return node.getPosY();
    }

    Node getNode(){ return this.node; }

    Point getPoint(){ return this.mypoint; }

    //静态工厂方法
    static EllipseNode FromNode(Node node) {
        return new EllipseNode(node);
    }
}
