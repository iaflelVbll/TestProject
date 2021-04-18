package com.busybird.mytest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by WuSihai on 2017/2/4.
 */

public class TestView extends View {

    private static final String TAG = "TestView";
    private int lastX;
    private int lastY;
    public TestView(Context context) {
        super(context);
        ininView();
    }
    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ininView();
    }
    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ininView();
    }
    private void ininView() {
        setBackgroundColor(Color.BLUE);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.d(TAG,"getX=="+x+";getY=="+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
        }
        return true;
    }
}
