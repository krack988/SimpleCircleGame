package com.example.rgbcircle;


public interface ICanvasView {

    void drowCircle(SimpleCircle mainCircle);
    void reDraw();
    void showMessage(String text);
    int recalculateRadius(int radius);
}
