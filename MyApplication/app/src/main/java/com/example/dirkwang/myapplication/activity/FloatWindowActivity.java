package com.example.dirkwang.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.dirkwang.myapplication.R;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.Screen;
import com.yhao.floatwindow.ViewStateListener;

public class FloatWindowActivity extends AppCompatActivity {

    private static final String TAG = "FloatWindowActivity";
    private TextView mStart;
    private TextView mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);
        mStart = findViewById(R.id.start);
        mStop = findViewById(R.id.stop);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFloatWin();
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopFloatWin();
            }
        });

        //createFloatWin();

    }


    private void createFloatWin() {
        View floatwinLayout = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_float_window, null);

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.mvp);

        FloatWindow
                .with(getApplicationContext())
                .setView(floatwinLayout)
                .setWidth(Screen.width, 0.7f) //设置悬浮控件宽高
                .setHeight(Screen.width, 0.7f)
                .setX(Screen.width, 0.8f)
                .setY(Screen.height, 0.3f)
                .setMoveType(MoveType.slide,100,-100)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(true, AppCompatActivity.class)
                .setViewStateListener(mViewStateListener)
                .setPermissionListener(mPermissionListener)
                .setDesktopShow(true)
                .setTag("test")
                .build();
    }


    private void startFloatWin() {

        if (FloatWindow.get("test") == null) {
            createFloatWin();
        }
        FloatWindow.get("test").show();
        ToastUtils.showShort("点击开启哈");
    }

    private void stopFloatWin() {
        if (FloatWindow.get("test") != null) {
            FloatWindow.destroy("test");
        }

    }

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {
            Log.d(TAG, "onSuccess");
        }

        @Override
        public void onFail() {
            Log.d(TAG, "onFail");
        }
    };

    private ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(int x, int y) {
            Log.d(TAG, "onPositionUpdate: x=" + x + " y=" + y);
        }

        @Override
        public void onShow() {
            Log.d(TAG, "onShow");
        }

        @Override
        public void onHide() {
            Log.d(TAG, "onHide");
        }

        @Override
        public void onDismiss() {
            Log.d(TAG, "onDismiss");
        }

        @Override
        public void onMoveAnimStart() {
            Log.d(TAG, "onMoveAnimStart");
        }

        @Override
        public void onMoveAnimEnd() {
            Log.d(TAG, "onMoveAnimEnd");
        }

        @Override
        public void onBackToDesktop() {
            Log.d(TAG, "onBackToDesktop");
        }
    };
}
