package com.example.decoration.module.homefrag.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.decoration.R;
import com.example.decoration.common.utils.LruCacheUtils;
import com.example.decoration.module.homefrag.bean.IndexBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 16-7-19.
 */
public class MyRecommandAdapter extends RecyclerView.Adapter<MyRecommandAdapter.MyHolder>{

    private List<IndexBean.DataBean.WeinituijianBean> data;
    private Context context;
    private Fragment fragment;

    public MyRecommandAdapter(List<IndexBean.DataBean.WeinituijianBean> data, Recommand_InnerFragment fragment) {
        this.data = data;
        this.context = fragment.getActivity();
        this.fragment = fragment;
    }

    @Override
    public MyRecommandAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recyclerview_item, parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecommandAdapter.MyHolder holder, final int position) {
        Picasso.with(context)
                .load(data.get(position).getUrl())
                .config(Bitmap.Config.RGB_565)
                .into(holder.mivItem);
        //给recyclerview中图片设置监听
        holder.mivItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("target_url", data.get(position).getTarget_url());
                    intent.putExtra("title", data.get(position).getTitle());
                    context.startActivity(intent);
                Log.d("huizhuang","点了position:"+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        ImageView mivItem;
        public MyHolder(final View itemView) {
            super(itemView);
            mivItem = (ImageView) itemView.findViewById(R.id.imageview_in_recommandfrag);

        }
    }
}
