package com.zjn.practiser.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.SeekBar;

import com.zjn.practiser.R;
import com.zjn.practiser.databinding.ActivityScaleAlphaAnimationBinding;

/**
 * Created by Marks zhang on 2017/11/3.
 */

public class ScaleAlphaAnimationActivity extends Activity implements View.OnClickListener {
    private int mAnimationToX=10;
    private int mAnimationToY=10;
    public ActivityScaleAlphaAnimationBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scale_alpha_animation);
        binding.btn.setOnClickListener(this);
        initAnimotion();
        initSeekBar();
    }

    private void initSeekBar() {
        binding.seekbar.setProgress(mAnimationToX);
        binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAnimationToX= progress*10;
                mAnimationToY= progress*10;
                initAnimotion();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initAnimotion() {
        final AnimationSet set = new AnimationSet(true);
        ScaleAnimation scale = new ScaleAnimation(1,mAnimationToX,1,mAnimationToY, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(3000);
        set.addAnimation(scale);
        AlphaAnimation alphta = new AlphaAnimation(1,0);
        alphta.setDuration(3000);
        set.addAnimation(alphta);
        set.setFillAfter(true);
        set.setInterpolator(new AccelerateInterpolator());
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.ivCircle.startAnimation(set);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        binding.ivCircle.startAnimation(set);
    }
    public  void  startAnimotion(View view){
    startActivity(new Intent(this,CircleWaveDivergenceViewActivity.class));
    }

    @Override
    public void onClick(View v) {
        startAnimotion(v);
    }
}
