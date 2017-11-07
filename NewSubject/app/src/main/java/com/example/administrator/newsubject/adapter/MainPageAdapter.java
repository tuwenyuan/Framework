package com.example.administrator.newsubject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.administrator.newsubject.factory.FragmentFactory;


/**
 * Created by Administrator on 2017/3/17.
 */

public class MainPageAdapter extends FragmentPagerAdapter {
    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragmentNew(position);
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
