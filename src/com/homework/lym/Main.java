package com.homework.lym;

import java.awt.*;


public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame frame = new MyFrame();
                frame.go();
            }
        });
    }

}









