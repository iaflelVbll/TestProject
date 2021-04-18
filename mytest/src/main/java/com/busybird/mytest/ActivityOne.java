package com.busybird.mytest;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends AppCompatActivity {

    private Button mButton1;
    private ValueAnimator valueAnimator;
    final int startX = 0;
    final int deltaX = -200;
    private View rootView;
    private TestView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_two);
        rootView = findViewById(R.id.rootView);
        mButton1 = (Button) findViewById(R.id.mButton1);
        tv_test = (TestView)findViewById(R.id.tv_test);
        valueAnimator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                rootView.scrollTo(startX+(int)(deltaX*animatedFraction),0);
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator.start();
            }
        });

    }
//    public void jumpToTwo(View v) {
//        Intent intent = new Intent(this, ScrollingActivity.class);
//        startActivity(intent);
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
