package com.example.dirkwang.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dirkwang.myapplication.R;

public class ImageTestActivity extends AppCompatActivity {

    private ImageView mTestImage;
    private String url = "http://p.qlogo.cn/qtl_user/b69bf01b878ea6a6e424e55d57e69ab57af274d93aa3f23a19eee54289224b2a/0?1557468882";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);
        mTestImage = findViewById(R.id.img_test);
        setImageTest();
    }


    private void setImageTest() {

        Glide.with(this).load(url).into(mTestImage);

    }
}
