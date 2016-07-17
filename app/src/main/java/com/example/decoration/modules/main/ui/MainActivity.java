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
import com.example.decoration.module.beautifuleffectfrag.ui.BeautifulEffectFragment;
import com.example.decoration.module.homefrag.ui.HomeFragment;
import com.example.decoration.module.myfrag.ui.MyFragment;
import com.example.decoration.module.nearbyfrag.ui.NearByFragment;
import com.example.decoration.module.ownerfrag.ui.OwnerFragment;
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
    private OwnerFragment ownerFragment;
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
        //加载PopupWindow的布局视图，并找出其中的按钮
        pwView = (RelativeLayout) getLayoutInflater().inflate(R.layout.layout_popupwindow, null);
        popupbtn_cancel = (Button) pwView.findViewById(R.id.popupbtn_cancel_main);
        popupbtn_exit = (Button) pwView.findViewById(R.id.popupbtn_exit_main);
    }

    @Override
    protected void init() {
        //默认设置进入页面时点击了主页
        radiobtn_home.setChecked(true);
        //初始化PopupWindow
        pw = new PopupWindow(pwView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, false);
        homeFragment = new HomeFragment();
        nearByFragment = new NearByFragment();
        ownerFragment = new OwnerFragment();
        beautifulEffectFragment = new BeautifulEffectFragment();
        myFragment = new MyFragment();
        //开启事务，并添加所需要的Fragment，将不需要显示的Fragment先隐藏，
        //默认设置homeFragment为进入时界面
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,homeFragment);
        lastFragment = homeFragment;
        transaction.add(R.id.fragment_container,nearByFragment);
        transaction.hide(nearByFragment);
        transaction.add(R.id.fragment_container, ownerFragment);
        transaction.hide(ownerFragment);
        transaction.add(R.id.fragment_container,beautifulEffectFragment);
        transaction.hide(beautifulEffectFragment);
        transaction.add(R.id.fragment_container,myFragment);
        transaction.hide(myFragment);
        transaction.commit();
    }

    @Override
    protected void initEvent() {
        //城市定位按钮点击事件监听
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
                        showFragment(lastFragment,homeFragment);
                        break;
                    case R.id.radiobtn_nearby_main:
                        Toast.makeText(MainActivity.this, "radiobtn_nearby_main", Toast.LENGTH_LONG).show();
                        showFragment(lastFragment,nearByFragment);
                        break;
                    case R.id.radiobtn_owner_main:
                        Toast.makeText(MainActivity.this, "radiobtn_owner_main", Toast.LENGTH_LONG).show();
                        showFragment(lastFragment, ownerFragment);
                        break;
                    case R.id.radiobtn_beautifuleffect_main:
                        Toast.makeText(MainActivity.this, "radiobtn_beautifuleffect_main", Toast.LENGTH_LONG).show();
                        showFragment(lastFragment,beautifulEffectFragment);
                        break;
                    case R.id.radiobtn_my_main:
                        Toast.makeText(MainActivity.this, "radiobtn_my_main", Toast.LENGTH_LONG).show();
                        showFragment(lastFragment,myFragment);
                        break;
                }
                transaction.commit();
            }

            //替换显示的Fragment方法
            private void showFragment(Fragment Fragment2Hide, Fragment Fragment2Show) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide(Fragment2Hide);
                transaction.show(Fragment2Show);
                lastFragment = Fragment2Show;
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
