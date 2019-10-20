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

public class SheetTriangle extends View {

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

    int setup =0,angle=0;
    Line vline,hline,sline;
    Paint linePaint,circlePaint;
    RectF rectF;

    public SheetTriangle(Context context) {
        super(context);
    }

    public SheetTriangle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SheetTriangle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SheetTriangle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet attributeSet)
    {}

    @Override
    protected void onDraw(Canvas canvas) {
        if (setup==0)
        {
            linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            circlePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStrokeWidth(3);
            circlePaint.setColor(Color.parseColor("#27488E"));
            circlePaint.setStrokeCap(Paint.Cap.ROUND);
            rectF = new RectF(0,0,getWidth()*2,getHeight()*2);
            linePaint.setStyle(Paint.Style.STROKE);
            linePaint.setPathEffect(new DashPathEffect(new float[]{20, 20}, 0));
            linePaint.setColor(Color.parseColor("#909090"));
            linePaint.setStrokeCap(Paint.Cap.ROUND);
            vline = new Line(getWidth(), getWidth() , getHeight(), getHeight());
            hline = new Line(0,0,getHeight(),getHeight());
            sline = new Line(getWidth(),getWidth(),0,0);
            setup = 1;
        }
        canvas.drawLine(hline.getX1(),hline.getY1(),hline.getX2(),hline.getY2(),linePaint);
        if(hline.getX2()<getWidth())
        {
            hline.setX2(hline.getX2()+3);
            postInvalidate();
        }
        canvas.drawLine(vline.getX1(),vline.getY1(),vline.getX2(),vline.getY2(),linePaint);
        if(hline.getX2()>=getWidth()&&vline.getY2()>0)
        {
            vline.setY2(vline.getY2()-3);
            postInvalidate();
        }
        canvas.drawLine(sline.getX1(),sline.getY1(),sline.getX2(),sline.getY2(),linePaint);
        if ((hline.getY2()>=getWidth()&&vline.getY2()<=0)&&(sline.getX2()>0&&sline.getY2()<getHeight()))
        {
            sline.setY2(sline.getY2()+3);
            sline.setX2(sline.getX2()-3);
            postInvalidate();
        }
    }
}
