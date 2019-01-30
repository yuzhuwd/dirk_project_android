package com.example.dirkwang.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.CacheDiskUtils;
import com.blankj.utilcode.util.CacheDoubleUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.example.dirkwang.myapplication.R;

public class UtilTestActivity extends AppCompatActivity {

    TextView mBarHigh;
    TextView mIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util_test);
        mBarHigh = (TextView) findViewById(R.id.util_bar_high);
        mBarHigh.setText(String.valueOf(BarUtils.getActionBarHeight()));
        mIp = (TextView) findViewById(R.id.util_net_ip);
        mIp.setText(NetworkUtils.getIPAddress(true));

        CacheDiskUtils.getInstance().put("test", "dirk");
        CacheDoubleUtils.getInstance().put("test2", "dirk");



    }
}
