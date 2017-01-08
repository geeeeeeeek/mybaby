package com.xqs.mybaby.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xqs.mybaby.R;
import com.xqs.mybaby.app.BaseFragmentActivity;
import com.xqs.mybaby.fragment.HomeFragment;
import com.xqs.mybaby.fragment.manager.MenuManager;
import com.xqs.mybaby.utils.ToastUtil;
import com.xqs.mybaby.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/2 0002.
 */

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {


    //tabs
    @BindView(R.id.layout0)
    protected LinearLayout mLayout0;

    @BindView(R.id.layout1)
    protected LinearLayout mLayout1;

    @BindView(R.id.layout2)
    protected LinearLayout mLayout2;

    @BindView(R.id.layout3)
    protected LinearLayout mLayout3;

    @BindView(R.id.image0)
    protected ImageView mImage0;

    @BindView(R.id.image1)
    protected ImageView mImage1;

    @BindView(R.id.image2)
    protected ImageView mImage2;

    @BindView(R.id.image3)
    protected ImageView mImage3;

    @BindView(R.id.tv_tab_first)
    protected TextView mTabText0;

    @BindView(R.id.tv_tab_second)
    protected TextView mTabText1;

    @BindView(R.id.tv_tab_third)
    protected TextView mTabText2;

    @BindView(R.id.tv_tab_forth)
    protected TextView mTabText3;

    private MenuManager menuManager;

    private Fragment mContent;

    public static int MENU_SELECT = 0;

    private long mExitTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData(savedInstanceState);
        initView();
    }

    private void initData(Bundle savedInstanceState) {
        initTab(savedInstanceState);
    }

    private void initView() {

        mLayout0.setOnClickListener(this);
        mLayout1.setOnClickListener(this);
        mLayout2.setOnClickListener(this);
        mLayout3.setOnClickListener(this);
    }

    private void initTab(Bundle savedInstanceState) {
        menuManager=new MenuManager(getSupportFragmentManager());
        if (savedInstanceState != null) {
            mContent = getSupportFragmentManager().getFragment(
                    savedInstanceState, "mContent");
        }
        if (mContent == null) {
            menuManager = new MenuManager(getSupportFragmentManager());
            mContent = new HomeFragment();
            menuSelected(MENU_SELECT);
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, mContent, MenuManager.MenuType.FIRST.getTitle())
                .commit();
    }


   

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout0:
                menuSelected(0);
                menuManager.show(MenuManager.MenuType.FIRST);
                break;
            case R.id.layout1:
                menuSelected(1);
                menuManager.show(MenuManager.MenuType.SECOND);
                break;
            case R.id.layout2:
                menuSelected(2);
                menuManager.show(MenuManager.MenuType.THIRD);
                break;
            case R.id.layout3:
                menuSelected(3);
                menuManager.show(MenuManager.MenuType.FORTH);
                break;

        }
    }


    public void menuSelected(int index) {

        MENU_SELECT = index;
        mImage0.setSelected(false);
        mImage1.setSelected(false);
        mImage2.setSelected(false);
        mImage3.setSelected(false);

        mTabText0.setSelected(false);
        mTabText1.setSelected(false);
        mTabText2.setSelected(false);
        mTabText3.setSelected(false);


        switch (index) {
            case 0:
                mImage0.setSelected(true);
                mTabText0.setSelected(true);
                break;
            case 1:
                mImage1.setSelected(true);
                mTabText1.setSelected(true);
                break;
            case 2:
                mImage2.setSelected(true);
                mTabText2.setSelected(true);
                break;
            case 3:
                mImage3.setSelected(true);
                mTabText3.setSelected(true);
                break;
            default:
                break;
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(MENU_SELECT!=0){
                menuSelected(0);
                menuManager.show(MenuManager.MenuType.FIRST);
            }else{
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    ToastUtil.showShort(this, "再按一次即可退出");
                    mExitTime = System.currentTimeMillis();
                } else {
                    System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
