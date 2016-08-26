package com.itexico.instrumentationtesting.espresso.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itexico.instrumentationtesting.espresso.fragments.ViewPagerFragment;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    public static final String[] PAGER_TABS = {"Software Engineers", "Human Resources"};
    public static final String PAGER_TABS_INDEX = "pager_tabs_index";

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return newFragment(position);
    }

    private Fragment newFragment(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(PAGER_TABS_INDEX, index);
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        viewPagerFragment.setArguments(bundle);
        return viewPagerFragment;
    }


    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return PAGER_TABS.length;
    }
}
