package xyy.map;


import java.util.ArrayList;
import java.util.List;

public class NodeGraph {
    private List<Node> nodeList = null;

    public NodeGraph() {
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
    }

    public void addNode(int id) {
        if (findNode(id) != null) {
            System.out.println("节点："+id+"已经存在");
        } else {
            Node node = new Node(id);
            nodeList.add(node);
            System.out.println("节点："+id+"添加成功");
        }
    }

    public void addEdge(int in, int out) {
        Node nodeIn, nodeOut;

        //遍历图，找到对应id的node
        nodeIn = findNode(in);
        nodeOut = findNode(out);

        if (nodeIn != null && nodeOut != null) {
            NodeEdge nodeEdge = new NodeEdge(nodeIn, nodeOut);
            if (nodeIn.addNodeEdge(nodeEdge)) {
                System.out.println(in +"->"+out+": 增加边成功");
            } else {
                System.out.println(in +"->"+out+": 增加边失败");
            }
        }
    }
    //重载，带概率的增加边
    public void addEdge(int in, int out, double probability) {
        Node nodeIn, nodeOut;

        //遍历图，找到对应id的node
        nodeIn = findNode(in);
        nodeOut = findNode(out);

        if (nodeIn != null && nodeOut != null) {
            NodeEdge nodeEdge = new NodeEdge(nodeIn, nodeOut);
            nodeEdge.setProbability(probability, false);
            if (nodeIn.addNodeEdge(nodeEdge)) {
                System.out.println(in +"->"+out+": 增加边成功，概率为"+probability);
            } else {
                System.out.println(in +"->"+out+": 增加边失败");
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

    Node getNode(int id) {
        Node node = findNode(id);
        if (node == null) {
            System.out.println("没有找到node："+id);
        }
        return node;
    }

    private NodeEdge getEdge(int a, int b) {
        Node nodeA = findNode(a);
        if (nodeA == null) return null;
        else {
            return nodeA.getNodeEdge(b);
        }
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

    private Node findNode(int id) {
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

    public void showRoute(int in, int out) {
        Node nodeIn, nodeOut;

        //遍历图，找到对应id的node
        nodeIn = findNode(in);
        nodeOut = findNode(out);

        if (nodeIn != null && nodeOut != null) {
            N2N temp = new N2N(nodeIn, nodeOut);
            int i = 1;
            double allProbability = 0;
            for (N2N.NodePath paths : temp.getNodePaths()) {
                System.out.println("\n第"+i+"种可能");
                double singleProbability = 1.0;
                for (NodeEdge e : paths.path) {
                    singleProbability *= e.showEdge();
                }
                System.out.println("概率为:"+singleProbability);
                i++;
                allProbability += singleProbability;
            }
            System.out.println("\n一共"+(i-1)+"种可能，总的概率是:"+allProbability);
        }
    }
}
