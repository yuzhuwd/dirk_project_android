package com.example.dirkwang.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<MainData> mData = new ArrayList<>();
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new MainAdapter(this, mData, R.layout.listitem_main);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        mData.add(getData("LoadingBar", "PPActivity"));
        mData.add(getData("AnimationDemo", "AnimationDemoActivity"));
        mData.add(getData("SkinningAssets", "SkinningAssetsActivity"));
        mData.add(getData("SkinningColorful", "SkinningColorfulActivity"));
        mData.add(getData("SkinView", "SkinViewActivity"));
        mData.add(getData("AndroidUtilTest", "UtilTestActivity"));
        mData.add(getData("FloatWindow","FloatWindowActivity"));

    }

    private MainData getData(String title, String intent) {
        MainData data = new MainData();
        data.title = title;
        data.intent = intent;
        return data;
    }
}
