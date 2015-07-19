package com.valterc.stevecrafts.crafting;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.HashMap;

/**
 * Created by Valter on 19/07/2015.
 */
public class CraftingRecipesFragmentAdapter extends FragmentStatePagerAdapter {

    private HashMap<String, CraftingRecipeFragment> fragments;
    private String[] ids;

    public CraftingRecipesFragmentAdapter(FragmentManager fm, String[] ids) {
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
        CraftingRecipeFragment fragment = fragments.get(id);

        if (fragment == null) {
            fragment = CraftingRecipeFragment.newInstance(id);
            fragments.put(id, fragment);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return ids.length;
    }
}