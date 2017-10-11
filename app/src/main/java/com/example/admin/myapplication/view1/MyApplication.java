package com.example.admin.myapplication.view1;

import android.app.Application;
import android.graphics.Color;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.view1.view.PathMeasureView;
import com.example.mylibrary.view.baseView.DataLayoutBuilder;

/**
 * Created by cuibowen on 2017/10/10.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PathMeasureView view=new PathMeasureView(this);
//        new DataLayoutBuilder.Builder(getApplicationContext())
//                .setBtnText("重试")
//                .setTextColor(Color.BLACK)
//                .setEmptyDrawable(R.drawable.connect)
//                .setErrorDrawable(R.drawable.connect)
//                .setNetErrorDrawable(R.drawable.connect)
//                .setLoadingRes(R.drawable.loadding_image)
//                .build();


//        new DataLayoutBuilder.Builder(getApplicationContext())
//                .setLoadingRes(null)
//                .build();
//        new DataLayoutBuilder.Builder(getApplicationContext())
//                .setLoadingRes(R.drawable.loadding_image)
//                .build();

        new DataLayoutBuilder.Builder(getApplicationContext())
                .setLoadingRes(view)
                .build();
    }
}
