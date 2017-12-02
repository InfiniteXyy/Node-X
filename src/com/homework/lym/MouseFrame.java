package com.homework.lym;

import javax.swing.*;
import java.awt.*;

public class MouseFrame extends JFrame {
    public MouseFrame(){

        /*Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeigh = screenSize.height;
        this.setSize(screenWidth / 2, screenHeigh / 2);
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("数据可视化处理");
        this.setBackground(SystemColor.window);
        setVisible(true);*/

        this.add(new MouseCompoent());
        pack();

    }
}
