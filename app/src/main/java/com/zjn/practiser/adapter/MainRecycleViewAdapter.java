package com.zjn.practiser.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjn.practiser.R;
import com.zjn.practiser.databinding.ItemRecycleviewFirstBinding;
import com.zjn.practiser.ui.PinterestActivity;

/**
 * Created by Marks zhang on 2017/10/15.
 */

public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.MyViewHolder> {
    public  String[] functions;
    public  Context context;
    public ItemRecycleviewFirstBinding first;

    public MainRecycleViewAdapter(Context context, String[] stringArray) {
        this.context = context;
        this.functions = stringArray;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        first = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recycleview_first, parent, false);
        return new MyViewHolder(first.getRoot());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(functions[position]);
        holder.itemView.setOnClickListener(new MyOnlickListener(position));
    }

    @Override
    public int getItemCount() {
        return functions.length;
    }
    class MyOnlickListener implements View.OnClickListener {
        public final int position;

        public MyOnlickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (position==0) {
                context.startActivity(new Intent(context, PinterestActivity.class));
            }

        }
    }
    class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            title= first.title;
        }
    }
}
