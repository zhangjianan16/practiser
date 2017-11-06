package com.zjn.practiser.handler;


import android.os.Handler;
import android.os.Message;

/**
 * Created by Marks zhang on 2017/11/6.
 */

public class ActivityLifeHandler  extends Handler {
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
    }

    @Override
    public String getMessageName(Message message) {
        return super.getMessageName(message);
    }

    @Override
    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        return super.sendMessageAtTime(msg, uptimeMillis);
    }
}
