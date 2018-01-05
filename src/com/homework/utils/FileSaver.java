package com.homework.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileSaver {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("YYMMDDHHmm");
    private String path;

    public FileSaver(String path) {
        this.path = path;
    }

    public boolean saveHistory(ArrayList<String> history, boolean justTry) {
        try {
            File writeName = new File(path);
            boolean exist;
            //若存在则exist = false
            exist = writeName.createNewFile();
            //如果文件存在或者只是尝试的话，返回true
            if (!exist && justTry) return true;
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            for (String method : history) {
                out.write(method+"\n");
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveImg(BufferedImage img, boolean justTry) {
        try {
            File writeName = new File(path);
            boolean exist;
            //若存在则exist = false
            exist = writeName.createNewFile();
            //如果文件存在或者只是尝试的话，返回true
            if (!exist && justTry) return true;
            ImageIO.write(img, "png", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveText(String text, boolean justTry) {
        try {
            File writeName = new File(path);
            boolean exist;
            //若存在则exist = false
            exist = writeName.createNewFile();
            //如果文件存在或者只是尝试的话，返回true
            if (!exist && justTry) return true;
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            out.write(text);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
