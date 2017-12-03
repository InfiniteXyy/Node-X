package com.homework.xyy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//这个类的作用是根据输入的data信息，转成可以被识别的路径信息。
public class ProbabilityListChecker {
    private String txt;
    private List<IdEdge> requestList;
    private NodeGraph nodeGraph;

    public ProbabilityListChecker(String txt, NodeGraph nodeGraph) {
        this.txt = txt;
        requestList = new ArrayList<IdEdge>();
        this.nodeGraph = nodeGraph;
        readData();
    }

    private void readData() {
        String[] data = txt.split("\n");
        for (String single : data) {
            checkRequest(single);
        }
    }

    private void checkRequest(String single) {
        if (Pattern.matches("^\\d+>\\d+$", single)) {
            //例如1->2
            String[] data =single.split(">");
            IdEdge upload = new IdEdge(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
            requestList.add(upload);
        } else if (Pattern.matches("^\\?>\\d+$", single)) {
            //例如?->2输出全部到2的节点的概率
            String[] data =single.split(">");
            int thisId = Integer.parseInt(data[1]);
            if (!nodeGraph.findNode(thisId)) return;
            int[] NodeIds = nodeGraph.getNodeIds();

            for (int i : NodeIds) {
                if (i != thisId) {
                    IdEdge upload = new IdEdge(i, thisId);
                    requestList.add(upload);
                }
            }
        } else if (Pattern.matches("^\\d+>\\?$", single)) {
            //例如2->?输出2到全部的节点的概率
            String[] data =single.split(">");
            int thisId = Integer.parseInt(data[0]);
            if (!nodeGraph.findNode(thisId)) return;
            int[] NodeIds = nodeGraph.getNodeIds();

            for (int i : NodeIds) {
                if (i != thisId) {
                    IdEdge upload = new IdEdge(thisId, i);
                    requestList.add(upload);
                }
            }
        } else {
            requestList.add(null);
        }
    }

    public List<IdEdge> getRequestList() {
        return requestList;
    }
}