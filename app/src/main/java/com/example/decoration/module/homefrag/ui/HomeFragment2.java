package com.example.decoration.module.homefrag.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.example.decoration.module.homefrag.bean.IndexBean;
import com.example.decoration.module.homefrag.dao.IndexDao;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HomeFragment2 extends BaseFragment {
    private Handler handler;
    private View locating_btn;
    private ImageView message_homefrag;
    private ViewPager viewpager_homefrag;
    private HomeFragPagerAdapter homeFragPagerAdapter;
    private RadioButton btn1_virepager;
    private RadioButton btn2_virepager;
    private RadioButton btn3_virepager;
    private RadioButton btn4_virepager;
    private boolean isPlay = true;
    private List<View> vpData;
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int setViewID() {
        return R.layout.layout_home_fragment_2;
    }

    @Override
    protected void findView(View view) {
        //轮播图小点RadioGroup容器
        RadioGroup viewpager_container = (RadioGroup) view.findViewById(R.id.btn_viewpager_container);
        locating_btn = view.findViewById(R.id.locating_homefrag);
        message_homefrag = (ImageView) view.findViewById(R.id.message_homefrag);
        viewpager_homefrag = (ViewPager) view.findViewById(R.id.viewpager_homefrag);
        btn1_virepager = (RadioButton) view.findViewById(R.id.btn1_virepager);
        btn2_virepager = (RadioButton) view.findViewById(R.id.btn2_virepager);
        btn3_virepager = (RadioButton) view.findViewById(R.id.btn3_virepager);
        btn4_virepager = (RadioButton) view.findViewById(R.id.btn4_virepager);

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
                    } else if (count == 0) {
                        btn1_virepager.setChecked(true);
                    }
                }
            }
        };
        //初始化Viewpager加载页面和数据
        vpData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            //为ViewPager创建视图，默认有4个
            ImageView view = createImageView();
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
                EventBus.getDefault().post("to1");
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

    //加载2布局数据
    @Subscribe
    public void onLoadData(LoadLayout2 layout2) {
        IndexBean indexBean = layout2.getIndexBean();
        String layoutID = indexBean.getData().getLayout();
        if ("2".equals(layoutID)) {
            List<IndexBean.DataBean.JiaodiantuBean> jiaodiantu = indexBean.getData().getJiaodiantu();
            //判断传回的视图个数与ViewPager中的视图个数是否相等，并使其二者包含视图个数相等
            if (jiaodiantu.size() > vpData.size()) {
                for (int i = 0; i < jiaodiantu.size() - vpData.size(); i++) {
                    ImageView imageView = createImageView();
                    vpData.add(imageView);
                    homeFragPagerAdapter.notifyDataSetChanged();
                }
            }
            //开始加载轮播图
            for (int i = 0; i < jiaodiantu.size(); i++) {
                Picasso.with(getActivity())
                        .load(jiaodiantu.get(i).getUrl())
                        .into((ImageView) vpData.get(i));
                Log.d("huizhuang", "加载了第" + i + "张轮播图");
            }

            //加载推荐工具图标和文字
            List<IndexBean.DataBean.TuijiangongjuBean> tuijiangongju = indexBean.getData().getTuijiangongju();
            loadTuiJianGongJu(tuijiangongju);
        }
    }

    private void loadTuiJianGongJu(List<IndexBean.DataBean.TuijiangongjuBean> tuijiangongju) {
        for (int i = 0; i < tuijiangongju.size(); i++) {
            String url = tuijiangongju.get(i).getUrl();
            String title = tuijiangongju.get(i).getTitle();
            switch (i) {
                case 0:
                    Picasso.with(getActivity()).load(url).into(gongjutu1);
                    tv_gongjutu1.setText(title);
                    break;
                case 1:
                    Picasso.with(getActivity()).load(url).into(gongjutu2);
                    tv_gongjutu2.setText(title);
                    break;
                case 2:
                    Picasso.with(getActivity()).load(url).into(gongjutu3);
                    tv_gongjutu3.setText(title);
                    break;
                case 3:
                    Picasso.with(getActivity()).load(url).into(gongjutu4);
                    tv_gongjutu4.setText(title);
                    break;
                case 4:
                    Picasso.with(getActivity()).load(url).into(gongjutu5);
                    tv_gongjutu5.setText(title);
                    break;
                case 5:
                    Picasso.with(getActivity()).load(url).into(gongjutu6);
                    tv_gongjutu6.setText(title);
                    break;
            }
            Log.d("huizhuang", "加载图片网址:" + url);
            Log.d("huizhuang", "加载标题:" + title);
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
