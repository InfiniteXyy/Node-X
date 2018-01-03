package com.homework.front;

import com.homework.core.Node;

import java.awt.geom.Ellipse2D;

class EllipseNode extends Ellipse2D.Double{
    public static final double WIDTH = 52;
    public static final double HEIGHT = 32.24;

    private Node node;

    private EllipseNode(Node node) {
        super(getPosX(node), getPosY(node), WIDTH, HEIGHT);
        this.node = node;
    }

    int getNodeId() {
        return node.getId();
    }

    void updatePos(int x, int y, double deltaX, double deltaY) {
        setFrame(x-deltaX, y-deltaY, WIDTH, HEIGHT);
    }

    void gridPosUpdate() {
        setFrame(getPosX(node), getPosY(node), WIDTH, HEIGHT);
    }

    static private double getPosX(Node node) {
        return 80+ node.getPosX()*90;
    }

    static private double getPosY(Node node) {
        return 30 + node.getPosY()*80;
    }

    int getDepth() {
        return node.getPosY();
    }

    Node getNode(){ return this.node; }

    void move(double x, double y) {
        setFrame(x+this.getCenterX(), y+this.getCenterY(), WIDTH, HEIGHT);
    }

    //静态工厂方法
    static EllipseNode FromNode(Node node) {
        return new EllipseNode(node);
    }

}
