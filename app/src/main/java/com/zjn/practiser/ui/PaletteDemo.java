package com.zjn.practiser.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjn.practiser.R;

/**
 * Created by Administrator on 2018/1/19.
 */

public class PaletteDemo extends AppCompatActivity {

    public TextView title;
    public TextView title1;
    public TextView title2;
    public TextView title3;
    public TextView title4;
    public TextView title5;
    public TextView title6;
    public ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palettedemo);
        imageView = (ImageView) findViewById(R.id.imageview);
        title = (TextView) findViewById(R.id.title);
        title1 = (TextView) findViewById(R.id.title1);
        title2 = (TextView) findViewById(R.id.title2);
        title3 = (TextView) findViewById(R.id.title3);
        title4 = (TextView) findViewById(R.id.title4);
        title5 = (TextView) findViewById(R.id.title5);
        title6 = (TextView) findViewById(R.id.title6);
        BitmapDrawable bitmapDrawable= (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        // 异步任务--可能分析的图片会比较大或颜色分布比较复杂，会耗时很久，防止卡死主线程。
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // 暗 柔和的颜色
                int darkMutedColor = palette.getDarkMutedColor(Color.BLUE);
                // 暗 鲜艳
                int darkVibrantColor = palette.getDarkVibrantColor(Color.BLUE);
                // 亮 柔和的颜色
                int lightMutedColor = palette.getLightMutedColor(Color.BLUE);
                // 亮 鲜艳
                int lightVibrantColor = palette.getLightVibrantColor(Color.BLUE);
                // 柔和
                int mutedColor = palette.getMutedColor(Color.BLUE);
                // 鲜艳
                int vibrantColor = palette.getVibrantColor(Color.BLUE);
                // 获取某种特性颜色的样品
                Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
                // 谷歌推荐的：图片的整体颜色rgb的混合值--主色调
                int rgb = lightVibrantSwatch.getRgb();
                // 谷歌推荐的：图片中间的文字颜色
                int bodyTextColor = lightVibrantSwatch.getBodyTextColor();
                // 谷歌推荐的：作为标题的颜色（）
                int titleTextColor = lightVibrantSwatch.getTitleTextColor();
                // 颜色向量
                float[] hsl = lightVibrantSwatch.getHsl();
                // 分析该颜色在图片中所占的像素多少值
                int population = lightVibrantSwatch.getPopulation();
                title.setBackgroundColor(darkMutedColor);
                title1.setBackgroundColor(lightMutedColor);
                title2.setBackgroundColor(darkVibrantColor);
                title3.setBackgroundColor(lightVibrantColor);
                title4.setBackgroundColor(mutedColor);
                title5.setBackgroundColor(vibrantColor);
                title6.setBackgroundColor(getTranslucentColor(0.6f,rgb));
                title6.setTextColor(titleTextColor);

            }
        });
    }

    private int getTranslucentColor(float percent, int rgb) {
        int blue=Color.blue(rgb);
        int green=Color.green(rgb);
        int red=Color.red(rgb);
        int alpha=Color.alpha(rgb);
//        int blue=rgb&0xff;
//        int green=rgb>>8&0xff;
//        int red=rgb>>16&0xff;
//        int alpha=rgb>>>24;
        alpha = Math.round(alpha * percent);
        return Color.argb(alpha, red, green, blue);
    }
}
