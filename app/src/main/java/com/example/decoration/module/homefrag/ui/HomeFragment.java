package com.example.decoration.module.homefrag.ui;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HomeFragment extends BaseFragment{
    private View locating_btn;
    private ImageView message_homefrag;


    @Override
    protected int setViewID() {
        return R.layout.layout_home_fragment;
    }

    @Override
    protected void findView(View view) {
        locating_btn = view.findViewById(R.id.locating_homefrag);
        message_homefrag = (ImageView) view.findViewById(R.id.message_homefrag);
    }

    @Override
    protected void init() {

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
}
