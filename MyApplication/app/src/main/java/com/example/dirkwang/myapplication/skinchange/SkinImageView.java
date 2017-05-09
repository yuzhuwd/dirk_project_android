package com.example.dirkwang.myapplication.skinchange;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.dirkwang.myapplication.R;

/**
 * Created by dirkwang on 2017/5/9.
 */

public class SkinImageView extends ImageView {

    int resId;
    private AttributeSet attributeSet;
    public SkinImageView(Context context) {
        super(context);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        attributeSet = attrs;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SkinImageView);
        resId = a.getResourceId(R.styleable.SkinImageView_skinBkg, R.drawable.l2);
        a.recycle();
        setBackgroundResource(resId);
    }

    public void doSomething(){
        TypedArray a = getContext().obtainStyledAttributes(attributeSet,
                R.styleable.SkinImageView);
        int resId = a.getResourceId(R.styleable.SkinImageView_skinBkg, R.drawable.l2);
        setBackgroundResource(resId);
    }




}
