package com.zjn.practiser.mycallback;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2017/12/23.
 */

public interface StartDragListener {
    /**
     * 该接口用于需要主 动回调拖拽效果的
     * @param viewHolder
     */
    public void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
