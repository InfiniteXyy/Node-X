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

    public String addNode(int id) {
        if (getNode(id) != null) {
            return("节点："+id+"已经存在");
        } else {
            Node node = new Node(id);
            nodeList.add(node);
            return("节点："+id+"添加成功");
        }
    }

    public String addEdge(int in, int out) {
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

    public int[] getNodeIds() {
        int[] data = new int[nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            data[i] = nodeList.get(i).getId();
        }
        return data;
    }

    //重载，带概率的增加边
    public String addEdge(int in, int out, double probability) {
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


    public void graphDemo() {
        /* 有十个节点的实例 */
        for (int i = 0; i < 10; i++) {
            addNode(i);
        }
        addEdge(0,1);
        addEdge(0, 2);
        addEdge(1,3);
        addEdge(1,4);
        addEdge(2,5);
        addEdge(2,6);
        addEdge(3,7);
        addEdge(4,7);
        addEdge(5,6);
    }

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

    public void showBFS(int id) {
        BFS bfs = new BFS();
        bfs.showBFS(this, id);
    }

    public void showDFS(int id) {
        DFS bfs = new DFS();
        bfs.showDFS(this, id);
    }

    public String showRoute(IdEdge idEdge) {
        StringBuilder output = new StringBuilder("从"+idEdge.in+"到"+idEdge.out+":");

        int in = idEdge.in;
        int out = idEdge.out;

        Node nodeIn, nodeOut;

        //遍历图，找到对应id的node
        nodeIn = getNode(in);
        nodeOut = getNode(out);
//这里的算法结构需要重构！！！！
        if (nodeIn != null && nodeOut != null) {
            N2N temp = new N2N(nodeIn, nodeOut);
            int i = 1;
            double allProbability = 0;
            for (N2N.NodePath paths : temp.getNodePaths()) {
                output.append("\n");
                double singleProbability = 1.0;
                for (NodeEdge e : paths.path) {
                    singleProbability *= e.showEdge(output);
                }
                output.append("\n概率为:"+singleProbability);
                i++;
                allProbability += singleProbability;
            }
            output.append("\n一共"+(i-1)+"种可能，总的概率是:"+allProbability);
            if (allProbability == 0) {
                return "";
            }
        }
        output.append("\n------------------------\n");

        return output.toString();
    }
}
