package com.example.decoration.module.homefrag.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.decoration.R;
import com.example.decoration.module.homefrag.bean.LatestBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 16-7-20.
 */
public class MyLatestFragAdapter extends RecyclerView.Adapter<MyLatestFragAdapter.MyHolder>{
    private List<LatestBean> data;
    private Context context;

    public MyLatestFragAdapter(List<LatestBean> data, LatestToday_InnerFragment fragment) {
        this.data = data;
        this.context = fragment.getActivity();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_latestitem_fragment,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        LatestBean latestBean = data.get(position);
        String url = latestBean.getUrl();
        String content = latestBean.getContent();
        String date = latestBean.getTime();
        String view = latestBean.getView();
        String praise = latestBean.getPraise();
        Picasso.with(context)
                .load(url)
                .into(holder.mivItem);
        holder.mtvItem.setText(content);
        holder.mtimeItem.setText(date);
        holder.mviewItem.setText(view);
        holder.mpraiseItem.setText(praise);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{
        private ImageView mivItem;
        private TextView mtvItem;
        private TextView mtimeItem;
        private TextView mviewItem;
        private TextView mpraiseItem;

        public MyHolder(View itemView) {
            super(itemView);
            mivItem = (ImageView) itemView.findViewById(R.id.mivItem_latestfrag);
            mtvItem = (TextView) itemView.findViewById(R.id.mtvItem_latestfrag);
            mtimeItem = (TextView) itemView.findViewById(R.id.mtimeItem_latestfrag);
            mviewItem = (TextView) itemView.findViewById(R.id.mviewItem_latestfrag);
            mpraiseItem = (TextView) itemView.findViewById(R.id.mpraiseItem_latestfrag);
            mivItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("huizhuang","点了视图为你推荐v:"+v.getId());
                }
            });
        }
    }
}
