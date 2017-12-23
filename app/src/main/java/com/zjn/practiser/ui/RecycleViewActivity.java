package com.zjn.practiser.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zjn.practiser.R;
import com.zjn.practiser.adapter.QQAdapter;
import com.zjn.practiser.bean.QQMessage;
import com.zjn.practiser.mycallback.MyCallBack;
import com.zjn.practiser.mycallback.StartDragListener;
import com.zjn.practiser.utils.DataUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class RecycleViewActivity extends Activity implements StartDragListener{

    public RecyclerView recyclerView;
    public ItemTouchHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<QQMessage> list= DataUtils.init();
        QQAdapter adapter = new QQAdapter(list,this);
        recyclerView.setAdapter(adapter);
        // 条目触摸帮助类
        ItemTouchHelper.Callback callback = new MyCallBack(adapter);
        helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

     }
     @Override
     public void  onStartDrag(RecyclerView.ViewHolder viewHolder){
         helper.startDrag(viewHolder);
     }
}
