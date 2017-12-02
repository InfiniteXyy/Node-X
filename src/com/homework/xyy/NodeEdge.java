package com.homework.xyy;

class NodeEdge {
    private Node nodeLeft;
    private Node nodeRight;

    //nodeLeft = 进入节点
    //nodeRight = 指向节点

    private double probability;
    private boolean hasSetProbability;

    NodeEdge(Node nodeLeft, Node nodeRight) {
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
        hasSetProbability = false;
        //一开始都没有设置过概率
        //设置过概率的话就不会自动生成概率，否则会根据出发节点的情况，全部平均分配概率
        //关于平均分配的算法在NodeGraph类里
    }

    void setProbability(double probability, boolean isDefault) {
        this.probability = probability;
        this.hasSetProbability = !isDefault;
    }

    double getProbability() {
        return probability;
    }

    boolean hasSetProbability() {
        return hasSetProbability;
    }

    Node getNodeLeft() {
        return nodeLeft;
    }

    Node getNodeRight() {
        return nodeRight;
    }

    double showEdge() {
        if (nodeLeft == null) {
            System.out.println("从节点:"+nodeRight.getId());
            return 1;
        } else {
            System.out.println("到节点:" + nodeRight.getId() + "   概率为：" + probability);
            return probability;
        }
    }
}
