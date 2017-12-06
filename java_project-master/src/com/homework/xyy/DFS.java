package com.homework.xyy;

import java.util.ArrayList;
import java.util.List;

class DFS {
    private List<Node> visited = null;

    void showDFS(NodeGraph nodeGraph, int id, StringBuilder out) {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        out.append("开始DFS\n");
        try {
            DFSearchNode(nodeGraph.getNode(id), out);
        } catch (Exception e) {
            out.append("无法继续DFS");
        }
    }

    private void DFSearchNode(Node node, StringBuilder out) {
        if (node.getNodeEdgeList()==null) return;
        if (!visited.contains(node)) {
            visited.add(node);
            out.append("节点：" + node.getId() + "\n");
            for (int i = 0; i < node.getNodeEdgeList().size(); i++) {
                DFSearchNode(node.getNodeEdgeList().get(i).getNodeRight(), out);
            }
        }
    }
}
