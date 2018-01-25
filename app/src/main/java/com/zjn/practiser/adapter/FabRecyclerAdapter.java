package com.zjn.practiser.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjn.practiser.R;

import java.util.List;

public class FabRecyclerAdapter extends Adapter<ViewHolder> {

	private List<String> list;

	public FabRecyclerAdapter(List<String> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
	}
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		String str = list.get(position);
		MyViewHolder holder = (MyViewHolder) viewHolder;
		holder.tv.setText(str);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(arg0.getContext()).inflate(R.layout.listitem1, arg0, false);
		return new MyViewHolder(view);
	}
	
	class MyViewHolder extends ViewHolder{

		private TextView tv;

		public MyViewHolder(View itemView) {
			super(itemView);
			tv = (TextView) itemView.findViewById(R.id.tv);
		}
		
	}

}
