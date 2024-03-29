package com.valterc.stevecrafts.item;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.breaks.MultipleBreaksFragment;
import com.valterc.stevecrafts.brewing.MultipleBrewingsFragment;
import com.valterc.stevecrafts.crafting.MultipleCraftingRecipesFragment;
import com.valterc.stevecrafts.data.model.Breaks;
import com.valterc.stevecrafts.data.model.Brewing;
import com.valterc.stevecrafts.data.model.CraftingRecipe;
import com.valterc.stevecrafts.data.model.Item;
import com.valterc.stevecrafts.data.model.Smelting;
import com.valterc.stevecrafts.smelting.MultipleSmeltingsFragment;
import com.vcutils.views.RepeatingImageView;

import java.util.ArrayList;

/**
 * Created by Valter on 05/07/2015.
 */
public class ItemFragment extends Fragment {

    private static final String ARGUMENT_ITEM_ID = "item_id";

    public static ItemFragment newInstance(String itemId) {
        ItemFragment fragment = new ItemFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_ITEM_ID, itemId);
        fragment.setArguments(arguments);
        return fragment;
    }

    private Item item;

    public ItemFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String itemId = "1";

        if (savedInstanceState != null) {
            itemId = savedInstanceState.getString(ARGUMENT_ITEM_ID);
        } else {
            itemId = getArguments().getString(ARGUMENT_ITEM_ID);
        }

        this.item = SteveCraftsApp.getDataManager().getItem(itemId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        ImageView imageViewItemImage = (ImageView) view.findViewById(R.id.imageViewItemImage);
        TextView textViewItemName = (TextView) view.findViewById(R.id.textViewItemName);
        TextView textViewItemType = (TextView) view.findViewById(R.id.textViewItemType);
        TextView textViewItemStackable = (TextView) view.findViewById(R.id.textViewItemStackable);
        TextView textViewItemDurability = (TextView) view.findViewById(R.id.textViewItemDurability);
        TextView textViewItemAttackSpeed = (TextView) view.findViewById(R.id.textViewItemAttackSpeed);
        LinearLayout linearLayoutDurability = (LinearLayout) view.findViewById(R.id.linearLayoutDurability);
        LinearLayout linearLayoutAttackDamage = (LinearLayout) view.findViewById(R.id.linearLayoutAttackDamage);
        LinearLayout linearLayoutAttackSpeed = (LinearLayout) view.findViewById(R.id.linearLayoutAttackSpeed);
        LinearLayout linearLayoutDPS = (LinearLayout) view.findViewById(R.id.linearLayoutDPS);
        LinearLayout linearLayoutArmor = (LinearLayout) view.findViewById(R.id.linearLayoutArmor);
        RepeatingImageView repeatImageViewAttackDamage = (RepeatingImageView) view.findViewById(R.id.repeatImageViewAttackDamage);
        RepeatingImageView repeatImageViewDPS = (RepeatingImageView) view.findViewById(R.id.repeatImageViewDPS);
        RepeatingImageView repeatImageViewArmor = (RepeatingImageView) view.findViewById(R.id.repeatImageViewArmor);

        LinearLayout linearLayoutResultOfMine = (LinearLayout) view.findViewById(R.id.linearLayoutResultOfMine);
        FrameLayout frameBreaks = (FrameLayout) view.findViewById(R.id.frameBreaks);

        LinearLayout linearLayoutCanBeCrafted = (LinearLayout) view.findViewById(R.id.linearLayoutCanBeCrafted);
        LinearLayout linearLayoutCanCraft = (LinearLayout) view.findViewById(R.id.linearLayoutCanCraft);

        LinearLayout linearLayoutCanBeSmelted = (LinearLayout) view.findViewById(R.id.linearLayoutCanBeSmelted);
        LinearLayout linearLayoutResultOfSmeltings = (LinearLayout) view.findViewById(R.id.linearLayoutResultOfSmeltings);

        LinearLayout linearLayoutCanBeUsedToBrew = (LinearLayout) view.findViewById(R.id.linearLayoutCanBeUsedToBrew);

        textViewItemName.setText(item.getLocalizedName());
        textViewItemType.setText(item.getLocalizedType(getActivity()));

        Bitmap itemImage = SteveCraftsApp.getDataManager().getItemImage(item.getId());
        imageViewItemImage.setImageBitmap(itemImage);

        if (item.getDurability() == 0){
            linearLayoutDurability.setVisibility(View.GONE);
        } else {
            textViewItemDurability.setText(String.format(getString(R.string.model_x_uses), item.getDurability()));
        }

        if (item.getStackable() == 0) {
            textViewItemStackable.setText(R.string.no);
        } else {
            textViewItemStackable.setText(String.format(getString(R.string.yes_more), item.getStackable()));
        }

        if (item.getArmor() == 0) {
            linearLayoutArmor.setVisibility(View.GONE);
        } else {
            repeatImageViewArmor.setDrawableCount((float) item.getArmor() / 2);
        }

        if (item.getAttackDamage() == 1 && !item.getNameEn().contains("Hoe")) {
            linearLayoutAttackDamage.setVisibility(View.GONE);
            linearLayoutAttackSpeed.setVisibility(View.GONE);
            linearLayoutDPS.setVisibility(View.GONE);
        } else {
            repeatImageViewAttackDamage.setDrawableCount((float)item.getAttackDamage() / 2);
            repeatImageViewDPS.setDrawableCount((float)item.getDPS() / 2);
            textViewItemAttackSpeed.setText(item.getAttackSpeedAsString());
        }

        ArrayList<Breaks> breaks = SteveCraftsApp.getDataManager().getBreaksThatDropItem(item.getId());
        if (breaks == null || breaks.isEmpty()) {
            frameBreaks.setVisibility(View.GONE);
            linearLayoutResultOfMine.setVisibility(View.GONE);
        } else {

            String[] breaksId = new String[breaks.size()];
            for (int i = 0; i < breaks.size(); i++) {
                breaksId[i] = breaks.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameBreaks, MultipleBreaksFragment.newInstance(breaksId)).commit();
        }

        ArrayList<CraftingRecipe> craftingRecipes = SteveCraftsApp.getDataManager().getCraftingRecipesForItem(item.getId());
        if (craftingRecipes == null || craftingRecipes.isEmpty()) {
            linearLayoutCanBeCrafted.setVisibility(View.GONE);
        } else {

            String[] craftingRecipesId = new String[craftingRecipes.size()];
            for (int i = 0; i < craftingRecipes.size(); i++) {
                craftingRecipesId[i] = craftingRecipes.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameCraftingRecipes, MultipleCraftingRecipesFragment.newInstance(craftingRecipesId)).commit();
        }

        ArrayList<CraftingRecipe> crafts = SteveCraftsApp.getDataManager().getCraftingRecipesThatUseItem(item.getId());
        if (crafts == null || crafts.isEmpty()) {
            linearLayoutCanCraft.setVisibility(View.GONE);
        } else {

            String[] craftingRecipesId = new String[crafts.size()];
            for (int i = 0; i < crafts.size(); i++) {
                craftingRecipesId[i] = crafts.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameCrafts, MultipleCraftingRecipesFragment.newInstance(craftingRecipesId)).commit();
        }

        ArrayList<Smelting> smeltings = SteveCraftsApp.getDataManager().getSmeltingsWithItem(item.getId());
        if (smeltings == null || smeltings.isEmpty()) {
            linearLayoutCanBeSmelted.setVisibility(View.GONE);
        } else {

            String[] smeltingId = new String[smeltings.size()];
            for (int i = 0; i < smeltings.size(); i++) {
                smeltingId[i] = smeltings.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameSmeltings, MultipleSmeltingsFragment.newInstance(smeltingId)).commit();
        }


        ArrayList<Smelting> smelts = SteveCraftsApp.getDataManager().getSmeltingsForItem(item.getId());
        if (smelts == null || smelts.isEmpty()) {
            linearLayoutResultOfSmeltings.setVisibility(View.GONE);
        } else {

            String[] smeltingId = new String[smelts.size()];
            for (int i = 0; i < smelts.size(); i++) {
                smeltingId[i] = smelts.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameResultOfSmeltings, MultipleSmeltingsFragment.newInstance(smeltingId)).commit();
        }

        ArrayList<Brewing> brews = SteveCraftsApp.getDataManager().getBrewingsWithIngredient(item.getId());
        if (brews == null || brews.isEmpty()) {
            linearLayoutCanBeUsedToBrew.setVisibility(View.GONE);
        } else {

            String[] brewingId = new String[brews.size()];
            for (int i = 0; i < brews.size(); i++) {
                brewingId[i] = brews.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameBrews, MultipleBrewingsFragment.newInstance(brewingId)).commit();
        }


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_ITEM_ID, item.getId());
        super.onSaveInstanceState(outState);
    }

}
