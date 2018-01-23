package com.zjn.practiser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.zjn.practiser.R;


/**
 * Created by Administrator on 2018/1/22.
 */

public class TabLayoutDemo extends AppCompatActivity {
    private String[] title = {"首页","视频","小说","动漫","综艺","娱乐","头条","看点","新闻","财经","汽车","美女","体育","房子","美食","旅游","国外","房子","美食","旅游","国外","新闻","财经","汽车","美女","体育","房子","美食","旅游","国外","房子","美食","旅游","国外"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tablayoutdemo);
//        改变状态栏颜色    5.0版本以上才能用
//        getWindow().setStatusBarColor(getResources().getColor(R.color.setting_blue));
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
//        // talayout和viewpager关联
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//              //被选中的时候回调
//                if (null!=viewPager&&tab!=null) {
//                viewPager.setCurrentItem(tab.getPosition(),true);
//                }
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        // viewpager滑动关联tablayout
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(myPagerAdapter);
        // 设置tablayout的标签来自于pageradapter
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(myPagerAdapter);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tabAt = tabLayout.getTabAt(i);
//            tabAt.setCustomView();


        }
    }
    class  MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",title[position]);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];

        }

        @Override
        public int getCount() {
            return title.length;
        }
    }
}
