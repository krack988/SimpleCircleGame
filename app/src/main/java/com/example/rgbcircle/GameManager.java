package com.example.rgbcircle;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class GameManager {


    private static int width;
    private static int height;
    private CanvasView canvasView;
    private ArrayList<EnemyCircle> circles;
    public static final int MAX_CIRCLES=10;

    private MainCircle mainCircle;

    public GameManager(CanvasView canvasView, int h, int w){
        this.canvasView=canvasView;
        width=w;
        height=h;
        initMainCircle();
        initEnemyCircles();

    }

    private void initEnemyCircles() {
        SimpleCircle mainCircleArea=mainCircle.getCircleArea();
        circles=new ArrayList<>();
        for (int i=0;i<MAX_CIRCLES;i++){
            EnemyCircle circle;
            do {
                circle = EnemyCircle.getRandomCircle();
            }while(circle.isInteesect(mainCircleArea));
            circles.add(circle);
        }
        calculateAndSetCircleColor();
    }

    private void calculateAndSetCircleColor() {
        for(EnemyCircle circle:circles){
            circle.setEnemyOrFoodColorDependsOn(mainCircle);
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public void onDraw(){
        canvasView.drowCircle(mainCircle);

        for (EnemyCircle circle:circles
             ) {
            canvasView.drowCircle(circle);
        }

    }

    private void initMainCircle(){
        mainCircle=new MainCircle(width/2,height/2);
    }

    public void onTouchEvent(int x, int y) {

        mainCircle.moveMainCircleWhenTouchAt(x,y);
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        SimpleCircle circleForDelete=null;
        for(EnemyCircle circle:circles){
            if (mainCircle.isInteesect(circle)){
                if (circle.isSmallerThan(mainCircle)){
                    mainCircle.growRadius(circle);
                    circleForDelete=circle;
                    calculateAndSetCircleColor();
                    break;
                }else {
                    gameEnd("YUO LOOSE!!!");
                    return;
                }
            }
        }
        if (circleForDelete!=null){
            circles.remove(circleForDelete);
        }
        if (circles.isEmpty()){
            gameEnd("YOU WIN!!!");
        }
    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.reDraw();
    }

    private void moveCircles() {
        for(EnemyCircle circle:circles){
            circle.moveOnStep();
        }
    }
}
