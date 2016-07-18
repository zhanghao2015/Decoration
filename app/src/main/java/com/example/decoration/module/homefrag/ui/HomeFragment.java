package com.example.decoration.module.homefrag.ui;

import android.app.FragmentTransaction;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HomeFragment extends BaseFragment {
    private Handler handler;
    private View locating_btn;
    private ImageView message_homefrag;
    private ViewPager viewpager_homefrag;
    private HomeFragPagerAdapter homeFragPagerAdapter;
    private RadioButton btn1_virepager;
    private RadioButton btn2_virepager;
    private RadioButton btn3_virepager;
    private RadioButton btn4_virepager;
    private boolean isPlay=false;


    @Override
    protected int setViewID() {
        return R.layout.layout_home_fragment;
    }

    @Override
    protected void findView(View view) {
        locating_btn = view.findViewById(R.id.locating_homefrag);
        message_homefrag = (ImageView) view.findViewById(R.id.message_homefrag);
        viewpager_homefrag = (ViewPager) view.findViewById(R.id.viewpager_homefrag);
        btn1_virepager = (RadioButton) view.findViewById(R.id.btn1_virepager);
        btn2_virepager = (RadioButton) view.findViewById(R.id.btn2_virepager);
        btn3_virepager = (RadioButton) view.findViewById(R.id.btn3_virepager);
        btn4_virepager = (RadioButton) view.findViewById(R.id.btn4_virepager);
    }

    @Override
    protected void init() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    int count = msg.arg1;
                    viewpager_homefrag.setCurrentItem(count, true);
                    Log.d("Dream", "count = " + count);
                    if (count == 1) {
                        btn2_virepager.setChecked(true);
                    } else if (count == 2) {
                        btn3_virepager.setChecked(true);
                    } else if (count == 3) {
                        btn4_virepager.setChecked(true);
                    }else if(count == 0){
                        btn1_virepager.setChecked(true);
                    }
                }
            }
        };
        //初始化Viewpager加载页面和数据
        List<View> vpData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.layout_viewpager, null);
            vpData.add(view);
        }
        Log.d("Dream", "vpData长度：" + vpData.size());
        homeFragPagerAdapter = new HomeFragPagerAdapter(vpData);
        viewpager_homefrag.setAdapter(homeFragPagerAdapter);

        //默认viewpager下面小点在第一个
        btn1_virepager.setChecked(true);

        //viewpager自动轮播方法
        new Thread(new Runnable() {
            int count = 1;

            @Override
            public void run() {
                while (isPlay) {
                    SystemClock.sleep(3000);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = count++ % 4;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    @Override
    protected void initEvent() {

        //城市定位按钮点击事件监听
        locating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点了locating_btn", Toast.LENGTH_LONG).show();
            }
        });

        //消息按钮点击事件监听
        message_homefrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点了message_home", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void loadDate() {

    }

    @Override
    public void onDestroy() {
        isPlay=false;
        super.onDestroy();
    }
}
