package com.zjn.practiser;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zjn.practiser.adapter.MainRecycleViewAdapter;
import com.zjn.practiser.databinding.ActivityMainBinding;
import com.zjn.practiser.databinding.HeaderLayoutBinding;
import com.zjn.practiser.listener.MyDrawerLayoutLitstener;
import com.zjn.practiser.listener.MyNavigationItemSelectedListener;
import com.zjn.practiser.ui.AppBarActivity;
import com.zjn.practiser.ui.BehaviorDemo;
import com.zjn.practiser.ui.CoordinatorLayoutDemo;
import com.zjn.practiser.ui.FirstActivity;
import com.zjn.practiser.ui.MySuperTextViewActivity;
import com.zjn.practiser.ui.PaletteDemo;
import com.zjn.practiser.ui.ParallaxContainerActivity;
import com.zjn.practiser.ui.PinterestActivity;
import com.zjn.practiser.ui.RecycleViewActivity;
import com.zjn.practiser.ui.SVGPathAnimatorActivity;
import com.zjn.practiser.ui.ScaleAlphaAnimationActivity;
import com.zjn.practiser.ui.TabLayoutDemo;
import com.zjn.practiser.ui.TooBarDemo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  final   String TAG="Observable";
    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 继承AppCompatActivity去掉titlebar的方法
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        设置状态栏的透明属性
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //全屏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setNavigationBarColor(getResources().getColor(R.color.setting_blue));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.drawerlayout.addDrawerListener(new MyDrawerLayoutLitstener());
        binding.floatingActionbutton.setOnClickListener(this);
        init();
        initNavigationView();
        //设置沉浸式
        setSystemStatusBar(Color.TRANSPARENT);
    }
    private final void setSystemStatusBar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {//4.4-5.0实现方式
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(color);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    private int getStateBar(Context context) {
        int dimensionPixelSize=-1;
        try {
            Class<?> aClass = Class.forName("com.android.internal.R$dimen");
            Object o = aClass.newInstance();
            String height = aClass.getField("status_bar_height").get(o).toString();
            Integer integer = Integer.valueOf(height);
             dimensionPixelSize = context.getResources().getDimensionPixelSize(integer);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return dimensionPixelSize;
    }

    /**
     * 初始化侧滑栏菜单
     */
    private void initNavigationView() {
        // 解决图片都是灰色
        binding.navigationView.setItemIconTintList(null);
        //获取头布局文件
        View headerView = binding.navigationView.getHeaderView(0);
        // 获取头布局
        HeaderLayoutBinding bind = DataBindingUtil.bind(headerView);
        // 头部局imageview点击事件
        bind.iv.setOnClickListener(this);
        // 菜单选择监听器
        binding.navigationView.setNavigationItemSelectedListener(new MyNavigationItemSelectedListener(this));
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
        mainRecycleViewAdapter.setOnItemClickListener(new MainRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onclick(int position, View view) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, PinterestActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ScaleAlphaAnimationActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, MySuperTextViewActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, TooBarDemo.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, PaletteDemo.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, TabLayoutDemo.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutDemo.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, AppBarActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, BehaviorDemo.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, FirstActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, SVGPathAnimatorActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, ParallaxContainerActivity.class));
                        break;
                    default:
                }

            }
        });

    }

    private boolean reverse = false;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_lift_touch:
                // 打开左侧侧滑栏
                binding.drawerlayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv:
                // 关闭左侧侧滑栏
                binding.drawerlayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.floating_actionbutton:
                // 关闭左侧侧滑栏
                float toDegree=reverse?-180f:180f;
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0.0f, toDegree).setDuration(400);
                reverse=!reverse;
                objectAnimator.start();
                break;
            default:
        }
    }
}
