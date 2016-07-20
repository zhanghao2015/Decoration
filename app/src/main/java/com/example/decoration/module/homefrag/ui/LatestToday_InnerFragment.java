package com.example.decoration.module.homefrag.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.example.decoration.module.homefrag.bean.IndexBean;
import com.example.decoration.module.homefrag.bean.LatestBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-7-20.
 */
public class LatestToday_InnerFragment extends BaseFragment {

    private MyRecyclerView recyclerView_latest;
    private List<LatestBean> data;
    private MyLatestFragAdapter mAdapter;

    @Override
    protected int setViewID() {
        return R.layout.layout_latesttoday_fragment;
    }

    @Override
    protected void findView(View view) {
        recyclerView_latest = (MyRecyclerView) view.findViewById(R.id.recyclerView_latest);
    }

    @Override
    protected void init() {
//加载竖向线性布局管理器
        recyclerView_latest.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        //设置横向分割线
        recyclerView_latest.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(android.R.color.white)
                .size(15)
                .build());


        data = new ArrayList<>();
        mAdapter = new MyLatestFragAdapter(data, this);
        recyclerView_latest.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadDate() {

    }

    @Subscribe
    public void onLoadLatestFragData(LoadLatestFrag latestFrag) {
        IndexBean indexBean = latestFrag.getIndexBean();
        List<IndexBean.DataBean.JinrizuixinBean> jinrizuixin = indexBean.getData().getJinrizuixin();
        for (int i = 0; i < jinrizuixin.size(); i++) {
            String url = jinrizuixin.get(i).getUrl();
            String content = jinrizuixin.get(i).getDigest();
            String date = jinrizuixin.get(i).getDate();
            String view = jinrizuixin.get(i).getView();
            String praise = jinrizuixin.get(i).getPraise();
            LatestBean latestBean = new LatestBean();
            latestBean.setUrl(url);
            latestBean.setContent(content);
            latestBean.setTime(date);
            latestBean.setView(view);
            latestBean.setPraise(praise);
            data.add(latestBean);
            Log.d("huizhuang","第"+i+"个数据");
            Log.d("huizhuang","url :"+url);
            Log.d("huizhuang","content :"+content);
            Log.d("huizhuang","date :"+date);
            Log.d("huizhuang","view :"+view);
            Log.d("huizhuang","praise :"+praise);
        }
        Log.d("huizhuang","data个数:"+data.size());
        mAdapter.notifyDataSetChanged();
        Log.d("huizhuang","今日最新加载完毕");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
