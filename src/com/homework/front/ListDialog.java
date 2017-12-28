package com.homework.front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class ListDialog {
    private JList list;
    private JLabel label;
    private JLabel plabel;
    private JTextField textField;
    private JOptionPane optionPane;
    private JButton setButton, cancelButton;
    private ActionListener setEvent, cancelEvent;
    private JDialog dialog;
    private Map<String, Double> map;

    public ListDialog(String message, Map<String, Double> map){
        this.map = map;
        list = new JList(map.keySet().toArray());
        label = new JLabel(message);
        textField = new JTextField(8);
        plabel = new JLabel("Probability: - ");

        createAndDisplayOptionPane();
    }

    public ListDialog(String title, String message, Map<String, Double> map){
        this(message, map);
        dialog.setTitle(title);
    }

    private void createAndDisplayOptionPane(){
        setupButtons();
        JPanel pane = layoutComponents();
        optionPane = new JOptionPane(pane);
        optionPane.setOptions(new Object[]{setButton, cancelButton});
        dialog = optionPane.createDialog("Change probability");
    }

    private void setupButtons(){
        setButton = new JButton("Set");
        setButton.addActionListener(e -> handleSetButtonClick(e));

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> handleCancelButtonClick(e));

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object choose = list.getSelectedValue();
                plabel.setText("Probability: " + String.format("%.2f",map.get(choose)));
            }
        });
    }

    private JPanel layoutComponents(){
        centerListElements();
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(label, BorderLayout.NORTH);

        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(plabel, BorderLayout.WEST);
        jPanel.add(textField, BorderLayout.EAST);

        panel.add(jPanel, BorderLayout.CENTER);
        panel.add(list, BorderLayout.SOUTH);
        return panel;
    }

    private void centerListElements(){
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setOnChange(ActionListener event){ setEvent = event; }

    public void setOnClose(ActionListener event){
        cancelEvent  = event;
    }

    private void handleSetButtonClick(ActionEvent e){
        if(setEvent != null){ setEvent.actionPerformed(e); }
    }

    private void handleCancelButtonClick(ActionEvent e){
        if(cancelEvent != null){ cancelEvent.actionPerformed(e);}
        hide();
    }



    public void show(){ dialog.setVisible(true); }

    public void hide(){ dialog.setVisible(false); }

    public Object getSelectedItem(){ return list.getSelectedValue(); }

    public String getInputValue(){ return textField.getText(); }

}