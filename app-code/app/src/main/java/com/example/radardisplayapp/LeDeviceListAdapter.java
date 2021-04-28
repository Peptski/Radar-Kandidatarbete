package com.example.radardisplayapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class LeDeviceListAdapter extends View {

    private List<BluetoothDevice> ls;
    private int debugNum;

    public LeDeviceListAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LeDeviceListAdapter,
                0, 0);

        ls = new ArrayList<BluetoothDevice>();
        debugNum = 1;
    }

    public void add(BluetoothDevice bleDevice){
        ls.add(bleDevice);
    }

    public void reset(){
        ls = new ArrayList<BluetoothDevice>();
        debugNum ++;
    }

    public void inc(){
        debugNum ++;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setARGB(255,255,255,255);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setARGB(255,0,0,0);
        int height = 70;
        String text;
        for (BluetoothDevice bleDevice : ls){
            text = "Name: " + bleDevice.getName();
            canvas.drawText(text,5,height,paint);
            height += 60;
        }
        paint.setTextSize(60f);
        canvas.drawText("Anything?" + debugNum,30,height,paint);
        height += 60;
        canvas.drawText(ls.size() + "",30,height,paint);
    }
}
