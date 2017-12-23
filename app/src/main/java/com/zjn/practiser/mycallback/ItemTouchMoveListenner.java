package com.zjn.practiser.mycallback;

/**
 * Created by Administrator on 2017/12/23.
 */

public interface ItemTouchMoveListenner {
    /**
     * 当拖拽的时候回调<br/>
     * 可以在此方法里面实现：拖拽提哦啊慕并实现刷新效果
     * @param formPosition 从什么位置拖
     * @param toPosition    到什么位置
     * @return  是否回调
     */
    boolean onItemMove(int formPosition,int toPosition);

    /**
     * 当条目被移除时回调
     * @param position 移除的位置
     * @return
     */
    boolean onItemRemove(int position);
}

