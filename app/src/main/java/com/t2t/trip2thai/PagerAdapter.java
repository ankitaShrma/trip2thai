package com.t2t.trip2thai;

/**
 * Created by AnKita on 19-Jul-16.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabBangkok tab1 = new TabBangkok();
                return tab1;
            case 1:
                TabPhuket tab2 = new TabPhuket();
                return tab2;
            case 2:
                TabPattaya tab3 = new TabPattaya();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}