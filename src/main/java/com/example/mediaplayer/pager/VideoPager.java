package com.example.mediaplayer.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.mediaplayer.base.BasePager;

public class VideoPager extends BasePager {

    private TextView textview;

    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textview=new TextView(context);
        textview.setText("asd");
        return textview;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
