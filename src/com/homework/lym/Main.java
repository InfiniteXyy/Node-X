package com.homework.lym;

import com.homework.xyy.NodeGraph;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame frame = new MyFrame();
                frame.go();
                NodeGraph nodeGraph = new NodeGraph();

//                MouseFrame mouseFrame = new MouseFrame();
//                mouseFrame.setVisible(true);
//                mouseFrame.setSize(1000,1000);
//                mouseFrame.setLocationByPlatform(true);
            }
        });
    }

}









