package com.example.dirkwang.myapplication.activity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dirkwang.myapplication.R;

import java.io.IOException;
import java.io.InputStream;

public class SkinningAssetsActivity extends AppCompatActivity {

    TextView mChangeTv;
    TextView mGameNameTv;
    ImageView mTestView;
    String mGameName = "dnf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skinning);
        mChangeTv = (TextView) findViewById(R.id.change_game);
        mGameNameTv = (TextView) findViewById(R.id.game_name);
        mTestView = (ImageView) findViewById(R.id.testview);

        mChangeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mGameName)) {
                    mGameName = "smoba";
                } else {
                    if (TextUtils.equals(mGameName, "smoba")) {
                        mGameName = "dnf";
                    } else {
                        mGameName = "smoba";
                    }
                }
                changeShowEx();
            }
        });
        changeShowEx();
    }

    private void changeShowEx() {
        AssetManager assetManager = getAssets();

        String[] files = null;

        try {

            files = assetManager.list(mGameName);

        } catch (IOException e) {

            Log.e("tag", e.getMessage());

        }
        Log.d("dirk", "files:" + files[0].toString());

        mGameNameTv.setText(mGameName);

        InputStream inputStream = null;

        try {
            String pic = "test.png";
            String path = mGameName + "/" + pic;
            inputStream = assetManager.open(path);


        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        Log.d("dirk", "inputStream:" + inputStream.toString());

        Bitmap bpause = BitmapFactory.decodeStream(inputStream);
        //mTestView.setImageBitmap(bpause);
        Drawable drawable = new BitmapDrawable(bpause);
        mTestView.setImageDrawable(drawable);

    }

/*    public Drawable InputStream2Drawable(InputStream is) {
        Bitmap bitmap = this.InputStream2Bitmap(is);
        return this.bitmap2Drawable(bitmap);
    }

    public Bitmap InputStream2Bitmap(InputStream is) {
        return BitmapFactory.decodeStream(is);
    }

    // Bitmap转换成Drawable
    public Drawable bitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        Drawable d = (Drawable) bd;
        return d;
    }*/
}
