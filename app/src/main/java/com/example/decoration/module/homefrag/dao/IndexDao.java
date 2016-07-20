package com.example.decoration.module.homefrag.dao;

import android.util.Log;

import com.example.decoration.base.NetCallback;
import com.example.decoration.common.constant.Constant;
import com.example.decoration.common.net.HttpNet;
import com.example.decoration.module.homefrag.bean.IndexBean;
import com.example.decoration.module.homefrag.ui.LoadLatestFrag;
import com.example.decoration.module.homefrag.ui.LoadLayout1;
import com.example.decoration.module.homefrag.ui.LoadLayout2;
import com.example.decoration.module.homefrag.ui.LoadRecommandFrag;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 16-7-19.
 */
public class IndexDao {

    static String TIMESTAMP = "timestamp";//1468779022444
    static String VERSION = "version";//3.5.2.0
    static String TERMINAL = "terminal";//2
    static String OS = "os";//1
    static String ACCESS_TOKEN = "access_token";//b1924b4
    static String BI_CHANNEL = "bi_channel";//wandoujia
    static String SITE_ID = "site_id";//100
    static String APP_TYPE = "app_type";//1
    static String MAC = "mac";//355251056748676
    private static IndexBean indexBean;

    public static void getIndexBean(String site_id) {

        Map<String, String> params = new HashMap<>();
        params.put(TIMESTAMP, "1468779022444");
        params.put(VERSION, "3.5.2.0");
        params.put(TERMINAL, "2");
        params.put(OS, "1");
        params.put(ACCESS_TOKEN, "b1924b4");
        params.put(BI_CHANNEL, "wandoujia");
        params.put(SITE_ID, site_id);
        params.put(APP_TYPE, "1");
        params.put(MAC, "355251056748676");

        HttpNet.doHttpRequest("POST", Constant.INDEX_URL, params, new NetCallback() {
            @Override
            public void success(String strResult) {
                Log.d("huizhuang", "网络请求成功 strResult:" + strResult);
                Gson gson = new Gson();
                indexBean = gson.fromJson(strResult, IndexBean.class);
                Log.d("huizhuang", "加载布局:" + indexBean.getData().getLayout());
                String layoutID = indexBean.getData().getLayout();
                if (indexBean.getStatus() == 200) {
                    EventBus.getDefault().post(indexBean);
                    EventBus.getDefault().post(new LoadLayout2(indexBean, "2"));
                    EventBus.getDefault().post(new LoadLayout1(indexBean, "1"));
                    EventBus.getDefault().post(new LoadRecommandFrag(indexBean, "3"));
                    EventBus.getDefault().post(new LoadLatestFrag(indexBean, "4"));
                }
            }

            @Override
            public void fail(String strMsg) {
                Log.d("huizhuang", "网络请求失败:" + strMsg);
            }
        });

    }
}


