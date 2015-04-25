package com.valterc.stevecrafts.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.valterc.stevecrafts.data.api.SteveCraftsData;
import com.valterc.stevecrafts.data.model.Block;
import com.valterc.stevecrafts.data.model.GenericItem;
import com.valterc.stevecrafts.data.model.Item;
import com.valterc.stevecrafts.data.model.Potion;
import com.valterc.stevecrafts.data.storage.DataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Valter on 08/01/2015.
 */
public class DataManager {

    private DataSource dataSource;

    public DataManager(Context context) {
        dataSource = new DataSource(context);
    }

    public void dispose() {
        dataSource.dispose();
    }

    public void saveData(SteveCraftsData data) {

        if (data.getBlocks() != null) {
            dataSource.insertBlocks(data.getBlocks());
        }

        if (data.getBreaks() != null) {
            dataSource.insertBreaks(data.getBreaks());
        }

        if (data.getBrewings() != null) {
            dataSource.insertBrewings(data.getBrewings());
        }

        if (data.getCraftingRecipes() != null) {
            dataSource.insertCraftingRecipes(data.getCraftingRecipes());
        }

        if (data.getItems() != null) {
            dataSource.insertItems(data.getItems());
        }

        if (data.getPotions() != null) {
            dataSource.insertPotions(data.getPotions());
        }

        if (data.getSmeltings() != null) {
            dataSource.insertSmeltings(data.getSmeltings());
        }

    }

    public ArrayList<GenericItem> getMostRecentItems() {
        ArrayList<Block> blocks = dataSource.getMostRecentBlocks();
        ArrayList<Item> items = dataSource.getMostRecentItems();
        ArrayList<Potion> potions = dataSource.getMostRecentPotions();

        ArrayList<GenericItem> genericItems = new ArrayList<>();

        for (Block block : blocks){
            genericItems.add(new GenericItem(block));
        }

        for (Item item : items){
            genericItems.add(new GenericItem(item));
        }

        for (Potion potion : potions){
            genericItems.add(new GenericItem(potion));
        }

        Collections.sort(genericItems, new Comparator<GenericItem>() {
            @Override
            public int compare(GenericItem lhs, GenericItem rhs) {
                return (int) (rhs.getTimestamp() - lhs.getTimestamp());
            }
        });

        return new ArrayList<>(genericItems.subList(0, 5 > genericItems.size() ? genericItems.size() : 5));
    }

    public GenericItem searchForItem(String query) {
        return null;
    }


    public Item getItem(String id) {
        return dataSource.getItem(id);
    }

    public Block getBlock(String id) {
        return dataSource.getBlock(id);
    }

    public Potion getPotion(String id) {
        return dataSource.getPotion(id);
    }


    public ArrayList<Item> getItems() {
        return dataSource.getItems();
    }

    public ArrayList<Block> getBlocks() {
        return dataSource.getBlocks();
    }

    public ArrayList<Potion> getPotions() {
        return dataSource.getPotions();
    }


    public Bitmap getGenericItemImage(GenericItem item) {
        switch (item.getType()) {
            case GenericItem.TYPE_BLOCK:
                return getBlockImage(item.getId());
            case GenericItem.TYPE_ITEM:
                return getItemImage(item.getId());
            case GenericItem.TYPE_POTION:
                return getPotionImage(item.getId());
        }
        return null;
    }

    public Bitmap getItemImage(String id) {
        return dataSource.getItemImage(id);
    }

    public Bitmap getBlockImage(String id) {
        return dataSource.getBlockImage(id);
    }

    public Bitmap getPotionImage(String id) {
        return dataSource.getPotionImage(id);
    }

}
