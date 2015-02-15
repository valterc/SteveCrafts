package com.valterc.stevecrafts.data.api;

import com.valterc.stevecrafts.data.model.Block;
import com.valterc.stevecrafts.data.model.Breaks;
import com.valterc.stevecrafts.data.model.Brewing;
import com.valterc.stevecrafts.data.model.CraftingRecipe;
import com.valterc.stevecrafts.data.model.Item;
import com.valterc.stevecrafts.data.model.Potion;
import com.valterc.stevecrafts.data.model.Smelting;

import java.util.List;

/**
 * Created by Valter on 12/01/2015.
 */
public class SteveCraftsData {

    private List<Block> blocks;
    private List<Breaks> breaks;
    private List<Brewing> brewings;
    private List<CraftingRecipe> craftingRecipes;
    private List<Item> items;
    private List<Potion> potions;
    private List<Smelting> smeltings;

    public SteveCraftsData(){
    }

    public SteveCraftsData(List<Block> blocks, List<Breaks> breaks, List<Brewing> brewings, List<CraftingRecipe> craftingRecipes, List<Item> items, List<Potion> potions, List<Smelting> smeltings) {
        this.blocks = blocks;
        this.breaks = breaks;
        this.brewings = brewings;
        this.craftingRecipes = craftingRecipes;
        this.items = items;
        this.potions = potions;
        this.smeltings = smeltings;
    }
}
