package com.valterc.stevecrafts.block;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.breaks.MultipleBreaksFragment;
import com.valterc.stevecrafts.crafting.MultipleCraftingRecipesFragment;
import com.valterc.stevecrafts.data.model.Block;
import com.valterc.stevecrafts.data.model.Breaks;
import com.valterc.stevecrafts.data.model.CraftingRecipe;
import com.valterc.stevecrafts.data.model.Smelting;
import com.valterc.stevecrafts.smelting.MultipleSmeltingsFragment;
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

        String blockId = "2";

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

        LinearLayout linearLayoutMined = (LinearLayout) view.findViewById(R.id.linearLayoutMined);

        LinearLayout linearLayoutBlockCannotBeCrafted = (LinearLayout) view.findViewById(R.id.linearLayoutBlockCannotBeCrafted);
        LinearLayout linearLayoutBlockCannotCraft = (LinearLayout) view.findViewById(R.id.linearLayoutBlockCannotCraft);

        LinearLayout linearLayoutBlockCannotBeSmelted = (LinearLayout) view.findViewById(R.id.linearLayoutBlockCannotBeSmelted);
        LinearLayout linearLayoutBlockCannotBeResultOfSmeltings = (LinearLayout) view.findViewById(R.id.linearLayoutBlockCannotBeResultOfSmeltings);

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
            linearLayoutMined.setVisibility(View.GONE);
        } else {

            String[] breaksIds = new String[breaksOfBlock.size()];
            for (int i = 0; i < breaksOfBlock.size(); i++) {
                breaksIds[i] = breaksOfBlock.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameBreaks, MultipleBreaksFragment.newInstance(breaksIds)).commit();
        }

        ArrayList<CraftingRecipe> craftingRecipes = SteveCraftsApp.getDataManager().getCraftingRecipesForBlock(block.getId());
        if (craftingRecipes == null || craftingRecipes.isEmpty()) {
            linearLayoutBlockCannotBeCrafted.setVisibility(View.GONE);
        } else {

            String[] craftingRecipesId = new String[craftingRecipes.size()];
            for (int i = 0; i < craftingRecipes.size(); i++) {
                craftingRecipesId[i] = craftingRecipes.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameCraftingRecipes, MultipleCraftingRecipesFragment.newInstance(craftingRecipesId)).commit();
        }

        ArrayList<CraftingRecipe> crafts = SteveCraftsApp.getDataManager().getCraftingRecipesThatUseBlock(block.getId());
        if (crafts == null || crafts.isEmpty()) {
            linearLayoutBlockCannotCraft.setVisibility(View.GONE);
        } else {

            String[] craftingRecipesId = new String[crafts.size()];
            for (int i = 0; i < crafts.size(); i++) {
                craftingRecipesId[i] = crafts.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameCrafts, MultipleCraftingRecipesFragment.newInstance(craftingRecipesId)).commit();
        }

        ArrayList<Smelting> smelts = SteveCraftsApp.getDataManager().getSmeltingsWithBlock(block.getId());
        if (smelts == null || smelts.isEmpty()) {
            linearLayoutBlockCannotBeSmelted.setVisibility(View.GONE);
        } else {

            String[] smeltingsId = new String[smelts.size()];
            for (int i = 0; i < smelts.size(); i++) {
                smeltingsId[i] = smelts.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameSmeltings, MultipleSmeltingsFragment.newInstance(smeltingsId)).commit();
        }

        ArrayList<Smelting> smeltsFor = SteveCraftsApp.getDataManager().getSmeltingsForBlock(block.getId());
        if (smeltsFor == null || smeltsFor.isEmpty()) {
            linearLayoutBlockCannotBeResultOfSmeltings.setVisibility(View.GONE);
        } else {

            String[] smeltingsId = new String[smeltsFor.size()];
            for (int i = 0; i < smeltsFor.size(); i++) {
                smeltingsId[i] = smeltsFor.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameResultOfSmeltings, MultipleSmeltingsFragment.newInstance(smeltingsId)).commit();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_BLOCK_ID, block.getId());
        super.onSaveInstanceState(outState);
    }
}
