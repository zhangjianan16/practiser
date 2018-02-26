package com.zjn.practiser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjn.practiser.view.ParallaxLayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/18.
 */

public class ParallaxFragment extends Fragment {
    private List<View> views = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int layoutId = bundle.getInt("layoutId");
        ParallaxLayoutInflater layoutInflater = new ParallaxLayoutInflater(inflater,getActivity(),this);
        return layoutInflater.inflate(layoutId,null);
    }

    public List<View> getViews() {
        return views;
    }
}
