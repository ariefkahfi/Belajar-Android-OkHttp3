package com.kahfi.arief.belajarandroidlocalbroadcastmanager.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kahfi.arief.belajarandroidlocalbroadcastmanager.fragments.FragmentInsert;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.fragments.FragmentList;

/**
 * Created by arief on 30/09/17.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :return new FragmentInsert();
            case 1 :return new FragmentList();
            default: return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Form Insert";
            case 1 :
                return "List Data";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
