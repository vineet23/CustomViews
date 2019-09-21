package com.views.custom.customviews.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BagHandle extends View {

    int radius;
    int setup = 0;
    int case_ = 1;
    float theta =0;
    boolean animate = false;
    float threshold = 0.25f;
    int px,py,fx,fy;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public BagHandle(Context context) {
        super(context);
    }

    public BagHandle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BagHandle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public BagHandle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    void init(@Nullable AttributeSet set){}

    //to start the animation
    public void startAnimate(){
        animate =true;
        postInvalidate();
    }

    //animates the rope
    void OnAnimate(Canvas canvas){
        if (case_ == 1) {
            if (theta != 0) {
                fx = (int) (px + radius * Math.sin(theta));
                fy = (int) (py - radius * (1 - Math.cos(theta)));
            }
            path.moveTo(50, 50);
            path.cubicTo(50, 50, fx, fy, getWidth() - 50, 50);
            paint.setColor(Color.parseColor("#ffffff"));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setStrokeCap(Paint.Cap.ROUND);

            canvas.drawPath(path, paint);
            path.rewind();

            theta = theta - 0.025f;
            if (theta <= -threshold)
            {
                case_ = 2;
                threshold = threshold - 0.05f;
            }
        } else if (case_ == 2) {
            fx = (int) (px + radius * Math.sin(theta));
            fy = (int) (py - radius * (1 - Math.cos(theta)));
            path.moveTo(50, 50);
            path.cubicTo(50, 50, fx, fy, getWidth() - 50, 50);
            paint.setColor(Color.parseColor("#ffffff"));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setStrokeCap(Paint.Cap.ROUND);

            canvas.drawPath(path, paint);
            path.rewind();

            theta = theta + 0.025f;
            if (theta >= threshold)
                case_ = 1;
        } else {
            theta = 0;
            setup = 0;
        }
        //todo change an offset for theta inc or dec depending on threshold i.e low threshold low speed of animation
        if(threshold<=0)
        {
            animate=false;
            threshold = 0.25f;
        }
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#80000000"));

        canvas.drawCircle(50,50,20,paint);
        canvas.drawCircle(getWidth()-50,50,20,paint);

        //first time when the view is created
        if(setup==0) {
            radius = getHeight() * 2 - 100;
            px = getWidth() / 2;
            py = radius;
            fx = px;
            fy = py;
            setup=1;
        }

        //to decide whether to animate or not
        if(animate) {
           OnAnimate(canvas);
        }
        else {
            radius = getHeight() * 2 - 100;
            px = getWidth() / 2;
            py = radius;
            fx = px;
            fy = py;
            path.moveTo(50, 50);
            path.cubicTo(50, 50, fx, fy, getWidth() - 50, 50);
            paint.setColor(Color.parseColor("#ffffff"));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setStrokeCap(Paint.Cap.ROUND);

            canvas.drawPath(path, paint);
            path.rewind();
        }
    }
}
