package com.example.decoration.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view=inflater.inflate(setViewID(),container,false);
         findView(view);
         init();
         initEvent();
         loadDate();
         return view;
    }
    protected abstract int setViewID();
    protected abstract void findView(View view);
    protected abstract void init();
    protected abstract void initEvent();
    protected abstract void loadDate();









}
