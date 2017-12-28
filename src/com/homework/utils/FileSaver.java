package com.homework.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaver {
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
}
