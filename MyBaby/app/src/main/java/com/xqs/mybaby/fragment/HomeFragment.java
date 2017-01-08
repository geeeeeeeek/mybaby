package com.xqs.mybaby.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xqs.mybaby.R;
import com.xqs.mybaby.app.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.vp_content)
    public ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this.getActivity());
        return v;

    }
}
