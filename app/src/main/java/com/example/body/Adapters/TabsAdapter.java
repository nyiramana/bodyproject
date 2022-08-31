package com.example.body.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.body.Fragments.BodyLocalListFragment;
import com.example.body.Fragments.BodyRemoteListFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoOfTabs){
        super(fm);
        this.mNumOfTabs = NoOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new BodyRemoteListFragment();
        }
        return new BodyLocalListFragment();
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}