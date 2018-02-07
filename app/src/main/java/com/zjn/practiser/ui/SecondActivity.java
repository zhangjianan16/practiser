package com.zjn.practiser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;

import com.zjn.practiser.R;

/**
 * Created by Administrator on 2018/2/7.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // 滑动效果
            //                Slide slide = new Slide();
            //                slide.setDuration(300);
            //                getWindow().setExitTransition(slide);//出去的动画
            //                getWindow().setEnterTransition(slide);//进来的动画
            // 展开效果
//            Explode explode = new Explode();
//            explode.setDuration(300);
//            getWindow().setExitTransition(explode);//出去的动画
//            getWindow().setEnterTransition(explode);//进来的动画

             Fade fade = new Fade();
            fade.setDuration(300);
            getWindow().setExitTransition(fade);//出去的动画
            getWindow().setEnterTransition(fade);//进来的动画
        }
    }
}
