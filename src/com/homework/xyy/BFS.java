package com.homework.xyy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BFS {
    private List<Node> visited = null;

    void showBFS(NodeGraph nodeGraph, int id) {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        System.out.println("开始BFS");
        try {
            BFSearchNode(nodeGraph.getNode(id));
        } catch (Exception e) {
            System.out.println("无法继续BFS");
        }
    }

    private void BFSearchNode(Node node) {
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(node);
        while (!nodeQueue.isEmpty()) {
            Node curNode = nodeQueue.poll();
            if (!visited.contains(curNode)) {
                visited.add(curNode);
                System.out.println("节点：" + curNode.getId());
                for (int i = 0; i < curNode.getNodeEdgeList().size(); i++) {
                    nodeQueue.offer(curNode.getNodeEdgeList().get(i).getNodeRight());
                }
            }
        }
    }

}
