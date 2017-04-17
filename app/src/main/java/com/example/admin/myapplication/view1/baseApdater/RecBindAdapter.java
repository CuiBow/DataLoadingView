package com.example.admin.myapplication.view1.baseApdater;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by Admin on 2016/9/29.
 */
public abstract class RecBindAdapter<T> extends RecyclerView.Adapter<BindHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    private LayoutInflater mInflater;
    private ViewDataBinding binding;
    private OnItemClickLitener mOnItemClickLitener;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public RecBindAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

    }

    @Override
    public BindHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), mLayoutId,
                parent,
                false);
        BindHolder holder = new BindHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BindHolder holder, int position) {


        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

        convert(holder, mDatas.get(position),binding);

    }

    public abstract void convert(BindHolder holder, T t,ViewDataBinding binding);

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

}
