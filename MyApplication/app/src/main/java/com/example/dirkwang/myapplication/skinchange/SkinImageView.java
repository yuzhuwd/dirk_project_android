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

public class SkinImageView extends ImageView implements SkinInterface {

    AttributeSet mAttrs;
    int offset;
    int mIndex ;

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
        String name = a.getNonResourceString(R.styleable.SkinView_skin_background);

        Log.w("dirk", "resId:" + resId);
        //a.getIndex();

/*        Resources resources = a.getResources();
        String name = resources.getResourceName(resId);*/
        //String name = resources.getResourceName(R.styleable.SkinView_skin_background);
        offset = getThemeId() - resId;

        int count = attrs.getAttributeCount();
        Log.w("dirk", "count:" + count);
        for (int i = 0; i < count; ++i ) {
            String attrName  = attrs.getAttributeName(i);
            Log.w("dirk", "attrName:" + attrName);
        }

        for (int i = 0; i < count; ++i) {
            String Value = attrs.getAttributeValue(i);
            Log.w("dirk", "Value:" + Value);
        }


        String value = attrs.getAttributeValue(3);
        int num = Integer.parseInt(value.substring(1));
        for (int i = 0; i < R.styleable.Skin.length; ++i) {
            if (num == R.styleable.Skin[i]){
                mIndex = i;
                break;
            }
        }

        Log.w("dirk", "index:" + mIndex);




        //a.getResources();
        a.recycle();

        setBackgroundResource(resId);
        ThemeSkinManager.getInstance().registeredSkinView(this);

        //test(resId, getThemeId());
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

        TypedArray ta = getContext().obtainStyledAttributes(R.styleable.Skin);
        int resId = ta.getResourceId(mIndex, 0);
        setBackgroundResource(resId);

/*        int resId = getThemeId() - offset;

        String name = getContext().getResources().getResourceName(resId);
        Log.d("dirk", "换肤后资源对应的名称：" + name);
        String Themename = getContext().getResources().getResourceName(getThemeId());
        Log.d("dirk", "换肤前资源对应的主题名称：" + Themename);
        setBackgroundResource(resId);*/
        //test(resId, getThemeId());
    }

    private void test(int resId, int themeId) {
        Log.e("dirk", "资源ID：【" + resId + "】主题ID：【" + themeId+"】");
        for (int i = resId; i <= themeId; i++) {
            try {
                String name = getContext().getResources().getResourceName(i);
                Log.e("dirk", "换肤后资源对应的Id：【" + i + "】换肤后资源对应的名称：【" + name + "】");
            } catch (Exception e) {
                //Log.e("dirk", "无法读取的ID：" + i);
            }
        }
    }
}
