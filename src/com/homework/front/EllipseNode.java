package com.homework.front;

import com.homework.core.Node;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

class EllipseNode extends Ellipse2D.Double{
    public static final double WIDTH = 52;
    public static final double HEIGHT = 32.24;

    double deltaX, deltaY;

    private Node node;

    private EllipseNode(Node node) {
        super(getPosX(node), getPosY(node), WIDTH, HEIGHT);
        this.node = node;
    }

    int getNodeId() {
        return node.getId();
    }

    void updatePos(Point mouse) {
        setFrame(mouse.x-deltaX, mouse.y-deltaY, WIDTH, HEIGHT);
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


    Point2D getCenter() {
        return new Point2D.Double(getX()+ WIDTH/2, getY()+HEIGHT/2);
    }
    //静态工厂方法
    static EllipseNode FromNode(Node node) {
        return new EllipseNode(node);
    }

}
