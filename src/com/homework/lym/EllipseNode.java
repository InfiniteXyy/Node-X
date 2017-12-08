package com.homework.lym;

import java.awt.geom.Ellipse2D;

public class EllipseNode {
    private Ellipse2D.Double g2;
    private int nodeid;

    public int getNodeid() {
        return nodeid;
    }

    public Ellipse2D.Double getG2() {
        return g2;
    }
    public EllipseNode(Ellipse2D.Double g,int id){
        g2 = g;
        nodeid = id;
    }
}
