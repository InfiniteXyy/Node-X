package com.homework.lym;

import javax.swing.*;
import java.util.regex.Pattern;


class AddPanel extends NewJPanel{
    private JTextField nodeField;
    private int nodeId;
    //nodeId用来传出得到的id
    private JButton addBtn;
    AddPanel() {
        addBtn = new JButton("ADD");
        nodeField = new JTextField(8);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(new JLabel("NodeList"));

        add(nodeField);
        add(addBtn);
        setAddBtn();
    }

    //为Add按钮设置监听器，用于增加点和边
    private void setAddBtn() {
        addBtn.addActionListener(e -> {
            String requests = nodeField.getText();
            StringBuilder outPut = new StringBuilder();

            String info = nodeGraph.addNodeAndEdges(requests);

            //将输出结果显示到Console
            textShow.append(info);

            //将光标聚集到nodeField处
            nodeField.setText("");
            nodeField.grabFocus();
        });
    }
}


