package com.example.rgbcircle;

public class SimpleCircle {

    protected int x,y,r,color;

    public SimpleCircle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getColor() {
        return color;
    }
    public void setColor(int color){
        this.color=color;
    }

    public SimpleCircle getCircleArea() {
            return new SimpleCircle(x,y,r*3);
    }

    public boolean isInteesect(SimpleCircle mainCircleArea) {
        return r+mainCircleArea.r>=Math.sqrt(Math.pow(x-mainCircleArea.x,2)+Math.pow(y-mainCircleArea.y,2));
    }
}
