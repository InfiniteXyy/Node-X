package com.homework.lym;

import com.homework.xyy.Node;

import java.awt.*;
import java.awt.geom.Ellipse2D;

class EllipseNode extends Ellipse2D.Double{
    private static final double WIDTH = 52;
    private static final double HEIGHT = 32.24;

    private Node node;

    EllipseNode(Node node) {
        super(getPosX(node), getPosY(node), WIDTH, HEIGHT);
        this.node = node;
    }

    int getNodeId() {
        return node.getId();
    }

    void updatePos(int x, int y) {
        setFrame(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
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

}
