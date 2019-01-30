package com.example.dirkwang.myapplication;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;

public class DirkApplication extends Application {
    private static final String TAG = "DirkApplication";
    private static DirkApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        Log.d(TAG, "application create");
    }

    public static DirkApplication getInstance() {
        return instance;
    }

    private void init() {
        Utils.init(getInstance());
    }
}
