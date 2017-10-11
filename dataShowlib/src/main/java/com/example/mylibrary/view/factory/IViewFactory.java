package com.example.mylibrary.view.factory;

import android.content.Context;

import com.example.mylibrary.view.baseView.dataShowLayout;
import com.example.mylibrary.view.errorViews.IView;

/**
 * Created by Admin on 2017/4/14.
 */

public interface IViewFactory {
    public IView getViewFactory(Context context, @dataShowLayout.ViewState int type);
}
