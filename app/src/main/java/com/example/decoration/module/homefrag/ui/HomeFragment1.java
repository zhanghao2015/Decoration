package com.example.decoration.module.homefrag.ui;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HomeFragment1 extends BaseFragment {
    private Handler handler;
    private View locating_btn;
    private ImageView message_homefrag;
    private ViewPager viewpager_homefrag_1;
    private HomeFragPagerAdapter homeFragPagerAdapter;
    private RadioButton btn1_virepager_1;
    private RadioButton btn2_virepager_1;
    private RadioButton btn3_virepager_1;
    private RadioButton btn4_virepager_1;
    private boolean isPlay=true;
    private RadioGroup togglebtn_group;
    private LinearLayout togglebtn_latest;
    private LinearLayout togglebtn_recommand;
    private RadioButton ic_home_btn_recommand;


    @Override
    protected int setViewID() {
        return R.layout.layout_home_fragment_1;
    }

    @Override
    protected void findView(View view) {
        locating_btn = view.findViewById(R.id.locating_homefrag);
        message_homefrag = (ImageView) view.findViewById(R.id.message_homefrag);
        viewpager_homefrag_1 = (ViewPager) view.findViewById(R.id.viewpager_homefrag_1);
        btn1_virepager_1 = (RadioButton) view.findViewById(R.id.btn1_virepager_1);
        btn2_virepager_1 = (RadioButton) view.findViewById(R.id.btn2_virepager_1);
        btn3_virepager_1 = (RadioButton) view.findViewById(R.id.btn3_virepager_1);
        btn4_virepager_1 = (RadioButton) view.findViewById(R.id.btn4_virepager_1);
        ic_home_btn_recommand = (RadioButton) view.findViewById(R.id.ic_home_btn_recommand);
        togglebtn_recommand = (LinearLayout) view.findViewById(R.id.togglebtn_recommand);
        togglebtn_latest = (LinearLayout) view.findViewById(R.id.togglebtn_latest);
        togglebtn_group = (RadioGroup)  view.findViewById(R.id.togglebtn_group);
    }

    @Override
    protected void init() {
        //初始化ic_home_btn_recommand
        ic_home_btn_recommand.setChecked(true);
        togglebtn_latest.setVisibility(View.VISIBLE);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    int count = msg.arg1;
                    viewpager_homefrag_1.setCurrentItem(count, true);
                    Log.d("Dream", "count = " + count);
                    if (count == 1) {
                        btn2_virepager_1.setChecked(true);
                    } else if (count == 2) {
                        btn3_virepager_1.setChecked(true);
                    } else if (count == 3) {
                        btn4_virepager_1.setChecked(true);
                    }else if(count == 0){
                        btn1_virepager_1.setChecked(true);
                    }
                }
            }
        };
        //初始化Viewpager加载页面和数据
        List<View> vpData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
//            View view = getActivity().getLayoutInflater().inflate(R.layout.layout_viewpager, null);
            ImageView view = new ImageView(getActivity());
            ViewGroup.LayoutParams params = new ViewPager.LayoutParams();
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            view.setLayoutParams(params);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setImageResource(R.mipmap.product_ad_top);
            vpData.add(view);
        }
        Log.d("Dream", "vpData长度：" + vpData.size());
        homeFragPagerAdapter = new HomeFragPagerAdapter(vpData);
        viewpager_homefrag_1.setAdapter(homeFragPagerAdapter);

        //默认viewpager下面小点在第一个
        btn1_virepager_1.setChecked(true);

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
                EventBus.getDefault().post("to2");
            }
        });

        //消息按钮点击事件监听
        message_homefrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点了message_home", Toast.LENGTH_LONG).show();
            }
        });

        togglebtn_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.ic_home_btn_recommand){
                    togglebtn_recommand.setVisibility(View.VISIBLE);
                    togglebtn_latest.setVisibility(View.GONE);
                }else if(checkedId == R.id.ic_home_btn_latest){
                    togglebtn_latest.setVisibility(View.VISIBLE);
                    togglebtn_recommand.setVisibility(View.GONE);
                }
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
