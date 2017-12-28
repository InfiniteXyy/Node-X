package com.homework.front;

import com.homework.core.NodeGraph;

import javax.swing.*;
import java.util.ArrayList;

//不一定是JPanel，最好有个更合适的类！！！
class NewJPanel extends JPanel{
    static MouseComponent mouse1;
    static NodeGraph nodeGraph;
    static JTextArea textShow;
    static JTextArea textEdit;

    //用一个列表记录每一步的操作，用来撤销和保存
    static ArrayList<String> history;
}
