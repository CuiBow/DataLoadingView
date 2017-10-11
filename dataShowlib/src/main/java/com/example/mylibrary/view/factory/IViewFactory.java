package com.example.mylibrary.view.factory;

import android.content.Context;

import com.example.mylibrary.view.baseView.DataShowLayout;
import com.example.mylibrary.view.errorViews.IView;

/**
 * Created by Admin on 2017/4/14.
 */

public interface IViewFactory {
    public IView getViewFactory(Context context, @DataShowLayout.ViewState int type);
}
