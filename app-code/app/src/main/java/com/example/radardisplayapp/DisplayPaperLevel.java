package com.example.radardisplayapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DisplayPaperLevel extends View {
    private Paint fillPaint;
    private Paint backgroundPaint;
    private Paint innerBackground;

    private float level;
    private float padding;


    public DisplayPaperLevel(Context context, AttributeSet attrs){
        super(context,attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DisplayPaperLevel,
                0,0);
        try {
            level = a.getInteger(R.styleable.DisplayPaperLevel_disppaperheight, 10);
        }
        finally {
            a.recycle();
        }
        init();
    }
    private void init(){
        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(0xFFFF0000);
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(0xFF000000);
        innerBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerBackground.setColor(0xFFFFFFFF);
        //level = 60;
        padding = 20;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawRect(0,0,getWidth(),getHeight(),backgroundPaint);

        float colorNum = level/100;
        float s =1.75f;

        int green = (int)  ((255) * (colorNum*s));
        int red = (int) ( (255) * (colorNum * (s-2) + 1 ));
        int blue = 0;

        if(colorNum>0.5) {
            green = (int) ((255) * (colorNum*(2-s)+(s-1)));
            red = (int) ( (255) * ( (colorNum-1) * (-s) ));
        }
        fillPaint.setARGB(255,red,green,blue);

        canvas.drawRect(padding,padding,getWidth()-padding,getHeight()-padding,innerBackground);
        canvas.drawRect(padding,padding + heightFromLevel(),getWidth()-padding,getHeight()-padding,fillPaint);
        canvas.drawText("g:"+green+", r:"+red+", b:"+blue+", with cNum:"+colorNum,30,30,backgroundPaint);
    }

    private float heightFromLevel()
    {
        float full = Math.max(getHeight() - 40,0);

        return full * (1-level/100);
    }

    public void setLevel(float newLevel){
        level = newLevel;
        invalidate();
    }
}
