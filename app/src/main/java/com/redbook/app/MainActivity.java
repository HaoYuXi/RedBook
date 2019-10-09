package com.redbook.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.activity.crop.ImagePickAndCropActivity;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.presenter.ICropPickerBindPresenter;

public class MainActivity extends AppCompatActivity {


    private int maxCount = 9;
    private ICropPickerBindPresenter imageLoaderProvider = new RedBookCropPresenter();
    private ImageItem firstImageItem = null;
    private boolean isShowBottomView = false;
    private boolean isShowDraft = false;
    private boolean isShowCamera = true;
    private boolean isShowVideo = false;
    private boolean isStartDirect;


    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView view2=findViewById(R.id.imageView2);
        ImageView view1=findViewById(R.id.imageView1);

        // 扫描功能
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 3);
        } else {

            String photoPath = "/storage/emulated/0/DCIM/Camera/IMG_20190916_202628.jpg";
            Glide.with(this).load(photoPath).into(view1);

        }


//        ImageView imageView = findViewById(R.id.iv_test);


//        Glide.with(this).load("DCIM/Camera/IMG_20190130_094752.jpg").into(view1);

        String photoPath = "/storage/emulated/0/DCIM/Camera/IMG_20190130_094752.jpg";
        Glide.with(this).load(photoPath).into(view1);


        findViewById(R.id.tv_redbook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent =new Intent(MainActivity.this, ImagePickAndCropActivity.class);
//                intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_IMAGELOADER, (Parcelable) new CropPickerBuilder(new RedBookCropPresenter()));
//                startActivity(intent);

                getIntent(MainActivity.this);
            }


        });
    }


    private void getIntent(AppCompatActivity activity) {
        Intent intent = new Intent(activity, ImagePickAndCropActivity.class);
        intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_IMAGELOADER, imageLoaderProvider);
        intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_MAXSELECTEDCOUNT, maxCount);
        intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_FIRSTIMAGEITEM, firstImageItem);
        intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_CROPPICSAVEFILEPATH, ImagePicker.cropPicSaveFilePath);
        intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_SHOWBOTTOMVIEW, isShowBottomView);
        intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_SHOWDRAFTDIALOG, isShowDraft);
        intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_SHOWCAMERA, isShowCamera);
        intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_SHOWVIDEO, isShowVideo);
        intent.putExtra(ImagePickAndCropActivity.INTENT_KEY_STARTDIRECT, isStartDirect);
        startActivity(intent);


    }
}
