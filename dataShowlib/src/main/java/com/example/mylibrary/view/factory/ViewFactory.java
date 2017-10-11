package com.example.mylibrary.view.factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.mylibrary.R;
import com.example.mylibrary.view.baseView.DataLayoutBuilder;
import com.example.mylibrary.view.baseView.DataShowLayout;
import com.example.mylibrary.view.type.ErrorType;
import com.example.mylibrary.view.errorViews.ErrorsView;
import com.example.mylibrary.view.errorViews.IView;
import com.example.mylibrary.view.errorViews.LoadingView;
import com.example.mylibrary.view.type.LoadingType;

/**
 * Created by Admin on 2017/4/14.
 */

public class ViewFactory implements IViewFactory {
    private int loadingType;
    @Override
    public IView getViewFactory(Context context, @DataShowLayout.ViewState int type) {
        ErrorsView view=new ErrorsView(context);
        LoadingView loadingView=new LoadingView(context);
        //初始值防止空指针
        DataLayoutBuilder builder=DataLayoutBuilder.getInstance();
        Bitmap emptyDrawable=DataLayoutBuilder.getImageBitmap(R.drawable.emptys,context);
        Bitmap errorDrawable=DataLayoutBuilder.getImageBitmap(R.drawable.error,context);
        Bitmap netErrorDrawable=DataLayoutBuilder.getImageBitmap(R.drawable.sockettime,context);
        int textColor=R.color.base_text_color_light;
        String btnText = null;
        //是否创建builder类
        if (builder!=null){
            btnText=builder.getBtnText();
            emptyDrawable=builder.getEmptyDrawable();
            errorDrawable=builder.getErrorDrawable();
            netErrorDrawable= builder.getNetErrorDrawable();
            textColor=builder.getTextColor();

            String textColorS=String.valueOf(textColor);
            if (textColorS.equals("")||textColorS==null||textColor==0){
                textColor=R.color.base_text_color_light;
            }

        }
        switch (type)
        {
            case ErrorType.EMPTY:
                loadingType= ErrorType.EMPTY;
                view.setText(R.string.empty_text);
                if (emptyDrawable==null){
                    view.setImage(R.drawable.emptys);
                }else{
                    view.setImage(emptyDrawable);
                }
                if (btnText==null){
                    view.setRetryBtnText(R.string.btn_text_empty);
                }else{
                    view.setRetryBtnText(btnText);
                }

                view.setTextColor(textColor);
                view.hideView();
                break;
            case ErrorType.NETERROR:
                loadingType= ErrorType.NETERROR;
                view.setText(R.string.net_error);
                if (emptyDrawable==null){
                    view.setImage(R.drawable.sockettime);
                }else{
                    view.setImage(netErrorDrawable);
                }
                if (btnText==null){
                    view.setRetryBtnText(R.string.btn_text_net);
                }else{
                    view.setRetryBtnText(btnText);
                }
                view.setTextColor(textColor);
                view.hideView();
                break;

            case ErrorType.ERROR:
                loadingType= ErrorType.ERROR;
                view.setText(R.string.error_text);

                if (emptyDrawable==null){
                    view.setImage(R.drawable.error);
                }else{
                    view.setImage(errorDrawable);
                }

                if (btnText==null){
                    view.setRetryBtnText(R.string.btn_text_net);
                }else{
                    view.setRetryBtnText(btnText);
                }
                view.setTextColor(textColor);
                view.hideView();
                break;
            case ErrorType.LOADING:
                loadingType= ErrorType.LOADING;
                if (builder!=null){
                    int types=DataLayoutBuilder.loadingType;

                    View views=builder.getLoadingView();
                    Drawable drawables= builder.getLoadingDrawable();

                    if (types== LoadingType.LOADING_DEFAULT){

                        loadingView.setLoadingView();

                    }else if (types==LoadingType.LOADING_DRAWABLE){

                        loadingView.setLoadingView(drawables);

                    }else if (types==LoadingType.LOADING_VIEW){

                        loadingView.setLoadingView(views);

                    }
                }else{
                    loadingView.setLoadingView();
                }
                loadingView.hideView();
                break;
        }
        return loadingType== ErrorType.LOADING?loadingView:view;
    }
}
