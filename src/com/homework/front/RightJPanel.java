package com.homework.front;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        JButton button4 = new JButton("guicamera");
        button4.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(null, "是否使用默认图片储存路径？", "是否继续", JOptionPane.YES_NO_OPTION);
            if(res ==JOptionPane.YES_OPTION){
                GuiCamera cam = new GuiCamera("Node.X_Camera","png");
                if(cam.genericImage(mouse1)==1){
                    JOptionPane.showMessageDialog(null, "截图成功", "GUI_CAMERA", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "出现错误", "GUI_CAMERA", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                try{
                    JFileChooser jfc = new JFileChooser();
                    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    jfc.showDialog(new JLabel(),"选择截图存放的位置");
                    GuiCamera cam = new GuiCamera(jfc.getSelectedFile().getAbsolutePath(),"png");
                    if(cam.genericImage(mouse1)==1){
                        JOptionPane.showMessageDialog(null, "截图成功", "GUI_CAMERA", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "出现错误", "GUI_CAMERA", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception ex){
                    System.out.println(ex.getStackTrace());
                }
            }
        });
        JPanel tempJ1 = new JPanel();
        tempJ1.add(button4);
        button4.setBounds((this.getWidth() - button4.getWidth())/2,(this.getHeight() - button4.getHeight())/2,button4.getWidth(),button4.getHeight());
        this.add(tempJ1);
    }

    public void actionPerformed(ActionEvent ev) {
        String outPut = nodeGraph.getEdgeProbability(textEdit.getText());
        textShow.append(outPut);
    }

}
