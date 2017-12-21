package com.zjn.practiser.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Marks zhang on 2017/11/2.
 */

public class DividerGridViewItemDecoration extends RecyclerView.ItemDecoration {
    public final Context context;
    private Drawable mDivider;
    private int[] attr={android.R.attr.listDivider};

    public DividerGridViewItemDecoration(Context context) {
        this.context = context;
        // 获取系统属性的方式
        TypedArray typedArray = context.obtainStyledAttributes(attr);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVerticle(c, parent);
        drawhorizontal(c, parent);
    }

    /**
     * 画横线
     * @param c
     * @param parent
     */
    private void drawhorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left=child.getLeft()-layoutParams.leftMargin;
            int right=child.getRight()+layoutParams.rightMargin;
            int top=child.getBottom()+layoutParams.bottomMargin;
            int bottom=top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 画竖线
     * @param c
     * @param parent
     */
    private void drawVerticle(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left=child.getRight()+layoutParams.rightMargin;
            int right=left+mDivider.getIntrinsicWidth();
            int top=child.getTop()-layoutParams.topMargin;
            int bottom=child.getBottom()+layoutParams.bottomMargin;
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        // 四个方向的偏移值
        int right=mDivider.getIntrinsicWidth();
        int bottom=mDivider.getIntrinsicHeight();
        if (isLastColum(itemPosition,parent)) {
            right=0;
        }
        if (isLastRow(itemPosition, parent)) {
            bottom=0;
        }
            outRect.set(0, 0, right, bottom);

    }

    private boolean isLastRow(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof  GridLayoutManager) {
            GridLayoutManager mGridLayoutManager = (GridLayoutManager) layoutManager;
            int itemCount = parent.getAdapter().getItemCount();
            int spanCount = mGridLayoutManager.getSpanCount();
            int lastRowCount=itemCount%spanCount;
            if (itemPosition==0||lastRowCount<spanCount) {
                return true;
            }
        }
        return false;
    }

    private boolean isLastColum(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof  GridLayoutManager) {
            GridLayoutManager mGridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = mGridLayoutManager.getSpanCount();
            if ((itemPosition+1)%spanCount==0) {
                return true;
            }
        }
        return false;
    }
}
