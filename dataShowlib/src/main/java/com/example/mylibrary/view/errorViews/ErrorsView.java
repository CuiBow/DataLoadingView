package com.example.mylibrary.view.errorViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mylibrary.R;

/**
 * Created by Admin on 2017/4/14.
 */
public class ErrorsView extends IView {
    private View mView;
    private TextView errorText;
    private ImageView errorImage;
    private TextView retryBtn;



    public ErrorsView(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        mView=inflate(getContext(), R.layout.error_view,null);
        mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        errorText= (TextView) mView.findViewById(R.id.error_text);
        errorImage= (ImageView) mView.findViewById(R.id.error_image);
        retryBtn= (TextView) mView.findViewById(R.id.error_reload_btn);
        addView(mView);
    }

    @Override
    public void setText(int text) {
        errorText.setText(text);
    }



    @Override
    public void setImage(@DrawableRes int res) {
        Glide.with(getContext())
                .load(res)
                .into(errorImage);
    }

    @Override
    public void setImage(Bitmap res) {
        errorImage.setImageBitmap(res);
    }

    @Override
    public void setRetryBtnText(@StringRes int text) {
        retryBtn.setText(text);
    }

    @Override
    public void setRetryBtnText(String text) {
        retryBtn.setText(text);
    }

    @Override
    public void setBtnOnClickListener(OnClickListener view) {
        retryBtn.setOnClickListener(view);
    }

    @Override
    public void setTextColor(int text) {
        retryBtn.setTextColor(text);
        errorText.setTextColor(text);
    }

    @Override
    public void setLoadingView(Drawable res) {

    }


    @Override
    public void setLoadingView(View res) {

    }

    @Override
    public void setLoadingView() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        mView.setOnClickListener(l);
    }


}
