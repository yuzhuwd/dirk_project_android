package com.example.dirkwang.myapplication.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * CommonAdapter
 * Created by nibbleswan on 2015/12/14.
 * Moved by dirkwang on 2017/3/17
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    //protected LayoutInflater layoutInflater;
    protected Context context;
    protected List<T> items;
    protected final int itemLayoutId;

    public CommonAdapter(Context context, List<T> items, int itemLayoutId) {
        this.context = context;
        //this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = getViewHolder(position, convertView, parent);
        if (holder != null) {
            convert(holder, getItem(position), position);
            return holder.getConvertView();
        } else {
            return null;
        }
    }

    public abstract void convert(ViewHolder holder, T item, int position);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(context, convertView, parent, itemLayoutId, position);
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return items;
    }
}
