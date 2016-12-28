package com.example.rgbcircle;

import android.graphics.Color;

import java.util.Random;

public class EnemyCircle extends SimpleCircle{
    public static final int FROM_RAD=10;
    public static final int TO_RAD=110;
    public static final int RANDOM_SPEED=10;
    private int dx,dy;
    public EnemyCircle(int x, int y, int r,int dx,int dy) {

        super(x, y, r);
        this.dx=dx;
        this.dy=dy;
    }

    public static EnemyCircle getRandomCircle() {
        Random random=new Random();
        int x=random.nextInt(GameManager.getWidth());
        int y=random.nextInt(GameManager.getHeight());
        int dx=1+random.nextInt(RANDOM_SPEED);
        int dy=1+random.nextInt(RANDOM_SPEED);
        int r=FROM_RAD+random.nextInt(TO_RAD-FROM_RAD);
        EnemyCircle enemyCircle=new EnemyCircle(x,y,r,dx,dy);
        enemyCircle.setColor(Color.RED);
        return enemyCircle;
    }

    public void setEnemyOrFoodColorDependsOn(MainCircle mainCircle) {
        if (isSmallerThan(mainCircle)){
            setColor(Color.rgb(0,200,0));
        }else{
            setColor(Color.RED);
        }
    }

    public boolean isSmallerThan(SimpleCircle simpleCircle) {
        if (r<simpleCircle.r){
            return true;
        }

        return false;
    }

    public void moveOnStep() {
        x+=dx;
        y+=dy;
        checkBound();
    }

    private void checkBound() {
        if (x>GameManager.getWidth()||x<0){
            dx=-dx;
        }
        if (y>GameManager.getHeight()||y<0){
            dy=-dy;
        }
    }
}
