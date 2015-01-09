package com.valterc.stevecrafts.data.model;

import android.graphics.Bitmap;

/**
 * Created by Valter on 08/01/2015.
 */
public class Item {

    private String id;
    private String minecraftId;
    private int minecraftDataValue;
    private int durability;

    /**
     * 0 - No; Other - Value
     */
    private int stackable;
    private int damage;
    private int armor;

    /**
     * @see com.valterc.stevecrafts.data.model.Item.Type
     */
    private int type;
    private Bitmap image;
    private String name_en;
    private String name_pt;
    private String name_de;
    private String name_es;
    private String name_fr;
    private String name_pl;
    private long timestamp;

    public static class Type{
        private Type(){}

        public static final int RawMaterial = 0;
        public static final int Manufactured = 1;
        public static final int Food = 2;
        public static final int Plant = 3;
        public static final int Dye = 4;
        public static final int Tool = 5;
        public static final int Informative = 6;
        public static final int Weapon = 7;
        public static final int Armor = 8;
        public static final int Vehicle = 9;
        public static final int Utility = 10;
        public static final int Decoration = 11;
        public static final int CreativeOnly = 12;
        public static final int PocketEditionOnly = 13;
        public static final int Unused = 14;
        public static final int Unimplemented = 15;

    }

}
