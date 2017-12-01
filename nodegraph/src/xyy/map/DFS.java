package xyy.map;

import java.util.ArrayList;
import java.util.List;

class DFS {
    private List<Node> visited = null;

    void showDFS(NodeGraph nodeGraph, int id) {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        System.out.println("开始DFS");
        try {
            DFSearchNode(nodeGraph.getNode(id));
        } catch (Exception e) {
            System.out.println("无法继续DFS");
        }
    }

    private void DFSearchNode(Node node) {
        if (!visited.contains(node)) {
            visited.add(node);
            System.out.println("节点：" + node.getId());
            for (int i = 0; i < node.getNodeEdgeList().size(); i++) {
                DFSearchNode(node.getNodeEdgeList().get(i).getNodeRight());
            }
        }
    }
}
