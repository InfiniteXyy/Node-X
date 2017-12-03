package com.homework.lym;

import com.homework.xyy.NodeGraph;

import javax.swing.*;


class NewJPanel extends JPanel{
    NodeGraph nodeGraph;
    JTextArea textShow;
    JTextArea textEdit;
    JButton addBtn;
    NewJPanel() {
        nodeGraph = new NodeGraph();

    }
    void showNodeId(int nodeId) {
        String info = nodeGraph.addNode(nodeId);
        textShow.append("info\n");
    }
}
