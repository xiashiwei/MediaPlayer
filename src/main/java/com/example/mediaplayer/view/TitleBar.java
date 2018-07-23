package com.example.mediaplayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mediaplayer.R;


public class TitleBar extends LinearLayout {
    private final Context context;
    private View search;
    private View game;
    private View iv_history;

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        search=getChildAt(1);
        game=getChildAt(2);
        iv_history=getChildAt(3);
        MyOnClickListener myOnClickListener=new MyOnClickListener();
        search.setOnClickListener(myOnClickListener);
        game.setOnClickListener(myOnClickListener);
        iv_history.setOnClickListener(myOnClickListener);
    }
    class MyOnClickListener implements OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.tv_search:
                    Toast.makeText(context,"search",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_game:
                    Toast.makeText(context,"game",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_history:
                    Toast.makeText(context,"history",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
