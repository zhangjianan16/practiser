package com.zjn.practiser.listener;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.zjn.practiser.utils.LogUtil;

/**
 * Created by Marks zhang on 2017/10/18.
 */

public class MyDrawerLayoutLitstener implements DrawerLayout.DrawerListener {
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        // slideOffset 开启进度0-1
        LogUtil.d("MyDrawerLayoutLitstener","onDrawerSlide----"+"-----"+slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        // 已打开
        LogUtil.d("MyDrawerLayoutLitstener","onDrawerOpened----");

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        // 已关闭
        LogUtil.d("MyDrawerLayoutLitstener","onDrawerClosed----");

    }

    @Override
    public void onDrawerStateChanged(int newState) {
        LogUtil.d("MyDrawerLayoutLitstener","onDrawerStateChanged----"+newState);

    }
}
