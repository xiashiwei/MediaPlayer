package com.example.mediaplayer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mediaplayer.base.BasePager;
import com.example.mediaplayer.pager.AudioPager;
import com.example.mediaplayer.pager.NetAudioPager;
import com.example.mediaplayer.pager.NetVideoPager;
import com.example.mediaplayer.pager.VideoPager;
import com.example.mediaplayer.MyFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_main;
    private ArrayList<BasePager> basePagers;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_main=(RadioGroup) findViewById(R.id.rg_main);
        basePagers=new ArrayList<>();
        basePagers.add(new VideoPager(this));
        basePagers.add(new AudioPager(this));
        basePagers.add(new NetVideoPager(this));
        basePagers.add(new NetAudioPager(this));

        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rg_main.check(R.id.rb_video);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                default:
                    position=0;
                    break;
                case R.id.rb_audio:
                    position=1;
                    break;
                case R.id.rb_net_audio:
                    position=3;
                    break;
                case R.id.rb_net_video:
                    position=2;
                    break;
            }
          setFragment();

        }
    }

//    private void setFragment() {
//       FragmentManager fm= getSupportFragmentManager();
//        FragmentTransaction ft=fm.beginTransaction();
//        ft.replace(R.id.fl_main,new Fragment(){
//            @Nullable
//            @Override
//            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//                //return super.onCreateView(inflater, container, savedInstanceState);
//                BasePager basepage=getBasePager();
//                if(basepage!=null){
//                    return basepage.rootView;
//                }
//                return null;
//            }
//        });
//        ft.commit();
//    }

    private void setFragment() {
         FragmentManager fm= getSupportFragmentManager();
         FragmentTransaction ft=fm.beginTransaction();
         ft.replace(R.id.fl_main,MyFragment.newInstance(this.getBasePager()));
         ft.commit();
     }
    private BasePager getBasePager() {
        BasePager basePager=basePagers.get(position);
        if(basePager!=null&&!basePager.is_initData){
            basePager.is_initData=true;
            basePager.initData();
        }
        return basePager;
    }


}

