package com.zjn.practiser.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.zjn.practiser.R;
import com.zjn.practiser.adapter.ParallaxAdapter;
import com.zjn.practiser.ui.ParallaxFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/18.
 */

public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {
    private List<ParallaxFragment> fragments;
    private float containerWith;
    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUp(int[] ints) {
        fragments = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            ParallaxFragment fragment = new ParallaxFragment();
            Bundle args = new Bundle();
            args.putInt("layoutId", ints[i]);
            fragment.setArguments(args);
            fragments.add(fragment);
        }
        ViewPager viewPager = new ViewPager(getContext());
        viewPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewPager.setId(R.id.parallax_pager);
        ParallaxAdapter parallaxAdapter = new ParallaxAdapter(((FragmentActivity) getContext()).getSupportFragmentManager(), fragments);
        viewPager.setAdapter(parallaxAdapter);
        addView(viewPager, 0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        containerWith = getWidth();
        //进入的页面
        ParallaxFragment fragmentin = null;
        try {
            fragmentin = fragments.get(position - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //退出的页面
        ParallaxFragment fragmentout = null;
        try {
            fragmentout = fragments.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragmentin != null) {
            List<View> views = fragmentin.getViews();
            for (int i = 0; i < views.size(); i++) {
                View view = views.get(i);
                ParallaxViewTag tag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                if (tag==null) {
                    continue;
                }
                // 进来的动画
                view.setTranslationX((containerWith-positionOffset)*tag.xIn);
                view.setTranslationX((containerWith-positionOffset)*tag.yIn);
            }
        }
        if (fragmentout != null) {
            List<View> views = fragmentout.getViews();
            for (int i = 0; i < views.size(); i++) {
                View view = views.get(i);
                ParallaxViewTag tag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                if (tag==null) {
                    continue;
                }
                // 进来的动画
                view.setTranslationX((-positionOffset)*tag.xOut);
                view.setTranslationX((-positionOffset)*tag.yOut);
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
