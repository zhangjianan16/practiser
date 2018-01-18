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
        toolbar.setAlpha(alpha);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
