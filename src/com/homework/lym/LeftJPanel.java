package com.homework.lym;

import com.homework.xyy.NodeGraph;

import javax.swing.*;
import java.awt.*;

class LeftJPanel extends NewJPanel{

    private MouseComponent mouse1;
    private JTextArea text2;

    LeftJPanel() {
        nodeGraph = new NodeGraph();

        JTabbedPane jp;

        //设置整体布局
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //添加选项卡
        jp = new JTabbedPane(SwingConstants.TOP);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        jp.add("painting", mouse1 = new MouseComponent());
        jp.add("code", p2);


        //添加绘画框和文本框

        p2.add(addScroller(this.text2));

        //加入输入控件
        this.add(jp);
        this.addNodeJPanel();
    }

    private JScrollPane addScroller(JTextArea text){
        text = new JTextArea(25, 60);
        JScrollPane scroller = new JScrollPane(text);
        text.setLineWrap(true);//启动自动换行
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroller;
    }

    private void addNodeJPanel(){
//        JPanel jp = new JPanel();
//        jp.setLayout(new BoxLayout(jp,BoxLayout.LINE_AXIS));
//        jp.add(new JLabel("NodeList"));
//        jp.add(new JTextField(8));
//        jp.add(new JButton("ADD"));
//        this.add(jp);

        AddPanel addPanel = new AddPanel();
        add(addPanel);
    }
}