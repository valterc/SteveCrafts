package com.valterc.stevecrafts.breaks;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.HashMap;

/**
 * Created by Valter on 18/07/2015.
 */
public class BreaksFragmentAdapter extends FragmentStatePagerAdapter {

    private HashMap<String, BreaksFragment> fragments;
    private String[] ids;

    public BreaksFragmentAdapter(FragmentManager fm, String[] ids) {
        super(fm);
        this.fragments = new HashMap<>();
        this.ids = ids;

        for (String id : ids) {
            this.fragments.put(id, null);
        }
    }

    @Override
    public Fragment getItem(int position) {
        String id = ids[position];
        BreaksFragment fragment = fragments.get(id);

        if (fragment == null) {
            fragment = BreaksFragment.newInstance(id);
            fragments.put(id, fragment);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return ids.length;
    }
}