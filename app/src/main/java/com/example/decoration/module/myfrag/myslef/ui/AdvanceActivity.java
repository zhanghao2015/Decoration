package com.example.decoration.module.myfrag.myslef.ui;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.decoration.R;
import com.example.decoration.base.BaseActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class AdvanceActivity extends BaseActivity {
    private ImageView maddphoto;
    private PopupWindow pw;
    private View pwbottom;
    private Button takePhoto;
    private Button photos;
    private Button btn_cancel;

    @Override
    protected int setViewId() {
        return R.layout.layout_advance_post;
    }

    @Override
    protected void findView() {
        maddphoto= (ImageView) findViewById(R.id.addPhoto_iv);
        pwbottom = getLayoutInflater().inflate(R.layout.layout_pw_bottom_photo, null);

        takePhoto= (Button) pwbottom.findViewById(R.id.btn_take_photo);
        photos= (Button)pwbottom.findViewById(R.id.btn_pick_photo);
        btn_cancel= (Button)pwbottom.findViewById(R.id.btn_cancel);
    }

    @Override
    protected void init() {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            ,Manifest.permission.READ_EXTERNAL_STORAGE},66);
        }
        pw=new PopupWindow(pwbottom, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,false);

    }

    @Override
    protected void initEvent() {
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,getOutputMediaFileUri());
                startActivityForResult(intent,66);
                //TODO 打开系统相机 拍摄照片 然后给添加上去

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
                setAplh(0);
            }
        });
    }

    private Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputFile());
    }

    private File getOutputFile() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        String imgName = simpleDateFormat.format(new Date());
        File imgFile=new File("/sdcard/base/image"+imgName+".jpg");
        return imgFile;
    }

    //点击PopupWindow时设置背景变暗方法
    private void setAplh(float alph) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = alph;
        getWindow().setAttributes(params);
    }

    @Override
    protected void loadDate() {

    }

    public void addPhoto(View view) {
        //TODO 一个从底下冒出来的popuwindow
        if(pw.isShowing()){
            setAplh(1f);
            pw.dismiss();
        }else{
            setAplh(0.5f);
            pw.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }
}
