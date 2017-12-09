package com.homework.lym;

import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {

    MyFrame() {
        set();
    }

    private void setFont() {//设置字体
        Font font = new Font("console", Font.PLAIN, 15);
        UIManager.put("Button.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
    }

    private void set() {//用一个go函数来创建界面

        //基础设置
        JFrame.setDefaultLookAndFeelDecorated(true);
        setFont();//设置字体
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("数据可视化处理");
        setResizable(false);

        //分辨率大小设置
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth/2, screenHeight/2);

        //图标设置
        setIconImage(kit.getImage(getClass().getResource("/img/ooopic_1512786383.ico")));

        //生成菜单
        setJMenuBar(new MyMenuBar().getJMenuBar());

        //添加右边的编辑框
        getContentPane().add(BorderLayout.EAST, new RightJPanel());

        //添加左边的编辑框
        getContentPane().add(BorderLayout.CENTER,new LeftJPanel());


    }
}
