package com.zjn.practiser.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.zjn.practiser.R;
import com.zjn.practiser.adapter.DividerAdapter;
import com.zjn.practiser.adapter.DividerGridViewItemDecoration;
import com.zjn.practiser.adapter.DividerItemDecoration;
import com.zjn.practiser.adapter.PinteresActivityAdapter;
import com.zjn.practiser.databinding.ActivityPinterestBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marks zhang on 2017/10/31.
 */

public class PinterestActivity extends Activity {

    public List<String> list;
    public ActivityPinterestBinding binding;
    public DividerItemDecoration dividerItemDecoration;
    public DividerAdapter dividerAdapter;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pinterest);
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
        dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
    }

    /**
     * 瀑布流点击事件
     * @param view
     */
    public void pinterest(View view){
        // 删除分割线
        binding.rc.removeItemDecoration(dividerItemDecoration);
        // 设置3行竖直瀑布流
        binding.rc.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        // 数据适配器
        PinteresActivityAdapter pinteresActivityAdapter = new PinteresActivityAdapter(this, list);
        // recycleview绑定数据适配器
        binding.rc.setAdapter(pinteresActivityAdapter);
    }
    /**
     * 表格
     * @param view
     */
    public void gride(View view){
        // 删除分割线
        binding.rc.removeItemDecoration(dividerItemDecoration);
        // 设置2行表格
        binding.rc.setLayoutManager(new GridLayoutManager(this, 3));
        // 数据适配器
        DividerAdapter dividerAdapter = new DividerAdapter(this, list);

        // recycleview绑定数据适配器
        binding.rc.setAdapter(dividerAdapter);
        // 添加分割线
        binding.rc.addItemDecoration(new DividerGridViewItemDecoration(this));
    }
    /**
     * 线性
     * @param view
     */
    public void linear(View view){
        // 设置线性布局
        binding.rc.setLayoutManager(new LinearLayoutManager(this));
        // 删除分割线
        binding.rc.removeItemDecoration(dividerItemDecoration);
        // 添加分割线
        binding.rc.addItemDecoration(dividerItemDecoration);
        // 数据适配器
        dividerAdapter = new DividerAdapter(this, list);
        // recycleview绑定数据适配器
        binding.rc.setAdapter(dividerAdapter);
    }
    /**
     * 横向
     * @param view
     */
    public void horizontal(View view){
        // 删除分割线
        binding.rc.removeItemDecoration(dividerItemDecoration);
        dividerItemDecoration=new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL);
        // 添加分割线
        binding.rc.addItemDecoration(dividerItemDecoration);
        // 设置线性布局
        binding.rc.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false));
    }
    /**
     * 横向
     * @param view
     */
    public void add(View view){
        dividerAdapter.addData(1);
    }
}
