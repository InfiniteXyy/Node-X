package com.homework.lym;

import com.homework.xyy.NodeGraph;
import com.homework.xyy.PositionChecker;

import javax.swing.*;

class LeftJPanel extends NewJPanel{
    private JTextArea text2;
    final private int mouse_width = 400;
    final private int mouse_height = 400;
    LeftJPanel() {
        nodeGraph = new NodeGraph();

        JTabbedPane jp;

        //设置整体布局
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //添加选项卡
        jp = new JTabbedPane(SwingConstants.TOP);
        JPanel p2 = new JPanel();
        mouse1 = new MouseComponent();
        mouse1.setSize(mouse_width,mouse_height);
        jp.add("painting", mouse1);
        jp.add("code", p2);
        renewGraph();

        //添加绘画框和文本框
        p2.add(addScroller());

        //加入输入控件
        this.add(jp);
        this.addNodeJPanel();
    }

    static void renewGraph() {
        if (!nodeGraph.isEmpty()) {
            PositionChecker positionChecker = new PositionChecker(nodeGraph);
            positionChecker.updateNodePosition(nodeGraph.getNodeIds()[0]);
            mouse1.addCom(nodeGraph);
        }
    }

    private JScrollPane addScroller(){
        text2 = new JTextArea(25, 58);
        JScrollPane scroller = new JScrollPane(text2);
        text2.setLineWrap(true);//启动自动换行
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroller;
    }

    private void addNodeJPanel(){
        AddPanel addPanel = new AddPanel();
        add(addPanel);
    }
    int getMouse_width(){
        return mouse_width;
    }
    int getMouse_height(){
        return mouse_height;
    }
}