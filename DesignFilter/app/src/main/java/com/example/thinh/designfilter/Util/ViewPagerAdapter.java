package com.example.thinh.designfilter.Util;

/**
 * Created by thinh on 11/28/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.thinh.designfilter.DFilter;
import com.example.thinh.designfilter.Dao.Filter;
import com.example.thinh.designfilter.Test;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private CharSequence titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    private int numbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    private Filter filter;
    public ViewPagerAdapter(FragmentManager fm, CharSequence titles[], int numbOfTabs) {
        super(fm);
        this.titles = titles;
        this.numbOfTabs = numbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            DFilter fragment = new DFilter();
            fragment.setFilter(filter);
            return fragment;
        } else {
            Test fragment = new Test();
            fragment.setFilter(filter);
            return fragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
