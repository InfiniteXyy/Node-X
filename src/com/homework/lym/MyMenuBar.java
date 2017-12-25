package com.homework.lym;

import javax.swing.*;
import java.util.Arrays;


class MyMenuBar extends NewJPanel{
    private JMenu File, Edit, View, Help;

    JMenuBar getJMenuBar() {
        JMenuBar br1 = new JMenuBar();

        setMenuItem();

        br1.add(File);
        br1.add(Edit);
        br1.add(View);
        br1.add(Help);
        return br1;
    }
    private void setMenuItem() {
        setFile();
        setEdit();
        setView();
        setHelp();
    }

    private void setFile() {
        File = new JMenu("File");
        JMenuItem fileOpen = new JMenuItem("Open...");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem demo = new JMenuItem("Use Demo");
        File.add(fileOpen);
        File.add(save);
        File.add(demo);
        demo.addActionListener((e -> {
            int n = JOptionPane.showConfirmDialog(null,
                    "确定导入内置的图示例吗", "", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                textShow.append(nodeGraph.graphDemo());
                LeftJPanel.renewGraph();
            }
        }));
    }

    private void setEdit() {
        Edit = new JMenu("Edit");
        JMenuItem undo  = new JMenuItem("Undo");
        JMenuItem find = new JMenuItem("Find");
        Edit.add(undo);
        Edit.add(find);
    }

    private void setView() {
        final int BFS_SET = 0;
        final int DFS_SET = 1;

        View = new JMenu("View");
        JMenuItem BFS = new JMenuItem("BFS");
        JMenuItem DFS = new JMenuItem("DFS");
        View.add(BFS);
        View.add(DFS);

        BFS.addActionListener((e) -> {
            setFS(BFS_SET);
        });

        DFS.addActionListener((e) -> {
            setFS(DFS_SET);

        });
    }

    private void setFS(int type) {
        if(nodeGraph.getNodeIds().length == 0)return;
        int[] nodes = nodeGraph.getNodeIds();
        String title;
        if (type == 0) {
            title = "BFS";
        } else {
            title = "DFS";
        }
        Integer[] nodeInts = Arrays.stream( nodes ).boxed().toArray( Integer[]::new );
        Object s = JOptionPane.showInputDialog(null,"请选择起始节点:\n",
                title, JOptionPane.PLAIN_MESSAGE,
                null, nodeInts, nodeInts[0]);
        if (s != null) {
            String out;
            if (type == 0) {
                out = nodeGraph.showBFS(nodeGraph.getNodeIds()[(int)s]);
            } else {
                out = nodeGraph.showDFS(nodeGraph.getNodeIds()[(int)s]);
            }
            textShow.append(out + "\n");
        }
    }

    private void setHelp() {
        Help = new JMenu("Help");
        JMenuItem addNode = new JMenuItem("add node...");
        JMenuItem checkProbability = new JMenuItem("check probability...");
        JMenuItem about = new JMenuItem("About...");
        Help.add(addNode);
        Help.add(checkProbability);
        Help.add(about);
        //设置说明文档
        addNode.addActionListener((e -> {
             JOptionPane.showMessageDialog(null,
                    "Use 1,2,3 to add node1, node2, node3\n" +
                            "Use 1:2 to add line between node1 and node2\n" +
                            "(If you want to add 'probability', use a:b:p, 'p' for probability)",
                     "Add nodes", JOptionPane.INFORMATION_MESSAGE);
        }));

        checkProbability.addActionListener((e -> {
            JOptionPane.showMessageDialog(null,
                    "Use 1>2 to check the probability of all the lines from node1 to node2\n" +
                            "Use ?>2 to check view through all the nodes and drag them to node2\n" +
                            "(2>? is the same)",
                    "check path", JOptionPane.INFORMATION_MESSAGE);
        }));

        about.addActionListener((e -> {
            JOptionPane.showMessageDialog(null,
                    "Nodex made by xyy and lym 2017",
                    "Nodex v1.0", JOptionPane.INFORMATION_MESSAGE);
        }));
    }
}