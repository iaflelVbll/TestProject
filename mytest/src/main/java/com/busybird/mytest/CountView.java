package com.busybird.mytest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by WuSihai on 2017/2/9.
 */

public class CountView extends View implements View.OnClickListener{

    private int mTxt_Color;
    private Paint mPaint;
    private Rect mBounds;
    private int mBg_Color;
    private int mCount;

    public CountView(Context context) {
        super(context);
        init();
    }

    public CountView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CountView);
        mBg_Color = a.getColor(R.styleable.CountView_bg_count_color, Color.RED);
        mTxt_Color = a.getColor(R.styleable.CountView_txt_color, Color.RED);
        a.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMeasureMode == MeasureSpec.AT_MOST && heightMeasureMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if(widthMeasureMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heightMeasureSize);
        }else if(heightMeasureMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthMeasureSize,200);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mBg_Color);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        canvas.drawRect(0,0,width,height,mPaint);
        mPaint.setColor(mTxt_Color);
        mPaint.setTextSize(50);
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text,0,text.length(),mBounds);
        float text_width = mBounds.width();
        float text_height = mBounds.height();
        canvas.drawText(text,width/2-text_width/2,height/2+text_height/2,mPaint);
    }
}
