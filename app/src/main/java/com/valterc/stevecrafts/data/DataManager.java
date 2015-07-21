package com.valterc.stevecrafts.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.valterc.stevecrafts.data.api.SteveCraftsData;
import com.valterc.stevecrafts.data.model.Block;
import com.valterc.stevecrafts.data.model.Breaks;
import com.valterc.stevecrafts.data.model.Brewing;
import com.valterc.stevecrafts.data.model.CraftingRecipe;
import com.valterc.stevecrafts.data.model.GenericItem;
import com.valterc.stevecrafts.data.model.Item;
import com.valterc.stevecrafts.data.model.Potion;
import com.valterc.stevecrafts.data.model.Smelting;
import com.valterc.stevecrafts.data.storage.DataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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

    public void backupDatabase() {
        dataSource.backupDatabase();
    }

    public ArrayList<GenericItem> getMostRecentItems() {
        ArrayList<Block> blocks = dataSource.getMostRecentBlocks();
        ArrayList<Item> items = dataSource.getMostRecentItems();
        ArrayList<Potion> potions = dataSource.getMostRecentPotions();

        ArrayList<GenericItem> genericItems = new ArrayList<>();

        for (Block block : blocks) {
            genericItems.add(new GenericItem(block));
        }

        for (Item item : items) {
            genericItems.add(new GenericItem(item));
        }

        for (Potion potion : potions) {
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

    public GenericItem getRandomItem() {

        int seed = Calendar.getInstance().get(Calendar.YEAR) + Calendar.getInstance().get(Calendar.DAY_OF_YEAR) + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) * 356;
        Random random = new Random(seed);

        GenericItem genericItem = null;

        do {
            switch (random.nextInt(3)) {
                case 0:
                    Item item = dataSource.getItem(Integer.toString(random.nextInt(dataSource.getLastItemId())));
                    if (item != null) {
                        genericItem = new GenericItem(item);
                    }
                    break;
                case 1:
                    Block block = dataSource.getBlock(Integer.toString(random.nextInt(dataSource.getLastBlockId())));
                    if (block != null) {
                        genericItem = new GenericItem(block);
                    }
                    break;
                case 2:
                    Potion potion = dataSource.getPotion(Integer.toString(random.nextInt(dataSource.getLastPotionId())));
                    if (potion != null) {
                        genericItem = new GenericItem(potion);
                    }
                    break;
            }
        } while (genericItem == null);

        return genericItem;
    }

    public ArrayList<GenericItem> search(String query) {

        final String processedQuery = query.toLowerCase();

        ArrayList<Block> blocks = dataSource.searchBlock(processedQuery);
        ArrayList<Item> items = dataSource.searchItem(processedQuery);
        ArrayList<Potion> potions = dataSource.searchPotion(processedQuery);

        ArrayList<GenericItem> results = new ArrayList<>();

        for (Block block : blocks) {
            results.add(new GenericItem(block));
        }

        for (Item item : items) {
            results.add(new GenericItem(item));
        }

        for (Potion potion : potions) {
            results.add(new GenericItem(potion));
        }

        Collections.sort(results, new Comparator<GenericItem>() {
            @Override
            public int compare(GenericItem lhs /*-1*/, GenericItem rhs /*1*/) {

                String lhsLocalizedName = lhs.getLocalizedName().toLowerCase();
                String rhsLocalizedName = rhs.getLocalizedName().toLowerCase();

                String lhsName = lhs.getName().toLowerCase();
                String rhsName = rhs.getName().toLowerCase();

                if (lhsLocalizedName.equals(processedQuery) && !rhsLocalizedName.equals(processedQuery)) {
                    return -1;
                } else if (!lhsLocalizedName.equals(processedQuery) && rhsLocalizedName.equals(processedQuery)) {
                    return 1;
                }

                if (lhsName.equals(processedQuery) && !rhsName.equals(processedQuery)) {
                    return -1;
                } else if (!lhsName.equals(processedQuery) && rhsName.equals(processedQuery)) {
                    return 1;
                }

                if (lhsLocalizedName.startsWith(processedQuery) && !rhsLocalizedName.startsWith(processedQuery)) {
                    return -1;
                } else if (!lhsLocalizedName.startsWith(processedQuery) && rhsLocalizedName.startsWith(processedQuery)) {
                    return 1;
                }

                if (lhsLocalizedName.contains(processedQuery) && !rhsLocalizedName.contains(processedQuery)) {
                    return -1;
                } else if (!lhsLocalizedName.contains(processedQuery) && rhsLocalizedName.contains(processedQuery)) {
                    return 1;
                }

                if (lhsName.startsWith(processedQuery) && !rhsName.startsWith(processedQuery)) {
                    return -1;
                } else if (!lhsName.startsWith(processedQuery) && rhsName.startsWith(processedQuery)) {
                    return 1;
                }

                return 0;
            }
        });

        return results;
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

    public ArrayList<Breaks> getBreaksOfBlock(String id){
        return dataSource.getBreaksDoneOfBlock(id);
    }

    public CraftingRecipe getCraftingRecipe(String craftingRecipeId) {
        return dataSource.getCraftingRecipe(craftingRecipeId);
    }

    public Breaks getBreaks(String breaksId) {
        return dataSource.getBreaks(breaksId);
    }

    public Brewing getBrewing(String brewingId) {
        return dataSource.getBrewing(brewingId);
    }

    public Smelting getSmelting(String smeltingId) {
        return dataSource.getSmelting(smeltingId);
    }

    public ArrayList<CraftingRecipe> getCraftingRecipesForBlock(String blockId) {
        return dataSource.getCraftingRecipesForBlock(blockId);
    }

    public ArrayList<CraftingRecipe> getCraftingRecipesThatUseBlock(String blockId) {
        return dataSource.getCraftingRecipesThatUseBlock(blockId);
    }

    public ArrayList<CraftingRecipe> getCraftingRecipesForItem(String itemId) {
        return dataSource.getCraftingRecipesForItem(itemId);
    }

    public ArrayList<CraftingRecipe> getCraftingRecipesThatUseItem(String itemId) {
        return dataSource.getCraftingRecipesThatUseItem(itemId);
    }

    public ArrayList<Smelting> getSmeltingsForBlock(String blockId) {
        return dataSource.getSmeltingsForBlock(blockId);
    }

    public ArrayList<Smelting> getSmeltingsForItem(String itemId) {
        return dataSource.getSmeltingsForItem(itemId);
    }

    public ArrayList<Smelting> getSmeltingsWithBlock(String blockId) {
        return dataSource.getSmeltingsWithBlock(blockId);
    }

    public ArrayList<Smelting> getSmeltingsWithItem(String itemId) {
        return dataSource.getSmeltingsWithItem(itemId);
    }

}
