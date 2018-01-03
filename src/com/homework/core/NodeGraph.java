package com.homework.core;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeGraph {
    public int root;
    //用来记录图的根节点（列表中的第几个元素）
    private List<Node> nodeList = null;

    public NodeGraph() {
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
        root = -1;
    }

    public static boolean isProbability(String data) {
        boolean result = true;
        try {
            Double parseDouble = Double.parseDouble(data);
            if (parseDouble > 1.0 || parseDouble <= 0) {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public void empty() {
        nodeList.clear();
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

    public boolean deleteNode(int i) {
        boolean result = false;
        Node node = getNode(i);
        if (node != null) {
            nodeList.remove(node);
            for (Node otherNode : nodeList) {
                otherNode.getNodeEdgeList().removeIf((x)->x.getNodeRight() == node);
            }
            result = true;
        }
        return  result;
    }
    //返回每一个Node的 id 和 位置的对应关系
    public List<Node> getNodeList() {
        return nodeList;
    }

    //内置了图的样式，可以快速设置图
    public String graphDemo(boolean onlyWord) {

        String demoRequest = "0,1,2,3,4,5,6,7,8,9,0:1,0:2,1:3,1:4,2:5,2:6,3:7,4:7,5:6";
        if (onlyWord)
            return demoRequest;

        return addNodeAndEdges(demoRequest);
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

    public int[] getNodeIds() {
        int[] data = new int[nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            data[i] = nodeList.get(i).getId();
        }
        return data;
    }

    public Map<String, Double> getNodeEdges(int a) {
        Node node = getNode(a);
        Map<String, Double> result = new HashMap<>();

        //找到所有的出发边
        List<NodeEdge> outEdges = node.getNodeEdgeList();
        //找到所有的进入边
        List<NodeEdge> inEdges = getInEdges(node);

        for (NodeEdge outEdge : outEdges) {
            result.put(a + "->" + outEdge.getNodeRight().getId(), outEdge.getProbability());
        }
        for (NodeEdge inEdge : inEdges) {
            result.put(inEdge.getNodeLeft().getId()+"->"+a, inEdge.getProbability());
        }
        return result;
    }


    private List<NodeEdge> getInEdges(Node node) {
        ArrayList<NodeEdge> inEdges = new ArrayList<>();
        //遍历每个节点的每个边，找到所有进入node的边
        for (Node thisNode : nodeList) {
            if (thisNode!=node) {
                for (NodeEdge edge : thisNode.getNodeEdgeList()) {
                    if (edge.getNodeRight() == node) {
                        inEdges.add(edge);
                    }
                }
            }
        }
        return inEdges;
    }
    public ArrayList<Node> getInEdgesNode(Node node) {
        ArrayList<Node> inEdges = new ArrayList<>();
        //遍历每个节点的每个边，找到所有进入node的边
        for (Node Node : nodeList) {
            if (Node!=node) {
                for (NodeEdge edge : Node.getNodeEdgeList()) {
                    if (edge.getNodeRight() == node) {
                        inEdges.add(edge.getNodeLeft());
                    }
                }
            }
        }
        return inEdges;
    }

    public double getProbability(int a, int b) {
        NodeEdge nodeEdge = getEdge(a,b);
        if (nodeEdge == null) {
            return 0;
        } else {
            return nodeEdge.getProbability();
        }
    }

    public void setProbability(int a, int b, double probability) {
        NodeEdge nodeEdge = getEdge(a,b);
        if (nodeEdge == null) {
        } else {
            nodeEdge.getNodeLeft().setProbability(b, probability);
        }
    }

    public boolean findNode(int id) {
        Node node = getNode(id);
        return node != null;
    }

    public boolean isEmpty() {
        return nodeList.isEmpty();
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
            return ("添加失败，没有找到节点："+in+"\n");
        } else if (nodeOut == null) {
            return ("添加失败，没有找到节点："+out+"\n");
        } else {
            NodeEdge nodeEdge = new NodeEdge(nodeIn, nodeOut);
            if (nodeIn.addNodeEdge(nodeEdge)) {
                return (in +"->"+out+": 增加边成功\n");
            } else {
                return (in +"->"+out+": 增加边失败（边已存在）\n");
            }
        }

    }

    String addEdge(int in, int out, double probability) {
        Node nodeIn, nodeOut;

        //遍历图，找到对应id的node
        nodeIn = getNode(in);
        nodeOut = getNode(out);

        if (nodeIn == null) {
            return ("添加失败，没有找到节点："+in+"\n");
        } else if (nodeOut == null) {
            return ("添加失败，没有找到节点："+out+"\n");
        } else {
            NodeEdge nodeEdge = new NodeEdge(nodeIn, nodeOut);
            nodeEdge.setProbability(probability, false);
            if (nodeIn.addNodeEdge(nodeEdge)) {
                return (in +"->"+out+": 增加边成功，概率为"+probability+"\n");
            } else {
                return (in +"->"+out+": 增加边失败（边已存在）\n");
            }
        }

    }

    private NodeEdge getEdge(int a, int b) {
        Node nodeA = getNode(a);
        return nodeA.getNodeEdge(b);
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
                output.append("概率为:").append(String.format("%.2f",singleProbability)).append("\n");
                i++;
                allProbability += singleProbability;
            }
            output.append("一共").append(i - 1).append("种可能，总的概率是:").append(String.format("%.2f", allProbability)).append("\n");
            if (allProbability == 0) {
                return "";
            }
        }
        output.append("--------------------------\n");

        return output.toString();
    }

}
