package com.valterc.stevecrafts.smelting;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.HashMap;

/**
 * Created by Valter on 19/07/2015.
 */
public class SmeltingFragmentAdapter extends FragmentStatePagerAdapter {

    private HashMap<String, SmeltingFragment> fragments;
    private String[] ids;

    public SmeltingFragmentAdapter(FragmentManager fm, String[] ids) {
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
        SmeltingFragment fragment = fragments.get(id);

        if (fragment == null) {
            fragment = SmeltingFragment.newInstance(id);
            fragments.put(id, fragment);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return ids.length;
    }
}