package com.ithb.jeffry.moviecatalogue.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ithb.jeffry.moviecatalogue.fragment.MovieFragment;
import com.ithb.jeffry.moviecatalogue.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public SectionsPagerAdapter(@NonNull FragmentManager fm, Context mContext) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mContext = mContext;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[] {
            R.string.tab_text_1,
            R.string.tab_text_2
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return MovieFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
