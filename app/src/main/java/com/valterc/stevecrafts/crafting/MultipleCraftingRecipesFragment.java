package com.valterc.stevecrafts.crafting;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.R;
import com.vcutils.utils.ScalingUtils;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Valter on 19/07/2015.
 */
public class MultipleCraftingRecipesFragment extends Fragment {

    private static final String ARGUMENT_CRAFTING_RECIPES_ID = "crafting_recipes_ids";

    public static MultipleCraftingRecipesFragment newInstance(String[] ids) {
        MultipleCraftingRecipesFragment fragment = new MultipleCraftingRecipesFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARGUMENT_CRAFTING_RECIPES_ID, ids);
        fragment.setArguments(args);
        return fragment;
    }

    private String[] ids;

    public MultipleCraftingRecipesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            ids = savedInstanceState.getStringArray(ARGUMENT_CRAFTING_RECIPES_ID);
        } else {
            ids = getArguments().getStringArray(ARGUMENT_CRAFTING_RECIPES_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiple_viewpager, container, false);
        ViewPager viewPagerBlockBreaks = (ViewPager) view.findViewById(R.id.viewPager);
        CirclePageIndicator pageIndicator = (CirclePageIndicator) view.findViewById(R.id.circlePageIndicator);

        viewPagerBlockBreaks.getLayoutParams().height = (int) ScalingUtils.convertDpToPixel(280);
        viewPagerBlockBreaks.setLayoutParams(viewPagerBlockBreaks.getLayoutParams());

        viewPagerBlockBreaks.setAdapter(new CraftingRecipesFragmentAdapter(getChildFragmentManager(), ids));
        pageIndicator.setViewPager(viewPagerBlockBreaks);

        pageIndicator.setFillColor(Color.GRAY);
        pageIndicator.setStrokeColor(Color.GRAY);
        pageIndicator.setStrokeWidth(2);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArray(ARGUMENT_CRAFTING_RECIPES_ID, ids);
        super.onSaveInstanceState(outState);
    }

}