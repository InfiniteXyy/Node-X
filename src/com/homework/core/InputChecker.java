package com.homework.core;


import java.util.regex.Pattern;

//根据输入的data信息，增加点、边，查询概率。
class InputChecker {
    private NodeGraph nodeGraph;
    private StringBuilder outPut;

    public static final int NODE_AND_EDGE = 1;     //匹配新加入节点
    public static final int PROBABILITY_CHECK = 0; //匹配概率检查请求

    private final int UNKNOWN_FIRST = 1;
    private final int UNKNOWN_SECOND = 0;

    InputChecker(NodeGraph nodeGraph) {
        this.nodeGraph = nodeGraph;
        outPut = new StringBuilder();
    }

    public void readData(String requests, int type) {
        switch (type) {
            case NODE_AND_EDGE:
                appendItems(requests);
                break;
            case PROBABILITY_CHECK:
                String[] data = requests.split("\n");
                for (String single : data) {
                    checkProbabilities(single);
                }
                break;
        }
    }

    private void appendItems(String requests) {
        try {
            String[] data = requests.split(",");
            for (String single : data) {
                if (Pattern.matches("^\\d+$", single)) {
                    int nodeId = Integer.parseInt(single);
                    nodeAppend(nodeId);
                } else if (Pattern.matches("^\\d+:\\d+(:0.\\d*)?$", single)) {
                    String[] nodes = single.split(":");
                    int a = Integer.parseInt(nodes[0]);
                    int b = Integer.parseInt(nodes[1]);
                    double probability = nodes.length==2 ? -1 : Double.parseDouble(nodes[2]);
                    edgeAppend(a, b, probability);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void nodeAppend(int nodeId) {
        String info = nodeGraph.addNode(nodeId);
        outPut.append(info);
    }

    private void edgeAppend(int nodeA, int nodeB, double probability) {
        String info;
        if (probability < 0 || !nodeGraph.probabilityValidate(probability, nodeA, nodeB)) {
            info = nodeGraph.addEdge(nodeA, nodeB);
        } else {
            info = nodeGraph.addEdge(nodeA, nodeB, probability);
        }
        outPut.append(info);
    }

    private void checkProbabilities(String single) {
        if (Pattern.matches("^\\d+>\\d+$", single)) {
            //例如1>2
            String[] data =single.split(">");
            int nodeA = Integer.parseInt(data[0]);
            int nodeB = Integer.parseInt(data[1]);
            outPut.append(nodeGraph.showRoute(nodeA, nodeB));
        } else if (Pattern.matches("^\\?>\\d+$", single)) {
            //例如?->2输出全部到2的节点的概率
            routesCheck(single, UNKNOWN_FIRST);
        } else if (Pattern.matches("^\\d+>\\?$", single)) {
            //例如2->?输出2到全部的节点的概率
            routesCheck(single, UNKNOWN_SECOND);
        } else {
            outPut.append("请输入正确的路径信息\n");
        }
    }

    private void routesCheck(String single, int type) {
        String[] data =single.split(">");
        int thisId = type == UNKNOWN_FIRST ? Integer.parseInt(data[1]) : Integer.parseInt(data[0]);

        if (!nodeGraph.findNode(thisId)){
            outPut.append("没有找到节点").append(thisId).append("\n");
        } else {
            int[] NodeIds = nodeGraph.getNodeIds();
            for (int i : NodeIds) {
                if (i != thisId) {
                    if (UNKNOWN_FIRST == type) {
                        outPut.append(nodeGraph.showRoute(i, thisId));
                    } else if (UNKNOWN_SECOND == type){
                        outPut.append(nodeGraph.showRoute(thisId, i));
                    }
                }
            }
        }
    }

    StringBuilder getOutPut() {
        return outPut;
    }
}
