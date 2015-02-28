package com.valterc.stevecrafts.data.api;

import com.valterc.stevecrafts.data.model.Block;
import com.valterc.stevecrafts.data.model.Breaks;
import com.valterc.stevecrafts.data.model.Brewing;
import com.valterc.stevecrafts.data.model.CraftingRecipe;
import com.valterc.stevecrafts.data.model.Item;
import com.valterc.stevecrafts.data.model.Potion;
import com.valterc.stevecrafts.data.model.Smelting;

import java.util.ArrayList;

/**
 * Created by Valter on 12/01/2015.
 */
public class SteveCraftsData {

    private ArrayList<Block> blocks;
    private ArrayList<Breaks> breaks;
    private ArrayList<Brewing> brewings;
    private ArrayList<CraftingRecipe> craftingRecipes;
    private ArrayList<Item> items;
    private ArrayList<Potion> potions;
    private ArrayList<Smelting> smeltings;

    public SteveCraftsData() {
    }

    public SteveCraftsData(ArrayList<Block> blocks, ArrayList<Breaks> breaks, ArrayList<Brewing> brewings, ArrayList<CraftingRecipe> craftingRecipes, ArrayList<Item> items, ArrayList<Potion> potions, ArrayList<Smelting> smeltings) {
        this.blocks = blocks;
        this.breaks = breaks;
        this.brewings = brewings;
        this.craftingRecipes = craftingRecipes;
        this.items = items;
        this.potions = potions;
        this.smeltings = smeltings;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Breaks> getBreaks() {
        return breaks;
    }

    public ArrayList<Brewing> getBrewings() {
        return brewings;
    }

    public ArrayList<CraftingRecipe> getCraftingRecipes() {
        return craftingRecipes;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<Smelting> getSmeltings() {
        return smeltings;
    }

}
