package com.cipher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Eduardo Gorio on 26/06/2015.
 */
public class MainActivity extends ActionBarActivity {

    FruitPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPagerAdapter = new FruitPagerAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(mPagerAdapter);
    }

    class FruitPagerAdapter extends FragmentPagerAdapter {
        FruitsListFragment mList;
        FruitsGridFragment mGrid;

        public FruitPagerAdapter(FragmentManager fm) {
            super(fm);
            mList = new FruitsListFragment();
            mGrid = new FruitsGridFragment();
        }

        @Override
        public Fragment getItem(int position) {
            return (position == 0) ? mList : mGrid;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return (position == 0) ? "Lista" : "Grid";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
