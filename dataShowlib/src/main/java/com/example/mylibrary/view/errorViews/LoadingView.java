package com.example.mylibrary.view.errorViews;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.R;

/**
 * Created by Admin on 2017/4/17.
 */

public class LoadingView extends IView {
    private View mView;
    public LoadingView(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        mView=inflate(getContext(), R.layout.loading_view,null);
        mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mView);
    }

    @Override
    public void setText(int text) {

    }

    @Override
    public void setImage(@DrawableRes int res) {

    }

    @Override
    public void setRetryBtnText(@StringRes int text) {

    }

    @Override
    public void setBtnOnClickListener(OnClickListener view) {

    }


}
