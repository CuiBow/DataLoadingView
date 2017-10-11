package com.example.mylibrary.view.baseView;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mylibrary.R;
import com.example.mylibrary.view.factory.IViewFactory;
import com.example.mylibrary.view.factory.ViewFactory;
import com.example.mylibrary.view.type.ErrorType;
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
    //外部引入view
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
                    case ErrorType.SUCCESS:
                        mView.setVisibility(VISIBLE);
                        loadingView.hideView();
                        loadingView.stopAnimation();
                        netErrorView.hideView();
                        emptyView.hideView();
                        errorView.hideView();
                        break;
                    case ErrorType.EMPTY:
                        mView.setVisibility(GONE);
                        loadingView.hideView();
                        netErrorView.hideView();
                        emptyView.showView();
                        errorView.hideView();
                        break;
                    case ErrorType.LOADING:
                        mView.setVisibility(GONE);
                        loadingView.showView();
                        netErrorView.hideView();
                        emptyView.hideView();
                        errorView.hideView();
                        break;
                    case ErrorType.NETERROR:
                        mView.setVisibility(GONE);
                        loadingView.hideView();
                        netErrorView.showView();
                        emptyView.hideView();
                        errorView.hideView();
                        break;
                  case ErrorType.ERROR:
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
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.dataShowLayout);
        isFirstVisible = a.getBoolean(R.styleable.dataShowLayout_isFirstVisible, false);
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }
        if (getChildCount()>0)
        {
            mView = this.getChildAt(0);
        }
        if (!isFirstVisible) {
            mView.setVisibility(View.GONE);
        }
        buildView();
    }

    public void buildView()
    {

        factory=creatViewFactory();

        setEmptyView(builder.emptyView==null?factory.getViewFactory(getContext(), ErrorType.EMPTY):builder.emptyView);
        setNetErrorView( builder.netErrorView==null?factory.getViewFactory(getContext(), ErrorType.NETERROR):builder.netErrorView);
        setLoadingView(builder.loadingView==null?factory.getViewFactory(getContext(), ErrorType.LOADING):builder.loadingView);
        setErrorView(builder.errorView==null?factory.getViewFactory(getContext(), ErrorType.ERROR):builder.errorView);


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



    @IntDef({ErrorType.EMPTY, ErrorType.ERROR, ErrorType.NETERROR, ErrorType.SUCCESS, ErrorType.LOADING})
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
