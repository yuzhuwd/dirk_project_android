package com.example.dirkwang.myapplication.skinchange;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.example.dirkwang.myapplication.R;

import java.lang.reflect.Field;

/**
 * Created by dirkwang on 2017/5/9.
 */

public class SkinImageView extends ImageView implements SkinInterface{

    AttributeSet mAttrs;
    int offset;

    //TypedArray mTypedArray;

    public SkinImageView(Context context) {
        super(context);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private AttributeSetWrap attributeSetWrap;

    public void init(Context context, AttributeSet attrs) {
        //attributeSetWrap = new AttributeSetWrap(attrs);
        mAttrs = attrs;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SkinView);
        int resId = a.getResourceId(R.styleable.SkinView_skin_background, 0);

        Resources resources = a.getResources();

        offset = getThemeId() - resId;

        //a.getResources();
        a.recycle();
        Log.d("dirk-test", "SkinImageView:NightTheme:resId" + resId);
        setBackgroundResource(resId);
        ThemeSkinManager.getInstance().registeredSkinView(this);
    }

    public void doSomething() {
/*        TypedArray a = getContext().obtainStyledAttributes(attributeSet,
                R.styleable.SkinImageView);
        int resId = a.getResourceId(R.styleable.SkinImageView_background, R.drawable.l2);
        setBackgroundResource(resId);*/

        //attributeSetWrap.setCore(null);

/*        TypedArray ta = getContext().obtainStyledAttributes(mAttrs, R.styleable.SkinImageView);
        int resId = ta.getResourceId(R.styleable.SkinImageView_skin_background, 0);*/

        //mTypedArray.recycle();
 //       Log.d("dirk-test", "SkinImageView:DayTheme:resId" + resId);
        //invalidate();
        //setBackgroundResource(resId);

        int resId = getThemeId() - offset;
        setBackgroundResource(resId);

    }


    public int getThemeId() {
        Resources.Theme test = getContext().getTheme();
        Class<?> clazz = test.getClass();

        try {
            Field field = clazz.getDeclaredField("mThemeResId");
            field.setAccessible(true);
            Integer value = (Integer) field.get(test);
            Log.d("dirk_test", "value:" + value);
            return value;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void changeSkin() {
        int resId = getThemeId() - offset;
        setBackgroundResource(resId);
    }
}
