package com.zjn.practiser.listener;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/1/25.
 */

public class SyncScrollBehavior extends CoordinatorLayout.Behavior<View> {

    public SyncScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL)|| super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);

    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        int y = target.getScrollY();
        child.setScrollY(y);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        ((NestedScrollView)child).fling((int) velocityY);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);

    }
}


