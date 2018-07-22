package com.example.mediaplayer.base;

import android.content.Context;
import android.view.View;

public abstract class BasePager {
    public Context context;
    public View rootView;

    public BasePager(Context context){
        this.context=context;
        rootView=initView();
    }

    public abstract View initView();

    public void initData(){

    }
}
