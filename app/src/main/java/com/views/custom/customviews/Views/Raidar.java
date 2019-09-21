package com.views.custom.customviews.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Raidar extends View {

    class RCircle{
        int radius;
        String color;

        RCircle(int radius,String color){
            this.radius = radius;
            this.color = color;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    ArrayList<RCircle> rCircles = new ArrayList<>();
    int side;
    int setup = 0;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Raidar(Context context) {
        super(context);
    }

    public Raidar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Raidar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Raidar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(getHeight()<=getWidth())
            side = getHeight();
        else
            side = getWidth();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#000000"));

        if(setup==0){
            rCircles.add(new RCircle((side/3)/2,"#9939ff14"));
            rCircles.add(new RCircle(((2*side)/3)/2,"#5039ff14"));
            rCircles.add(new RCircle(side/2,"#2539ff14"));
            setup =1;
        }

        for(RCircle rCircle : rCircles){
            paint.setColor(Color.parseColor(rCircle.getColor()));
            canvas.drawCircle(getWidth()/2,getHeight()/2,rCircle.getRadius(),paint);
            rCircle.setRadius(rCircle.getRadius()+1);
            if(rCircle.getRadius()>=side/2)
                rCircle.setRadius(0);
            if(rCircle.getRadius()<=(side/3)/2)
                rCircle.setColor("#9939ff14");
            else if(rCircle.getRadius()<=((2*side)/3)/2)
                rCircle.setColor("#5039ff14");
            else if(rCircle.getRadius()<=(side/2)-20)
                rCircle.setColor("#2539ff14");
            else
                rCircle.setColor("#1039ff14");
        }

        postInvalidate();

    }
}
