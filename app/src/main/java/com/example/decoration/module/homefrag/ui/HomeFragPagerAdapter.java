package com.example.decoration.module.homefrag.ui;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/17.
 */
public class HomeFragPagerAdapter extends PagerAdapter{
    private List<View> vpDatas;

    public HomeFragPagerAdapter(List<View> vpData) {
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
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(vpDatas.get(position));
        return vpDatas.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(vpDatas.get(position));
    }
}
