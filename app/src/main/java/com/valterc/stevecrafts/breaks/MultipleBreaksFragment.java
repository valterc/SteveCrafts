package com.valterc.stevecrafts.breaks;

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
 * Created by Valter on 18/07/2015.
 */
public class MultipleBreaksFragment extends Fragment {

    private static final String ARGUMENT_BREAKS_ID = "breaks_ids";

    public static MultipleBreaksFragment newInstance(String[] ids) {
        MultipleBreaksFragment fragment = new MultipleBreaksFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARGUMENT_BREAKS_ID, ids);
        fragment.setArguments(args);
        return fragment;
    }

    private String[] ids;

    public MultipleBreaksFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            ids = savedInstanceState.getStringArray(ARGUMENT_BREAKS_ID);
        } else {
            ids = getArguments().getStringArray(ARGUMENT_BREAKS_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiple_viewpager, container, false);
        ViewPager viewPagerBlockBreaks = (ViewPager) view.findViewById(R.id.viewPager);
        CirclePageIndicator pageIndicator = (CirclePageIndicator) view.findViewById(R.id.circlePageIndicator);

        viewPagerBlockBreaks.getLayoutParams().height = (int)ScalingUtils.convertDpToPixel(210);
        viewPagerBlockBreaks.setLayoutParams(viewPagerBlockBreaks.getLayoutParams());

        viewPagerBlockBreaks.setAdapter(new BreaksFragmentAdapter(getChildFragmentManager(), ids));
        pageIndicator.setViewPager(viewPagerBlockBreaks);

        pageIndicator.setFillColor(Color.GRAY);
        pageIndicator.setStrokeColor(Color.GRAY);
        pageIndicator.setStrokeWidth(2);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArray(ARGUMENT_BREAKS_ID, ids);
        super.onSaveInstanceState(outState);
    }

}