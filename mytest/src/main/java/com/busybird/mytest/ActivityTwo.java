package com.busybird.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * 第二个界面
 * Created by WuSihai on 2018/1/31.
 */

public class ActivityTwo extends Activity {

    private CheckView checkView;
    private Button btn_check;
    private Button btn_uncheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initListener();
    }

    private void initListener() {
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.check();
            }
        });
        btn_uncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.unCheck();
            }
        });
    }

    private void initUI() {
        setContentView(R.layout.activity_test_check_layout);
        checkView = (CheckView) findViewById(R.id.checkView);
        btn_check = (Button)findViewById(R.id.btn_check);
        btn_uncheck = (Button)findViewById(R.id.btn_uncheck);
    }
}
