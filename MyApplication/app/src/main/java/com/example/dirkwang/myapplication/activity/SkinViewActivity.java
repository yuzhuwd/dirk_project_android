package com.example.dirkwang.myapplication.activity;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dirkwang.myapplication.R;
import com.example.dirkwang.myapplication.skinchange.SkinImageView;
import com.example.dirkwang.myapplication.skinchange.SkinTextView;
import com.example.dirkwang.myapplication.skinchange.ThemeSkinManager;

import static com.example.dirkwang.myapplication.R.style.DayTheme;
import static com.example.dirkwang.myapplication.R.style.NightTheme;

public class SkinViewActivity extends AppCompatActivity {

    TextView mChange;
    SkinImageView mSkinImageView;
    SkinTextView mSkinTextView;
    View mTestView;
    int mState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(NightTheme);
        setContentView(R.layout.activity_skin_view);
        mTestView = findViewById(R.id.testView);
        mSkinImageView = (SkinImageView) findViewById(R.id.skinIv);
        mSkinTextView = (SkinTextView) findViewById(R.id.skin_tv);
        mChange = (TextView) findViewById(R.id.tv_change);

        TypedArray ta = obtainStyledAttributes(R.styleable.Skin);
        int resId = ta.getResourceId(R.styleable.Skin_root_view_bg, 0);
        //int resId = ta.getResourceId(R.styleable.SkinImageView_skin_background, 0);
        Log.d("dirk-test", "NightTheme:resId" + resId);
        ta.recycle();



        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (0 == mState % 2) {
                    //setTheme(DayTheme);
                    ThemeSkinManager.getInstance().noticeChangeTheme(SkinViewActivity.this, DayTheme);
                } else {
                    ThemeSkinManager.getInstance().noticeChangeTheme(SkinViewActivity.this, NightTheme);
                    //setTheme(NightTheme);
                }

                TypedArray ta = obtainStyledAttributes(R.styleable.Skin);
                int resId = ta.getResourceId(R.styleable.Skin_root_view_bg, 0);
                String attrName = "root_view_bg";

                //int resId = ta.getResourceId(R.styleable.SkinImageView_skin_background, 0);
                Log.d("dirk-test", "DayTheme:resId" + resId);
                ta.recycle();
                mTestView.setBackgroundResource(resId);
/*                mSkinImageView.doSomething();
                mSkinTextView.doSomething();*/

                mState++;
            }
        });
    }
}
