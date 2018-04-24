package com.gamma.billboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emers on 23/4/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> frList = new ArrayList<>();
    private final  List<String> frTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return frList.get(position);
    }

    @Override
    public int getCount() {
        return frTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return frTitles.get(position);
    }

    public void addFragment(Fragment f, String title){
        frList.add(f);
        frTitles.add(title);
    }

}
