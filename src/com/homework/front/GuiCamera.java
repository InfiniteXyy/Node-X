package com.homework.front;

import org.omg.PortableInterceptor.SUCCESSFUL;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuiCamera {

    private String fileName; // 文件的前缀

    private String defaultName = "GuiCamera";

    private static int serialNum = 0;

    private String imageFormat; // 图像文件的格式

    private String defaultImageFormat = "jpg";

    private final static int SUCCESS = 1;
    private final static int ERROR = 0;

    private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();


    /***************************************************************************
     * 默认的文件前缀为GuiCamera，文件格式为PNG格式 The default construct

     will use the default
     * Image file surname "GuiCamera", and default image format "png"


     **************************************************************************/
    public GuiCamera() {
        fileName = defaultName;
        imageFormat = defaultImageFormat;
    }


    /***************************************************************************
     * @param s
     *            the surname of the snapshot file
     * @param format
     *            the format of the image file, it can be "jpg" or "png"
     *            本构造支持JPG和PNG文件的存储


     **************************************************************************/
    GuiCamera(String s, String format) {
        fileName = s;
        imageFormat = format;
    }


    /***************************************************************************
     * 对屏幕进行拍照 snapShot the Gui once


     **************************************************************************/
    public int snapShot() {

        try {
            // 拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot())
                    .createScreenCapture(new Rectangle(0, 0,
                            (int) d.getWidth(), (int)

                            d.getHeight()));
            serialNum++;
            // 根据文件前缀变量和文件格式变量，自动生成文件名
            String name = fileName + String.valueOf(serialNum) + "."
                    + imageFormat;
            File f = new File(name);
            System.out.print("Save File " + name);
            // 将screenshot对象写入图像文件
            ImageIO.write(screenshot, imageFormat, f);
            System.out.print("..Finished!\n");
            return SUCCESS;
        } catch (Exception ex) {
            System.out.println(ex);
            return ERROR;
        }
    }
    /***************************************************************************
     * 对NODE.X进行截图


     **************************************************************************/
    int genericImage(JComponent ta){
        try {
            BufferedImage img = new BufferedImage(ta.getWidth(), ta.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();
            ta.printAll(g2d);
            g2d.dispose();
            serialNum++;
            try {
                File dir = new File(fileName);
                if (!dir.exists()) {//该file不存在
                    if (!dir.mkdir()) {//用返回值来标志是否创建成功
                        throw new Exception("您要创建的目录所依托的目录不存在");//
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 根据文件前缀变量和文件格式变量，自动生成文件名
            String name = fileName +"\\" + String.valueOf(serialNum) + "."
                    + imageFormat;
            File f = new File(name);
            System.out.print("Save File " + name);
            // 将screenshot对象写入图像文件
            ImageIO.write(img,imageFormat, f);
            System.out.print("..Finished!\n");
            return SUCCESS;
        } catch (Exception ex) {
            System.out.println(ex);
            return ERROR;
        }
    }
}
