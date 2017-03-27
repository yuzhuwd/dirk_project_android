package com.example.dirkwang.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.dirkwang.myapplication.util.CommonAdapter;
import com.example.dirkwang.myapplication.util.ViewHolder;

import java.util.List;

/**
 *
 * Created by dirkwang on 2017/3/21.
 */

public class MainAdapter extends CommonAdapter<MainData> {
    public MainAdapter(Context context, List<MainData> items, int itemLayoutId) {
        super(context, items, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, final MainData item, int position) {
        TextView title = holder.getView(R.id.tv_title);
        title.setText(item.title);
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(item.intent);
                context.startActivity(intent);
            }
        });
    }
}

