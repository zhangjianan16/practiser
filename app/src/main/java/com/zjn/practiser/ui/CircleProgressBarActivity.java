package com.zjn.practiser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.zjn.practiser.R;
import com.zjn.practiser.view.CircleProgressBar;
import com.zjn.practiser.view.RoundLightBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */

public class CircleProgressBarActivity extends AppCompatActivity {

    public ArrayList<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circleprogressbar);
       final CircleProgressBar circleProgressBar= (CircleProgressBar) findViewById(R.id.circleprofressbar);
       circleProgressBar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       int i=0;
                       while (i <=100) {
                           i+=2;
                           circleProgressBar.setProgress(i);
                           try {
                               Thread.sleep(100);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }

                       }


                   }
               }).start();
           }
       });
        LineChart mLineChart = (LineChart) findViewById(R.id.lineChart);
        //显示边界
        mLineChart.setDrawBorders(true);
        mList = new ArrayList<String>();
        mList.add("0天");
        mList.add("1天");
        mList.add("7天");
        mList.add("3个月");
        mList.add("6个月");
        mList.add("1年");
        mList.add("2年");
        mList.add("3年");

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//值：BOTTOM,BOTH_SIDED,BOTTOM_INSIDE,TOP,TOP_INSIDE
        xAxis.setLabelCount(8, true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mList.get((int) value); //mList为存有月份的集合
            }
        });
        //设置数据
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 0f));
        entries.add(new Entry(1, 0.011f));
        entries.add(new Entry(2, 0.013f));
        entries.add(new Entry(3, 0.014f));
        entries.add(new Entry(4, 0.015f));
        entries.add(new Entry(5, 0.018f));
        entries.add(new Entry(6, 0.020f));
        entries.add(new Entry(7, 0.023f));
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "利率");
        LineData data = new LineData(lineDataSet);
        mLineChart.setData(data);
       final RoundLightBarView roundLightBarView= (RoundLightBarView) findViewById(R.id.circle);
        roundLightBarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roundLightBarView.isRunFlag()) {
                    roundLightBarView.stopDraw();
                } else {
                    roundLightBarView.startDraw();
                }
            }
        });
    }


}
