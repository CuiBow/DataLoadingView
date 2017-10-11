package com.example.mylibrary.view.baseView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.mylibrary.R;
import com.example.mylibrary.view.type.LoadingType;

import java.io.InputStream;

/**
 * Created by cuibowen on 2017/10/11.
 */

public class DataLayoutBuilder {
    private static DataLayoutBuilder mDataLayoutBuilder;
    private int textColor;
    private String btnText;
    private Bitmap emptyDrawable;
    private Bitmap netErrorDrawable;
    private Bitmap errorDrawable;
    private View loadingView;
    private Drawable loadingDrawable;
    public static int loadingType;
    private static Context mContent;

    public int getTextColor() {
        return textColor;
    }

    public String getBtnText() {
        return btnText;
    }

    public Bitmap getEmptyDrawable() {
        return emptyDrawable;
    }

    public Bitmap getNetErrorDrawable() {
        return netErrorDrawable;
    }

    public Bitmap getErrorDrawable() {
        return errorDrawable;
    }

    public View getLoadingView() {
        return loadingView;
    }

    public Drawable getLoadingDrawable() {
        return loadingDrawable;
    }

    public static DataLayoutBuilder getInstance() {
        return mDataLayoutBuilder;
    }

   public DataLayoutBuilder(Builder builder){
       this.mContent = builder.context;
       this.textColor = builder.textColor;
       this.btnText = builder.btnText;
       this.emptyDrawable = builder.emptyDrawable;
       this.netErrorDrawable = builder.netErrorDrawable;
       this.errorDrawable = builder.errorDrawable;
       this.loadingView = builder.loadingView;
       this.loadingDrawable = builder.loadingDrawable;

       if (emptyDrawable == null) {
           this.emptyDrawable = getImageBitmap(R.drawable.emptys, mContent);
       }
       if (netErrorDrawable == null) {
           this.netErrorDrawable = getImageBitmap(R.drawable.sockettime, mContent);
       }
       if (errorDrawable == null) {
           this.errorDrawable = getImageBitmap(R.drawable.error, mContent);
       }
   }

    public static class Builder {
        public static int textColor;
        public static String btnText;
        public static Bitmap emptyDrawable;
        public static Bitmap netErrorDrawable;
        public static Bitmap errorDrawable;
        public static View loadingView;
        public static Drawable loadingDrawable;
        public static  Context context;

        public Builder(Context context) {
            this.context = context;
            mContent = context;
        }

        public Builder setTextColor(@NonNull int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder setBtnText(@NonNull String btnText) {
            this.btnText = btnText;
            return this;
        }
        //图片
        public Builder setEmptyDrawable(@NonNull int emptyDrawable) {
            this.emptyDrawable = getImageBitmap(emptyDrawable, context);
            return this;
        }

        public Builder setNetErrorDrawable(@NonNull int netErrorDrawable) {
            this.netErrorDrawable = getImageBitmap(netErrorDrawable, context);
            return this;
        }

        public Builder setErrorDrawable(@NonNull int errorDrawable) {
            this.errorDrawable = getImageBitmap(errorDrawable, context);
            return this;
        }
        //资源
        public Builder setLoadingRes(Object res) {
            if (res instanceof Integer) {
                this.loadingDrawable = mContent.getResources().getDrawable((Integer) res);
                loadingType = LoadingType.LOADING_DRAWABLE;
            } else if (res instanceof View) {
                this.loadingView = (View) res;
                loadingType = LoadingType.LOADING_VIEW;
            } else {
                loadingType = LoadingType.LOADING_DEFAULT;
            }
            return this;
        }
        public void build() {
            mDataLayoutBuilder = new DataLayoutBuilder(this);
        }
    }


    public static Bitmap getImageBitmap(int resources, Context context) {
        Resources r = context.getResources();
        InputStream is = r.openRawResource(resources);
        BitmapDrawable bmpDraw = new BitmapDrawable(is);
        Bitmap bmp = bmpDraw.getBitmap();
        return bmp;
    }

}
