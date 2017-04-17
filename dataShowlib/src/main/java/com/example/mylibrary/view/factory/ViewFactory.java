package com.example.mylibrary.view.factory;

import android.content.Context;

import com.example.mylibrary.R;
import com.example.mylibrary.view.baseView.dataShowLayout;
import com.example.mylibrary.view.type.errorType;
import com.example.mylibrary.view.errorViews.ErrorsView;
import com.example.mylibrary.view.errorViews.IView;
import com.example.mylibrary.view.errorViews.LoadingView;

/**
 * Created by Admin on 2017/4/14.
 */

public class ViewFactory implements IViewFactory {
    private int loadingType;

    @Override
    public IView getViewFactory(Context context, @dataShowLayout.ViewState int type) {
        ErrorsView view=new ErrorsView(context);
        LoadingView loadingView=new LoadingView(context);
        switch (type)
        {
            case errorType.EMPTY:
                loadingType=errorType.EMPTY;
                view.setText(R.string.empty_text);
                view.setImage(R.drawable.emptys);
                view.setRetryBtnText(R.string.btn_text_empty);

                view.hideView();
                break;
            case errorType.NETERROR:
                loadingType=errorType.NETERROR;
                view.setText(R.string.net_error);
                view.setImage(R.drawable.sockettime);
                view.setRetryBtnText(R.string.btn_text_net);

                view.hideView();
                break;

            case errorType.ERROR:
                loadingType=errorType.ERROR;
                view.setText(R.string.error_text);
                view.setImage(R.drawable.error);
                view.setRetryBtnText(R.string.btn_text_net);
                view.hideView();
                break;
            case errorType.LOADING:
                loadingType=errorType.LOADING;
                loadingView.hideView();
                break;
        }
        return loadingType==errorType.LOADING?loadingView:view;
    }
}
