package com.zjn.practiser.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjn.practiser.R;
import com.zjn.practiser.bean.QQMessage;
import com.zjn.practiser.mycallback.ItemTouchMoveListenner;
import com.zjn.practiser.mycallback.StartDragListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/12/23.
 */

public class QQAdapter extends RecyclerView.Adapter<QQAdapter.MyViewHolder> implements ItemTouchMoveListenner {

    public final List<QQMessage> list;
    public final StartDragListener recycleViewActivity;

    public QQAdapter(List<QQMessage> list, StartDragListener recycleViewActivity) {
        this.list = list;
        this.recycleViewActivity = recycleViewActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, null );
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        QQMessage qqMessage = list.get(position);
        holder.iv_logo.setImageResource(qqMessage.getLogo());
        holder.tv_name.setText(qqMessage.getName());
        holder.tv_time.setText(qqMessage.getTime());
        holder.tv_lastMsg.setText(qqMessage.getLastMsg());
        holder.iv_logo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
                    //传递触摸情况
                    recycleViewActivity.onStartDrag(holder);
                }
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onItemMove(int formPosition, int toPosition) {
        // 数据交换  刷新
        Collections.swap(list,formPosition,toPosition);
        notifyItemMoved(formPosition,toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        return true;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder{

        public  ImageView iv_logo;
        public  TextView tv_name;
        public  TextView tv_time;
        public  TextView tv_lastMsg;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_logo = itemView.findViewById(R.id.iv_logo);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_lastMsg = itemView.findViewById(R.id.tv_lastMsg);

        }
    }
}
