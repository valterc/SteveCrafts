package com.valterc.stevecrafts.breaks;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.block.BlockFragment;
import com.valterc.stevecrafts.data.model.Breaks;
import com.valterc.stevecrafts.item.ItemFragment;
import com.valterc.stevecrafts.main.IMainFragmentController;
import com.vcutils.views.PixelImageView;

public class BreaksFragment extends Fragment {

    private static final String ARGUMENT_BREAKS_ID = "breaks_id";

    public static BreaksFragment newInstance(String id) {
        BreaksFragment fragment = new BreaksFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_BREAKS_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    private IMainFragmentController mainFragmentController;
    private Breaks breaks;

    public BreaksFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String breaksId = "0";

        if (savedInstanceState != null) {
            breaksId = savedInstanceState.getString(ARGUMENT_BREAKS_ID);
        } else {
            breaksId = getArguments().getString(ARGUMENT_BREAKS_ID);
        }

        this.breaks = SteveCraftsApp.getDataManager().getBreaks(breaksId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breaks, container, false);
        PixelImageView imageViewTool = (PixelImageView) view.findViewById(R.id.imageViewBreaksTool);
        PixelImageView imageViewBlock = (PixelImageView) view.findViewById(R.id.imageViewBreaksBlock);
        PixelImageView imageViewDrop = (PixelImageView) view.findViewById(R.id.imageViewBreaksDrop);
        TextView textViewSilktouch = (TextView) view.findViewById(R.id.textViewSilktouch);
        TextView textViewAnytool = (TextView) view.findViewById(R.id.textViewAnytool);
        TextView textViewDropCount = (TextView) view.findViewById(R.id.textViewDropCount);

        if (breaks.getAnytool() != 1) {
            textViewAnytool.setVisibility(View.GONE);
        }

        if (breaks.getSilktouch() != 1) {
            textViewSilktouch.setVisibility(View.GONE);
        }

        imageViewTool.setImageBitmap(SteveCraftsApp.getDataManager().getItemImage(breaks.getItemId()));
        imageViewBlock.setImageBitmap(SteveCraftsApp.getDataManager().getBlockImage(breaks.getBlockId()));

        if (breaks.getDropType() == 1) {
            imageViewDrop.setImageBitmap(SteveCraftsApp.getDataManager().getItemImage(breaks.getDropId()));
        } else {
            imageViewDrop.setImageBitmap(SteveCraftsApp.getDataManager().getBlockImage(breaks.getDropId()));
        }

        if (breaks.getDropCountMax() != 0) {
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/minecraftia.ttf");
            textViewDropCount.setTypeface(tf);
            textViewDropCount.setText(breaks.getDropCountMin() + "-" + breaks.getDropCountMax());
        } else if (breaks.getDropCount() > 0) {
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/minecraftia.ttf");
            textViewDropCount.setTypeface(tf);
            textViewDropCount.setText(breaks.getDropCount() + "");
        }

        imageViewTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFragmentController.openFragment(ItemFragment.newInstance(breaks.getItemId()));
            }
        });

        imageViewBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFragmentController.openFragment(BlockFragment.newInstance(breaks.getBlockId()));
            }
        });

        imageViewDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (breaks.getDropType() == 1) {
                    mainFragmentController.openFragment(ItemFragment.newInstance(breaks.getDropId()));
                } else {
                    mainFragmentController.openFragment(BlockFragment.newInstance(breaks.getDropId()));
                }
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_BREAKS_ID, breaks.getId());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IMainFragmentController) {
            mainFragmentController = (IMainFragmentController) activity;
        }
    }
}
