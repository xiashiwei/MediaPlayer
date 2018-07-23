package com.example.mediaplayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediaplayer.base.BasePager;

public class MyFragment extends Fragment {
    private static BasePager basePage;

    public MyFragment(){

    }

    public static final MyFragment newInstance(BasePager page) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        basePage = page;
        fragment.setArguments(bundle);
        return fragment ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (basePage != null){
            return basePage.rootView;
        }
        return null;
    }
}
