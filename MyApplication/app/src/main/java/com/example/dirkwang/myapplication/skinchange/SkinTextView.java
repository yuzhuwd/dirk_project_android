package com.example.dirkwang.myapplication.skinchange;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.dirkwang.myapplication.R;

import java.lang.reflect.Field;

/**
 * Created by dirkwang on 2017/5/10.
 */

public class SkinTextView extends TextView implements SkinInterface {

    private int bkgOffset;
    private int colorOffset;

    public SkinTextView(Context context) {
        super(context);
    }

    public SkinTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SkinTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SkinTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context,  AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SkinView);
        int resBkgId = a.getResourceId(R.styleable.SkinView_skin_background, 0);
        int resColorId = a.getResourceId(R.styleable.SkinView_skin_textcolor, 0);
        int resThemeId = getThemeId(context);
        setTextColor(context.getResources().getColor(resColorId));
        setBackgroundResource(resBkgId);
        bkgOffset = resThemeId - resBkgId;
        colorOffset = resThemeId - resColorId;

        ThemeSkinManager.getInstance().registeredSkinView(this);
    }

    public void doSomething() {
        int newThemeId = getThemeId(getContext());
        int bkgId = newThemeId - bkgOffset;
        int colorId = newThemeId - colorOffset;

        Log.d("dirk", String.format("ThemeId:%s   bkgId:%s   colorId:%s", newThemeId, bkgId, colorId));
        setBackgroundResource(bkgId);
        setTextColor(getContext().getResources().getColor(colorId));
    }

    public static int getThemeId(Context context) {
        Resources.Theme test = context.getTheme();
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
        int newThemeId = getThemeId(getContext());
        int bkgId = newThemeId - bkgOffset;
        int colorId = newThemeId - colorOffset;

        Log.d("dirk", String.format("ThemeId:%s   bkgId:%s   colorId:%s", newThemeId, bkgId, colorId));
        setBackgroundResource(bkgId);
        setTextColor(getContext().getResources().getColor(colorId));
    }
}
