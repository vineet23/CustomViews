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
    Line vline,hline,sline,d1,m1,m2;
    Paint linePaint,circlePaint,paint;
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
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            circlePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setColor(Color.parseColor("#909090"));
            paint.setStrokeCap(Paint.Cap.ROUND);
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
            d1 = new Line(0,0,0,0);
            m1 = new Line(0,0,getHeight()/2,getHeight()/2);
            m2 = new Line(getWidth()/2,getWidth()/2,0,0);
            setup = 1;
        }
        canvas.drawLine(hline.getX1(),hline.getY1(),hline.getX2(),hline.getY2(),paint);
        if(hline.getX2()<getWidth())
        {
            hline.setX2(hline.getX2()+3);
            postInvalidate();
        }
        canvas.drawLine(vline.getX1(),vline.getY1(),vline.getX2(),vline.getY2(),paint);
        if(hline.getX2()>=getWidth()&&vline.getY2()>0)
        {
            vline.setY2(vline.getY2()-3);
            postInvalidate();
        }
        canvas.drawLine(sline.getX1(),sline.getY1(),sline.getX2(),sline.getY2(),paint);
        if ((hline.getY2()>=getWidth()&&vline.getY2()<=0)&&(sline.getX2()>0&&sline.getY2()<getHeight()))
        {
            sline.setY2(sline.getY2()+3);
            sline.setX2(sline.getX2()-3);
            postInvalidate();
        }
        canvas.drawArc(rectF,180,angle,false,circlePaint);
        if((sline.getX2()<=0||sline.getY2()>=getHeight())&&angle<90)
        {
            angle = angle +1;
            postInvalidate();
        }
        canvas.drawLine(d1.getX1(),d1.getY1(),d1.getX2(),d1.getY2(),linePaint);
        if(angle>=90 && (d1.getX2()<getWidth()&&d1.getY2()<getHeight()))
        {
            d1.setX2(d1.getX2()+3);
            d1.setY2(d1.getY2()+3);
            postInvalidate();
        }
        canvas.drawLine(m1.getX1(),m1.getY1(),m1.getX2(),m1.getY2(),linePaint);
        if((d1.getX2()>=getWidth()&&d1.getY2()>=getHeight())&&m1.getX2()<getWidth())
        {
            m1.setX2(m1.getX2()+3);
            postInvalidate();
        }
        canvas.drawLine(m2.getX1(),m2.getY1(),m2.getX2(),m2.getY2(),linePaint);
        if (m1.getX2()>=getWidth()&&m2.getY2()<getHeight())
        {
            m2.setY2(m2.getY2()+3);
            postInvalidate();
        }
    }
}
