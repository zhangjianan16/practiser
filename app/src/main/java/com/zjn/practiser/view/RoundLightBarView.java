package com.zjn.practiser.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zjn.practiser.utils.DensityUtils;

/**
 * Created by Administrator on 2018/3/22.
 */

public class RoundLightBarView extends ImageView {
    private int mTotalWidth, mTotalHeight;
    private int mCenterX, mCenterY;

    //进度条画笔
    private Paint mProgressPaint;

    //是否开始会话
    private boolean runFlag = true;
    private int mCircleR;


    private Context mContext;
    //距离外围的边距
    private float interval;

    private int startAngle = 1;


    public RoundLightBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public RoundLightBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        interval = DensityUtils.px2dip(mContext, 50);
        //初始化画笔
        initPaint();
    }


    private void initPaint() {
        //圆半径
        mCircleR = DensityUtils.px2dip(mContext, 20);
        //画彩色圆弧的画笔
        mProgressPaint = new Paint();
        //抗锯齿
        mProgressPaint.setAntiAlias(true);
        // 防抖动
        mProgressPaint.setDither(true);
        // 开启图像过滤，对位图进行滤波处理。
        mProgressPaint.setFilterBitmap(true);
        mProgressPaint.setColor(Color.BLUE);
        //空心圆
        mProgressPaint.setStyle(Paint.Style.STROKE);
        //设置笔刷样式为原型
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置圆弧粗
        mProgressPaint.setStrokeWidth(mCircleR);
        //将绘制的内容显示在第一次绘制内容之上
        mProgressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas去锯齿
        canvas.setDrawFilter(
                new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        //画底色圆
//        canvas.drawCircle(mCenterX, mCenterY, mCenterX - mCircleR - interval, mCirclePaint);
        //画进度条
        int colorSweep[] = {Color.BLACK, Color.WHITE};

        //设置渐变色
        sweepGradient = new SweepGradient(mCenterX, mCenterY, colorSweep, null);
        //按照圆心旋转
        Matrix matrix = new Matrix();
        matrix.setRotate(startAngle, mCenterX, mCenterY);
        sweepGradient.setLocalMatrix(matrix);

        mProgressPaint.setShader(sweepGradient);

        canvas.drawArc(
                new RectF(0 + mCircleR + interval, 0 + mCircleR + interval,
                        mTotalWidth - mCircleR - interval, mTotalHeight - mCircleR - interval),
                2 + startAngle, 350, false, mProgressPaint);

        startAngle += 10;
        if (startAngle >= 360) {
            startAngle = 1;
        }

        if (runFlag) {
            //启动绘制
            postInvalidateDelayed(10);
        }

    }

    public boolean isRunFlag() {
        return runFlag;
    }

    /**
     * 关闭绘画
     */
    public void stopDraw() {
        runFlag = false;
    }

    /**
     * 开启绘画
     */
    public void startDraw() {
        runFlag = true;
        //启动绘制
        postInvalidateDelayed(10);
    }
    SweepGradient sweepGradient;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mCenterX = mTotalWidth / 2;
        mCenterY = mTotalHeight / 2;


    }

}