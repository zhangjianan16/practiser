package com.zjn.practiser.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjn.practiser.R;
import com.zjn.practiser.databinding.ItemRecycleviewSeccondBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marks zhang on 2017/10/31.
 */

public class PinteresActivityAdapter extends RecyclerView.Adapter<PinteresActivityAdapter.MypinteresActivityAdapterHolder> {
    public final Context context;
    /**
     * 填充的数据
     */
    public final List<String> list;
    public ItemRecycleviewSeccondBinding binding;
    /**
     * 随机高度
     */
    public final List<Integer> heights;
    /**
     * 自定义单击监听事件
     */
    public OnclickListerner onclickListerner;

    public PinteresActivityAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
        heights = new ArrayList();
        for (int i = 0; i < 30; i++) {
            heights.add((int) (200 + (Math.random() * 100)));
        }
    }

    @Override
    public MypinteresActivityAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recycleview_seccond, parent, false);
        return new MypinteresActivityAdapterHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MypinteresActivityAdapterHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.title.getLayoutParams();
        layoutParams.height = heights.get(position);
        holder.title.setBackgroundColor(Color.rgb(100, heights.get(position), heights.get(position)));
        holder.title.setText(list.get(position));
        holder.itemView.setOnClickListener(new MyOnclickListener(position));
    }


    public interface OnclickListerner {
        void itemOnclick(View view, int position);
    }

    public void setItemOnClickListerner(OnclickListerner onclickListerner) {
        this.onclickListerner = onclickListerner;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 自定义监听器
     */
    class MyOnclickListener implements View.OnClickListener {

        public final int position;

        public MyOnclickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (null != onclickListerner) {
                onclickListerner.itemOnclick(view, position);
            }
        }
    }

    class MypinteresActivityAdapterHolder extends RecyclerView.ViewHolder {

        public final TextView title;

        public MypinteresActivityAdapterHolder(View itemView) {
            super(itemView);
            title = binding.title;
        }
    }
}
