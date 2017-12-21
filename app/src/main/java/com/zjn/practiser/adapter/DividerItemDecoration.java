package com.zjn.practiser.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Marks zhang on 2017/11/1.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    public final Context context;
    public  int orientation=LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int[] attr={android.R.attr.listDivider};

    public DividerItemDecoration(Context context,int orientation) {
        this.context = context;
        // 获取系统属性的方式
        TypedArray typedArray = context.obtainStyledAttributes(attr);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();

        if (orientation!= LinearLayoutManager.VERTICAL&&orientation!= LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("只能是水平和垂直");
        }
        this.orientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        // recycleview会回掉该方法，需要自己绘制分割线
        // 垂直
        if (orientation == LinearLayoutManager.VERTICAL) {
            drawVerticle(c,parent);
        } else {
            // 水平
            drawHorizontal(c,parent);
        }

        super.onDraw(c, parent, state);

    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top=parent.getPaddingTop();
        int bottom=parent.getHeight()-parent.getPaddingBottom();
        int childCount=parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left=child.getRight()+layoutParams.rightMargin+ Math.round(ViewCompat.getTranslationX(child));
            int right=left+mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }

    }

    private void drawVerticle(Canvas c, RecyclerView parent) {
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();
        int childCount=parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top=child.getBottom()+layoutParams.bottomMargin+ Math.round(ViewCompat.getTranslationY(child));
            int bottom=top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // 1.调用此方法，（首先会获得条目之间的间隙宽度---outRect矩形区域）
        // 获得条目的偏移量（所有的条目都会调用一次该方法）

        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        } else if(orientation == LinearLayoutManager.HORIZONTAL) {
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }
    }
}
