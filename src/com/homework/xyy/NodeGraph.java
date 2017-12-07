package com.homework.xyy;


import java.util.ArrayList;
import java.util.List;

public class NodeGraph {
    private List<Node> nodeList = null;

    public NodeGraph() {
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
    }

    //根据输入的Add语句，返回输出值
    public String addNodeAndEdges(String requests) {
        InputChecker inputChecker = new InputChecker(this);
        inputChecker.readData(requests, InputChecker.NODE_AND_EDGE);
        StringBuilder outPut = inputChecker.getOutPut();
        return outPut.toString();
    }

    //根据输入的Check语句，返回输出值
    public String getEdgeProbability(String requests) {
        InputChecker inputChecker = new InputChecker(this);
        inputChecker.readData(requests, InputChecker.PROBABILITY_CHECK);
        StringBuilder outPut = inputChecker.getOutPut();
        return outPut.toString();
    }

    //返回每一个Node的 id 和 位置的对应关系
    public void getNodePosition() {

    }

    //内置了图的样式，可以快速设置图
    public String graphDemo() {
        String demoRequest = "0,1,2,3,4,5,6,7,8,9,0:1,0:2,1:3,1:4,2:5,2:6,3:7,4:7,5:6";

        StringBuilder out = new StringBuilder();
        addNodeAndEdges(demoRequest, out);

        return out.toString();
        /*
        节点路径关系如下
        0-1 0-2 1-3 1-4 2-5 2-6 3-7 4-7 5-6
        */

    }

    public String showBFS(int id) {
        BFS bfs = new BFS();
        StringBuilder stringBuilder = new StringBuilder();
        bfs.showBFS(this, id, stringBuilder);
        return stringBuilder.toString();
    }

    public String showDFS(int id) {
        DFS bfs = new DFS();
        StringBuilder stringBuilder = new StringBuilder();
        bfs.showDFS(this, id, stringBuilder);
        return stringBuilder.toString();
    }

    String addNode(int id) {
        if (getNode(id) != null) {
            return("节点："+id+"已经存在\n");
        } else {
            Node node = new Node(id);
            nodeList.add(node);
            return("节点："+id+"添加成功\n");
        }
    }

    String addEdge(int in, int out) {
        Node nodeIn, nodeOut;

        //遍历图，找到对应id的node
        nodeIn = getNode(in);
        nodeOut = getNode(out);

        if (nodeIn == null) {
            return ("添加失败，没有找到节点："+in);
        } else if (nodeOut == null) {
            return ("添加失败，没有找到节点："+out);
        } else {
            NodeEdge nodeEdge = new NodeEdge(nodeIn, nodeOut);
            if (nodeIn.addNodeEdge(nodeEdge)) {
                return (in +"->"+out+": 增加边成功");
            } else {
                return (in +"->"+out+": 增加边失败（边已存在）");
            }
        }

    }

    String addEdge(int in, int out, double probability) {
        Node nodeIn, nodeOut;

        //遍历图，找到对应id的node
        nodeIn = getNode(in);
        nodeOut = getNode(out);

        if (nodeIn == null) {
            return ("添加失败，没有找到节点："+in);
        } else if (nodeOut == null) {
            return ("添加失败，没有找到节点："+out);
        } else {
            NodeEdge nodeEdge = new NodeEdge(nodeIn, nodeOut);
            nodeEdge.setProbability(probability, false);
            if (nodeIn.addNodeEdge(nodeEdge)) {
                return (in +"->"+out+": 增加边成功，概率为"+probability);
            } else {
                return (in +"->"+out+": 增加边失败（边已存在）");
            }
        }

    }

    public int[] getNodeIds() {
        int[] data = new int[nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            data[i] = nodeList.get(i).getId();
        }
        return data;
    }

    //重载，带概率的增加边


    public double getProbability(int a, int b) {
        NodeEdge nodeEdge = getEdge(a,b);
        if (nodeEdge == null) {
            return 0;
        } else {
            return nodeEdge.getProbability();
        }
    }

    public boolean setProbability(int a, int b, double probability) {
        NodeEdge nodeEdge = getEdge(a,b);
        if (nodeEdge == null) {
            return false;
        } else {
            nodeEdge.getNodeLeft().setProbability(b, probability);
            return true;
        }
    }

    public boolean findNode(int id) {
        Node node = getNode(id);
        return node != null;
    }

    private NodeEdge getEdge(int a, int b) {
        Node nodeA = getNode(a);
        if (nodeA == null) return null;
        else {
            return nodeA.getNodeEdge(b);
        }
    }

    Node getNode(int id) {
        for (Node i : nodeList) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    String showRoute(int in, int out) {
        StringBuilder output = new StringBuilder("从"+in+"到"+out+":\n");

        Node nodeIn, nodeOut;
        nodeIn = getNode(in);
        nodeOut = getNode(out);

        if (nodeIn != null && nodeOut != null) {
            PathFinder temp = new PathFinder(nodeIn, nodeOut);
            int i = 1;
            double allProbability = 0;
            for (PathFinder.NodePath paths : temp.getNodePaths()) {
                double singleProbability = 1.0;
                for (NodeEdge e : paths.path) {
                    singleProbability *= e.showEdge(output);
                }
                output.append("概率为:"+singleProbability+"\n");
                i++;
                allProbability += singleProbability;
            }
            output.append("一共"+(i-1)+"种可能，总的概率是:"+allProbability+"\n");
            if (allProbability == 0) {
                return "";
            }
        }
        output.append("--------------------------");

        return output.toString();
    }
}
