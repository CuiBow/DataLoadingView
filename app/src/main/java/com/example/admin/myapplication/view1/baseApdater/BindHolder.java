package com.example.admin.myapplication.view1.baseApdater;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Admin on 2016/9/29.
 */
public class BindHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    public BindHolder(View itemView) {
        super(itemView);
        binding= DataBindingUtil.bind(itemView);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }


}
