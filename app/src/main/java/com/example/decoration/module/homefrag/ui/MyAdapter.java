package com.example.decoration.module.homefrag.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.decoration.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 16-7-19.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    private List<String> data;
    private Context context;

    public MyAdapter(List<String> data, Recommand_InnerFragment fragment) {
        this.data = data;
        this.context = fragment.getActivity();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recyclerview_item, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //网络加载图片
        Picasso.with(context)
                .load(data.get(position))
                .into(holder.mivItem);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        ImageView mivItem;
        public MyHolder(View itemView) {
            super(itemView);
            mivItem = (ImageView) itemView.findViewById(R.id.imageview_in_recommandfrag);
        }
    }
}
