package com.homework.lym;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RightJPanle extends JPanel implements ActionListener {
    private JTextArea text1;
    private JTextArea text2;

    public RightJPanle() {
        //jpanel基础设置
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        /*Color c1 = new Color(62,134,160);
        this.setBackground(c1);*/

        //添加第一个文本框
        this.add(new MyJComponent("Probability"));
        text2 = new JTextArea(10, 20);
        JScrollPane scroller1 = new JScrollPane(text2);
        text2.setLineWrap(true);//启动自动换行
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scroller1);

        //添加第二个文本框
        this.add(new MyJComponent("please input your node"));
        text1 = new JTextArea(10, 20);
        JScrollPane scroller2 = new JScrollPane(text1);
        text1.setLineWrap(true);//启动自动换行
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scroller2);

        //添加按钮
        JButton button3 = new JButton("check probability!");
        button3.addActionListener(this);
        JPanel temjp = new JPanel();
        temjp.add(button3);
        button3.setBounds((this.getWidth() - button3.getWidth())/2,(this.getHeight() - button3.getHeight())/2,button3.getWidth(),button3.getHeight());
        this.add(temjp);
    }

    public void actionPerformed(ActionEvent ev) {
        text1.append("just click me!\n");
    }
}
