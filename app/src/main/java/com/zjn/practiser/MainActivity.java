package com.zjn.practiser;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;

import com.zjn.practiser.adapter.MainRecycleViewAdapter;
import com.zjn.practiser.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 继承AppCompatActivity去掉titlebar的方法
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        String[] functions = getResources().getStringArray(R.array.functions);
        MainRecycleViewAdapter mainRecycleViewAdapter = new MainRecycleViewAdapter(this,functions);
        // LayoutManager 布局拜访管理器，（线性布局，瀑布流）
        // 线性布局：默认和LinearLayoutManager.VERTICAL是垂直 LinearLayoutManager.HORIZONTAL 是横向  boolean reverseLayout 是否倒叙显示
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        // 格子布局： int spanCount 显示几行
        // binding.recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(),3,GridLayoutManager.VERTICAL,false));

        // 瀑布流
        // binding.recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        binding.recyclerview.setAdapter(mainRecycleViewAdapter);
        binding.ivLiftTouch.setOnClickListener(this);
        binding.ivHeader.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_lift_touch:
                //打开左侧侧滑栏
                binding.drawerlayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_header:
                //关闭左侧侧滑栏
                binding.drawerlayout.closeDrawer(GravityCompat.START);
                break;
            default:
        }
    }
}
