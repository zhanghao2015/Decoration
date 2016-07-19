package com.example.decoration.module.myfrag.myself.ui;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.decoration.R;
import com.example.decoration.base.BaseActivity;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class WebViewActivity extends BaseActivity {

    private WebView webView;
    @Override
    protected int setViewId() {
        return R.layout.layout_webview_make_money;
    }

    @Override
    protected void findView() {
        webView= (WebView) findViewById(R.id.webview);
    }

    @Override
    protected void init() {
        String getCodeUrl="http://market.huizhuang.com/red_packet/appinvite.html?key=624zhanglei&node_id=99&appid=2003520&userid=null&sid=36&channel=wandoujia&app_name=hzAndroid";
        webView.loadUrl(getCodeUrl);
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadDate() {

    }
}
