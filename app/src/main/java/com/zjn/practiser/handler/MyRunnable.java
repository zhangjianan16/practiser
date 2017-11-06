package com.zjn.practiser.handler;

import com.zjn.practiser.utils.LogUtil;

/**
 * Created by Marks zhang on 2017/11/6.
 */

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        LogUtil.d("MyRunnable","----------------isbackgroud----------------");
    }
}
