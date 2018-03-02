package com.zjn.practiser.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018/2/27.
 */

public class LinearGradientTextView extends AppCompatTextView {
    private TextPaint paint;
    public LinearGradient linearGradient;
    public Matrix localem;
    public float gradientSize;
    public float textwith;

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    float dx=0;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint = getPaint();
        int[] colors={0x22ffffff,0xffffffff,0x22ffffff};
        // gradientSize定义两个文字大小
        String text=getText().toString();
        textwith = paint.measureText(text);
        gradientSize = textwith /text.length()*2;
        linearGradient = new LinearGradient(-gradientSize, 0, 0, 0, colors, new float[]{0, 0.5f, 1}, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        localem = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        dx += gradientSize;
        if (dx >= textwith+1 ||dx<1) {
            gradientSize = -gradientSize;
        }
        localem.setTranslate(dx,0);
        linearGradient.setLocalMatrix(localem);
        postInvalidateDelayed(50);
    }
}
