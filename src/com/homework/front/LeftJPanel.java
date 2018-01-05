package com.homework.front;

import com.homework.core.NodeGraph;
import com.homework.core.PositionChecker;
/*import com.sun.tools.javac.jvm.Code;*/

import javax.swing.*;
import java.util.ArrayList;

class LeftJPanel extends NewJPanel{
    LeftJPanel() {
        //左边是控制栏
        //在这里生成新的图和用于保存记录的history
        nodeGraph = new NodeGraph();
        history = new ArrayList<>();


        //设置整体布局
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //添加选项卡
        JTabbedPane jp = new JTabbedPane(SwingConstants.TOP);

        CodePanel codePanel = new CodePanel();
        mouse1 = new MouseComponent(nodeGraph);
        jp.add("painting", mouse1);
        jp.add("code", codePanel);
        jp.addChangeListener(e -> {
            if (codePanel == jp.getSelectedComponent()) {
                codePanel.updateCode(mouse1);
            }
        });

        //加入输入控件
        this.add(jp);
        this.addNodeJPanel();
    }

    static void renewGraph(boolean needInit) {
        if (!needInit) {
            PositionChecker positionChecker = new PositionChecker(nodeGraph);

            positionChecker.updateNodePosition(nodeGraph.root);
            mouse1.updateCom();
        } else {
            mouse1.init();
        }
    }



    private void addNodeJPanel(){
        AddPanel addPanel = new AddPanel();
        add(addPanel);
    }
}