package com.example.decoration;

import android.app.Application;

import com.lidroid.xutils.DbUtils;
import com.se7en.utils.SystemUtil;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        SystemUtil.setContext(this);

    }
}
