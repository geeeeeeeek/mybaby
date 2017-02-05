package com.xqs.mybaby.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xqs.mybaby.R;
import com.xqs.mybaby.adapter.CenterPagerAdapter;
import com.xqs.mybaby.app.BaseFragment;
import com.xqs.mybaby.ui.CenterViewPager;
import com.xqs.mybaby.ui.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

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
        ButterKnife.bind(this,v);
        initData();
        initView();
        return v;

    }

    private void initData() {
        List<String> data=new ArrayList<String>();
        data.add("hhh");
        data.add("kkk");
        data.add("kkk");
        data.add("kkk2");
        data.add("kkk3");
        data.add("kkk23");
        data.add("kkk31");
        CenterPagerAdapter adapter=new CenterPagerAdapter(this.app,data);
        mViewPager.setAdapter(adapter);
//        mViewPager.enableCenterLockOfChilds();
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private void initView() {

    }


}
