package com.example.decoration.module.homefrag.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.example.decoration.module.homefrag.bean.IndexBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-7-19.
 */
public class Recommand_InnerFragment extends BaseFragment {


    private MyRecyclerView recyclerView;
    private MyRecommandAdapter mAdapter;
    private List<IndexBean.DataBean.WeinituijianBean> data;

    @Override
    protected int setViewID() {
        return R.layout.layout_recommand_fragment;
    }

    @Override
    protected void findView(View view) {
        recyclerView = (MyRecyclerView) view.findViewById(R.id.recyclerView_recommand);

    }

    @Override
    protected void init() {
        //加载竖向线性布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        //设置横向分割线
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(android.R.color.transparent)
                .size(4)
                .build());

        data = new ArrayList<>();
        mAdapter = new MyRecommandAdapter(data,this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {


    }

    @Override
    protected void loadDate() {

    }

    @Subscribe
    public void onLoadRecommandFrag(LoadRecommandFrag recommandFrag){
        IndexBean indexBean = recommandFrag.getIndexBean();
        List<IndexBean.DataBean.WeinituijianBean> weinituijian = indexBean.getData().getWeinituijian();
        if(data.size()==0){
            for (int i = 0; i < weinituijian.size(); i++) {
                data.add(weinituijian.get(i));
            }
            mAdapter.notifyDataSetChanged();
        }else{
            data.clear();
            for (int i = 0; i < weinituijian.size(); i++) {
                data.add(weinituijian.get(i));
            }
            mAdapter.notifyDataSetChanged();
        }
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
