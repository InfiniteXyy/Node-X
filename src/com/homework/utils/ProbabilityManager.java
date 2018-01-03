package com.homework.utils;

import com.homework.core.NodeGraph;
import com.homework.front.ListDialog;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ProbabilityManager {
    private NodeGraph nodeGraph;
    public ProbabilityManager(NodeGraph nodeGraph) {
        this.nodeGraph = nodeGraph;
    }

    public String showDialog(int s) {
        StringBuilder outPut = new StringBuilder();
        Map<String, Double> map = nodeGraph.getNodeEdges(s);
        ListDialog dialog = new ListDialog("Please select an 'Edge' From this list: ", map);

        //设置确定按键的函数
        dialog.setOnChange(e1 -> {
            Object item = dialog.getSelectedItem();
            if (item == null) return;
            //获取选取值
            String data = item.toString();
            String[] nodeInAndOut = data.split("->");
            //获取输入值
            data = dialog.getInputValue();
            if (NodeGraph.isProbability(data)) {
                nodeGraph.setProbability(
                        Integer.parseInt(nodeInAndOut[0]),
                        Integer.parseInt(nodeInAndOut[1]),
                        Double.parseDouble(data)
                );
                outPut.append("设置P(").append(item).append(") = ").append(dialog.getInputValue()).append("\n");
                dialog.hide();
            } else {
                JOptionPane.showMessageDialog(null, "请输入正确的概率值！", "Error", JOptionPane.PLAIN_MESSAGE);
            }
        });
        dialog.show();
        return outPut.toString();
    }

    public Map<String, Double> getProbabilityMap() {
        Map<String, Double> map = new HashMap<>();
        if (nodeGraph == null) return null;
        for (int a : nodeGraph.getNodeIds()) {
            map.putAll(nodeGraph.getNodeEdges(a));
        }
        return map;
    }
}
