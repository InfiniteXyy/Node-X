package com.homework.front;

import com.homework.utils.FileSaver;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;


class RightJPanel extends NewJPanel implements ActionListener {

    RightJPanel() {
        //jPanel基础设置
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        /*Color c1 = new Color(62,134,160);
        this.setBackground(c1);*/

        //添加第一个文本框
        this.add(new MyJComponent("Console"));
        textShow = new JTextArea(10, 20);
        textShow.setEditable(false);
        //设置不可编辑
        JScrollPane scroller2 = new JScrollPane(textShow);
        textShow.setLineWrap(true);//启动自动换行
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scroller2);

        //添加第二个文本框
        this.add(new MyJComponent("Please input your node"));
        textEdit = new JTextArea(10, 20);
        JScrollPane scroller1 = new JScrollPane(textEdit);
        textEdit.setLineWrap(true);//启动自动换行
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scroller1);

        //添加check probability按钮
        JButton button3 = new JButton("check probability");
        button3.addActionListener(this);
        JPanel tempJ = new JPanel();
        tempJ.add(button3);
        button3.setBounds((this.getWidth() - button3.getWidth())/2,(this.getHeight() - button3.getHeight())/2,button3.getWidth(),button3.getHeight());
        this.add(tempJ);

        //添加生成图片的按钮
        JButton button4 = new JButton("Gui Camera");
        button4.addActionListener(e -> {
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
            fileChooser.setSelectedFile(new File(FileSaver.dateFormat.format(new Date())+".png"));
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                String path=fileChooser.getSelectedFile().getPath();
                if (!path.endsWith(".png")) {
                    path += ".png";
                }
                FileSaver fileSaver = new FileSaver(path);
                fileChooser.setFileFilter(png);

                //如果已存在
                if (fileSaver.saveImg(GuiCamera.getImg(mouse1), true)) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog
                            (null, "文件已存在，是否覆盖？", "", JOptionPane.YES_NO_OPTION)) {
                        fileSaver.saveImg(GuiCamera.getImg(mouse1), false);
                    }
                }
            }
        });
        JPanel tempJ1 = new JPanel();
        tempJ1.add(button4);
        button4.setBounds((this.getWidth() - button4.getWidth())/2,(this.getHeight() - button4.getHeight())/2,button4.getWidth(),button4.getHeight());
        this.add(tempJ1);
    }
    //设置文件过滤器
    private FileFilter png = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return (pathname.getPath().endsWith(".png"));
        }

        @Override
        public String getDescription() {
            return ".png files";
        }
    };

    public void actionPerformed(ActionEvent ev) {
        String outPut = nodeGraph.getEdgeProbability(textEdit.getText());
        textShow.append(outPut);
    }

}
