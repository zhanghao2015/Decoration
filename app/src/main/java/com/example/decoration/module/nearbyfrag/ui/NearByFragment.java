package com.example.decoration.module.nearbyfrag.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.decoration.R;
import com.example.decoration.base.BaseFragment;

/**
 * Created by Administrator on 2016/7/16.
 */
public class NearByFragment extends BaseFragment{

    private ImageView foremanbtn;
    private RadioGroup titlebtn_nearby;
    private RadioButton btn_foreman;
    private boolean flag = true;

    @Override
    protected int setViewID() {
        return R.layout.layout_nearby_fragment;
    }

    @Override
    protected void findView(View view) {
        titlebtn_nearby = (RadioGroup) view.findViewById(R.id.radio_group_titlebtn_nearby);
        btn_foreman = (RadioButton) view.findViewById(R.id.radiobtn_foreman);
        foremanbtn = (ImageView) view.findViewById(R.id.foremanbtn_nearby);

    }

    @Override
    protected void init() {
        //设置进入界面默认选中'附近工长'
        btn_foreman.setChecked(true);
    }

    @Override
    protected void initEvent() {
        titlebtn_nearby.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radiobtn_decorationsite){
                    foremanbtn.setEnabled(true);
                    foremanbtn.setVisibility(View.VISIBLE);
                }
                else if(checkedId == R.id.radiobtn_foreman){
                    foremanbtn.setEnabled(false);
                    foremanbtn.setVisibility(View.GONE);
                    foremanbtn.setImageResource(R.mipmap.ic_nearby_b_foreman_list);
                    flag = true;
                }
            }
        });

        foremanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    foremanbtn.setImageResource(R.mipmap.ic_foreman_list_map);
                    flag = false;
                }else{
                    foremanbtn.setImageResource(R.mipmap.ic_nearby_b_foreman_list);
                    flag = true;
                }
            }
        });
    }

    @Override
    protected void loadDate() {

    }
}
