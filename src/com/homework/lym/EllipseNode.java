package com.homework.lym;

import com.homework.xyy.Node;

import java.awt.*;
import java.awt.geom.Ellipse2D;

class EllipseNode extends Ellipse2D.Double{
    private int nodeId;
    EllipseNode(Node node) {
        super(80+ node.getPosX()*70,30+node.getPosY()*60,52,33);
        nodeId = node.getId();
    }

    public int getNodeId() {
        return nodeId;
    }
}
