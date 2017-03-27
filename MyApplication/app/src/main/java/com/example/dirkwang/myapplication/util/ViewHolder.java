package com.example.dirkwang.myapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ViewHolder
 * 这里引入tagKey主要是为了防止冲突
 * 如果引入了tagKey，那么就通过has/bind/getViewHolderTag接口来获取tag
 * Created by nibbleswan on 2015/12/14.
 * Moved by dirkwang on 2017/3/17
 */
public class ViewHolder {
    private final SparseArray<View> views;
    private int position;
    private View convertView;

    private ViewHolder(View convertView, int position, int tagKey) {
        this.views = new SparseArray<>();
        this.position = position;
        this.convertView = convertView;
        setViewTag(this.convertView, tagKey, this);
    }

    private static void setViewTag(View view, int tagKey, Object tag) {
        if (view == null) return;

        if (tagKey == 0) {
            view.setTag(tag);
        } else {
            view.setTag(tagKey, tag);
        }
    }

    private static Object getViewTag(View view, int tagKey) {
        if (view == null) return null;

        if (tagKey == 0) {
            return view.getTag();
        }

        return view.getTag(tagKey);
    }

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position, int tagKey) {
        this(LayoutInflater.from(context).inflate(layoutId, parent, false), position, tagKey);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position, int tagKey) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder(context, parent, layoutId, position, tagKey);
        } else {
            Object tag = getViewTag(convertView, tagKey);
            if (tag instanceof ViewHolder) {
                holder = (ViewHolder) tag;
                holder.position = position;
            }
        }
        return holder;
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        return get(context, convertView, parent, layoutId, position, 0);
    }

    public static boolean hasViewHolderTag(View convertView, int tagKey) {
        return convertView != null && (getViewTag(convertView, tagKey) instanceof ViewHolder);
    }

    public static boolean hasViewHolderTag(View convertView) {
        return hasViewHolderTag(convertView, 0);
    }

    public static ViewHolder bindViewHolderTag(View convertView, int position, int tagKey) {
        if (convertView == null) return null;

        return new ViewHolder(convertView, position, tagKey);
    }

    public static ViewHolder bindViewHolderTag(View convertView, int position) {
        return bindViewHolderTag(convertView, position, 0);
    }

    public static ViewHolder getViewHolderTag(View convertView, int tagKey) {
        if (hasViewHolderTag(convertView, tagKey)) {
            return (ViewHolder) getViewTag(convertView, tagKey);
        }
        return null;
    }

    public static ViewHolder getViewHolderTag(View convertView) {
        return getViewHolderTag(convertView, 0);
    }

    public View getConvertView() {
        return convertView;
    }

    public int getPosition() {
        return position;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bmp) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bmp);
        return this;
    }
}
