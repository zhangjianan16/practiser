package com.zjn.practiser.mycallback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zjn.practiser.R;

/**
 * Created by Administrator on 2017/12/23.
 */

public class MyCallBack extends ItemTouchHelper.Callback {

    public final ItemTouchMoveListenner itemTouchMoveListenner;

    public MyCallBack(ItemTouchMoveListenner itemTouchMoveListenner) {
        this.itemTouchMoveListenner = itemTouchMoveListenner;
    }

    /**
     * Callback回调监听时先调用的，用来判断当前是什么动作，比如判断方向。（意思就是我要监听哪个方向的拖拽）
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 方向:up,down,left,right
        int up = ItemTouchHelper.UP;
        int down = ItemTouchHelper.DOWN;
        int left = ItemTouchHelper.LEFT;
        int right = ItemTouchHelper.RIGHT;
        // 我要监听的拖拽方向是哪两个方向
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //我要监听的wipe侧滑方向是哪个方向
        //        int swipeFlags = 0;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        int flags = makeMovementFlags(dragFlags, swipeFlags);
        return flags;
    }

    /**
     * 是否允许长按拖拽效果
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    /**
     * 当移动的时候回调的方法--拖拽
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        //在拖拽的过程当中不断的调用adapter.notifyItemMoved(form,to);
        boolean result = itemTouchMoveListenner.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return result;
    }

    /**
     * 侧滑的时候回调的
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // 监听侧滑，1.删除数据2.调用adapter.notifyItemRemove(position)
        boolean result = itemTouchMoveListenner.onItemRemove(viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // 判断选中状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.colorAccent));
        }
        super.onSelectedChanged(viewHolder, actionState);

    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //回复
        viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.white));
        //擦除动画效果
        viewHolder.itemView.setAlpha(1);//1~0
        viewHolder.itemView.setScaleX(1);//1~0
        viewHolder.itemView.setScaleY(1);//1~0
        super.clearView(recyclerView, viewHolder);

    }

    boolean swipeDeleteFlag = true;

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (swipeDeleteFlag) {

            //dx:水平方向移动的增量（负：往左 正往右）范围：0~View.getWidth
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //透明度动画
                float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
                viewHolder.itemView.setAlpha(alpha);//1~0
                viewHolder.itemView.setScaleX(alpha);//1~0
                viewHolder.itemView.setScaleY(alpha);//1~0
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        } else {
            // 判断是否超出或者达到width/2，就让其setTranslationX位于一半的位置
            if (Math.abs(dX) <= viewHolder.itemView.getWidth() / 2) {
                viewHolder.itemView.setTranslationX(-0.5f * viewHolder.itemView.getWidth());
            } else {
                viewHolder.itemView.setTranslationX(dX);
            }

        }

    }
}
