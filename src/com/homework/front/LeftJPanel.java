package com.homework.front;

import com.homework.core.NodeGraph;
import com.homework.core.PositionChecker;

import javax.swing.*;
import java.util.ArrayList;

class LeftJPanel extends NewJPanel{
    final private int mouse_width = 400;
    final private int mouse_height = 400;
    LeftJPanel() {
        //左边是控制栏
        //在这里生成新的图和用于保存记录的history
        nodeGraph = new NodeGraph();
        history = new ArrayList<>();


        //设置整体布局
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //添加选项卡
        JTabbedPane jp = new JTabbedPane(SwingConstants.TOP);
        JPanel p2 = new JPanel();
        mouse1 = new MouseComponent(nodeGraph);
        mouse1.setSize(mouse_width,mouse_height);
        jp.add("painting", mouse1);
        jp.add("code", p2);

        //添加绘画框和文本框
        p2.add(addScroller());

        //加入输入控件
        this.add(jp);
        this.addNodeJPanel();
    }

    static void renewGraph(boolean needInit) {
        if (!needInit) {
            PositionChecker positionChecker = new PositionChecker(nodeGraph);
            positionChecker.updateNodePosition(nodeGraph.getNodeIds()[0]);
            mouse1.updateCom();
        } else {
            mouse1.init();
        }
    }

    private JScrollPane addScroller(){
        JTextArea text2;
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
}