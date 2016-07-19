package com.example.decoration.modules.main.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.decoration.R;
import com.example.decoration.base.BaseActivity;
import com.example.decoration.module.beautifuleffectfrag.ui.BeautifulEffectFragment;
import com.example.decoration.module.homefrag.bean.IndexBean;
import com.example.decoration.module.homefrag.dao.IndexDao;
import com.example.decoration.module.homefrag.ui.HomeFragment1;
import com.example.decoration.module.homefrag.ui.HomeFragment2;
import com.example.decoration.module.myfrag.myself.ui.MyFragment;
import com.example.decoration.module.nearbyfrag.ui.NearByFragment;
import com.example.decoration.module.ownerfrag.ui.OwnerFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseActivity {

    @ViewInject(R.id.radiobtn_home_main)
    private RadioButton radiobtn_home;

    @ViewInject(R.id.radio_group)
    private RadioGroup radio_Group;
    private PopupWindow pw;
    private RelativeLayout pwView;
    private Button popupbtn_cancel;
    private Button popupbtn_exit;
    private HomeFragment2 homeFragment2;
    private HomeFragment1 homeFragment1;
    private NearByFragment nearByFragment;
    private OwnerFragment ownerFragment;
    private BeautifulEffectFragment beautifulEffectFragment;
    private MyFragment myFragment;
    private Fragment lastFragment;
    private FragmentTransaction transaction;
    public static MainActivity mainActivity;
    private IndexBean indexBean;


    //退出鲜果
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
        homeFragment2 = new HomeFragment2();
        homeFragment1 = new HomeFragment1();

        //加载PopupWindow的布局视图，并找出其中的按钮
        pwView = (RelativeLayout) getLayoutInflater().inflate(R.layout.layout_popupwindow,null);
        popupbtn_cancel = (Button) pwView.findViewById(R.id.popupbtn_cancel_main);
        popupbtn_exit = (Button) pwView.findViewById(R.id.popupbtn_exit_main);
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        mainActivity=this;
        //默认设置进入页面时点击了主页
        radiobtn_home.setChecked(true);
        //初始化PopupWindow
        pw = new PopupWindow(pwView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, false);

        nearByFragment = new NearByFragment();
        ownerFragment = new OwnerFragment();
        beautifulEffectFragment = new BeautifulEffectFragment();
        myFragment = new MyFragment();
        //开启事务，并添加所需要的Fragment，将不需要显示的Fragment先隐藏，
        //默认设置homeFragment2为进入时界面
        lastFragment = homeFragment2;
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,homeFragment2);
        transaction.add(R.id.fragment_container,homeFragment1);
        transaction.hide(homeFragment1);
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
    //设定一个flag值，用来判断当前主页fragment显示的是1布局还是2布局
    boolean flag = true;
    @Subscribe
    public void onEvent(String str){
        transaction = getSupportFragmentManager().beginTransaction();
        if("to1".equals(str)){
            IndexDao.getIndexBean("16");
            flag = false;
            transaction.hide(lastFragment);
            transaction.show(homeFragment1);
            lastFragment = homeFragment1;
        }else if("to2".equals(str)){
            IndexDao.getIndexBean("100");
            flag = true;
            transaction.hide(lastFragment);
            transaction.show(homeFragment2);
            lastFragment = homeFragment2;
        }
        transaction.commit();
    }

    @Override
    protected void initEvent() {
        //radio_Group点击事件监听
        radio_Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobtn_home_main:
                        Toast.makeText(MainActivity.this, "radiobtn_home_main", Toast.LENGTH_LONG).show();
                        if (flag) {
                            showFragment(lastFragment,homeFragment2);
                        }else{
                            showFragment(lastFragment,homeFragment1);
                        }
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

    @Subscribe
    public void onLoadLayout(IndexBean indexBean){
        String layoutID = indexBean.getData().getLayout();
        transaction = getSupportFragmentManager().beginTransaction();
        if("1".equals(layoutID)){
            flag = false;
            transaction.hide(lastFragment);
            transaction.show(homeFragment1);
            lastFragment = homeFragment1;
            Log.d("huizhuang","主页面加载了布局1");
        }else if("2".equals(layoutID)){
            flag = true;
            transaction.hide(lastFragment);
            transaction.show(homeFragment2);
            lastFragment = homeFragment2;
            Log.d("huizhuang","主页面加载了布局2");
        }
        transaction.commit();
    }


    @Override
    protected void loadDate() {
        IndexDao.getIndexBean("100");

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
