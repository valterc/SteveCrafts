package com.valterc.stevecrafts.data;

import android.content.Context;

import com.valterc.stevecrafts.data.api.SteveCraftsData;
import com.valterc.stevecrafts.data.storage.DataSource;

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

}
