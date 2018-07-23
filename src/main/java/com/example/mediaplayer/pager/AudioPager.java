package com.example.mediaplayer.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.mediaplayer.base.BasePager;

public class AudioPager extends BasePager {

    private TextView textview;

    public AudioPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textview=new TextView(context);
        textview.setText("bibibibbi");
        return textview;
    }

    @Override
    public void initData() {
        super.initData();
        System.out.println("本地音频初始化了。。");
    }
}
