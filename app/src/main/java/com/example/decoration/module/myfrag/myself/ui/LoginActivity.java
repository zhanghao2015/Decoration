package com.example.decoration.module.myfrag.myself.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.decoration.R;
import com.example.decoration.base.BaseActivity;
import com.example.decoration.base.NetCallback;
import com.example.decoration.common.net.HttpNet;
import com.example.decoration.module.myfrag.myself.bean.LoginInfo;
import com.example.decoration.modules.main.ui.MainActivity;
import com.google.gson.Gson;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class LoginActivity extends BaseActivity {
    Button loginbtn;
    EditText phoneet;
    EditText smset;
    @Override
    protected int setViewId() {
        return R.layout.layout_login;
    }

    @Override
    protected void findView() {
        loginbtn= (Button) findViewById(R.id.login);
        phoneet= (EditText) findViewById(R.id.phone_et);
        smset= (EditText) findViewById(R.id.sms_et);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneet.getText().toString();
                String smscode = smset.getText().toString();
                Map<String,String> map=new Hashtable<String, String>();
                map.put("mobile",phone);
                map.put("code",smscode);
                String url="http://hzapi.huizhuang.com/user/index/login.do";
                HttpNet.doHttpRequest("POST", url, map, new NetCallback() {
                    @Override
                    public void success(String strResult) {
                        Log.d("QF","访问成功"+strResult);
                        Gson gson=new Gson();
                        LoginInfo loginInfo = gson.fromJson(strResult, LoginInfo.class);
                        int status = loginInfo.getStatus();
                        if(status==404){
                            Log.d("QF","登录成功");
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Log.d("QF","登录失败");
                        }

                    }



                    @Override
                    public void fail(String strMsg) {
                        Log.d("QF","访问失败"+strMsg);

                    }
                });
            }
        });
    }

    @Override
    protected void loadDate() {

    }
}
