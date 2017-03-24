package com.example.minh98.ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by minh98 on 22/03/2017.
 */

public class Sang extends SurfaceView implements Runnable,SurfaceHolder.Callback{

    SurfaceHolder surfaceHolder;
    Thread t;
    int x=500,y=700;
    Canvas canvas;
    private float vy=-10;
    private int vx=5;
    private boolean a=true;

    public Sang(Context context) {
        super(context);
        setFocusable(true);
        getHolder().addCallback(this);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.GREEN);
        canvas.drawPaint(p);
        p.setColor(Color.WHITE);
        canvas.drawCircle(x,y,15,p);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceHolder=holder;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        vy=-10;
            if(event.getX()<x){
                vx=-5;
            }else vx=5;
            return super.onTouchEvent(event);
        }

    @Override
    public void run() {
        while(a){canvas=surfaceHolder.lockCanvas();
            try{
                draw(canvas);
                Main();
                chet();
                Thread.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                if(canvas!=null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void Main() {
        x+=vx;
        y+=vy;
        vy+=0.5;
    }
    public void chet(){
        if (x>=getWidth()||x<=0){
            a=false;
        }if (y>=getHeight()){
            a=false;
        }
    }

}

