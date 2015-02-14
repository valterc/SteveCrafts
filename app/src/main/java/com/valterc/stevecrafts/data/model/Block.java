package com.valterc.stevecrafts.data.model;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Valter on 08/01/2015.
 */
public class Block {

    private String id;
    private int minecraftBlockId;
    private int minecraftDataValue;
    private String minecraftId;

    /**
     * @see com.valterc.stevecrafts.data.model.Block.Type
     */
    private int type;

    /**
     * @see com.valterc.stevecrafts.data.model.Block.Category
     */
    private int category;

    /**
     * 0 - No; 1 - Yes
     */
    private int physics;

    /**
     * 0 - No; 1 - Yes; 2 - Partial
     */
    private int transparency;

    /**
     * 0 - No; Other - Value
     */
    private int luminance;

    /**
     * 0 - No; Other - Value
     */
    private double blastResistance;

    /**
     * 0 - No; Other - Value
     */
    private int stackable;

    /**
     * 0 - No; 1 - Yes
     */
    private int flamable;
    private Bitmap image;
    private String name_en;
    private String name_pt;
    private String name_de;
    private String name_es;
    private String name_fr;
    private String name_pl;
    private long timestamp;

    public Block(){

    }

    public Block(JSONObject json) throws JSONException {
        this.id = json.getString("id");
        this.minecraftBlockId = json.getInt("minecraft_blockid");
        this.minecraftDataValue = json.getInt("minecraft_datavalue");
        this.minecraftId = json.getString("minecraft_id");
        this.type = json.getInt("type");
        this.category = json.getInt("category");
        this.physics = json.getInt("physics");
        this.transparency = json.getInt("transparency");
        this.luminance = json.getInt("luminance");
        this.blastResistance = json.getInt("blast_resistance");
        this.stackable = json.getInt("stackable");
        this.flamable = json.getInt("flamable");
        this.image = null; //TODO: Load bitmap
        this.name_en = json.getString("name_en");
        this.name_pt = json.getString("name_pt");
        this.name_de = json.getString("name_de");
        this.name_es = json.getString("name_es");
        this.name_fr = json.getString("name_fr");
        this.name_pl = json.getString("name_pl");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+1"));

        try {
            this.timestamp = dateFormat.parse(json.getString("timestamp")).getTime();
        } catch (ParseException e) {
            this.timestamp = Calendar.getInstance().getTimeInMillis();
        }
    }


    /**
     *
     * Internal types
     *
     */

    public static class Type {

        private Type(){}

        public static final int Solid = 0;
        public static final int NonSolid = 1;
        public static final int TileEntity = 2;
        public static final int Food = 3;
        public static final int Tool = 4;
        public static final int Plant = 5;
        public static final int Fluid = 6;

    }

    public static class Category {

        private Category(){}

        public static final int Natural = 0;
        public static final int Manufactured = 1;
        public static final int Ore = 2;
        public static final int Mineral = 3;
        public static final int Utility = 4;
        public static final int Mechanism = 5;
        public static final int Plant = 6;
        public static final int Liquid = 7;
        public static final int Nether = 8;
        public static final int End = 9;
        public static final int CreativeOnly = 10;
        public static final int PocketEdition = 11;
        public static final int ConsoleEditionOnly = 12;
        public static final int Technical = 13;
        public static final int PocketEditionOnly = 14;
        public static final int Upcoming = 15;
        public static final int Removed = 16;
        public static final int Unimplemented = 17;

    }

}
