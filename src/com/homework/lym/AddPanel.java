package com.homework.lym;

import javax.swing.*;


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

    private void setAddBtn() {
        addBtn.addActionListener(e -> {
            String nodeName = nodeField.getText();
            try {
                nodeId = Integer.parseInt(nodeName);
                showNodeId(nodeId);
            } catch (NumberFormatException e1) {
                nodeId = -1;
            } finally {
                nodeField.setText("");
                nodeField.grabFocus();
                //将光标聚集到nodeField处
            }
        });
    }
    private void showNodeId(int nodeId) {
        String info = nodeGraph.addNode(nodeId);
        textShow.append(info+"\n");
    }

}


