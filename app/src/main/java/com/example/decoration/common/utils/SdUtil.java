package com.example.decoration.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

//工具类：用于从sd卡读取数据，和写入数据
public class SdUtil {
    private static Bitmap bitmap;
    private static File file;
    public static Bitmap loadBitmapFromSd(String urlString) {
        // 判断sd卡的挂载状态
        if (!isMounted()) {
            return null;
        }
        if(!getFile(urlString).exists()){
            Log.e("hui","所要加载图片文件不存在");
            return null;
        }
        // 重用一个Bitmap，避免OOM
        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bitmap;
    }

    private static File getFile(String urlString) {
        file = Environment.getExternalStoragePublicDirectory("Decoration/Cache");
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName = urlString.substring(urlString.lastIndexOf("/") + 1);
        if (!fileName.endsWith(".jpg") || !fileName.endsWith(".jpeg") || !fileName.endsWith(".png")){
            StringBuffer sb = new StringBuffer(fileName);
            sb.append(".png");
            fileName = sb.toString();
        }
        file = new File(file,fileName);
        Log.e("hui","file绝对路径:"+file.getAbsolutePath());
        return file;
    }

    private static boolean isMounted() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    // 用于保存图片
    public static String saveBitmapToSd(String urlString, Bitmap bitmap) {
        if (!isMounted()) {
            return null;
        }
        // 计算文件应该保存的路径
        String filePath = getFile(urlString).getAbsolutePath();
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            // 将图片压缩保存
            CompressFormat format = CompressFormat.JPEG;
            if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
                format = CompressFormat.JPEG;
            } else if (filePath.endsWith(".png")) {
                format = CompressFormat.PNG;
            }
            bitmap.compress(format, // 压缩格式
                    60,// 压缩质量
                    fos);// 压缩后的输出位置
            fos.flush();
            fos.close();
            //存到本地后将Bitmap回收，节约内存
            bitmap.recycle();
            Log.e("hui", "图片文件已保存:" + filePath);
            return filePath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
