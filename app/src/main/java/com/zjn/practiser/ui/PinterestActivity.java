package com.zjn.practiser.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.zjn.practiser.R;
import com.zjn.practiser.adapter.PinteresActivityAdapter;
import com.zjn.practiser.databinding.ActivityPinterestBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marks zhang on 2017/10/31.
 */

public class PinterestActivity extends Activity {

    public List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 初始化recycleview
     */
    private void init() {
        // 实例数据
        list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add("item" + i);

        }
        // databinding绑定
        ActivityPinterestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_pinterest);
        // 设置3行竖直瀑布流
        binding.rc.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        // 数据适配器
        PinteresActivityAdapter pinteresActivityAdapter = new PinteresActivityAdapter(this, list);
        // recycleview绑定数据适配器
        binding.rc.setAdapter(pinteresActivityAdapter);
        // 自定义的条目单击事件
        pinteresActivityAdapter.setItemOnClickListerner(new PinteresActivityAdapter.OnclickListerner() {
            @Override
            public void itemOnclick(View view, int position) {
                Toast.makeText(PinterestActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
