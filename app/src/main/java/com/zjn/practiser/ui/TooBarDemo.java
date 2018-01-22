package com.zjn.practiser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.Window;

import com.zjn.practiser.R;
import com.zjn.practiser.listener.TranlucentListener;
import com.zjn.practiser.view.MyScrollView;


/**
 * Created by Administrator on 2018/1/18.
 */

public class TooBarDemo extends AppCompatActivity implements TranlucentListener{

    public MyScrollView myScrollView;
    public Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_toobar);
        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myScrollView.setTranlucentListener(this);
    }

    @Override
    public void setTranlucentListener(float alpha) {
        //   android:clipChildren="false"//子控件是否不能超出padding的区域（比如scroollview 上滑动的时候，child可以滑出该区域）
        // android:clipToPadding="false" 改控件的绘制范围是否不在padding里面。 false绘制的时候范围会考虑padding值即会往里面缩进
        toolbar.setAlpha(alpha);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
