package com.example.decoration.module.main.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.decoration.R;
import com.example.decoration.base.BaseActivity;
import com.example.decoration.common.constant.Constant;
import com.se7en.utils.SystemUtil;

public class Welcome1Activity extends BaseActivity {

    public final long SPLASH_LENGTH=2000;
    private int mcurrentVersion;
    private int mlastVersion;

    @Override
    protected int setViewId() {
        return R.layout.activity_welcome1;
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void init() {


        mcurrentVersion= SystemUtil.getSystemVersionCode();
        mlastVersion=SystemUtil.getSharedInt(Constant.VERSION_STRING,-1);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //如果是版本更新 或者第一次安装 进入viewpager欢迎界面
                if(mcurrentVersion>mlastVersion||mlastVersion==-1){
                    Intent intent=new Intent(Welcome1Activity.this,Welcome2Activity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent=new Intent(Welcome1Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },SPLASH_LENGTH);



    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadDate() {

    }
}
