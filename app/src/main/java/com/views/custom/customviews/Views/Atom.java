package com.views.custom.customviews.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Atom extends View {

    int side;
    int theta = 0;
    Paint neon = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Atom(Context context) {
        super(context);
    }

    public Atom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Atom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Atom(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    void init(@Nullable AttributeSet set){

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();
        canvas.rotate(theta,getWidth()/2,getHeight()/2);
        if(getHeight()<=getWidth())
        {
            side = getHeight();
        }else {
            side = getWidth();
        }

        int centre = side/2;
        int dx = (int) (centre/1.2);
        int dy = (int) (centre/2.5);

        neon.setStrokeWidth(7);
        neon.setStyle(Paint.Style.STROKE);
        neon.setColor(Color.rgb(21, 244, 238));

        int x = centre-dx;
        int y = centre-dy;
        int xh = centre+dx;
        int yh = centre+dy;

        for(int i=0;i<=360;i=i+45) {
            canvas.drawOval(x, y, xh, yh, neon);
            canvas.rotate(i,getWidth()/2,getHeight()/2);
        }

        neon.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centre,centre,centre/6,neon);

        canvas.restore();
        if(theta!=360)
            theta = theta +1;
        else
            theta = 0;
        postInvalidate();

    }
}
