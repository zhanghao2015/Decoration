package com.example.decoration.module.main.ui;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.decoration.R;
import com.example.decoration.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class Welcome2Activity extends BaseActivity {


    private ViewPager viewPager;
    private List<ImageView> listLV;
    private Button entry_btn;
    @Override
    protected int setViewId() {
        return R.layout.activity_welcome2;
    }

    @Override
    protected void findView() {
        viewPager = (ViewPager) findViewById(R.id.welcome_vp);
        entry_btn= (Button) findViewById(R.id.entry_btn);
    }

    @Override
    protected void init() {
        listLV = new ArrayList<>();
        addImageView(R.mipmap.guide_new_1);
        addImageView(R.mipmap.guide_new_2);
        addImageView(R.mipmap.guide_new_3);


        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return listLV.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(listLV.get(position));
                return listLV.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(listLV.get(position));
            }

        });


    }

    private void addImageView(int ivRes) {
        ImageView iv1=new ImageView(Welcome2Activity.this);
        iv1.setImageResource(ivRes);
        iv1.setScaleType(ImageView.ScaleType.FIT_XY);
        listLV.add(iv1);
    }

    @Override
    protected void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //如果到最后一个pagex显示button
                if(position==listLV.size()-1){
                    entry_btn.setVisibility(View.VISIBLE);
                }else{
                    entry_btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        entry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome2Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void loadDate() {

    }
}
