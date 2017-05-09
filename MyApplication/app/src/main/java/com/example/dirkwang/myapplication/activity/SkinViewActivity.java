package com.example.dirkwang.myapplication.activity;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dirkwang.myapplication.R;

import static com.example.dirkwang.myapplication.R.style.DayTheme;
import static com.example.dirkwang.myapplication.R.style.NightTheme;

public class SkinViewActivity extends AppCompatActivity {

    TextView mChange;
    View mTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(NightTheme);
        setContentView(R.layout.activity_skin_view);
        mTestView = findViewById(R.id.testView);
        mChange = (TextView) findViewById(R.id.tv_change);

        TypedArray ta = obtainStyledAttributes(R.styleable.SkinImageView);
        int resId = ta.getResourceId(R.styleable.SkinImageView_root_view_bg, 0);
        ta.recycle();
        Log.d("dirk-test", "NightTheme:resId" + resId);

        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(DayTheme);
                TypedArray ta = obtainStyledAttributes(R.styleable.SkinImageView);
                int resId = ta.getResourceId(R.styleable.SkinImageView_root_view_bg, 0);
                ta.recycle();
                mTestView.setBackgroundResource(resId);
                Log.d("dirk-test", "DayTheme:resId" + resId);
            }
        });
    }
}
