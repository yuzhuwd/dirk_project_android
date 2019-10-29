package com.example.dirkwang.myapplication.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dirk.assistapp.IMyAidlInterface;
import com.dirk.assistapp.IPostMsgAidlInterface;
import com.example.dirkwang.myapplication.R;

public class AIDLTestActivity extends AppCompatActivity {

    private ImageView mTestImage;
    private TextView mShowTv;

    private ImageView mTestImage1;
    private TextView mShowTv1;

    private int num = 0;

    private String url = "http://p.qlogo.cn/qtl_user/b69bf01b878ea6a6e424e55d57e69ab57af274d93aa3f23a19eee54289224b2a/0?1557468882";

    private IMyAidlInterface iMyAidlInterface;
    private IPostMsgAidlInterface iPostMsgAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindService();
        setContentView(R.layout.activity_aidl_test);
        mShowTv = findViewById(R.id.show_text);
        mTestImage = findViewById(R.id.img_test);

        mShowTv1 = findViewById(R.id.show_text_1);
        mTestImage1 = findViewById(R.id.img_test_1);

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

        mTestImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String temp = "hello, hello, i am coming";
                    iPostMsgAidl.postMsg(temp + num);
                    mShowTv1.setText(temp + num);
                    num++;
                } catch (Exception e) {
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


        Intent intent1 = new Intent();
        intent1.setAction("com.dirk.assistapp.service.PostMsgService");
        intent1.setPackage("com.dirk.assistapp");

        bindService(intent1, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iPostMsgAidl = IPostMsgAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

}
