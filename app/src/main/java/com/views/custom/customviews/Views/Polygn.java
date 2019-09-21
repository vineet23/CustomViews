package com.views.custom.customviews.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Polygn extends View {

    int side;
    float rotate = 0f;
    int[] x = new int[6];
    int[] y = new int[6];

    public Polygn(Context context) {
        super(context);
        init(null);
    }

    public Polygn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Polygn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Polygn(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attributeSet){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(rotate,getWidth()/2,getHeight()/2);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(8);
        paint.setColor(Color.rgb(212,175,55));

        if(getHeight() <= getWidth())
           side = getHeight();
        else
           side = getWidth();

        int dx = (side/2)-50;

        x[0] = side/2;
        y[0] = 50;
        x[1] = side-50;
        y[1] = (side/2)-(dx/2);
        x[2] = side-50;
        y[2] = (side/2)+(dx/2);
        x[3] = side/2;
        y[3] = side-50;
        x[4] = 50;
        y[4] = (side/2)+(dx/2);
        x[5] = 50;
        y[5] = (side/2)-(dx/2);

        for(int i=0;i<6;i++)
        {
            for(int j=i+1;j<6;j++)
            {
                canvas.drawLine(x[i],y[i],x[j],y[j],paint);
            }
        }

        canvas.restore();
        if(rotate!=360)
            rotate = rotate +1;
        else
            rotate = 0;
        postInvalidate();
    }

}
