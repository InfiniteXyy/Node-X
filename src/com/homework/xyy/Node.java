package com.homework.xyy;

import java.util.ArrayList;
import java.util.List;


public class Node {
    //用来标注节点的唯一标识
    private int id;
    //用来标注从该节点出发的所有边的信息
    private List<NodeEdge> nodeEdgeList = null;

    private int posX;
    private int posY;

    Node (int id) {
        if (nodeEdgeList == null) {
            nodeEdgeList = new ArrayList<>();
        }
        this.id = id;
    }

    boolean addNodeEdge(NodeEdge nodeEdge) {
        for (NodeEdge x : nodeEdgeList) {
            if (x.getNodeLeft() == nodeEdge.getNodeLeft() &&
                    x.getNodeRight() == nodeEdge.getNodeRight()) {
                //若已经存在这条边，则返回false
                return false;
            }
        }
        //增加边的时候，自动生成合适的概率。
        //默认加入的边是不带概率的
        nodeEdgeList.add(nodeEdge);
        autoChangeProbability();
        return true;
    }

    boolean setProbability(int b, double probability) {
        NodeEdge nodeEdge = getNodeEdge(b);
        if (nodeEdge != null) {
            nodeEdge.setProbability(probability, false);
            autoChangeProbability();
            return true;
        }
        return false;
    }

    private void autoChangeProbability() {
        int nodesWithDefaultProbability = 0;
        double allProbability = 1.0;
        for (NodeEdge e : nodeEdgeList) {
            if (e.hasSetProbability()) {
                allProbability -= e.getProbability();
            } else {
                nodesWithDefaultProbability++;
            }
        }
        double averageProbability = allProbability/nodesWithDefaultProbability;
        for (NodeEdge e : nodeEdgeList) {
            if (!e.hasSetProbability()) {
                e.setProbability(averageProbability, true);
            }
        }
    }

    NodeEdge getNodeEdge(int id) {
        for (NodeEdge e : nodeEdgeList) {
            if (e.getNodeRight().getId() == id) {
                return e;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    void setPosX(int posX) {
        this.posX = posX;
    }

    void setPosY(int posY) {
        this.posY = posY;
    }

    List<NodeEdge> getNodeEdgeList() {
        return nodeEdgeList;
    }
}
