package com.example.mylibrary.view.baseView;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mylibrary.R;
import com.example.mylibrary.view.errorViews.ErrorsView;
import com.example.mylibrary.view.factory.IViewFactory;
import com.example.mylibrary.view.factory.ViewFactory;
import com.example.mylibrary.view.type.errorType;
import com.example.mylibrary.view.errorViews.IView;

/**
 * Created by Admin on 2017/4/14.
 */

public class dataShowLayout extends FrameLayout {
    private View mView;
    private IView emptyView;
    private IView netErrorView;
    private IView loadingView;
    private IView errorView;


    private IViewFactory factory;
    private Builder builder=new Builder();

    private boolean isFirstVisible;
    public void setEmptyView(IView emptyView) {
        this.emptyView = emptyView;
    }
    public void setNetErrorView(IView netErrorView) {
        this.netErrorView = netErrorView;
    }
    public void setLoadingView(IView loadingView) {
        this.loadingView = loadingView;
    }
    public void setErrorView(IView errorView) {
        this.errorView = errorView;
    }

    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what)
            {
                    case errorType.SUCCESS:
                        mView.setVisibility(VISIBLE);
                        loadingView.hideView();
                        netErrorView.hideView();
                        emptyView.hideView();
                        errorView.hideView();
                        break;
                    case errorType.EMPTY:
                        mView.setVisibility(GONE);
                        loadingView.hideView();
                        netErrorView.hideView();
                        emptyView.showView();
                        errorView.hideView();
                        break;
                    case errorType.LOADING:
                        mView.setVisibility(GONE);
                        loadingView.showView();
                        netErrorView.hideView();
                        emptyView.hideView();
                        errorView.hideView();
                        break;
                    case errorType.NETERROR:
                        mView.setVisibility(GONE);
                        loadingView.hideView();
                        netErrorView.showView();
                        emptyView.hideView();
                        errorView.hideView();
                        break;
                  case errorType.ERROR:
                        mView.setVisibility(GONE);
                        loadingView.hideView();
                        netErrorView.hideView();
                        emptyView.hideView();
                         errorView.showView();
                     break;
            }
        }
    };


    public dataShowLayout(Context context) {
        this(context,null);
    }

    public dataShowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public dataShowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DataShowLayout);
        isFirstVisible = a.getBoolean(R.styleable.DataShowLayout_isFirstVisible, false);
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }
        mView = this.getChildAt(0);
        if (!isFirstVisible) {
            mView.setVisibility(View.GONE);
        }
        buildView();
    }

    public void buildView()
    {

        factory=creatViewFactory();

        setEmptyView(builder.emptyView==null?factory.getViewFactory(getContext(),errorType.EMPTY):builder.emptyView);
        setNetErrorView( builder.netErrorView==null?factory.getViewFactory(getContext(),errorType.NETERROR):builder.netErrorView);
        setLoadingView(builder.loadingView==null?factory.getViewFactory(getContext(),errorType.LOADING):builder.loadingView);
        setErrorView(builder.errorView==null?factory.getViewFactory(getContext(),errorType.ERROR):builder.errorView);


        builder.setNetErrorView(netErrorView).setEmptyView(emptyView).setLoadingView(loadingView).setErrorView(errorView);

        addView(emptyView);
        addView(netErrorView);
        addView(errorView);
        addView(loadingView);

    }

    public IViewFactory creatViewFactory()
    {
        return new ViewFactory();
    }

    public void setStatus(@ViewState int status) {
        handler.sendEmptyMessage(status);
    }



    @IntDef({errorType.EMPTY,errorType.ERROR,errorType.NETERROR,errorType.SUCCESS,errorType.LOADING})
    public @interface ViewState
    {

    }

    public static class Builder
    {
        private IView emptyView;
        private IView netErrorView;
        private IView loadingView;
        private IView errorView;
        public Builder setEmptyView(IView view)
        {
            this.emptyView=view;

            return this;
        }
        public Builder setNetErrorView(IView view)
        {
            this.netErrorView=view;

            return this;
        }
        public Builder setLoadingView(IView view)
        {
            this.loadingView=view;

            return this;
        }
        public Builder setErrorView(IView view)
        {
            this.errorView=view;
            return this;
        }
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        emptyView.setOnClickListener(onClickListener);
        netErrorView.setOnClickListener(onClickListener);
        errorView.setOnClickListener(onClickListener);
    }
    public void setBtnRetryListener(OnClickListener onClickListener) {
        emptyView.setBtnOnClickListener(onClickListener);
        netErrorView.setBtnOnClickListener(onClickListener);
        errorView.setBtnOnClickListener(onClickListener);
    }



}
