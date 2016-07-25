package com.example.decoration.module.homefrag.ui;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.decoration.R;
import com.example.decoration.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private TextView tv_title;

    @Override
    protected int setViewId() {
        return R.layout.layout_webview;
    }

    @Override
    protected void findView() {
        webView = (WebView) findViewById(R.id.webview_zhuangxiubaodian);
        tv_title = (TextView) findViewById(R.id.tv_title);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_zxbd);

    }

    @Override
    protected void init() {
        //开启JavaScript特效
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.loadUrl(getIntent().getStringExtra("target_url"));
        tv_title.setText(getIntent().getStringExtra("title"));
    }

    @Override
    protected void initEvent() {

    }


    @Override
    protected void loadDate() {

    }

    //左上角回退按钮
    public void backpress(View view) {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
