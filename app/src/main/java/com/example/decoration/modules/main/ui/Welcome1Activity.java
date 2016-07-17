package com.example.decoration.modules.main.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import com.example.decoration.R;
import com.example.decoration.base.BaseActivity;
import com.example.decoration.common.constant.Constant;
import com.se7en.utils.SystemUtil;

public class Welcome1Activity extends BaseActivity {

    public final long SPLASH_LENGTH = 2000;
    private int mcurrentVersion;
    private int mlastVersion;
    private SharedPreferences sp;
    private boolean flag = true;

    @Override
    protected int setViewId() {
        return R.layout.activity_welcome1;
    }

    @Override
    protected void findView() {

    }

    //进入程序过程中结束程序的优化
    @Override
    public void onBackPressed() {
        flag = false;
        super.onBackPressed();
    }

    @Override
    protected void init() {
        sp = getSharedPreferences("count", MODE_PRIVATE);
        mcurrentVersion = SystemUtil.getSystemVersionCode();
        mlastVersion = SystemUtil.getSharedInt(Constant.VERSION_STRING, -1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //如果是版本更新 或者第一次安装 进入viewpager欢迎界面
                if (mcurrentVersion > mlastVersion || mlastVersion == -1) {
                    int count = sp.getInt("count", 0);
                    if (flag) {
                        if (count == 0) {
                            Intent intent = new Intent(Welcome1Activity.this, Welcome2Activity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(Welcome1Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        SharedPreferences.Editor edit = sp.edit();
                        edit.putInt("count", ++count);
                        edit.commit();
                    }
                }

            }
        }, SPLASH_LENGTH);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadDate() {

    }
}
