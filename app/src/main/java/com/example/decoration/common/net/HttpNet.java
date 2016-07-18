package com.example.decoration.common.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;


import com.example.decoration.base.NetCallback;
import com.example.decoration.common.thread.ThreadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by zhouyi on 16/3/28.
 */
public class HttpNet {

    private static Handler handler = new Handler();

    public static void doHttpRequest(final String strRequestMethod,
                                     final String strUrl,
                                     final Map<String, String> params,
                                     final NetCallback netCallback) {

        ThreadTask.getInstance().executorNetThread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(strUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


                    urlConnection.setRequestMethod(strRequestMethod);
                    urlConnection.connect();

                    OutputStream outputStream = urlConnection.getOutputStream();
                    StringBuffer sbParams = new StringBuffer();
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        sbParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                    }
                    outputStream.write(sbParams.substring(0, sbParams.length()).getBytes());
                    outputStream.flush();

                    InputStream inputStream = urlConnection.getInputStream();

                    final String strResult = getResultString(inputStream);

                    if (strResult != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                netCallback.success(strResult);
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                netCallback.fail("网络访问失败");
                            }
                        });
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    final String strMsg = e.getMessage();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            netCallback.fail(strMsg);
                        }
                    });

                }
            }
        }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);

    }

    private static String getResultString(InputStream inputStream) {
        String strResult = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = -1;
        try {
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        strResult = new String(outputStream.toByteArray());

        return strResult;
    }

    // 检测网络
    public static boolean checkNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return true;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    //将图片URL网址格式化
    public static String urlFormat(String resUrl){
//        http:\/\/hzyzq-10006163.image.myqcloud.com\/2bb4cacb-8967-447b-808d-a03413ac8718
        StringBuffer sb = new StringBuffer(resUrl);
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i)!='/'){
                sb2.append(sb.charAt(i));
            }
        }
        return sb2.toString();
    }
    //将Unicode转成中文
    public static String convertUnicode(String ori) {
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }



}
