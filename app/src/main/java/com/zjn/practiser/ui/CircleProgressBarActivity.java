package com.zjn.practiser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zjn.practiser.R;
import com.zjn.practiser.view.CircleProgressBar;

/**
 * Created by Administrator on 2018/2/26.
 */

public class CircleProgressBarActivity extends AppCompatActivity {
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
    }
}
