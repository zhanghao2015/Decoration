package com.example.decoration.base;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public interface NetCallback {

    void success(String strResult);
    void fail(String strMsg);
}
