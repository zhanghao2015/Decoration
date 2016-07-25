package com.example.decoration.module.homefrag.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.example.decoration.common.utils.LruCacheUtils;
import com.example.decoration.module.homefrag.bean.IndexBean;
import com.example.decoration.module.homefrag.dao.IndexDao;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HomeFragment1 extends BaseFragment {
    private Handler handler;
    private View locating_btn;
    private ImageView message_homefrag;
    private ViewPager viewpager_homefrag_1;
    private HomeFragPagerAdapter homeFragPagerAdapter;
    private boolean isPlay = true;
    private RadioGroup togglebtn_group;
    private ImageView togglebtn_latest;
    private ImageView togglebtn_recommand;
    private RadioButton ic_home_btn_recommand;
    private View fragment_inner_container;
    private List<ImageView> vpData;
    private List<RadioButton> vpDots;
    private ImageView jingxuanfenlei1;
    private ImageView jingxuanfenlei2;
    private ImageView jingxuanfenlei3;
    private ImageView jingxuanfenlei4;
    private TextView tv_jingxuanfenlei1;
    private TextView tv_jingxuanfenlei2;
    private TextView tv_jingxuanfenlei3;
    private TextView tv_jingxuanfenlei4;
    private TextView tv_site_name;
    private Recommand_InnerFragment recommandFragment;
    private FragmentTransaction transaction;
    private LatestToday_InnerFragment latestToday_innerFragment;
    private Fragment lastInnerFrag;
    private List<IndexBean.DataBean.JiaodiantuBean> jiaodiantu;
    private List<IndexBean.DataBean.JingxuanfenleiBean> jingxuanfenlei;
    private LinearLayout jingxuangongju1;
    private LinearLayout jingxuangongju2;
    private LinearLayout jingxuangongju3;
    private LinearLayout jingxuangongju4;
    private ImageView advertise_right;
    private int dotSize = 2;
    private RadioGroup viewpager_container;
    private Timer timer;
    private SwipeToLoadLayout swipeToLoadLayout;
    private String site_id;
    private String site_name;

    @Override
    protected int setViewID() {
        return R.layout.layout_home_fragment_1;
    }

    @Override
    protected void findView(View view) {
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        locating_btn = view.findViewById(R.id.locating_homefrag);
        message_homefrag = (ImageView) view.findViewById(R.id.message_homefrag);
        viewpager_homefrag_1 = (ViewPager) view.findViewById(R.id.viewpager_homefrag_1);
        fragment_inner_container = view.findViewById(R.id.fragment_inner_container);
        ic_home_btn_recommand = (RadioButton) view.findViewById(R.id.ic_home_btn_recommand);
        togglebtn_recommand = (ImageView) view.findViewById(R.id.togglebtn_recommand);
        togglebtn_latest = (ImageView) view.findViewById(R.id.togglebtn_latest);
        togglebtn_group = (RadioGroup) view.findViewById(R.id.togglebtn_group);
        viewpager_container = (RadioGroup) view.findViewById(R.id.btn_viewpager_container2);
        advertise_right = (ImageView) view.findViewById(R.id.advertise_right_homefrag_homefrag);

        jingxuangongju1 = (LinearLayout) view.findViewById(R.id.jingxuangongju1);
        jingxuangongju2 = (LinearLayout) view.findViewById(R.id.jingxuangongju2);
        jingxuangongju3 = (LinearLayout) view.findViewById(R.id.jingxuangongju3);
        jingxuangongju4 = (LinearLayout) view.findViewById(R.id.jingxuangongju4);

        jingxuanfenlei1 = (ImageView) view.findViewById(R.id.jingxuanfenlei1);
        jingxuanfenlei2 = (ImageView) view.findViewById(R.id.jingxuanfenlei2);
        jingxuanfenlei3 = (ImageView) view.findViewById(R.id.jingxuanfenlei3);
        jingxuanfenlei4 = (ImageView) view.findViewById(R.id.jingxuanfenlei4);

        tv_jingxuanfenlei1 = (TextView) view.findViewById(R.id.tv_jingxuanfenlei1);
        tv_jingxuanfenlei2 = (TextView) view.findViewById(R.id.tv_jingxuanfenlei2);
        tv_jingxuanfenlei3 = (TextView) view.findViewById(R.id.tv_jingxuanfenlei3);
        tv_jingxuanfenlei4 = (TextView) view.findViewById(R.id.tv_jingxuanfenlei4);
        tv_site_name = (TextView) view.findViewById(R.id.tv_site_name);

    }

    @Override
    protected void init() {
        //初始化ic_home_btn_recommand(为你推荐Button)
        ic_home_btn_recommand.setChecked(true);
        togglebtn_recommand.setBackgroundColor(Color.RED);

        //轮播图播放
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    int count = msg.arg1;
                    viewpager_homefrag_1.setCurrentItem(count, true);
                    vpDots.get(count).setChecked(true);
                    Log.d("Dream", "count = " + count);

                }
            }
        };
        //初始化Viewpager加载页面和数据
        vpDots = new ArrayList<>();
        vpData = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ImageView view = createImageView();
            vpData.add(view);
            RadioButton radioButton = createRadioButton();
            vpDots.add(radioButton);
            viewpager_container.addView(radioButton);
        }
        Log.d("Dream", "vpData长度：" + vpData.size());
        homeFragPagerAdapter = new HomeFragPagerAdapter(this, vpData);
        viewpager_homefrag_1.setAdapter(homeFragPagerAdapter);

        //默认viewpager下面小点在第一个
        vpDots.get(0).setChecked(true);

        //viewpager自动轮播方法
        playViewPager(1);

        //加载内部fragment
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        recommandFragment = new Recommand_InnerFragment();
        latestToday_innerFragment = new LatestToday_InnerFragment();
        lastInnerFrag = recommandFragment;
        transaction.add(R.id.fragment_inner_container, recommandFragment);
        transaction.add(R.id.fragment_inner_container, latestToday_innerFragment);
        transaction.hide(latestToday_innerFragment);
        transaction.commit();

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
        //设置margin
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 20, 0);
        radioButton.setLayoutParams(lp);
        return radioButton;
    }

    private ImageView createImageView() {
        ImageView view = new ImageView(getActivity());
        ViewGroup.LayoutParams params = new ViewPager.LayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setImageResource(R.mipmap.product_ad_top);
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
                advertise_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post("跳转到附近");
                        Log.d("hui", "右广告图");
                    }
                });
            }
        });

        //为你推荐，今日最新按键点击监听
        togglebtn_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //点击为你推荐
                if (checkedId == R.id.ic_home_btn_recommand) {
                    togglebtn_latest.setBackgroundColor(Color.TRANSPARENT);
                    togglebtn_recommand.setBackgroundColor(Color.RED);
                    lastInnerFrag = recommandFragment;
                    //显示recommandFragment视图
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.hide(latestToday_innerFragment);
                    transaction.show(recommandFragment);
                    transaction.commit();

                    //点击今日最新
                } else if (checkedId == R.id.ic_home_btn_latest) {
                    togglebtn_recommand.setBackgroundColor(Color.TRANSPARENT);
                    togglebtn_latest.setBackgroundColor(Color.RED);
                    lastInnerFrag = latestToday_innerFragment;
                    //显示latestToday_innerFragment视图
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.hide(recommandFragment);
                    transaction.show(latestToday_innerFragment);
                    transaction.commit();
                }
            }
        });

        //精选分类按钮监听
        jingxuangongju1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jingxuanfenlei != null) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("target_url", jingxuanfenlei.get(0).getTarget_url());
                    intent.putExtra("title", jingxuanfenlei.get(0).getTitle());
                    startActivity(intent);
                    Log.d("hui", "装修宝典");
                }
            }
        });

        jingxuangongju2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jingxuanfenlei != null){
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("target_url", jingxuanfenlei.get(1).getTarget_url());
                    intent.putExtra("title", jingxuanfenlei.get(1).getTitle());
                    startActivity(intent);
                    Log.d("hui", "装修日记");
                }
            }
        });

        jingxuangongju3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jingxuanfenlei != null) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("target_url", jingxuanfenlei.get(2).getTarget_url());
                    intent.putExtra("title", jingxuanfenlei.get(2).getTitle());
                    startActivity(intent);
                    Log.d("hui", "精选案例");
                }
            }
        });

        jingxuangongju4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jingxuanfenlei != null) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("target_url", jingxuanfenlei.get(3).getTarget_url());
                    intent.putExtra("title", jingxuanfenlei.get(3).getTitle());
                    startActivity(intent);
                    Log.d("hui", "领500元");
                }
            }
        });

        //viewpager滑动监听
        viewpager_homefrag_1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vpDots.get(position).setChecked(true);
                if (timer != null) {
                    timer.cancel();
                    timer.purge();
                    timer = null;
                }
                playViewPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //下拉刷新监听
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("huizhuang","下拉刷新");
                IndexDao.getIndexBean(site_id,site_name,getActivity());
                //设置下拉刷新结束
