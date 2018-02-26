package com.zjn.practiser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zjn.practiser.R;
import com.zjn.practiser.view.ParallaxContainer;

/**
 * Created by Administrator on 2018/2/18.
 */

public class ParallaxContainerActivity extends AppCompatActivity {

    public ParallaxContainer parallaxContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax_container);
        parallaxContainer = (ParallaxContainer) findViewById(R.id.parallax_container);
        parallaxContainer.setUp(new int[]{R.layout.view_intro_1,R.layout.view_intro_2,R.layout.view_intro_3,R.layout.view_intro_4,R.layout.view_intro_5,R.layout.view_login});
    }
}
