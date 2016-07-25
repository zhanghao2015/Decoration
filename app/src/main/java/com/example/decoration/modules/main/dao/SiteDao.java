package com.example.decoration.modules.main.dao;

import android.util.Log;

import com.example.decoration.base.NetCallback;
import com.example.decoration.common.constant.Constant;
import com.example.decoration.common.net.HttpNet;
import com.example.decoration.modules.main.bean.CityBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 16-7-25.
 */
public class SiteDao {


    public static void getSite(){

        Map<String, String> map = new HashMap<>();
        HttpNet.doHttpRequest("POST", Constant.GETSITE_URL, map, new NetCallback() {
            @Override
            public void success(String strResult) {
                Log.d("huizhuang","获取城市列表访问成功");
                Gson gson = new Gson();
                CityBean cityBean = gson.fromJson(strResult, CityBean.class);
                if(cityBean.getStatus()==200){
                    //请求成功即返回数据
                    EventBus.getDefault().post(cityBean);
                }
            }

            @Override
            public void fail(String strMsg) {
                Log.d("huizhuang","获取城市列表访问失败:"+strMsg);
            }
        });


    }






}
