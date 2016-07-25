package com.example.decoration.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.example.decoration.base.NetCallback;
import com.example.decoration.common.thread.ThreadTask;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 16-7-21.
 */
public class LruCacheUtils {

    private static LruCache<String, Bitmap> lruCache;
    private static Bitmap bitmap;
    private static NetCallback netCallback;

    public static void setOnNetCallBack(NetCallback mNetCallBack) {
        netCallback = mNetCallBack;
    }

    //初始化Lrucache:实例化Lrucache,传入最大空间值，重写sizeof方法
    public static void initLruCache() {
        //参数是:允许用于内存缓存的空间的大小，这里使用内存的1/8作为缓存的空间大小
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8 / 1024 / 1024);
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            //需要做内存缓存的时候，回调这个方法来计算要缓存的数据占用的空间大小
            //注意：这里返回的图片的大小的单位应该和最大空间的单位一致
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int size = value.getByteCount() / 1024 / 1024;
                return size;
            }
        };
    }

    //从缓存获取数据方法
    public static void getDataFromLrucacheOrSD(String urlString, ImageView imageView, Activity activity) {
        //查询SD卡中是否有该图片，有就直接加载，之后再查询内存缓存中是否有对应的图片，有的话，直接加载
        bitmap = SdUtil.loadBitmapFromSd(urlString);
        if (bitmap != null) {
            Log.d("huizhuang", "从SD卡中读取了数据");
            imageView.setImageBitmap(bitmap);
        } else {
            //SD卡中数据不存在，从缓存中找数据
            bitmap = lruCache.get(urlString);
            if (bitmap != null) {
                Log.d("huizhuang", "从缓存中读取了数据");
                imageView.setImageBitmap(bitmap);
            } else {
                //缓存中数据不存在，开启网络线程开始加载，并存入缓存和SD
                loadBitmapFromNet(urlString, imageView, activity);
            }
        }
    }

    //加载网络图片,并加入缓存,写入SD存储
    private static void loadBitmapFromNet(final String urlString, final ImageView imageView, final Activity activity) {
        ThreadTask.getInstance().executorNetThread(new Runnable() {
            private HttpURLConnection conn;
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    conn = (HttpURLConnection) url.openConnection();
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                        lruCache.put(urlString, bitmap);
                        SdUtil.saveBitmapToSd(urlString, bitmap);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(SdUtil.loadBitmapFromSd(urlString));
                            }
                        });
                        Log.d("huizhuang", "从网络加载了数据");
                    } else {
                        Log.e("huizhuang", "网络连接异常");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 5);
    }



}
