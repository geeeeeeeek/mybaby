package com.xqs.mybaby.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xqs.mybaby.R;
import com.xqs.mybaby.app.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class MyFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_forth, container, false);
        ButterKnife.bind(this.getActivity());
        return v;

    }
}
