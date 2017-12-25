package com.homework.lym;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


class AddPanel extends NewJPanel{
    private JTextField nodeField;

    private JButton addBtn;
    AddPanel() {
        addBtn = new JButton("ADD");
        nodeField = new JTextField(8);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(new JLabel("NodeList"));

        add(nodeField);
        add(addBtn);
        setAddBtn();
        setKeyPress();
    }

    //为Add按钮设置监听器，用于增加点和边
    private void setAddBtn() {
        addBtn.addActionListener(e -> {
            String requests = nodeField.getText();
            String info = nodeGraph.addNodeAndEdges(requests);

            //将操作记录到history
            history.add(requests);

            //将输出结果显示到Console
            textShow.append(info);

            //更新图的显示
            LeftJPanel.renewGraph();

            //将光标聚集到nodeField处
            nodeField.setText("");
            nodeField.grabFocus();
        });
    }

    private void setKeyPress() {
        nodeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER) {
                    addBtn.doClick();
                }
            }
        });
    }
}


