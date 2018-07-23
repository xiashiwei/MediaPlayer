package com.example.mediaplayer.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.mediaplayer.R;
import com.example.mediaplayer.base.BasePager;

public class VideoPager extends BasePager {

    private TextView textview;

    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view=View.inflate(context,R.layout.video_pager,null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
