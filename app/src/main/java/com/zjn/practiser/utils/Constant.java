package com.zjn.practiser.utils;

import android.app.Application;

import com.zjn.practiser.listener.MyAndroidLifecycleCallbacks;

/**
 *
 * @author Marks zhang
 * @date 2017/11/6
 */

public class Constant extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 判断app是否进入后台 （方式2）
        new MyAndroidLifecycleCallbacks(this);
        // 监听所有activity的生命周期 判断app是否进入后台 （方式1 handle.postDelayed(runnable，500) 延时清楚方式）
//        registerActivityLifecycleCallbacks(new MyAndroidLifecycleCallbacks());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }
}
