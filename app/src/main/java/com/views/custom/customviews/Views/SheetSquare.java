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

public class SheetSquare extends View {

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

    Line s1,s2,s3,s4,d1,d2;
    int setup =0,angle=0,oangle=45,offset=150,cangle=0;
    Paint linePaint,circlePaint,paint;
    RectF rectF;

    public SheetSquare(Context context) {
        super(context);
    }

    public SheetSquare(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SheetSquare(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SheetSquare(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (setup==0)
        {
            linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            circlePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setColor(Color.parseColor("#909090"));
            paint.setStrokeCap(Paint.Cap.ROUND);
            circlePaint.setColor(Color.parseColor("#27488E"));
            circlePaint.setStrokeCap(Paint.Cap.ROUND);
            linePaint.setStyle(Paint.Style.STROKE);
            linePaint.setPathEffect(new DashPathEffect(new float[]{20, 20}, 0));
            linePaint.setColor(Color.parseColor("#909090"));
            linePaint.setStrokeCap(Paint.Cap.ROUND);
            rectF = new RectF(190,190,getWidth()-190,getHeight()-190);
            s1 = new Line(offset,offset,getHeight()-offset,getHeight()-offset);
            s2 = new Line(getWidth()-offset,getWidth()-offset,getHeight()-offset,getHeight()-offset);
            s3 = new Line(getWidth()-offset,getWidth()-offset,offset,offset);
            s4 = new Line(offset,offset,offset,offset);
            d1 = new Line(0,0,0,0);
            d2 = new Line(0,0,getHeight(),getHeight());
            setup = 1;
        }
        if (s4.getY2()>=getHeight()-offset&&angle<=45)
        {
            canvas.save();
            canvas.rotate(angle++,getWidth()/2,getHeight()/2);
        }
        else if(angle>45&&oangle==45)
        {
            canvas.rotate(45,getWidth()/2,getHeight()/2);
        }
        else if (oangle!=45)
        {
            canvas.rotate(oangle,getWidth()/2,getHeight()/2);
        }
        canvas.drawLine(s1.getX1(),s1.getY1(),s1.getX2(),s1.getY2(),paint);
        if (s1.getX2()<getWidth()-offset)
        {
            s1.setX2(s1.getX2()+3);
            postInvalidate();
        }
        canvas.drawLine(s2.getX1(),s2.getY1(),s2.getX2(),s2.getY2(),paint);
        if (s1.getX2()>=getWidth()-offset&&s2.getY2()>offset)
        {
            s2.setY2(s2.getY2()-3);
            postInvalidate();
        }
        canvas.drawLine(s3.getX1(),s3.getY1(),s3.getX2(),s3.getY2(),paint);
        if (s2.getY2()<=offset&&s3.getX2()>offset)
        {
            s3.setX2(s3.getX2()-3);
            postInvalidate();
        }
        canvas.drawLine(s4.getX1(),s4.getY1(),s4.getX2(),s4.getY2(),paint);
        if (s3.getX2()<=offset&&s4.getY2()<getHeight()-offset)
        {
            s4.setY2(s4.getY2()+3);
            postInvalidate();
        }
        if (s4.getY2()>=getHeight()-offset&&angle<=45)
        {
            try {
                postInvalidate();
            }catch (Exception e){}
        }
        canvas.drawLine(d1.getX1(),d1.getY1(),d1.getX2(),d1.getY2(),linePaint);
        if(angle>45 && (d1.getX2()<getWidth()&&d1.getY2()<getHeight()))
        {

            d1.setX2(d1.getX2()+3);
            d1.setY2(d1.getY2()+3);
            postInvalidate();
        }
        canvas.drawLine(d2.getX1(),d2.getY1(),d2.getX2(),d2.getY2(),linePaint);
        if((d1.getX2()>=getWidth()&&d1.getY2()>=getHeight())&&(d2.getY2()>0&&d2.getX2()<getWidth()))
        {

            d2.setX2(d2.getX2()+3);
            d2.setY2(d2.getY2()-3);
            postInvalidate();
        }
        canvas.drawArc(rectF,0,cangle,false,circlePaint);
        if((d2.getY2()<=0&&d2.getX2()>=getWidth())&&cangle<360)
        {
            cangle++;
            postInvalidate();
        }
        if (cangle>=360 && oangle<=90)
        {
            oangle=oangle+1;
            postInvalidate();
        }
    }
}
