package com.valterc.stevecrafts.crafting;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.block.BlockFragment;
import com.valterc.stevecrafts.data.model.CraftingRecipe;
import com.valterc.stevecrafts.item.ItemFragment;
import com.valterc.stevecrafts.main.IMainFragmentController;
import com.vcutils.views.PixelImageView;

/**
 * Created by Valter on 19/07/2015.
 */
public class CraftingRecipeFragment extends Fragment {

    private static final String ARGUMENT_CRAFTING_RECIPE_ID = "crafting_recipe_id";

    public static CraftingRecipeFragment newInstance(String id) {
        CraftingRecipeFragment fragment = new CraftingRecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_CRAFTING_RECIPE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    private IMainFragmentController mainFragmentController;
    private CraftingRecipe craftingRecipe;

    public CraftingRecipeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String craftingRecipeId = "0";

        if (savedInstanceState != null) {
            craftingRecipeId = savedInstanceState.getString(ARGUMENT_CRAFTING_RECIPE_ID);
        } else {
            craftingRecipeId = getArguments().getString(ARGUMENT_CRAFTING_RECIPE_ID);
        }

        this.craftingRecipe = SteveCraftsApp.getDataManager().getCraftingRecipe(craftingRecipeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crafting_recipe, container, false);

        PixelImageView[] imageViewSlots = new PixelImageView[9];
        imageViewSlots[0] = (PixelImageView) view.findViewById(R.id.imageViewCraftingRecipeSlot0);
        imageViewSlots[1] = (PixelImageView) view.findViewById(R.id.imageViewCraftingRecipeSlot1);
        imageViewSlots[2] = (PixelImageView) view.findViewById(R.id.imageViewCraftingRecipeSlot2);
        imageViewSlots[3] = (PixelImageView) view.findViewById(R.id.imageViewCraftingRecipeSlot3);
        imageViewSlots[4] = (PixelImageView) view.findViewById(R.id.imageViewCraftingRecipeSlot4);
        imageViewSlots[5] = (PixelImageView) view.findViewById(R.id.imageViewCraftingRecipeSlot5);
        imageViewSlots[6] = (PixelImageView) view.findViewById(R.id.imageViewCraftingRecipeSlot6);
        imageViewSlots[7] = (PixelImageView) view.findViewById(R.id.imageViewCraftingRecipeSlot7);
        imageViewSlots[8] = (PixelImageView) view.findViewById(R.id.imageViewCraftingRecipeSlot8);
        PixelImageView imageViewResult = (PixelImageView) view.findViewById(R.id.imageViewResult);

        TextView[] textViewSlotsCount = new TextView[9];
        textViewSlotsCount[0] = (TextView) view.findViewById(R.id.textViewCraftingRecipeSlot0Count);
        textViewSlotsCount[1] = (TextView) view.findViewById(R.id.textViewCraftingRecipeSlot1Count);
        textViewSlotsCount[2] = (TextView) view.findViewById(R.id.textViewCraftingRecipeSlot2Count);
        textViewSlotsCount[3] = (TextView) view.findViewById(R.id.textViewCraftingRecipeSlot3Count);
        textViewSlotsCount[4] = (TextView) view.findViewById(R.id.textViewCraftingRecipeSlot4Count);
        textViewSlotsCount[5] = (TextView) view.findViewById(R.id.textViewCraftingRecipeSlot5Count);
        textViewSlotsCount[6] = (TextView) view.findViewById(R.id.textViewCraftingRecipeSlot6Count);
        textViewSlotsCount[7] = (TextView) view.findViewById(R.id.textViewCraftingRecipeSlot7Count);
        textViewSlotsCount[8] = (TextView) view.findViewById(R.id.textViewCraftingRecipeSlot8Count);
        TextView textViewResult = (TextView) view.findViewById(R.id.textViewResultCount);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/minecraftia.ttf");
        textViewResult.setTypeface(tf);
        for (int i = 0; i < textViewSlotsCount.length; i++) {
            textViewSlotsCount[i].setTypeface(tf);
        }

        for (int i = 0; i < craftingRecipe.getSlots().length; i++) {
            CraftingRecipe.Slot slot = craftingRecipe.getSlots()[i];
            if (slot != null) {
                Bitmap bitmap;
                if (slot.getType() == 0) {
                    bitmap = SteveCraftsApp.getDataManager().getBlockImage(slot.getId());
                } else {
                    bitmap = SteveCraftsApp.getDataManager().getItemImage(slot.getId());
                }

                imageViewSlots[i].setImageBitmap(bitmap);
                textViewSlotsCount[i].setText(slot.getCount() + "");
                imageViewSlots[i].setTag(i);
            } else {
                imageViewSlots[i].setImageBitmap(null);
            }
        }

        if (craftingRecipe.getType() == 0) {
            imageViewResult.setImageBitmap(SteveCraftsApp.getDataManager().getBlockImage(craftingRecipe.getCraftId()));
        } else {
            imageViewResult.setImageBitmap(SteveCraftsApp.getDataManager().getItemImage(craftingRecipe.getCraftId()));
        }

        textViewResult.setText(craftingRecipe.getCount() + "");


        imageViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (craftingRecipe.getType() == 1) {
                    mainFragmentController.openFragment(ItemFragment.newInstance(craftingRecipe.getCraftId()));
                } else {
                    mainFragmentController.openFragment(BlockFragment.newInstance(craftingRecipe.getCraftId()));
                }
            }
        });

        View.OnClickListener slotClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer index = (Integer) v.getTag();
                if (index != null) {
                    if (craftingRecipe.getSlots()[index].getType() == 1) {
                        mainFragmentController.openFragment(ItemFragment.newInstance(craftingRecipe.getSlots()[index].getId()));
                    } else {
                        mainFragmentController.openFragment(BlockFragment.newInstance(craftingRecipe.getSlots()[index].getId()));
                    }
                }
            }
        };

        for (int i = 0; i < imageViewSlots.length; i++) {
            imageViewSlots[i].setOnClickListener(slotClickListener);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_CRAFTING_RECIPE_ID, craftingRecipe.getId());
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