package com.example.decoration.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewId());
        findView();
        init();
        initEvent();
        loadDate();
    }
    protected abstract int setViewId();
    protected abstract void findView();
    protected abstract void init();
    protected abstract void initEvent();
    protected abstract void loadDate();










}
