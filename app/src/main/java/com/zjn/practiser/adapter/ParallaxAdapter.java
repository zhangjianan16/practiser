package com.zjn.practiser.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zjn.practiser.ui.ParallaxFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/2/18.
 */

public class ParallaxAdapter extends FragmentPagerAdapter {
    private List<ParallaxFragment> fragments;
    public ParallaxAdapter(FragmentManager fm,List<ParallaxFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
