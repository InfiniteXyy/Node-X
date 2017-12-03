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

    //这里不仅可以增加点，还能设置边，甚至还能设置边的概率，厉害了
    private void setAddBtn() {
        addBtn.addActionListener(e -> {
            String nodeName = nodeField.getText();
            try {
                String[] data = nodeName.split(",");
                for (String single : data) {

                    if (Pattern.matches("^\\d+$", single)) {
                        nodeId = Integer.parseInt(single);
                        showNodeAddInfo(nodeId);
                    } else if (Pattern.matches("^\\d+:\\d+(:0.\\d*)?$", single)) {
                        String[] nodes = single.split(":");
                        int a = Integer.parseInt(nodes[0]);
                        int b = Integer.parseInt(nodes[1]);
                        System.out.println(nodes.length);
                        double probability = nodes.length==2 ? -1 : Double.parseDouble(nodes[2]);
                        showEdgeAddInfo(a, b, probability);
                    }
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                nodeField.setText("");
                nodeField.grabFocus();
                //将光标聚集到nodeField处
            }
        });
    }

    private void showNodeAddInfo(int nodeId) {
        String info = nodeGraph.addNode(nodeId);
        textShow.append(info+"\n");
    }

    private void showEdgeAddInfo(int a, int b, double probability) {
        String info;
        if (probability < 0) {
            info = nodeGraph.addEdge(a, b);
        } else {
            info = nodeGraph.addEdge(a, b, probability);
        }

        textShow.append(info+"\n");
    }

}


