package com.zjn.practiser.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.zjn.practiser.R;

/**
 * Created by Administrator on 2017/12/29.
 */

public class MySuperTextViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysupertextview);
    }

    public void showSnackBar(View view) {
        Snackbar snackbar = Snackbar.make(view, "渐变按钮", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MySuperTextViewActivity.this,"点击",Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                Toast.makeText(MySuperTextViewActivity.this,"消失",Toast.LENGTH_SHORT).show();
                super.onDismissed(snackbar, event);
            }

            @Override
            public void onShown(Snackbar snackbar) {
                Toast.makeText(MySuperTextViewActivity.this,"显示动画完成",Toast.LENGTH_SHORT).show();
                super.onShown(snackbar);
            }
        });
        snackbar.show();

    }
}
