package com.example.dirkwang.myapplication.skinchange;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.example.dirkwang.myapplication.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dirkwang on 2017/5/9.
 */

public class SkinImageView extends ImageView implements SkinInterface {

    private Map<String, String> mSkinAttributeMap = new HashMap<>();
    private static final int INDEX_ERROR = -1;

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

    public void init(Context context, AttributeSet attrs) {
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; ++i) {
            String value = attrs.getAttributeValue(i);
            if (value.startsWith("?")) {
                mSkinAttributeMap.put(attrs.getAttributeName(i), attrs.getAttributeValue(i));
            }
        }
        Log.w("dirk", "Map:" + mSkinAttributeMap.toString());
        ThemeSkinManager.getInstance().registeredSkinView(this);
    }

/*    public void doSomething() {
*//*        TypedArray a = getContext().obtainStyledAttributes(attributeSet,
                R.styleable.SkinImageView);
        int resId = a.getResourceId(R.styleable.SkinImageView_background, R.drawable.l2);
        setBackgroundResource(resId);*//*

        //attributeSetWrap.setCore(null);

*//*        TypedArray ta = getContext().obtainStyledAttributes(mAttrs, R.styleable.SkinImageView);
        int resId = ta.getResourceId(R.styleable.SkinImageView_skin_background, 0);*//*

        //mTypedArray.recycle();
        //       Log.d("dirk-test", "SkinImageView:DayTheme:resId" + resId);
        //invalidate();
        //setBackgroundResource(resId);

        int resId = getThemeId() - offset;
        setBackgroundResource(resId);

    }*/


/*    public int getThemeId() {
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
    }*/

    @Override
    public void changeSkin() {

        TypedArray ta = getContext().obtainStyledAttributes(R.styleable.Skin);

        // 背景设置
        String background = mSkinAttributeMap.get("background");
        int background_index = getIndex(background);
        if (INDEX_ERROR != background_index) {
            setBackgroundResource(ta.getResourceId(background_index, 0));
        }
        // 设置资源
        String src = mSkinAttributeMap.get("src");
        int src_index = getIndex(src);
        if (INDEX_ERROR != src_index) {
            setImageResource(ta.getResourceId(src_index, 0));
        }

        ta.recycle();

/*        TypedArray ta = getContext().obtainStyledAttributes(R.styleable.Skin);
        int resId = ta.getResourceId(mIndex, 0);
        setBackgroundResource(resId);*/

/*        int resId = getThemeId() - offset;

        String name = getContext().getResources().getResourceName(resId);
        Log.d("dirk", "换肤后资源对应的名称：" + name);
        String Themename = getContext().getResources().getResourceName(getThemeId());
        Log.d("dirk", "换肤前资源对应的主题名称：" + Themename);
        setBackgroundResource(resId);*/
        //test(resId, getThemeId());
    }

/*    private void test(int resId, int themeId) {
        Log.e("dirk", "资源ID：【" + resId + "】主题ID：【" + themeId+"】");
        for (int i = resId; i <= themeId; i++) {
            try {
                String name = getContext().getResources().getResourceName(i);
                Log.e("dirk", "换肤后资源对应的Id：【" + i + "】换肤后资源对应的名称：【" + name + "】");
            } catch (Exception e) {
                //Log.e("dirk", "无法读取的ID：" + i);
            }
        }
    }*/

    public int getIndex(String value) {
        if (TextUtils.isEmpty(value)) return INDEX_ERROR;
        int index = 0;
        int num = Integer.parseInt(value.substring(1));
        for (int i = 0; i < R.styleable.Skin.length; ++i) {
            if (num == R.styleable.Skin[i]) {
                index = i;
                break;
            }
        }
        return index;
    }
}
