package com.example.decoration.module.homefrag.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.decoration.module.homefrag.bean.IndexBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/17.
 */
public class HomeFragPagerAdapter extends PagerAdapter {
    private List<ImageView> vpDatas;
    private Context context;
    private Fragment fragment;
    private List<IndexBean.DataBean.JiaodiantuBean> jiaodiantu;

    public void setJiaodiantu(List<IndexBean.DataBean.JiaodiantuBean> mJiaodiantu){
        this.jiaodiantu = mJiaodiantu;
    }


    public HomeFragPagerAdapter(Fragment fragment, List<ImageView> vpData) {
        this.fragment = fragment;
        this.context = fragment.getActivity();
        this.vpDatas = vpData;
    }

    @Override
    public int getCount() {
        return vpDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(vpDatas.get(position));
        vpDatas.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jiaodiantu != null) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    String target_url = jiaodiantu.get(position).getTarget_url();
                    String title = jiaodiantu.get(position).getTitle();
                    intent.putExtra("target_url", target_url);
                    intent.putExtra("title", title);
                    if (target_url != null && target_url.length() > 0) {
                        context.startActivity(intent);
                    }
                    Log.d("huizhuang", "点击了" + position);
                }
            }
        });
        return vpDatas.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(vpDatas.get(position));
    }
}
