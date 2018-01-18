package com.zjn.practiser.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.zjn.practiser.listener.TranlucentListener;

/**
 * Created by Administrator on 2018/1/18.
 */

public class MyScrollView extends ScrollView {
    public TranlucentListener tranlucentListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTranlucentListener(TranlucentListener tranlucentListener) {
        this.tranlucentListener = tranlucentListener;

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (tranlucentListener != null) {
            int scrollY = getScrollY();
            int heightPixels = getContext().getResources().getDisplayMetrics().heightPixels;
            if (scrollY < heightPixels / 3f) {//0-if  透明度要1f-0
                tranlucentListener.setTranlucentListener(1 - scrollY / (heightPixels / 3f));//alpha=滑出去的高度/(screen_height/3)
            }
        }
    }
}
