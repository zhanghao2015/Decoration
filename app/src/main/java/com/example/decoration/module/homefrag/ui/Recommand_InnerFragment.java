package com.example.decoration.module.homefrag.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.example.decoration.module.homefrag.bean.IndexBean;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-7-19.
 */
public class Recommand_InnerFragment extends BaseFragment {


    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private List<String> data;

    @Override
    protected int setViewID() {
        return R.layout.layout_recommand_fragment;
    }

    @Override
    protected void findView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

    }

    @Override
    protected void init() {
        //加载竖向线性布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        //设置间距
        recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(getActivity())
                .color(android.R.color.transparent)
                .size(10)
                .build());

        data = new ArrayList<>();
        mAdapter = new MyAdapter(data,this);
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
        for (int i = 0; i < weinituijian.size(); i++) {
            String url = weinituijian.get(i).getUrl();
            data.add(url);
        }
        mAdapter.notifyDataSetChanged();

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
