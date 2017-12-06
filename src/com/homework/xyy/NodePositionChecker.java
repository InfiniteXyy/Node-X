package com.homework.xyy;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NodePositionChecker {
    private NodeGraph nodeGraph;
    private int maxHeight, maxWidth, r;
    private List<Node> visited;

    public NodePositionChecker(NodeGraph nodeGraph, int maxHeight, int maxWidth, int r) {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        this.nodeGraph = nodeGraph;
        this.r = r;
        visited = new ArrayList<>();
    }

    public void updateNodePosition(int id) {
        Node node = nodeGraph.getNode(id);

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(node);
        node.pos = 0;
        node.depth = 0;
        int maxDepth = 0;
        if (node.getNodeEdgeList()==null) return;
        while (!nodeQueue.isEmpty()) {
            Node curNode = nodeQueue.poll();
            if (!visited.contains(curNode)) {
                visited.add(curNode);
                for (int i = 0; i < curNode.getNodeEdgeList().size(); i++) {
                    Node nodeTemp = curNode.getNodeEdgeList().get(i).getNodeRight();
                    nodeQueue.offer(nodeTemp);
                    if (!visited.contains(nodeTemp)) nodeTemp.depth = curNode.depth+1;
                    maxDepth = nodeTemp.depth;
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
                node1.depth=0;
                node1.pos=i;
            } else {
                node1.pos = pos[node1.depth]++;
            }

        }
        //把没有被遍历到的节点放在第一层。
        //把每一层的节点从左往右排列

    }


}

