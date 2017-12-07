package com.homework.xyy;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PositionChecker {
    private NodeGraph nodeGraph;
    private List<Node> visited;
    private List<Node> hasSetDepth;

    public PositionChecker(NodeGraph nodeGraph) {
        this.nodeGraph = nodeGraph;
        visited = new ArrayList<>();
        hasSetDepth = new ArrayList<>();
    }

    public void updateNodePosition(int id) {
        Node node = nodeGraph.getNode(id);

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(node);
        node.setPosX(0);
        node.setPosY(0);

        int maxDepth = 0;
        if (node.getNodeEdgeList()==null) return;
        while (!nodeQueue.isEmpty()) {
            Node curNode = nodeQueue.poll();
            if (!visited.contains(curNode)) {
                visited.add(curNode);
                for (int i = 0; i < curNode.getNodeEdgeList().size(); i++) {
                    Node nodeTemp = curNode.getNodeEdgeList().get(i).getNodeRight();
                    nodeQueue.offer(nodeTemp);
                    if (!hasSetDepth.contains(nodeTemp)) {
                        nodeTemp.setPosY(curNode.getPosY()+1);
                        hasSetDepth.add(nodeTemp);
                    }
                    maxDepth = nodeTemp.getPosY();
                }
            }
        }
        int[] pos = new int[20];
        for (int i = 0; i < 20; i++) {
            pos[i] = 0;
        }

        //一共是j层，每一行的最大宽度是maxi
        int i = 1;
        for (Node node1 : nodeGraph.getNodeList()) {
            if (!visited.contains(node1)) {
                i++;
                node1.setPosY(0);
                node1.setPosX(i);
            } else {
                node1.setPosX(pos[node1.getPosY()]++);
            }

        }
        //把没有被遍历到的节点放在第一层。
        //把每一层的节点从左往右排列

    }


}

