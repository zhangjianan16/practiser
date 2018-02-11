package com.zjn.practiser.ui;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zjn.practiser.R;

/**
 * Created by Administrator on 2018/2/11.
 */

public class SVGPathAnimatorActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_adnimator);
      ImageView imageView= (ImageView) findViewById(R.id.iv1);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.iv1) {
            Drawable drawable = ((ImageView)v).getDrawable();
            if (drawable instanceof Animatable) {
                Animatable animatable= (Animatable) drawable;
                animatable.start();
            }
        }
    }
}
