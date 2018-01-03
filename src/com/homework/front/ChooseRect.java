package com.homework.front;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ChooseRect extends Rectangle2D.Double{
    boolean isDragging;
    Point dragPoint;
    boolean isSelected;

    public ChooseRect() {
        isDragging = false;
        isSelected = false;
    }

    public void update(Point point) {
        int outX = point.x;
        int outY = point.y;
        int inX = dragPoint.x;
        int inY = dragPoint.y;

        if (outX > inX) {
            if (outY > inY) {
                setFrame(inX, inY, outX - inX, outY - inY);
            } else {
                setFrame(inX, outY, outX - inX, inY - outY);
            }
        } else {
            if (outY > inY) {
                setFrame(outX, inY, inX - outX, outY - inY);
            } else {
                setFrame(outX, outY, inX - outX, inY - outY);
            }
        }
    }

    public void setDrag(Point drag) {
        this.dragPoint = drag;
        isDragging = true;
        isSelected = true;
    }
}
