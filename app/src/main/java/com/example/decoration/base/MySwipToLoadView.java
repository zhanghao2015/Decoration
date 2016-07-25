package com.example.decoration.base;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.example.decoration.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 16-7-25.
 */
public class MySwipToLoadView extends RelativeLayout implements SwipeRefreshTrigger, SwipeTrigger {

    private final TextView refresh_msg;
    private final TextView pre_refresh_time;
    private final ImageView iv_refresh;
    private boolean isfirst = true;

    public MySwipToLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.swipetoloadview,this);
        refresh_msg = (TextView) findViewById(R.id.refresh_msg);
        pre_refresh_time = (TextView) findViewById(R.id.pre_refresh_time);
        iv_refresh = (ImageView) findViewById(R.id.iv_refresh);
    }

    @Override
    public void onRefresh() {
        pre_refresh_time.setText("上次刷新时间："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        refresh_msg.setText("正在刷新");
        iv_refresh.setRotation(0);
        iv_refresh.setImageBitmap(null);
        iv_refresh.setBackgroundResource(R.drawable.animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_refresh.getBackground();
        animationDrawable.start();
    }

    @Override
    public void onPrepare() {
        if(isfirst){
            pre_refresh_time.setVisibility(INVISIBLE);
            isfirst = false;
        }else{
            pre_refresh_time.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean b1) {
        if (!isComplete) {
            float v = yScrolled * 1.0f / getHeight();
            iv_refresh.setRotation(180*v);
            if (yScrolled >= getHeight()) {
                refresh_msg.setText("释放刷新");
            } else {
                refresh_msg.setText("继续下拉");
            }
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        refresh_msg.setText("刷新完成");
    }

    @Override
    public void onReset() {
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        iv_refresh.setImageResource(R.mipmap.pull_to_refresh_arrow);
        iv_refresh.setBackgroundResource(android.R.color.transparent);
    }
}
