package com.example.rgbcircle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class CanvasView extends View implements ICanvasView{

    private GameManager gameManager;
    private static int width;
    private static int height;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;
    public CanvasView (Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        initWidthAndHeight(context);
        gameManager=new GameManager(this,height,width);
        initPain();

    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display=windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }


    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
//        gameManager.onDraw(canvas);
        this.canvas=canvas;
        gameManager.onDraw();

    }

    private void initPain(){
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void drowCircle(SimpleCircle mainCircle) {
        paint.setColor(mainCircle.getColor());
        canvas.drawCircle(mainCircle.getX(),mainCircle.getY(),mainCircle.getR(),paint);

    }

    @Override
    public void reDraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {

        if(toast!=null){
            toast.cancel();
        }
        toast=Toast.makeText(getContext(),text,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    @Override
    public void showDialog(String text){
//        MainActivity activity =new MainActivity();
//        Context context=activity.getBaseContext();
//        Context context=activity.getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(text)
                .setMessage("Click")
                .setIcon(android.R.drawable.btn_star)
                .setCancelable(false)
                .setNegativeButton("Ok!",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }


    @Override
    public int recalculateRadius(int radius) {
        return 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int) event.getX();
        int y=(int) event.getY();
        if (event.getAction()==MotionEvent.ACTION_MOVE){
            gameManager.onTouchEvent(x,y);
        }
    invalidate();
        return true;
//        return super.onFilterTouchEventForSecurity(event);
    }


}
