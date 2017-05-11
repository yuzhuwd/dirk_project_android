package com.example.dirkwang.myapplication.skinchange;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dirkwang on 2017/5/10.
 */

public class ThemeSkinManager {

    private volatile static ThemeSkinManager sInstance = null;

    public static ThemeSkinManager getInstance() {
        if (sInstance == null) {
            synchronized (ThemeSkinManager.class) {
                if (sInstance == null) {
                    sInstance = new ThemeSkinManager();
                }
            }
        }
        return sInstance;
    }

    private List<WeakReference<View>> mSkinList = new ArrayList<>();
    private int mCurcentTheme;

    public int getCurcentTheme() {
        return mCurcentTheme;
    }

    public <T extends View & SkinInterface> void registeredSkinView(T view) {
        WeakReference<View> skinView = new WeakReference<View>(view);
        mSkinList.add(skinView);
        Log.d("ThemeSkinManager", "注册换肤view：" + view.getId());
    }

    public void noticeChangeTheme(Context context, int themeId) {
        Log.d("ThemeSkinManager", "修改主题：" + themeId);
        mCurcentTheme = themeId;
        context.setTheme(mCurcentTheme);
        handleSkinView();
    }

    private void handleSkinView() {
        if (mSkinList == null || 0 == mSkinList.size()) {
            return;
        }

        for (WeakReference<View> view : mSkinList) {
            View skinView = view.get();
            if (skinView == null) {
                return;
            } else {
                SkinInterface skinInterface = (SkinInterface) skinView;
                skinInterface.changeSkin();
            }
        }

        deleteInvalidView();
    }

    private void deleteInvalidView() {
        Iterator<WeakReference<View>> iterator = mSkinList.iterator();
        while (iterator.hasNext()) {
            WeakReference<View> view = iterator.next();
            if (view.get() == null) {
                iterator.remove();
            }
        }
    }
}
