package com.example.decoration.module.myfrag.myself.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.decoration.MyApplication;
import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.example.decoration.module.myfrag.myself.ui.LoginActivity;
import com.example.decoration.modules.main.ui.MainActivity;


/**
 * Created by Administrator on 2016/7/16.
 */
public class MyFragment extends BaseFragment  {
    ImageView myself_translate_iv1;
    ImageView myself_translate_iv2;
    ImageView myself_translate_iv3;
    Button loginbtn;
    Activity activity;

    private boolean isAlive = true;
    private TranslateAnimation animLoading;
    private TranslateAnimation animHiddening;


    @Override
    protected int setViewID() {
        return R.layout.layout_my_fragment;

    }


    @Override
    protected void findView(View view) {
        myself_translate_iv1 = (ImageView) view.findViewById(R.id.myself_translate_iv1);
        myself_translate_iv2 = (ImageView) view.findViewById(R.id.myself_translate_iv2);
        myself_translate_iv3 = (ImageView) view.findViewById(R.id.myself_translate_iv3);
        loginbtn = (Button) view.findViewById(R.id.login_btn);
    }

    @Override
    protected void init() {
        activity = MainActivity.mainActivity;
        initAnimation();

    }

    /**
     * 一张动画循环左右平移
     */
    @Override
    protected void initEvent() {
        startLoadingAnim(myself_translate_iv1);
//        startLoadingAnim(myself_translate_iv2);
//        startLoadingAnim(myself_translate_iv3);


        new Thread(new Runnable() {
            int what = 1;

            @Override
            public void run() {
                while (isAlive) {
                    try {
                        Thread.sleep(4000);
                        Message msg = mHandler.obtainMessage(what);
                        mHandler.sendMessage(msg);
                        what++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void loadDate() {

    }

    /**
     * 图片无限循环左右平移
     */
    protected void initAnimation() {
        WindowManager windowManager = (WindowManager) MyApplication.context.getSystemService(MyApplication.context.WINDOW_SERVICE);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        Log.d("QF", "屏幕宽度=" + screenWidth);
        //从屏幕右边 2s内移动到最左边
        animLoading = new TranslateAnimation(screenWidth, 0, 0, 0);
        animLoading.setDuration(5000);
        //从屏幕原点 往左边走
        animHiddening = new TranslateAnimation(0, -screenWidth, 0, 0);
        animHiddening.setDuration(5000);

    }

    private void startLoadingAnim(ImageView imageView) {
        imageView.startAnimation(animLoading);
        imageView.setVisibility(View.VISIBLE);
    }

    private void startHiddeningAnim(ImageView imageView) {
        imageView.startAnimation(animHiddening);
        imageView.setVisibility(View.GONE);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int flag = msg.what % 4;
            Log.d("QF", "flag==" + flag);
            // Log.i("QF", "handleMessage(Message msg) -- " +"被执行...");
            if (flag == 0) {
                startLoadingAnim(myself_translate_iv1);
                startHiddeningAnim(myself_translate_iv2);
            } else if (flag == 1) {
                startLoadingAnim(myself_translate_iv2);
                startHiddeningAnim(myself_translate_iv1);
            } else if (flag == 2) {
                startLoadingAnim(myself_translate_iv1);
                startHiddeningAnim(myself_translate_iv2);
            } else if (flag == 3) {
                startLoadingAnim(myself_translate_iv2);
                startHiddeningAnim(myself_translate_iv1);
            }
        }
    };

    @Override
    public void onDestroy() {
        isAlive = false;
        super.onDestroy();

    }


    /**
     * 我的界面 没有登录的情况
     * 1.信息图片 点击登录 我的钱包 我的日记 我的话题 我的收藏  我的投诉 都是跳转到登录界面
     * 2.赚曲装修基金 跳到 获取专属优惠码的界面
     * 3.我的订单 先是跳到我的订单界面 在跳到登录界面
     * 4.意见反馈 跳到反馈界面需要手动填写自己号码
     *
     * 我的界面 登录的情况
     * 1.信息图片跳到消息界面
     * 2.圆形头像 跳到个人资料界面
     * 3.点击登录按钮 变成 请设置昵称  我的钱包跳到钱包界面
     * 4.赚曲装修基金 跳到 已经生存优惠码的界面
     * 5.我的订单跳到预约装修界面
     * 6.我的日记 跳到我的日子界面
     * 7.我的话题跳到 发表帖子界面
     * 8.我的收藏 跳到收藏界面
     * 9.意见反馈跳到自动获取手机号码 反馈界面
     * 10.我的投诉 跳到 投诉界面
     *
     * 联系客服和设置项与登录逻辑无关
     */

}
