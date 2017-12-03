package com.homework.lym;

import javax.swing.*;

class MyMenuBar {
    private JMenu getMymenu(String mennu_name, String items1, String items2) {
        JMenu jm = new JMenu(mennu_name);
        JMenuItem t1 = new JMenuItem(items1);
        JMenuItem t2 = new JMenuItem(items2);
        jm.add(t1);
        jm.add(t2);
        return jm;
    }

    JMenuBar setMyJMenuBar() {
        JMenuBar br1 = new JMenuBar();
        br1.add(getMymenu("File", "item 1", "item 2"));
        br1.add(getMymenu("Edit", "item 1", "item 2"));
        br1.add(getMymenu("View", "item 1", "item 2"));
        br1.add(getMymenu("Help", "item 1", "item 2"));
        /*this.setJMenuBar(br1);*/
        return br1;
    }
}