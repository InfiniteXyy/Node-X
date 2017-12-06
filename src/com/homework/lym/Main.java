package com.homework.lym;

import java.awt.*;


public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame frame = new MyFrame();
                frame.go();
//                MouseFrame mouseFrame = new MouseFrame();
//                mouseFrame.setVisible(true);
//                mouseFrame.setSize(1000,1000);
//                mouseFrame.setLocationByPlatform(true);

            }
        });
    }

}









