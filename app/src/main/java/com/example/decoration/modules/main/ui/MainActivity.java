package com.example.decoration.modules.main.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.decoration.R;
import com.example.decoration.base.BaseActivity;

import com.example.decoration.modules.beautifulImg.BeautifulEffectFragment;
import com.example.decoration.modules.home.HomeFragment;
import com.example.decoration.modules.hzone.OwenerFragment;
import com.example.decoration.modules.myself.MyFragment;
import com.example.decoration.modules.nearby.NearByFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends BaseActivity {

    @ViewInject(R.id.radiobtn_home_main)
    private RadioButton radiobtn_home;

    @ViewInject(R.id.radio_group)
    private RadioGroup radio_Group;

    @ViewInject(R.id.locating_main)
    private View locating_btn;

    @ViewInject(R.id.message_home)
    private ImageView message_home;

    private PopupWindow pw;
    private RelativeLayout pwView;
    private Button popupbtn_cancel;
    private Button popupbtn_exit;
    private HomeFragment homeFragment;
    private NearByFragment nearByFragment;
    private OwenerFragment owenerFragment;
    private BeautifulEffectFragment beautifulEffectFragment;
    private MyFragment myFragment;
    private Fragment lastFragment;
    private FragmentTransaction transaction;

    @Override
    public void onBackPressed() {
        if (pw.isShowing()) {
            setAplh(1f);
            pw.dismiss();
        } else {
            setAplh(0.5f);
            pw.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }

    }

    //点击PopupWindow时设置背景变暗方法
    private void setAplh(float alph) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = alph;
        getWindow().setAttributes(params);
    }

    @Override
    protected int setViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findView() {
        ViewUtils.inject(this);
        pwView = (RelativeLayout) getLayoutInflater().inflate(R.layout.layout_popupwindow, null);
        popupbtn_cancel = (Button) pwView.findViewById(R.id.popupbtn_cancel_main);
        popupbtn_exit = (Button) pwView.findViewById(R.id.popupbtn_exit_main);
    }

    @Override
    protected void init() {
        //默认设置进入页面时点击了主页
        radiobtn_home.setChecked(true);
        pw = new PopupWindow(pwView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, false);
        homeFragment = new HomeFragment();
        nearByFragment = new NearByFragment();
        owenerFragment = new OwenerFragment();
        beautifulEffectFragment = new BeautifulEffectFragment();
        myFragment = new MyFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,homeFragment);
        //默认进入主界面处于主页Fragment
        lastFragment = homeFragment;
        transaction.add(R.id.fragment_container,nearByFragment);
        transaction.hide(nearByFragment);
        transaction.add(R.id.fragment_container,owenerFragment);
        transaction.hide(owenerFragment);
        transaction.add(R.id.fragment_container,beautifulEffectFragment);
        transaction.hide(beautifulEffectFragment);
        transaction.add(R.id.fragment_container,myFragment);
        transaction.hide(myFragment);
        transaction.commit();
    }

    @Override
    protected void initEvent() {
        //定位按钮点击事件监听
        locating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点了locating_btn", Toast.LENGTH_LONG).show();
            }
        });

        //消息按钮点击事件监听
        message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点了message_home", Toast.LENGTH_LONG).show();
            }
        });

        //radio_Group点击事件监听
        radio_Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobtn_home_main:
                        Toast.makeText(MainActivity.this, "radiobtn_home_main", Toast.LENGTH_LONG).show();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.hide(lastFragment);
                        transaction.show(homeFragment);
                        lastFragment = homeFragment;
                        break;
                    case R.id.radiobtn_nearby_main:
                        Toast.makeText(MainActivity.this, "radiobtn_nearby_main", Toast.LENGTH_LONG).show();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.hide(lastFragment);
                        transaction.show(nearByFragment);
                        lastFragment = nearByFragment;
                        break;
                    case R.id.radiobtn_owner_main:
                        Toast.makeText(MainActivity.this, "radiobtn_owner_main", Toast.LENGTH_LONG).show();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.hide(lastFragment);
                        transaction.show(owenerFragment);
                        lastFragment = owenerFragment;
                        break;
                    case R.id.radiobtn_beautifuleffect_main:
                        Toast.makeText(MainActivity.this, "radiobtn_beautifuleffect_main", Toast.LENGTH_LONG).show();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.hide(lastFragment);
                        transaction.show(beautifulEffectFragment);
                        lastFragment = beautifulEffectFragment;
                        break;
                    case R.id.radiobtn_my_main:
                        Toast.makeText(MainActivity.this, "radiobtn_my_main", Toast.LENGTH_LONG).show();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.hide(lastFragment);
                        transaction.show(myFragment);
                        lastFragment = myFragment;
                        break;
                }
                transaction.commit();
            }
        });
        //给Popupwindow取消按钮设监听
        popupbtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "popupbtn_cancel", Toast.LENGTH_LONG).show();
                setAplh(1f);
                pw.dismiss();
            }
        });
        //给Popupwindow退出按钮设监听
        popupbtn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "popupbtn_exit", Toast.LENGTH_LONG).show();
                pw.dismiss();
                finish();
            }
        });

    }

    @Override
    protected void loadDate() {

    }
}
