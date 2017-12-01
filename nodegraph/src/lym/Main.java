package lym;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

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

class MyFrame extends JFrame {

    public MyFrame() {
        //根据系统桌面的分辨路来设置大小，增强可移植性
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeigh = screenSize.height;
        setSize(screenWidth / 2, screenHeigh / 2);
        setLocationByPlatform(true);
        Image img = new ImageIcon("idea.ico").getImage();
        setIconImage(img);
    }

    public void setFont() {//设置字体
        Font font = new Font("console", Font.PLAIN, 15);
        UIManager.put("Button.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
    }

    public void go() {//用一个go函数来创建界面

        //基础设置
        JFrame.setDefaultLookAndFeelDecorated(true);
        setFont();//设置字体
        MyFrame MyFrame = new MyFrame();
        MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyFrame.setVisible(true);
        MyFrame.setTitle("数据可视化处理");
        MyFrame.setBackground(SystemColor.window);

        //生成菜单
        myMenubar mymenubar = new myMenubar();
        MyFrame.setJMenuBar(mymenubar.setMyJMenuBar());

        //添加右边的编辑框
        RightJPanle jp = new RightJPanle();
        MyFrame.getContentPane().add(BorderLayout.EAST, jp);

        //test
        JLabel jL = new JLabel("text");
        JPanel jp2 = new JPanel();
        jp2.add(jL);
        MyFrame.getContentPane().add(BorderLayout.WEST, jp2);

        //添加左边的编辑框
        LeftJPanle lp = new LeftJPanle();
        MyFrame.getContentPane().add(BorderLayout.CENTER, lp.getJp());
    }
}

class RightJPanle extends JPanel implements ActionListener {
    private JTextArea text1;
    private JTextArea text2;

    public RightJPanle() {
        //jpanel基础设置
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        /*Color c1 = new Color(62,134,160);
        this.setBackground(c1);*/

        //添加第一个文本框
        this.add(new MyJComponent("Probability"));
        text2 = new JTextArea(10, 20);
        JScrollPane scroller1 = new JScrollPane(text2);
        text2.setLineWrap(true);//启动自动换行
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scroller1);

        //添加第二个文本框
        this.add(new MyJComponent("please input your node"));
        text1 = new JTextArea(10, 20);
        JScrollPane scroller2 = new JScrollPane(text1);
        text1.setLineWrap(true);//启动自动换行
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scroller2);

        //添加按钮
        JButton button3 = new JButton("check probability!");
        button3.addActionListener(this);
        JPanel temjp = new JPanel();
        temjp.add(button3);
        button3.setBounds((this.getWidth() - button3.getWidth())/2,(this.getHeight() - button3.getHeight())/2,button3.getWidth(),button3.getHeight());
        this.add(temjp);
    }

    public void actionPerformed(ActionEvent ev) {
        text1.append("just click me!\n");
    }
}

class myMenubar {
    private JMenu getMymenu(String mennu_name, String items1, String items2) {
        JMenu jm = new JMenu(mennu_name);
        JMenuItem t1 = new JMenuItem(items1);
        JMenuItem t2 = new JMenuItem(items2);
        jm.add(t1);
        jm.add(t2);
        return jm;
    }

    public JMenuBar setMyJMenuBar() {
        JMenuBar br1 = new JMenuBar();
        br1.add(getMymenu("File", "item 1", "item 2"));
        br1.add(getMymenu("Edit", "item 1", "item 2"));
        br1.add(getMymenu("View", "item 1", "item 2"));
        br1.add(getMymenu("Help", "item 1", "item 2"));
        /*this.setJMenuBar(br1);*/
        return br1;
    }
}

class MyJComponent extends JComponent {
    private String title;

    public MyJComponent(String name) {
        this.title = name;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("console", Font.BOLD, 12);
        g2.setFont(font);

        //测量消息大小
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(title, context);

        //set(x,y)
        double x = (getWidth() - bounds.getWidth()) / 2;
        double y = (getHeight() - bounds.getHeight()) / 2;

        //add ascent to y to reach the baseline
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        //draw the baseline
        g2.drawString(title, (int) x, (int) baseY);
        /*g2.set*/
    }
}

class LeftJPanle extends JPanel {
    private JTabbedPane jp;

    public LeftJPanle() {
        jp = new JTabbedPane(JTabbedPane.NORTH);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        jp.add("Main", p1);
        jp.add("main2", p2);
    }

    public JTabbedPane getJp() {
        return jp;
    }
}