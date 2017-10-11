package com.example.mylibrary.view.errorViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.mylibrary.R;

/**
 * Created by Admin on 2017/4/17.
 */

public class LoadingView extends IView {
    private View mView;
    private ProgressBar defaultView;
    private LinearLayout rootView;
    private ImageView loadingView;
    private AnimationDrawable animationDrawable;
    public LoadingView(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        mView=inflate(getContext(), R.layout.loading_view,null);
        mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        defaultView= (ProgressBar)mView.findViewById(R.id.progress_view);
        rootView= (LinearLayout)mView.findViewById(R.id.root_view);
        loadingView= (ImageView)mView.findViewById(R.id.loading_view);
        stopAnimation();
        addView(mView);
    }
    @Override
    public void setText(int text) {

    }


    @Override
    public void setImage(@DrawableRes int res) {

    }

    @Override
    public void setImage(Bitmap res) {

    }

    @Override
    public void setRetryBtnText(@StringRes int text) {

    }

    @Override
    public void setRetryBtnText(String text) {

    }

    @Override
    public void setBtnOnClickListener(OnClickListener view) {

    }

    @Override
    public void setTextColor(int text) {

    }
    //逐帧动画加载
    @Override
    public void setLoadingView(Drawable res) {
        loadingView.setBackground(res);
        animationDrawable= (AnimationDrawable) loadingView.getBackground();
        defaultView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        rootView.setVisibility(GONE);
        animationDrawable.start();
    }

    //自定义加载view
    @Override
    public void setLoadingView(View res) {
        removeView();
        stopAnimation();
        defaultView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        rootView.setVisibility(VISIBLE);
        rootView.addView(res);
    }
    @Override
    public void setLoadingView() {
        stopAnimation();
        defaultView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        rootView.setVisibility(GONE);
    }
    private void removeView(){
        rootView.removeAllViews();
    }
    //停止动画
    @Override
    public void stopAnimation(){
        if (animationDrawable!=null){
            animationDrawable.stop();
        }
    }

}
