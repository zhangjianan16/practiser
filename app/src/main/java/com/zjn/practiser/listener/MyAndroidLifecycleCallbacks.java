package com.zjn.practiser.listener;


import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.zjn.practiser.handler.ActivityLifeHandler;
import com.zjn.practiser.handler.MyRunnable;
import com.zjn.practiser.utils.LogUtil;

/**
 * Created by Marks zhang on 2017/11/6.
 */

public class MyAndroidLifecycleCallbacks implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private  Handler handler=new ActivityLifeHandler();
    private Runnable runnable=new MyRunnable();
    private boolean mIsBackgroud=false;

    public MyAndroidLifecycleCallbacks(Application application) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.registerComponentCallbacks(this);
            application.registerActivityLifecycleCallbacks(this);
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtil.d("onActivityCreated","-----"+activity.getLocalClassName());

    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtil.d("onActivityStarted","-----"+activity.getLocalClassName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        handler.removeCallbacks(runnable);
        if (mIsBackgroud) {
            mIsBackgroud = false;
            LogUtil.d("onActivityResumed","--App 进入前台---"+activity.getLocalClassName());
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtil.d("onActivityPaused","-----"+activity.getLocalClassName());
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 500);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtil.d("onActivityStopped","-----"+activity.getLocalClassName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtil.d("onActivitySaveInstanceState","-----"+activity.getLocalClassName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtil.d("onActivityDestroyed","-----"+activity.getLocalClassName());
    }

    @Override
    public void onTrimMemory(int level) {
        if (level ==TRIM_MEMORY_UI_HIDDEN) {
            mIsBackgroud=true;
            LogUtil.d("onTrimMemory","App 退出到后台");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onLowMemory() {

    }
    public  void  release(Application application) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.unregisterActivityLifecycleCallbacks(this);
            application.unregisterComponentCallbacks(this);
        }
    }
}
