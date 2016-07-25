package com.example.decoration.module.homefrag.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.util.TimeUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.example.decoration.module.homefrag.bean.IndexBean;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HomeFragment2 extends BaseFragment {
    private Handler handler;
    private View locating_btn;
    private ImageView message_homefrag;
    private ViewPager viewpager_homefrag;
    private HomeFragPagerAdapter homeFragPagerAdapter;
    private boolean isPlay = true;
    private List<ImageView> vpData;
    private List<RadioButton> vpDots;
    private ImageView gongjutu1;
    private ImageView gongjutu2;
    private ImageView gongjutu3;
    private ImageView gongjutu5;
    private ImageView gongjutu4;
    private ImageView gongjutu6;
    private TextView tv_gongjutu1;
    private TextView tv_gongjutu2;
    private TextView tv_gongjutu3;
    private TextView tv_gongjutu4;
    private TextView tv_gongjutu5;
    private TextView tv_gongjutu6;
    private TextView tv_site_name;
    private LinearLayout gongju1;
    private LinearLayout gongju2;
    private LinearLayout gongju3;
    private LinearLayout gongju4;
    private LinearLayout gongju5;
    private LinearLayout gongju6;
    private List<IndexBean.DataBean.JiaodiantuBean> jiaodiantu;
    private List<IndexBean.DataBean.TuijiangongjuBean> tuijiangongju;
    private ImageView advertise_right;
    private ImageView advertise_left;
    private RadioGroup viewpager_container;
    private int dotSize = 2;
    private Timer timer;


    @Override
    protected int setViewID() {
        return R.layout.layout_home_fragment_2;
    }

    @Override
    protected void findView(View view) {
        //轮播图小点RadioGroup容器
        viewpager_container = (RadioGroup) view.findViewById(R.id.btn_viewpager_container);
        locating_btn = view.findViewById(R.id.locating_homefrag);
        message_homefrag = (ImageView) view.findViewById(R.id.message_homefrag);
        viewpager_homefrag = (ViewPager) view.findViewById(R.id.viewpager_homefrag);
        advertise_right = (ImageView) view.findViewById(R.id.advertise_right_homefrag_homefrag);
        advertise_left = (ImageView) view.findViewById(R.id.advertise_left_homefrag_homefrag);
        gongju1 = (LinearLayout) view.findViewById(R.id.gongju1);
        gongju2 = (LinearLayout) view.findViewById(R.id.gongju2);
        gongju3 = (LinearLayout) view.findViewById(R.id.gongju3);
        gongju4 = (LinearLayout) view.findViewById(R.id.gongju4);
        gongju5 = (LinearLayout) view.findViewById(R.id.gongju5);
        gongju6 = (LinearLayout) view.findViewById(R.id.gongju6);
        gongjutu1 = (ImageView) view.findViewById(R.id.gongjutu1);
        gongjutu2 = (ImageView) view.findViewById(R.id.gongjutu2);
        gongjutu3 = (ImageView) view.findViewById(R.id.gongjutu3);
        gongjutu4 = (ImageView) view.findViewById(R.id.gongjutu4);
        gongjutu5 = (ImageView) view.findViewById(R.id.gongjutu5);
        gongjutu6 = (ImageView) view.findViewById(R.id.gongjutu6);
        tv_gongjutu1 = (TextView) view.findViewById(R.id.tv_gongjutu1);
        tv_gongjutu2 = (TextView) view.findViewById(R.id.tv_gongjutu2);
        tv_gongjutu3 = (TextView) view.findViewById(R.id.tv_gongjutu3);
        tv_gongjutu4 = (TextView) view.findViewById(R.id.tv_gongjutu4);
        tv_gongjutu5 = (TextView) view.findViewById(R.id.tv_gongjutu5);
        tv_gongjutu6 = (TextView) view.findViewById(R.id.tv_gongjutu6);
        tv_site_name = (TextView) view.findViewById(R.id.tv_site_name);

    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        //处理dot小点与viewpager一起运动
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    int count = msg.arg1;
                    viewpager_homefrag.setCurrentItem(count, true);
                    Log.d("Dream", "count = " + count);
                    vpDots.get(count).setChecked(true);
                }
            }
        };
        //初始化Viewpager加载页面和数据
        vpDots = new ArrayList<>();
        vpData = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            //为ViewPager和下部小点创建视图，默认有4个
            ImageView imageView = createImageView();
            vpData.add(imageView);
            RadioButton radioButton = createRadioButton();
            vpDots.add(radioButton);
            viewpager_container.addView(radioButton);
        }
        Log.d("Dream", "vpData长度：" + vpData.size());
        Log.d("Dream", "vpDots长度：" + vpDots.size());
        homeFragPagerAdapter = new HomeFragPagerAdapter(this, vpData);
        viewpager_homefrag.setAdapter(homeFragPagerAdapter);

        //默认viewpager下面小点在第一个
        vpDots.get(0).setChecked(true);

        //viewpager自动轮播方法
        playViewPager(1);
    }

    private void playViewPager(final int count) {
        new Thread(new Runnable() {
            int mCount = count;

            @Override
            public void run() {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.arg1 = mCount++ % dotSize;
                        handler.sendMessage(msg);
                        if (mCount == dotSize) {
                            mCount = 0;
                        }
                    }
                }, 0, 3000);
            }
        }).start();
    }

    private RadioButton createRadioButton() {
        RadioButton radioButton = new RadioButton(getActivity());
        radioButton.setButtonDrawable(R.drawable.btn_viewpager_homefrag_selector);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        lp.setMargins(0, 0, 100, 0);
//        radioButton.setLayoutParams(lp);
        return radioButton;
    }

    private ImageView createImageView() {
        ImageView view = new ImageView(getActivity());
        ViewGroup.LayoutParams params = new ViewPager.LayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setImageResource(R.mipmap.ic_layout_empty);
        return view;
    }

    @Override
    protected void initEvent() {

        //城市定位按钮点击事件监听
        locating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点了locating_btn", Toast.LENGTH_LONG).show();
                EventBus.getDefault().post("请求选择城市");
            }
        });

        //消息按钮点击事件监听
        message_homefrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点了message_home", Toast.LENGTH_LONG).show();
            }
        });

        advertise_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("跳转到附近");
                Log.d("hui", "右广告图");
            }
        });

        //推荐工具点击事件
        gongju1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("跳转到美图");
                Log.d("hui", "效果图");
            }
        });
        gongju2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tuijiangongju != null) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("target_url", tuijiangongju.get(1).getTarget_url());
                    intent.putExtra("title", tuijiangongju.get(1).getTitle());
                    startActivity(intent);
                    Log.d("hui", "装修宝典");
                }
            }
        });
        gongju3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hui", "写日记");
            }
        });
        gongju4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tuijiangongju != null) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("target_url", tuijiangongju.get(3).getTarget_url());
                    intent.putExtra("title", tuijiangongju.get(3).getTitle());
                    startActivity(intent);
                    Log.d("hui", "精选案例");
                }
            }
        });
        gongju5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hui", "我的收藏");
            }
        });
        gongju6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tuijiangongju != null) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("target_url", tuijiangongju.get(5).getTarget_url());
                    intent.putExtra("title", tuijiangongju.get(5).getTitle());
                    startActivity(intent);
                    Log.d("hui", "装修基金");
                }
            }
        });

        viewpager_homefrag.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vpDots.get(position).setChecked(true);
                if (timer != null) {
                    timer.cancel();
                    timer.purge();
                }
                playViewPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //加载2布局数据
    @Subscribe
    public void onLoadData(LoadLayout2 layout2) {
        IndexBean indexBean = layout2.getIndexBean();
        tv_site_name.setText(layout2.getSite_name());
        if ("2".equals(indexBean.getData().getLayout())) {
            jiaodiantu = indexBean.getData().getJiaodiantu();
            dotSize = jiaodiantu.size();
            Log.d("hui", "dotSize = " + dotSize);
            //判断传回的视图个数与ViewPager中的视图个数是否相等，并使其二者包含视图个数相等
            if (jiaodiantu.size() > 4) {
                for (int i = 0; i < jiaodiantu.size() - vpData.size(); i++) {
                    vpData.add(createImageView());
                    RadioButton radioButton = createRadioButton();
                    vpDots.add(radioButton);
                    viewpager_container.addView(radioButton);
                }
                homeFragPagerAdapter.setJiaodiantu(jiaodiantu);
                homeFragPagerAdapter.notifyDataSetChanged();
            }

            //开始加载轮播图
            for (int i = 0; i < jiaodiantu.size(); i++) {
                Picasso.with(getActivity())
                        .load(jiaodiantu.get(i).getUrl())
                        .config(Bitmap.Config.RGB_565)
                        .into(vpData.get(i));
            }
            //加载推荐工具图标和文字
            tuijiangongju = indexBean.getData().getTuijiangongju();
            loadTuiJianGongJu(tuijiangongju);
        }
    }

    private void loadTuiJianGongJu(List<IndexBean.DataBean.TuijiangongjuBean> tuijiangongju) {
        for (int i = 0; i < tuijiangongju.size(); i++) {
            String url = tuijiangongju.get(i).getUrl();
            String title = tuijiangongju.get(i).getTitle();
            switch (i) {
                case 0:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(gongjutu1);
                    tv_gongjutu1.setText(title);
                    break;
                case 1:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(gongjutu2);
                    tv_gongjutu2.setText(title);
                    break;
                case 2:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(gongjutu3);
                    tv_gongjutu3.setText(title);
                    break;
                case 3:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(gongjutu4);
                    tv_gongjutu4.setText(title);
                    break;
                case 4:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(gongjutu5);
                    tv_gongjutu5.setText(title);
                    break;
                case 5:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(gongjutu6);
                    tv_gongjutu6.setText(title);
                    break;
            }
        }
    }

    @Override
    protected void loadDate() {

    }

    @Override
    public void onDestroy() {
        isPlay = false;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
