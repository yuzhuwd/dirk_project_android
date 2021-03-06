package com.example.dirkwang.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dirk.assistapp.IMyAidlInterface;
import com.example.dirkwang.myapplication.R;

public class ImageTestActivity extends AppCompatActivity {

    private ImageView mTestImage;
    private TextView mShowTv;
    private String url = "http://p.qlogo.cn/qtl_user/b69bf01b878ea6a6e424e55d57e69ab57af274d93aa3f23a19eee54289224b2a/0?1557468882";

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindService();
        setContentView(R.layout.activity_image_test);
        mShowTv = findViewById(R.id.show_text);
        mTestImage = findViewById(R.id.img_test);
        setImageTest();

        mTestImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mShowTv.setText(iMyAidlInterface.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void setImageTest() {

        Glide.with(this).load(url).into(mTestImage);

    }


    private void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.dirk.assistapp.service.MyService");
        intent.setPackage("com.dirk.assistapp");

        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

}