//                swipeToLoadLayout.setRefreshing(false);
            }
        });


    }

    @Override
    protected void loadDate() {

    }

    //加载布局1数据
    @Subscribe
    public void onLoadLayout1(LoadLayout1 loadLayout1) {
        IndexBean indexBean = loadLayout1.getIndexBean();
        //设置城市名字
        site_name = loadLayout1.getSite_name();
        site_id = loadLayout1.getSite_id();
        tv_site_name.setText(site_name);
        if ("1".equals(indexBean.getData().getLayout())) {
            //加载轮播图
            jiaodiantu = indexBean.getData().getJiaodiantu();
            dotSize = jiaodiantu.size();
            //判断传回的视图个数与ViewPager中的视图个数是否相等，并使其二者包含视图个数相等
            if (jiaodiantu.size() > vpData.size()) {
                for (int i = 0; i < jiaodiantu.size() - vpData.size(); i++) {
                    ImageView imageView = createImageView();
                    vpData.add(imageView);
                    RadioButton radioButton = createRadioButton();
                    vpDots.add(radioButton);
                    viewpager_container.addView(radioButton);
                }
                homeFragPagerAdapter.setJiaodiantu(jiaodiantu);
                homeFragPagerAdapter.notifyDataSetChanged();
            }
            for (int i = 0; i < jiaodiantu.size(); i++) {
                Picasso.with(getActivity())
                        .load(jiaodiantu.get(i).getUrl())
                        .config(Bitmap.Config.RGB_565)
                        .into(vpData.get(i));
            }
            //加载精选分类数据
            jingxuanfenlei = indexBean.getData().getJingxuanfenlei();
            loadJingXuanFenLei(jingxuanfenlei);
        }
    }

    private void loadJingXuanFenLei(List<IndexBean.DataBean.JingxuanfenleiBean> jingxuanfenlei) {
        for (int i = 0; i < jingxuanfenlei.size(); i++) {
            String url = jingxuanfenlei.get(i).getUrl();
            String title = jingxuanfenlei.get(i).getTitle();
            switch (i) {
                case 0:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(jingxuanfenlei1);
                    tv_jingxuanfenlei1.setText(title);
                    break;
                case 1:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(jingxuanfenlei2);
                    tv_jingxuanfenlei2.setText(title);
                    break;
                case 2:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(jingxuanfenlei3);
                    tv_jingxuanfenlei3.setText(title);
                    break;
                case 3:
                    Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565).into(jingxuanfenlei4);
                    tv_jingxuanfenlei4.setText(title);
                    break;
            }
        }

        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        isPlay = false;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
