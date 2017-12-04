package com.homework.xyy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BFS {
    private List<Node> visited = null;

    void showBFS(NodeGraph nodeGraph, int id, StringBuilder out) {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        out.append("开始BFS\n");
        try {
            BFSearchNode(nodeGraph.getNode(id), out);
        } catch (Exception e) {
            out.append("无法继续BFS\n");
        }
    }

    private void BFSearchNode(Node node, StringBuilder out) {
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(node);
        if (node.getNodeEdgeList()==null) return;
        while (!nodeQueue.isEmpty()) {
            Node curNode = nodeQueue.poll();
            if (!visited.contains(curNode)) {
                visited.add(curNode);
                out.append("节点：" + curNode.getId() + "\n");
                for (int i = 0; i < curNode.getNodeEdgeList().size(); i++) {
                    nodeQueue.offer(curNode.getNodeEdgeList().get(i).getNodeRight());
                }
            }
        }
    }

}
