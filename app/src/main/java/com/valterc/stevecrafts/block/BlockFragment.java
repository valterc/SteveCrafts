package com.valterc.stevecrafts.block;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.Block;
import com.valterc.stevecrafts.data.model.Breaks;
import com.vcutils.views.PixelImageView;

import java.util.ArrayList;

/**
 * Created by Valter on 05/07/2015.
 */
public class BlockFragment extends Fragment {

    private static final String ARGUMENT_BLOCK_ID = "block_id";

    public static BlockFragment newInstance(String blockId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_BLOCK_ID, blockId);

        BlockFragment fragment = new BlockFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    private Block block;

    public BlockFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String blockId = "0";

        if (savedInstanceState != null) {
            blockId = savedInstanceState.getString(ARGUMENT_BLOCK_ID);
        } else {
            blockId = getArguments().getString(ARGUMENT_BLOCK_ID);
        }

        this.block = SteveCraftsApp.getDataManager().getBlock(blockId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_block, container, false);

        PixelImageView imageView = (PixelImageView) view.findViewById(R.id.imageViewBlockImage);
        TextView textViewBlockName = (TextView) view.findViewById(R.id.textViewBlockName);
        TextView textViewBlockType = (TextView) view.findViewById(R.id.textViewBlockType);
        TextView textViewBlockCategory = (TextView) view.findViewById(R.id.textViewBlockCategory);
        TextView textViewBlockPhysics = (TextView) view.findViewById(R.id.textViewBlockPhysics);
        TextView textViewBlockTransparent = (TextView) view.findViewById(R.id.textViewBlockTransparent);
        TextView textViewBlockLuminance = (TextView) view.findViewById(R.id.textViewBlockLuminance);
        TextView textViewBlockBlastResistance = (TextView) view.findViewById(R.id.textViewBlockBlastResistance);
        TextView textViewBlockStackable = (TextView) view.findViewById(R.id.textViewBlockStackable);
        TextView textViewBlockFlamable = (TextView) view.findViewById(R.id.textViewBlockFlamable);
        TextView textViewBlockCannotBeMined = (TextView) view.findViewById(R.id.textViewBlockCannotBeMined);
        TextView textViewBlockCanBeMined = (TextView) view.findViewById(R.id.textViewBlockCanBeMined);
        ViewPager viewPagerBlockBreaks = (ViewPager) view.findViewById(R.id.viewPagerBlockBreaks);

        Bitmap blockImage = SteveCraftsApp.getDataManager().getBlockImage(block.getId());
        imageView.setImageBitmap(blockImage);
        textViewBlockName.setText(block.getLocalizedName());
        textViewBlockType.setText(block.getLocalizedType(getActivity()));
        textViewBlockCategory.setText(block.getLocalizedCategory(getActivity()));
        textViewBlockPhysics.setText(block.getLocalizedPhysics(getActivity()));
        textViewBlockTransparent.setText(block.getLocalizedTransparent(getActivity()));
        textViewBlockLuminance.setText(block.getLocalizedLuminance(getActivity()));
        textViewBlockBlastResistance.setText(block.getLocalizedBlastResistance());
        textViewBlockStackable.setText(block.getLocalizedStackable(getActivity()));
        textViewBlockFlamable.setText(block.getLocalizedFlamable(getActivity()));

        ArrayList<Breaks> breaksOfBlock = SteveCraftsApp.getDataManager().getBreaksOfBlock(block.getId());

        if (breaksOfBlock == null || breaksOfBlock.isEmpty()) {
            textViewBlockCannotBeMined.setVisibility(View.VISIBLE);
            textViewBlockCanBeMined.setVisibility(View.GONE);
            viewPagerBlockBreaks.setVisibility(View.GONE);
        } else {

        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_BLOCK_ID, block.getId());
        super.onSaveInstanceState(outState);
    }
}
