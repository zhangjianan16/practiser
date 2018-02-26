package com.zjn.practiser.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zjn.practiser.R;

/**
 * Created by Administrator on 2018/2/26.
 */

public class CircleProgressBar extends View {
    private int max;
    private int textColor;
    private int roundColor;
    private int roundProgressColor;
    private float textSize;
    private float roundWith;
    private boolean textShow;
    private int style;
    private int progress;
    public static final int STROKE = 0;
    public static final int FILL = 1;
    public Paint paint;

    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        max = typedArray.getInteger(R.styleable.CircleProgressBar_max, 100);
        textColor = typedArray.getColor(R.styleable.CircleProgressBar_textColor, Color.RED);
        roundColor = typedArray.getColor(R.styleable.CircleProgressBar_roundColor, Color.RED);
        roundProgressColor = typedArray.getColor(R.styleable.CircleProgressBar_roundProgressColor, Color.BLUE);
        textSize = typedArray.getDimension(R.styleable.CircleProgressBar_textSize, 15);
        roundWith = typedArray.getDimension(R.styleable.CircleProgressBar_roundWith, 10);
        textShow = typedArray.getBoolean(R.styleable.CircleProgressBar_textShow, true);
        style = typedArray.getInt(R.styleable.CircleProgressBar_style, 0);
        typedArray.recycle();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画默认的大圆环
        int center = getWidth() / 2;
        float radius = center - roundWith / 2;//半径
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);//设置空心（描边）
        paint.setStrokeWidth(roundWith);
        paint.setAntiAlias(true);
        canvas.drawCircle(center, center, radius, paint);

        //画进度百分比
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        int precent = (int) (progress / (float) max * 100);

        if (textShow && precent != 0 && style == STROKE) {

            canvas.drawText(precent + "%", (getWidth() - paint.measureText(precent + "%")) / 2f, getWidth() / 2f - (paint.descent() + paint.ascent()) / 2f, paint);
        }
        //画圆弧
        //矩形区域，定义圆弧的形状大小
        RectF avol = new RectF(center - radius, center - radius, center + radius, center + radius);
        paint.setColor(roundProgressColor);
        paint.setStrokeWidth(roundWith);
        switch (style) {
            case STROKE:
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(avol, 0, 360 * progress / max, false, paint);
                break;
            case FILL:
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0) {
                    canvas.drawArc(avol, 0, 360 * progress / max, true, paint);
                }
                break;
            default:
        }

    }

    public synchronized int getMax() {
        return max;

    }

    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max不能小于0");
        }
        this.max = max;
    }

    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress不能小于0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWith() {
        return roundWith;
    }

    public void setRoundWith(float roundWith) {
        this.roundWith = roundWith;
    }

    public boolean isTextShow() {
        return textShow;
    }

    public void setTextShow(boolean textShow) {
        this.textShow = textShow;
    }
}
