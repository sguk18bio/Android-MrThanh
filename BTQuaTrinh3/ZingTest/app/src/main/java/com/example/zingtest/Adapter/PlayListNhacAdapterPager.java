package com.example.zingtest.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PlayListNhacAdapterPager extends FragmentPagerAdapter {
    private ArrayList<Fragment> listfragment = new ArrayList<>();

    public PlayListNhacAdapterPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listfragment.get(position);
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }

    public void addFragment(Fragment fragment) {
        listfragment.add(fragment);
    }


}
