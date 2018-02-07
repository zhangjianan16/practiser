package com.zjn.practiser.ui;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.zjn.practiser.R;

/**
 * Created by Administrator on 2018/1/31.
 */

public class BehaviorDemo extends AppCompatActivity {

    public TextView tv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavoir);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // 圆形水波纹揭露效果 5.0以后
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator circularReveal = ViewAnimationUtils.createCircularReveal(v, v.getWidth() / 2, v.getHeight() / 2, 0, v.getHeight());
                    circularReveal.setDuration(2000);
                    circularReveal.setInterpolator(new AccelerateDecelerateInterpolator());
                    circularReveal.start();
                }
            }
        });
    }
}
