package com.zjn.practiser.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/22.
 */

public class NewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        Bundle arguments = getArguments();
        String title = arguments.getString("title");
        textView.setBackgroundColor(Color.rgb((int)Math.random()*255,(int)Math.random()*255,(int)(Math.random()*255)));
        textView.setText(title);
        return textView;

    }
}
