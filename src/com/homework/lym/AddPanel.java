package com.homework.lym;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class AddPanel extends NewJPanel{
    private JTextField nodeField;
    private int nodeId;
    //nodeId用来传出得到的id

    AddPanel() {
        nodeField = new JTextField(8);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(new JLabel("NodeList"));
        add(nodeField);
        add(setAddBtn());
    }

    private JButton setAddBtn() {
        JButton addBtn = new JButton("ADD");
        addBtn.addActionListener(e -> {
            String nodeName = nodeField.getText();
            try {
                nodeId = Integer.parseInt(nodeName);
                showNodeId();
            } catch (NumberFormatException e1) {
                nodeId = -1;
            } finally {
                nodeField.setText("");
            }
        });
        return addBtn;
    }

    private void showNodeId() {
        String info = nodeGraph.addNode(nodeId);
        textShow.append("12\n");
    }
}


