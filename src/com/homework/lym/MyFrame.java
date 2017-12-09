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
        setBackground(SystemColor.window);
        setResizable(false);

        //图标设置
        ImageIcon img = new ImageIcon(this.getClass().getResource("/img/ooopic_1512786383.ico"));
        setIconImage(img.getImage());

        //分辨率大小设置
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth/2, screenHeight/2);

        //生成菜单
        MyMenuBar MyMenuBar = new MyMenuBar();
        setJMenuBar(MyMenuBar.setMyJMenuBar());

        //添加右边的编辑框
        RightJPanel jp = new RightJPanel();
        getContentPane().add(BorderLayout.EAST, jp);

        //添加左边的编辑框
        LeftJPanel lp = new LeftJPanel();
        getContentPane().add(BorderLayout.CENTER,lp);


    }
}
