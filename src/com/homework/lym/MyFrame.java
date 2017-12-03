package com.homework.lym;

import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {

    MyFrame() {
        //根据系统桌面的分辨路来设置大小，增强可移植性
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeigh = screenSize.height;
        setSize(screenWidth / 2, screenHeigh / 2);
        setLocationByPlatform(true);
        Image img = new ImageIcon("idea.ico").getImage();
        setIconImage(img);
    }

    private void setFont() {//设置字体
        Font font = new Font("console", Font.PLAIN, 15);
        UIManager.put("Button.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
    }

    void go() {//用一个go函数来创建界面

        //基础设置
        JFrame.setDefaultLookAndFeelDecorated(true);
        setFont();//设置字体
        MyFrame MyFrame = new MyFrame();
        MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyFrame.setVisible(true);
        MyFrame.setTitle("数据可视化处理");
        MyFrame.setBackground(SystemColor.window);

        //生成菜单
        MyMenuBar MyMenuBar = new MyMenuBar();
        MyFrame.setJMenuBar(MyMenuBar.setMyJMenuBar());

        //添加右边的编辑框
        RightJPanel jp = new RightJPanel();
        MyFrame.getContentPane().add(BorderLayout.EAST, jp);

        //test


        //添加左边的编辑框
        LeftJPanel lp = new LeftJPanel();
        MyFrame.getContentPane().add(BorderLayout.CENTER,lp);
    }
}
