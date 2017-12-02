package xyy.map;

public class Main {
    //这个纯粹演示，不用管它
    public static void main(String[] args) {
        NodeGraph nodeGraph = new NodeGraph();
        nodeGraph.graphDemo();
        //先用demo创造一个图
        //在NodeGraph里面封装了 添加点的函数和添加边的函数，并且有错误提示
        int a;
        nodeGraph.addEdge(4, 6, 0.2);
        nodeGraph.addEdge(5, 0);
        nodeGraph.addEdge(4, 0);

//        DFS dfs = new DFS();
//        dfs.showDFS(nodeGraph, 0);
//        //根据输入的图，给出从点id开始的遍历
//        BFS bfs = new BFS();
//        bfs.showBFS(nodeGraph, 0);

        nodeGraph.showRoute(0, 6);
    }
}
