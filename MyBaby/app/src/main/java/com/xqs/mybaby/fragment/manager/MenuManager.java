package com.xqs.mybaby.fragment.manager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xqs.mybaby.R;
import com.xqs.mybaby.app.BaseApp;
import com.xqs.mybaby.fragment.FootprintFragment;
import com.xqs.mybaby.fragment.HomeFragment;
import com.xqs.mybaby.fragment.MyFragment;
import com.xqs.mybaby.fragment.TopicFragment;

/**
 * 菜单管理的类
 *
 */
public class MenuManager {

    private FragmentManager fragmentManager;
    private MenuType curType;
    public BaseApp app;

    public MenuManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        curType = MenuType.FIRST;//默认首页
        app = BaseApp.getInstance();
    }

    public enum MenuType {

        FIRST("成长", true),
        SECOND("足迹", false),
        THIRD("群组",true),
        FORTH("个人",true);

        private final String title;
        private final boolean removed; // 表示fragment是否需要remove

        private MenuType(final String title, boolean removed) {
            this.title = title;
            this.removed = removed;
        }

        public String getTitle() {
            return title;
        }

        public boolean hasRemoved() {
            return removed;
        }
    }

    public MenuType getCurType() {
        return curType;
    }

    public boolean show(MenuType type) {
        if (curType == type) {
            return true;
        } else {
            hide(curType);
        }
        Fragment fragment = (Fragment) fragmentManager.findFragmentByTag(type.getTitle());
        if (fragment == null) {

            fragment = create(type);
            if (fragment == null) {
                return false;
            }
        }

        fragmentManager.beginTransaction().show(fragment).commit();
        curType = type;
        return true;
    }

    public Fragment getFragmentByTag(String tag){
        return fragmentManager.findFragmentByTag(tag);
    }

    private void hide(MenuType type) {
        Fragment fragment = (Fragment) fragmentManager.findFragmentByTag(type.getTitle());
        if (fragment != null) {

            if (type.hasRemoved()) {
                fragmentManager.beginTransaction().remove(fragment).commit();
            } else {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.hide(fragment);
                ft.commit();
            }
        }
    }

    public Fragment create(MenuType type) {
        Fragment fragment = null;
        switch(type) {
            case FIRST:
                fragment=new HomeFragment();
                break;
            case SECOND:
                fragment=new FootprintFragment();
                break;
            case THIRD:
                fragment=new TopicFragment();
                break;
            case FORTH:
                fragment=new MyFragment();
                break;
        }

        fragmentManager.beginTransaction().add(R.id.fl_content ,fragment, type.getTitle()).commit();
        return fragment;
    }


}
