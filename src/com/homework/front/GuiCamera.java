package com.homework.front;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GuiCamera {
    private String filePath; // 文件路径

    private static int serialNum = 0;

    private String imageFormat; // 图像格式

    private final static int SUCCESS = 1;
    private final static int ERROR = 0;

    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    GuiCamera(String s, String format) {
        filePath = s;
        imageFormat = format;
    }

    public int snapShot() {
        try {
            // 拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0, 0,
                            (int) screen.getWidth(), (int) screen.getHeight()));
            File file = new File(filePath);
            System.out.print("Save File " + filePath);
            // 将screenshot对象写入图像文件
            ImageIO.write(screenshot, imageFormat, file);
            System.out.print("..Finished!\n");
            return SUCCESS;
        } catch (Exception ex) {
            System.out.println(ex);
            return ERROR;
        }
    }

    int genImg(JComponent jComponent){
        try {
            BufferedImage img = getImg(jComponent);
            serialNum++;
            try {
                File file = new File(filePath);
                if (!file.exists()) {//该file不存在
                    if (!file.mkdir()) {//用返回值来标志是否创建成功
                        throw new Exception("目录创建失败");//
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            File file = new File(filePath);
            System.out.print("Save File " + filePath);
            // 写入图像文件
            ImageIO.write(img, imageFormat, file);
            System.out.println("..Finished!");
            return SUCCESS;
        } catch (Exception ex) {
            System.out.println(ex);
            return ERROR;
        }
    }

    public static BufferedImage getImg(JComponent jComponent) {
        BufferedImage img =  new BufferedImage(jComponent.getWidth(), jComponent.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        jComponent.printAll(g2d);
        g2d.dispose();
        return img;
    }
}
