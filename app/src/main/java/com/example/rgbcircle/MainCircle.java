package com.example.rgbcircle;

import android.graphics.Color;

public class MainCircle extends SimpleCircle{


    public static final int RADIUS=50;
    public static final int SPEED=300;

    public MainCircle(int x, int y) {
        super(x, y,RADIUS);
        setColor(Color.BLUE);
    }

    public void moveMainCircleWhenTouchAt(int x1, int y1) {
    int dx=(x1-x)*SPEED/GameManager.getWidth();
        int dy=(y1-y)*SPEED/GameManager.getHeight();
        x+=dx;
        y+=dy;

    }

    public void initRadius() {
        r=RADIUS;
    }

    public void growRadius(SimpleCircle circle) {
        r=(int) Math.sqrt(Math.pow(r,2)+Math.pow(circle.r,2));
    }
}
