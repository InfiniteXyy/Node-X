package com.homework.utils;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class TextAreaPlaceHolder extends JTextArea{

    private String placeholder;

    public TextAreaPlaceHolder() {
    }

    public TextAreaPlaceHolder(int row, int col) {
        super(row, col);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);

    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}
