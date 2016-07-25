package com.example.decoration.modules.main.ui;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
import com.example.decoration.modules.main.bean.CityBean;
import com.example.decoration.modules.main.dao.SiteDao;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

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
    private RadioButton radiobtn_beautifuleffect_main;
    private RadioButton radiobtn_nearby_main;
    private AlertDialog dialog;


    //退出优化
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
        //注入控件
        ViewUtils.inject(this);
        //加载PopupWindow的布局视图，并找出其中的按钮
        pwView = (RelativeLayout) getLayoutInflater().inflate(R.layout.layout_popupwindow, null);
        radiobtn_beautifuleffect_main = (RadioButton) findViewById(R.id.radiobtn_beautifuleffect_main);
        radiobtn_nearby_main = (RadioButton) findViewById(R.id.radiobtn_nearby_main);
        popupbtn_cancel = (Button) pwView.findViewById(R.id.popupbtn_cancel_main);
        popupbtn_exit = (Button) pwView.findViewById(R.id.popupbtn_exit_main);
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        //默认设置进入页面时点击了主页
        radiobtn_home.setChecked(true);
        //初始化PopupWindow
        pw = new PopupWindow(pwView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, false);
        homeFragment2 = new HomeFragment2();
        homeFragment1 = new HomeFragment1();
        nearByFragment = new NearByFragment();
        ownerFragment = new OwnerFragment();
        beautifulEffectFragment = new BeautifulEffectFragment();
        myFragment = new MyFragment();
        //开启事务，并添加所需要的Fragment，将不需要显示的Fragment先隐藏，
        //默认设置homeFragment1为进入时界面
        lastFragment = homeFragment1;
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, homeFragment1);
        transaction.add(R.id.fragment_container, homeFragment2);
        transaction.hide(homeFragment2);
        transaction.add(R.id.fragment_container, nearByFragment);
        transaction.hide(nearByFragment);
        transaction.add(R.id.fragment_container, ownerFragment);
        transaction.hide(ownerFragment);
        transaction.add(R.id.fragment_container, beautifulEffectFragment);
        transaction.hide(beautifulEffectFragment);
        transaction.add(R.id.fragment_container, myFragment);
        transaction.hide(myFragment);
        transaction.commit();
    }

    //设定一个flag值，用来判断当前主页fragment显示的是1布局还是2布局
    boolean flag = true;

    @Subscribe
    public void onEvent(String str) {
        transaction = getSupportFragmentManager().beginTransaction();
        if ("请求选择城市".equals(str)) {
             SiteDao.getSite();
            dialog.show();
        } else if ("跳转到美图".equals(str)) {
            radiobtn_beautifuleffect_main.setChecked(true);
            showFragment(lastFragment, beautifulEffectFragment);
        }else if ("跳转到附近".endsWith(str)) {
            radiobtn_nearby_main.setChecked(true);
            showFragment(lastFragment, nearByFragment);
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
                            showFragment(lastFragment, homeFragment1);
                        } else {
                            showFragment(lastFragment, homeFragment2);
                        }
                        break;
                    case R.id.radiobtn_nearby_main:
                        Toast.makeText(MainActivity.this, "radiobtn_nearby_main", Toast.LENGTH_LONG).show();
                        showFragment(lastFragment, nearByFragment);
                        break;
                    case R.id.radiobtn_owner_main:
                        Toast.makeText(MainActivity.this, "radiobtn_owner_main", Toast.LENGTH_LONG).show();
                        showFragment(lastFragment, ownerFragment);
                        break;
                    case R.id.radiobtn_beautifuleffect_main:
                        Toast.makeText(MainActivity.this, "radiobtn_beautifuleffect_main", Toast.LENGTH_LONG).show();
                        showFragment(lastFragment, beautifulEffectFragment);
                        break;
                    case R.id.radiobtn_my_main:
                        Toast.makeText(MainActivity.this, "radiobtn_my_main", Toast.LENGTH_LONG).show();
                        showFragment(lastFragment, myFragment);
                        break;
                }
                transaction.commit();
            }

            //替换显示的Fragment方法

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

    private void showFragment(Fragment Fragment2Hide, Fragment Fragment2Show) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(Fragment2Hide);
        transaction.show(Fragment2Show);
        lastFragment = Fragment2Show;
    }


    @Subscribe
    public void onLoadLayout(IndexBean indexBean) {
        String layoutID = indexBean.getData().getLayout();
        transaction = getSupportFragmentManager().beginTransaction();
        if ("1".equals(layoutID)) {
            flag = true;
            transaction.hide(lastFragment);
            transaction.show(homeFragment1);
            lastFragment = homeFragment1;
            Log.d("huizhuang", "主页面加载了布局1");
        } else if ("2".equals(layoutID)) {
            flag = false;
            transaction.hide(lastFragment);
            transaction.show(homeFragment2);
            lastFragment = homeFragment2;
            Log.d("huizhuang", "主页面加载了布局2");
        }
        transaction.commit();
    }


    @Subscribe
    public void onSiteListListener(CityBean cityBean){
        final List<CityBean.ItemsBean> items = cityBean.getItems();
        final String[] site_name = new String[items.size()];
        final String[] site_id = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            site_name[i] = items.get(i).getSite_name();
            site_id[i] = items.get(i).getSite_id();
        }
        dialog = new AlertDialog.Builder(this)
                                .setTitle("请选择城市")
                                .setItems(site_name, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        IndexDao.getIndexBean(site_id[which],site_name[which],MainActivity.this);
                                        Log.d("huizhuang","选择了"+site_name[which]+"城市ID："+site_id[which]);
                                    }
                                })
                                .create();
    }

    @Override
    protected void loadDate() {
        //请求城市列表
        SiteDao.getSite();
        //默认加载深圳
        IndexDao.getIndexBean("100","深圳", this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
