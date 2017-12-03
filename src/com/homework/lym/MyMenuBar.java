package com.homework.lym;

import javax.swing.*;


class MyMenuBar extends NewJPanel{
    private JMenu File, Edit, View, Help;

    JMenuBar setMyJMenuBar() {
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
            nodeGraph.graphDemo();
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
        View = new JMenu("View");
        JMenuItem BFS = new JMenuItem("BFS");
        JMenuItem DFS = new JMenuItem("DFS");
        View.add(BFS);
        View.add(DFS);

        BFS.addActionListener((e) -> {
            if(nodeGraph.getNodeIds().length == 0)return;
            String temp = nodeGraph.showBFS(nodeGraph.getNodeIds()[0]);
            textShow.append(temp + "\n");
        });
    }

    private void setHelp() {
        Help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        Help.add(about);
    }
}