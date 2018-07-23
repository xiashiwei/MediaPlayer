package com.example.mediaplayer.base;

import android.content.Context;
import android.view.View;

public abstract class BasePager {
    public Context context;
    public View rootView;
    public boolean is_initData=false;

    public BasePager(Context context){
        this.context=context;
        rootView=initView();
        this.is_initData=false;
    }

    public abstract View initView();

    public void initData(){

    }
}
