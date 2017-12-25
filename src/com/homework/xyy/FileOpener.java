package com.homework.xyy;

import java.io.*;
import java.util.ArrayList;

public class FileOpener {
    private String path;

    public FileOpener(String path) {
        this.path = path;
    }

    public ArrayList<String> renderFile() {
        ArrayList<String> outPut = new ArrayList<>();
        try {
            File filename = new File(path);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)
            );
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                outPut.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outPut;
    }
}
