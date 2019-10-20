package com.views.custom.customviews.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class SheetCircle extends View {

    class Line{
        int x1,x2,y1,y2;

        Line(int x1,int x2,int y1,int y2)
        {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        public int getX1() {
            return x1;
        }

        public int getX2() {
            return x2;
        }

        public void setX2(int x2) {
            this.x2 = x2;
        }

        public int getY1() {
            return y1;
        }

        public int getY2() {
            return y2;
        }

        public void setY2(int y2) {
            this.y2 = y2;
        }
    }

    int setup =0,angle=0,mangle=0;
    //vertical line and horizontal line
    Line vline;
    Line hline;
    Paint linePaint,circlePaint,paint;
    RectF rectF,mRectF;

    public SheetCircle(Context context) {
        super(context);
    }

    public SheetCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SheetCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SheetCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (setup==0) {
            linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setColor(Color.parseColor("#909090"));
            circlePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStrokeWidth(3);
            circlePaint.setColor(Color.parseColor("#27488E"));
            circlePaint.setStrokeCap(Paint.Cap.ROUND);
            rectF = new RectF(80,80,getWidth()-80,getHeight()-80);
            mRectF = new RectF(150,150,getWidth()-150,getHeight()-150);
            linePaint.setStyle(Paint.Style.STROKE);
            linePaint.setPathEffect(new DashPathEffect(new float[]{20, 20}, 0));
            linePaint.setColor(Color.parseColor("#909090"));
            linePaint.setStrokeCap(Paint.Cap.ROUND);
            vline = new Line(getWidth() / 2, getWidth() / 2, 0, 0);
            hline = new Line(0,0,getHeight()/2,getHeight()/2);
            setup = 1;
        }
        canvas.drawLine(vline.getX1(),vline.getY1(),vline.getX2(),vline.getY2(),paint);
        if(vline.getY2()<=getHeight())
        {
            vline.setY2(vline.getY2()+3);
            postInvalidate();
        }
        canvas.drawLine(hline.getX1(),hline.getY1(),hline.getX2(),hline.getY2(),paint);
        if(hline.getX2()<=getWidth() && vline.getY2()>=getHeight()){
            hline.setX2(hline.getX2()+3);
            postInvalidate();
        }
        canvas.drawArc(rectF,0,angle,false,circlePaint);
        if ((hline.getX2()>=getWidth() && vline.getY2()>=getHeight() )&& angle<=360)
        {
            angle = angle+1;
            postInvalidate();
        }
        canvas.drawArc(mRectF,0,mangle,false,linePaint);
        if(angle>=360&&mangle<=360)
        {
            mangle = mangle+1;
            postInvalidate();
        }
    }
}
