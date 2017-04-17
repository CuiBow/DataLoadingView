package com.example.mylibrary.view.errorViews;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Admin on 2017/4/14.
 */

public abstract class IView extends LinearLayout {
    public IView(Context context) {
        this(context,null);
    }

    public IView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected abstract void initView();

    public final void showView()
    {
        setVisibility(VISIBLE);
    }
    public final void hideView()
    {
        setVisibility(GONE);
    }
    public abstract void setText(int text);
    public abstract void setImage(@DrawableRes int res);
    public abstract void setRetryBtnText(@StringRes int text);
    public abstract void setBtnOnClickListener(OnClickListener view);






}
