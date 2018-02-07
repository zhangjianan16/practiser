package com.zjn.practiser.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.zjn.practiser.R;

/**
 * Created by Administrator on 2018/2/7.
 */

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView iv1;
    public Button btn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 允许使用转场动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_first);
        iv1 = (ImageView) findViewById(R.id.iv1);
        btn1 = (Button) findViewById(R.id.btn1);
        iv1.setOnClickListener(this);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv1) {
            //            ActivityOptionsCompat optionsCompat =ActivityOptionsCompat.makeSceneTransitionAnimation(
            //                    this,//当前的activity
            //                    iv1,//共享元素----那个view
            //                    "iv_meinv3");//共享元素名称      android:transitionName="iv_meinv3"
            //            Intent intent=new Intent(this,SecondActivity.class);
            //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //                startActivity(intent,optionsCompat.toBundle());
            //            }

//             多个共享元素
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(
                            this,//当前的activity
                            new Pair[]{Pair.create(iv1, "iv_meinv3"), Pair.create(btn1, "bt")});
            Intent intent = new Intent(this, SecondActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent, optionsCompat.toBundle());
            }


//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
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

                //隐藏渐变效果
//                Fade fade = new Fade();
//                fade.setDuration(300);
//                getWindow().setExitTransition(fade);//出去的动画
//                getWindow().setEnterTransition(fade);//进来的动画

            //如果用共享元素 可以设置共享元素 那么它就会按照共享元素动画执行，其他的子view按照fade动画执行
//            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
//                    makeSceneTransitionAnimation(
//                            this);
//            Intent intent = new Intent(this, SecondActivity.class);
//
//                startActivity(intent, optionsCompat.toBundle());
//            }

        }
    }
}
