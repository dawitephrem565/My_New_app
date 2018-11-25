package com.example.filenber.my_new_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/7/2018.
 */

public class ViewPageradapter extends FragmentPagerAdapter {
    private final List<Fragment> lstfragment=new ArrayList<>();
    private final List<String> lstTitle = new ArrayList<>();
    public ViewPageradapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return lstfragment.get(position);
    }

    @Override
    public int getCount() {
        return lstfragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitle.get(position);
    }
    public void AddFragment(Fragment fragment ,String title)
    {
lstfragment.add(fragment);
lstTitle.add(title);

    }
}
