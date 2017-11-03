package com.zjn.practiser.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zjn.practiser.R;
import com.zjn.practiser.databinding.ActivityCirclewaveDivergenceBinding;

/**
 * Created by Marks zhang on 2017/11/3.
 */

public class CircleWaveDivergenceViewActivity extends Activity{

    public ActivityCirclewaveDivergenceBinding bindinging;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindinging = DataBindingUtil.setContentView(this, R.layout.activity_circlewave_divergence);
        // 设置后viewgroup才会走ondraw方法
        bindinging.circlrwava.setWillNotDraw(false);
    }
    public void  start(View view){
        bindinging.circlrwava.setSearchingFlag(true);
    }
    public void  stop(View view){
        bindinging.circlrwava.setSearchingFlag(false);
    }

}
