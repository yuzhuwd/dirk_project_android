package com.example.dirkwang.myapplication.skinchange;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.dirkwang.myapplication.R;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dirkwang on 2017/5/10.
 */

public class SkinTextView extends TextView implements SkinInterface {

    private Map<String, String> mSkinAttributeMap = new HashMap<>();
    private static final int INDEX_ERROR = -1;
    private static final String SUPPORT_TAG = "skin_support";
    private int mSupport = 0;

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
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; ++i) {
            String name = attrs.getAttributeName(i);
            String value = attrs.getAttributeValue(i);

            if (value.startsWith("?")) {
                mSkinAttributeMap.put(name, value);
            }

            if (name.equals(SUPPORT_TAG)) {
                mSupport = Integer.parseInt(value);
            }
            Log.w("dirk", "Name:【" + name + "】value:【" + value+"】");
        }
        Log.w("dirk", "Map:" + mSkinAttributeMap.toString());
        ThemeSkinManager.getInstance().registeredSkinView(this);
        Log.w("dirk", "mSupport:" + mSupport);
        Log.w("dirk", "context" + context);
    }

    @Override
    public void changeSkin() {

        TypedArray ta = getContext().obtainStyledAttributes(R.styleable.Skin);

        // 背景设置
        String background = mSkinAttributeMap.get("background");
        int background_index = getIndex(background);
        if (INDEX_ERROR != background_index) {
            setBackgroundResource(ta.getResourceId(background_index, 0));
        }

        Log.w("dirk", "background_index:" + background_index);

        // textColor设置
        String textColor = mSkinAttributeMap.get("textColor");
        int textColor_index = getIndex(textColor);
        if (INDEX_ERROR != textColor_index) {
            setTextColor(ta.getColor(textColor_index, 0));
        }

        Log.w("dirk", "textColor_index:" + textColor_index);

        // text设置
        String text = mSkinAttributeMap.get("text");
        int text_index = getIndex(text);
        if (INDEX_ERROR != text_index) {
            setText(ta.getString(text_index));
        }

        Log.w("dirk", "text_index:" + text_index);

        // textDrawable设置
        String drawLeft = mSkinAttributeMap.get("drawableLeft");
        String drawRight = mSkinAttributeMap.get("drawableRight");
        String drawTop = mSkinAttributeMap.get("drawableTop");
        String drawBottom = mSkinAttributeMap.get("drawableBottom");

        int drawLeft_index = getIndex(drawLeft);
        int drawRight_index = getIndex(drawRight);
        int drawTop_index = getIndex(drawTop);
        int drawBottom_index = getIndex(drawBottom);

        int resId = ta.getResourceId(drawLeft_index, 0);
        Log.w("dirk", "drawLeft_resId:" + resId);
        if (drawLeft_index == INDEX_ERROR && drawRight_index == INDEX_ERROR && drawTop_index == INDEX_ERROR && drawBottom_index == INDEX_ERROR) {
            return;
        } else {
            setCompoundDrawablesWithIntrinsicBounds(drawLeft_index == INDEX_ERROR?null:ta.getDrawable(drawLeft_index),
                    drawTop_index == INDEX_ERROR?null:ta.getDrawable(drawTop_index),
                    drawRight_index == INDEX_ERROR?null:ta.getDrawable(drawRight_index),
                    drawBottom_index == INDEX_ERROR?null:ta.getDrawable(drawBottom_index));
        }

        ta.recycle();
        Log.w("dirk", "drawLeft_index:" + drawLeft_index);
        Log.w("dirk", "drawTop_index:" + drawTop_index);
        Log.w("dirk", "drawRight_index:" + drawRight_index);
        Log.w("dirk", "drawBottom_index:" + drawBottom_index);
    }

    public int getIndex(String value) {
        if (TextUtils.isEmpty(value)) return INDEX_ERROR;
        int index = 0;
        int num = Integer.parseInt(value.substring(1));
        for (int i = 0; i < R.styleable.Skin.length; ++i) {
            if (num == R.styleable.Skin[i]){
                index = i;
                break;
            }
        }
        return index;
    }



/*    private void init(Context context,  AttributeSet attrs) {
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
    }*/

/*    public void doSomething() {
        int newThemeId = getThemeId(getContext());
        int bkgId = newThemeId - bkgOffset;
        int colorId = newThemeId - colorOffset;

        Log.d("dirk", String.format("ThemeId:%s   bkgId:%s   colorId:%s", newThemeId, bkgId, colorId));
        setBackgroundResource(bkgId);
        setTextColor(getContext().getResources().getColor(colorId));
    }*/

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



/*    @Override
    public void changeSkin() {
*//*        int newThemeId = getThemeId(getContext());
        int bkgId = newThemeId - bkgOffset;
        int colorId = newThemeId - colorOffset;

        Log.d("dirk", String.format("ThemeId:%s   bkgId:%s   colorId:%s", newThemeId, bkgId, colorId));
        setBackgroundResource(bkgId);
        setTextColor(getContext().getResources().getColor(colorId));*//*
    }*/
}
