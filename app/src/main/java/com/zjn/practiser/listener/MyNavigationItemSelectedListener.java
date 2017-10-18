package com.zjn.practiser.listener;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.zjn.practiser.utils.LogUtil;

/**
 * Created by Marks zhang on 2017/10/18.
 */

public class MyNavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {
    public final Activity context;

    public MyNavigationItemSelectedListener(Activity context){
        this.context = context;
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        LogUtil.d("MyNavigationItemSelectedListener","----------"+item);
        Toast.makeText(context,""+item,Toast.LENGTH_SHORT).show();
        return false;
    }
}
