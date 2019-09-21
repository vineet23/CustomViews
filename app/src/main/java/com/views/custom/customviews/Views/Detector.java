package com.views.custom.customviews.Views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Detector extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int top,bottom,left,right;
    int uh=0,uw=0;
    int setup=0;
    int color = 99;

    public Detector(Context context) {
        super(context);
    }

    public Detector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Detector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Detector(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    void init(@Nullable AttributeSet set){}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static public Path RoundedRect(float left, float top, float right, float bottom, float rx) {
        Path path = new Path();
        if (rx < 0) rx = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width/2) rx = width/2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * rx));

        path.moveTo(right, top + rx);
        path.arcTo(right - 2*rx, top, right, top + 2*rx, 0, -90, false); //top-right-corner
        path.rLineTo(-widthMinusCorners, 0);
        path.arcTo(left, top, left + 2*rx, top + 2*rx, 270, -90, false);//top-left corner.
        path.rLineTo(0, heightMinusCorners);
        path.arcTo(left, bottom - 2 * rx, left + 2 * rx, bottom, 180, -90, false); //bottom-left corner
        path.rLineTo(widthMinusCorners, 0);
        path.arcTo(right - 2 * rx, bottom - 2 * rx, right, bottom, 90, -90, false); //bottom-right corner

        path.rLineTo(0, -heightMinusCorners);

        path.close();//Given close, last lineto can be removed.
        return path;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        int cx=getWidth()/2,cy=getHeight()/2;

        if(setup==0) {
            uh = cy / 2;
            uw = cx / 2;
            color=99;
            paint.setColor(Color.parseColor("#"+color+"ffffff"));
            setup=1;
        }

        top = cy-uh;
        bottom = cy+uh;
        left = cx-uw;
        right = cx+uw;

        Path path = RoundedRect(left,top,right,bottom,uw/4);

        canvas.drawPath(path,paint);

        uh++;
        uw++;
        color--;
        if(color<10)
            color=10;

        if(uh>=cy)
           setup=0;
        if(uw>=cx)
            setup=0;

        paint.setColor(Color.parseColor("#"+color+"ffffff"));

        postInvalidate();
    }
}
