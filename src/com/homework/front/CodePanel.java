package com.homework.front;

import com.homework.utils.FileSaver;
import com.homework.utils.ProbabilityManager;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CodePanel extends JPanel {
    private JTextArea text;
    CodePanel() {
        this.setPreferredSize(new Dimension(600, 420));
        addScroller();
        addBtn();
    }

    void updateCode(MouseComponent mouse) {
        text.setText("");
        Map<String, Double> map = mouse.probabilityMap;
        List<EllipseNode> nodes = mouse.nodes;
        if (nodes.isEmpty()) {text.append("请在下方add框添加节点！"); return;}
        for (EllipseNode node : nodes) {
            text.append("节点：" + String.valueOf(node.getNodeId())+"\n");
            for (String edge : map.keySet()) {
                if (edge.startsWith(String.valueOf(node.getNodeId()))) {
                    text.append("P("+edge + String.format(") = %.3f; ", map.get(edge)));
                }
            }
            text.append("\n\n");
        }
    }

    private void addScroller(){
        text = new JTextArea(22,39);
        text.setEditable(false);
        JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroller);
    }
    private void addBtn() {
        JButton save = new JButton("save TXT");
        save.addActionListener(e -> {
            File directory = new File("");
            JFileChooser fileChooser = new JFileChooser();
            try {
                fileChooser.setCurrentDirectory(directory.getCanonicalFile());//得到当前路径
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            fileChooser.setDialogTitle("保存为...");
            fileChooser.setApproveButtonText("确定");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setSelectedFile(new File("edges"+FileSaver.dateFormat.format(new Date())+".txt"));
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                String path=fileChooser.getSelectedFile().getPath();
                if (!path.endsWith(".txt")) {
                    path += ".txt";
                }
                FileSaver fileSaver = new FileSaver(path);
                fileChooser.setFileFilter(txt);

                //如果已存在
                if (fileSaver.saveText(text.getText(), true)) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog
                            (null, "文件已存在，是否覆盖？", "", JOptionPane.YES_NO_OPTION)) {
                        fileSaver.saveText(text.getText(), false);
                    }
                }
            }
        });
        this.add(save);
    }

    //设置文件过滤器
    private FileFilter txt = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return (pathname.getPath().endsWith(".txt"));
        }

        @Override
        public String getDescription() {
            return ".txt files";
        }
    };
}
