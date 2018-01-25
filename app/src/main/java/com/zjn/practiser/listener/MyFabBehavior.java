package com.zjn.practiser.listener;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;



public class MyFabBehavior extends CoordinatorLayout.Behavior<View> {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private float viewY;//控件距离coordinatorLayout底部距离
    private boolean isAnimate;//动画是否在进行

    public MyFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //在嵌套滑动开始前回调
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        // 当观察的View（RecyclerView）发生滑动的开始的时候回调的
        //nestedScrollAxes:滑动关联轴， 我们现在只关心垂直的滑动。
//        return nestedScrollAxes==ViewCompat.SCROLL_AXIS_VERTICAL||super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
//                target, nestedScrollAxes);
        if(child.getVisibility() == View.VISIBLE&&viewY==0){
            //获取控件距离父布局（coordinatorLayout）底部距离
            viewY=coordinatorLayout.getHeight()-child.getY();
        }
//
        return nestedScrollAxes==ViewCompat.SCROLL_AXIS_VERTICAL||super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
                                target, nestedScrollAxes);
//        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//判断是否竖直滚动
    }
    private boolean visible = true;//是否可见
    //在嵌套滑动进行时，对象消费滚动距离前回调
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        //dy大于0是向上滚动 小于0是向下滚动

//        if (dy >=0&&visible) {
//            visible = false;
//            hide(child);
//        } else if (dy <0) {
//            visible = true;
//            show(child);
//        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed >0&&visible) {
            visible = false;
            hide(child);
        } else if (dyConsumed <0) {
            visible = true;
            show(child);
        }
    }

    //隐藏时的动画
    private void hide(final View view) {
        // 隐藏动画--属性动画
        //		toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
        CoordinatorLayout.LayoutParams layoutParams = ( CoordinatorLayout.LayoutParams) view.getLayoutParams();
//
        view.animate().translationY(view.getHeight()+layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
        ViewCompat.animate(view).scaleX(0f).scaleY(0f).start();
//        ViewPropertyAnimator animator = view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(200);
//
//        animator.setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//                isAnimate=true;
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
////                view.setVisibility(View.GONE);
//                isAnimate=false;
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//                show(view);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//            }
//        });
//        animator.start();
    }

    //显示时的动画
    private void show(final View view) {
        // 显示动画--属性动画
        //		toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

        CoordinatorLayout.LayoutParams layoutParams = ( CoordinatorLayout.LayoutParams) view.getLayoutParams();
        view.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
        ViewCompat.animate(view).scaleX(1f).scaleY(1f).start();
//        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(200);
//        animator.setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
////                view.setVisibility(View.VISIBLE);
//                isAnimate=true;
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                isAnimate=false;
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//                hide(view);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//            }
//        });
//        animator.start();
    }
}
