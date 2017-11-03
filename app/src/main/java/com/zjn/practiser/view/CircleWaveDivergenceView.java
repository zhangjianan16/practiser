package com.zjn.practiser.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.zjn.practiser.R;

/**
 *
 * @author Marks zhang
 * @date 2017/11/3
 */

public class CircleWaveDivergenceView extends RelativeLayout {
    private Bitmap mBitmap;
    private Bitmap mBitmap1;
    private Bitmap mBitmap2;
    public Context context;
    /**
     *  是否扫描
      */
    private boolean searchingFlag =false;
    /**
     *   扫描动画的偏移量
      */
    private int offsetArgs=0;
    public CircleWaveDivergenceView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public boolean isSearchingFlag() {
        return searchingFlag;
    }

    public void setSearchingFlag(boolean searchingFlag) {
        this.searchingFlag = searchingFlag;
        // 偏移量清0
        offsetArgs=0;
        invalidate();
    }

    public CircleWaveDivergenceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CircleWaveDivergenceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.gplus_search_bg);
        }
        if (mBitmap1 == null) {
            mBitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.gplus_search_args);
        }
        if (mBitmap2 == null) {
            mBitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.locus_round_click);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, getWidth() / 2 - mBitmap.getWidth() / 2, getHeight() / 2 - mBitmap.getHeight() / 2, null);
        if (searchingFlag) {
            canvas.rotate(offsetArgs,getWidth()/2,getHeight()/2);
            canvas.drawBitmap(mBitmap1, getWidth() / 2 - mBitmap1.getWidth(), getHeight() / 2, null);
            offsetArgs+=3;
        } else {
            canvas.drawBitmap(mBitmap1, getWidth() / 2 - mBitmap1.getWidth(), getHeight() / 2, null);
        }
        canvas.drawBitmap(mBitmap2, getWidth() / 2 - mBitmap2.getWidth()/2, getHeight() / 2 - mBitmap2.getHeight() / 2, null);

        if (searchingFlag) {
            invalidate();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handleActionDownEvenet(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 通过点击范围响应点击事件
     * @param event
     */
    private void handleActionDownEvenet(MotionEvent event){
        RectF rectF = new RectF(getWidth() / 2 - mBitmap1.getWidth() / 2,
                getHeight() / 2 - mBitmap1.getHeight() / 2,
                getWidth() / 2 + mBitmap1.getWidth() / 2,
                getHeight() / 2 + mBitmap1.getHeight() / 2);

        if(rectF.contains(event.getX(), event.getY())){
            if(!isSearchingFlag()) {
                setSearchingFlag(true);
            }else{
                setSearchingFlag(false);
            }
        }
    }
}
