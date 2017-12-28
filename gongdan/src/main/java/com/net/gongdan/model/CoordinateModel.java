package com.net.gongdan.model;

public class CoordinateModel {

    private Double axis_X;
    private Double axis_Y;


    private Boolean select = false;

    public Double getX() {
        return axis_X;
    }

    public void setX(Double axis_x) {
        this.axis_X = axis_x;
    }

    public Double getY() {
        return axis_Y;
    }

    public void setY(Double axis_y) {
        this.axis_Y = axis_y;
    }
}
