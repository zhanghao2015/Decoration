package com.example.decoration.module.myfrag.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.decoration.MyApplication;
import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.example.decoration.modules.main.ui.MainActivity;

/**
 * Created by Administrator on 2016/7/16.
 */
public class MyFragment extends BaseFragment{
    ImageView myself_translate_iv1;
    ImageView myself_translate_iv2;
    ImageView myself_translate_iv3;
    Button loginbtn;
    Activity activity;
    private boolean isAlive=true;
    private TranslateAnimation animLoading;
    private TranslateAnimation animHiddening;

    @Override
    protected int setViewID() {
        return R.layout.layout_my_fragment;

    }


    @Override
    protected void findView(View view) {
        myself_translate_iv1= (ImageView) view.findViewById(R.id.myself_translate_iv1);
        myself_translate_iv2= (ImageView) view.findViewById(R.id.myself_translate_iv2);
        myself_translate_iv3= (ImageView) view.findViewById(R.id.myself_translate_iv3);
        loginbtn= (Button) view.findViewById(R.id.login_btn);
    }

    @Override
    protected void init() {
        activity=MainActivity.mainActivity;
        initAnimation();

    }
    /**
     *一张动画循环左右平移
     */
    @Override
    protected void initEvent() {
        startLoadingAnim(myself_translate_iv1);
//        startLoadingAnim(myself_translate_iv2);
//        startLoadingAnim(myself_translate_iv3);


        new Thread(new Runnable(){
            int what = 1;
            @Override
            public void run() {
                while(isAlive){
                    try {
                        Thread.sleep(4000);
                        Message msg = mHandler.obtainMessage(what);
                        mHandler.sendMessage(msg);
                        what ++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }}).start();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,LoginActivity.class);
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
    protected  void initAnimation(){
       WindowManager windowManager= (WindowManager) MyApplication.context.getSystemService(MyApplication.context.WINDOW_SERVICE);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        Log.d("QF","屏幕宽度="+screenWidth);
        //从屏幕右边 2s内移动到最左边
        animLoading = new TranslateAnimation(screenWidth,0,0,0);
        animLoading.setDuration(5000);
        //从屏幕原点 往左边走
        animHiddening = new TranslateAnimation(0,-screenWidth,0,0);
        animHiddening.setDuration(5000);

    }
    private void startLoadingAnim(ImageView imageView){
        imageView.startAnimation(animLoading);
        imageView.setVisibility(View.VISIBLE);
    }

    private void startHiddeningAnim(ImageView imageView){
        imageView.startAnimation(animHiddening);
        imageView.setVisibility(View.GONE);
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            int flag = msg.what%4;
            Log.d("QF","flag=="+flag);
           // Log.i("QF", "handleMessage(Message msg) -- " +"被执行...");
            if(flag == 0){
                startLoadingAnim(myself_translate_iv1);
                startHiddeningAnim(myself_translate_iv2);
            }else if(flag == 1){
                startLoadingAnim(myself_translate_iv2);
                startHiddeningAnim(myself_translate_iv1);
            }else if(flag == 2){
                startLoadingAnim(myself_translate_iv1);
                startHiddeningAnim(myself_translate_iv2);
            }else if(flag == 3){
                startLoadingAnim(myself_translate_iv2);
                startHiddeningAnim(myself_translate_iv1);
            }
        }
    };

    @Override
    public void onDestroy() {
        isAlive=false;
        super.onDestroy();
    }
}
