package com.homework.xyy;

import java.util.ArrayList;
import java.util.List;

class N2N {
    class NodePath{
        List<NodeEdge> path;

        NodePath() {
            path = new ArrayList<>();
        }

        void addPath(NodeEdge nodeEdge) {

            path.add(nodeEdge);
        }

        void rmPath() {
            path.remove(path.size()-1);
        }

        boolean hasWalkedThroughThisEdge() {
            if (path.size() == 1) {
                return false;
            }
            List<NodeEdge> temp = path.subList(0,path.size()-2);
            return temp.contains(path.get(path.size()-1));
        }

        boolean reachEnd() {
            return path.get(path.size()-1).getNodeRight() == endNode;
        }

        List<NodeEdge> getLastNodeList() {
            return path.get(path.size()-1).getNodeRight().getNodeEdgeList();
        }

        private NodePath myClone(){
            NodePath nodePath = new NodePath();
            nodePath.path.addAll(path);
            return nodePath;
        }
    }

    private Node endNode;
    private Node inNode;
    private  List<NodePath> nodePaths = null;

    N2N(Node in, Node out) {
        endNode = out;
        inNode = in;
        nodePaths = new ArrayList<>();
    }


    List<NodePath> getNodePaths() {
        NodePath path = new NodePath();
        path.path.add(new NodeEdge(null, inNode));
        findPaths(path);
        return nodePaths;
    }

    private void findPaths(NodePath curPath) {
        if (!curPath.hasWalkedThroughThisEdge()) {
            if (curPath.reachEnd()) {
                nodePaths.add(curPath.myClone());
            } else {
                for (NodeEdge i : curPath.getLastNodeList()) {
                    curPath.addPath(i);
                    findPaths(curPath);
                    curPath.rmPath();
                }
            }
        }
    }
}
//伪代码：
//        if（走了重复的路径）{
//            结束
//        } else if (到达终点) {
//            得到一条路径 结束
//        } else {
//            for (当前点的每一个后续路径) {
//                加入路径
//                递归
//                移出路径
//
//            }
//        }




