package com.homework.lym;

import com.homework.xyy.FileOpener;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.io.IOException;
import java.util.Arrays;


class MyMenuBar extends NewJPanel{
    private JMenu Files, Edit, View, Help;

    JMenuBar getJMenuBar() {
        JMenuBar br1 = new JMenuBar();

        setMenuItem();

        br1.add(Files);
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
        Files = new JMenu("File");
        JMenuItem fileOpen = new JMenuItem("Open...");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem demo = new JMenuItem("Use Demo");
        Files.add(fileOpen);
        Files.add(save);
        Files.add(demo);
        demo.addActionListener((e -> {
            int n = JOptionPane.showConfirmDialog(null,
                    "确定导入内置的图示例吗", "", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                history.clear();
                nodeGraph.empty();
                history.add(nodeGraph.graphDemo(true));
                textShow.append(nodeGraph.graphDemo(false));
                LeftJPanel.renewGraph(false);
            }
        }));

        fileOpen.addActionListener((e -> {
            File directory = new File("");
            JFileChooser fileChooser = new JFileChooser();
            try {
                fileChooser.setCurrentDirectory(directory.getCanonicalFile());//得到当前路径
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            fileChooser.setDialogTitle("请选择要读取的文件(.ndx)");
            fileChooser.setApproveButtonText("确定");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(ndx);

            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                String path=fileChooser.getSelectedFile().getPath();
                FileOpener fileOpener = new FileOpener(path);
                //重置整个界面
                history.clear();
                nodeGraph.empty();
                LeftJPanel.renewGraph(true);

                history.addAll(fileOpener.renderFile());
                for (String method : history) {
                    textShow.append(nodeGraph.addNodeAndEdges(method));
                }
                LeftJPanel.renewGraph(false);
            }
        }));

    }
//设置文件过滤器
    private FileFilter ndx = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return (pathname.getPath().endsWith(".ndx"));
        }

        @Override
        public String getDescription() {
            return ".ndx files";
        }
    };

        private void setEdit() {
        Edit = new JMenu("Edit");
        JMenuItem undo  = new JMenuItem("Undo");
        JMenuItem find = new JMenuItem("Find");
        Edit.add(undo);
        Edit.add(find);

        //设置撤销功能
        undo.addActionListener((e -> {
            if (history.size() >= 1) {
                history.remove(history.size()-1);
                nodeGraph.empty();
                for (String input : history) {
                    nodeGraph.addNodeAndEdges(input);
                }
                //若history中没有元素，就initGraph
                LeftJPanel.renewGraph(history.size()==0);
            }
        }));

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