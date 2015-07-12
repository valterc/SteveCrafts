package com.valterc.stevecrafts.data.model;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.data.api.SteveCraftsApi;
import com.vcutils.utils.ImageUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;

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
    private int blastResistance;

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

    public Block() {

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
        this.image = ImageUtils.fromBase64String(json.getString("image"));
        this.name_en = json.getString("name_en");
        this.name_pt = json.getString("name_pt");
        this.name_de = json.getString("name_de");
        this.name_es = json.getString("name_es");
        this.name_fr = json.getString("name_fr");
        this.name_pl = json.getString("name_pl");

        try {
            this.timestamp = SteveCraftsApi.getDateFormat().parse(json.getString("timestamp")).getTime();
        } catch (ParseException e) {
            this.timestamp = Calendar.getInstance().getTimeInMillis();
        }
    }

    public Block(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.minecraftBlockId = cursor.getInt(cursor.getColumnIndex("minecraft_blockid"));
        this.minecraftDataValue = cursor.getInt(cursor.getColumnIndex("minecraft_datavalue"));
        this.minecraftId = cursor.getString(cursor.getColumnIndex("minecraft_id"));
        this.type = cursor.getInt(cursor.getColumnIndex("type"));
        this.category = cursor.getInt(cursor.getColumnIndex("category"));
        this.physics = cursor.getInt(cursor.getColumnIndex("physics"));
        this.transparency = cursor.getInt(cursor.getColumnIndex("transparency"));
        this.luminance = cursor.getInt(cursor.getColumnIndex("luminance"));
        this.blastResistance = cursor.getInt(cursor.getColumnIndex("blast_resistance"));
        this.stackable = cursor.getInt(cursor.getColumnIndex("stackable"));
        this.flamable = cursor.getInt(cursor.getColumnIndex("flamable"));
        if (cursor.getColumnIndex("image") != -1) {
            this.image = ImageUtils.byteArrayToBitmap(cursor.getBlob(cursor.getColumnIndex("image")));
        }
        this.name_en = cursor.getString(cursor.getColumnIndex("name_en"));
        this.name_pt = cursor.getString(cursor.getColumnIndex("name_pt"));
        this.name_de = cursor.getString(cursor.getColumnIndex("name_de"));
        this.name_es = cursor.getString(cursor.getColumnIndex("name_es"));
        this.name_fr = cursor.getString(cursor.getColumnIndex("name_fr"));
        this.name_pl = cursor.getString(cursor.getColumnIndex("name_pl"));
        this.timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
    }


    public String getId() {
        return id;
    }

    public int getMinecraftBlockId() {
        return minecraftBlockId;
    }

    public int getMinecraftDataValue() {
        return minecraftDataValue;
    }

    public String getMinecraftId() {
        return minecraftId;
    }

    public int getType() {
        return type;
    }

    public int getCategory() {
        return category;
    }

    public int getPhysics() {
        return physics;
    }

    public int getTransparency() {
        return transparency;
    }

    public int getLuminance() {
        return luminance;
    }

    public int getBlastResistance() {
        return blastResistance;
    }

    public int getStackable() {
        return stackable;
    }

    public int getFlamable() {
        return flamable;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getNameEn() {
        return name_en;
    }

    public String getNamePt() {
        return (name_pt == null || name_pt.isEmpty()) ? name_en : name_pt;
    }

    public String getNameDe() {
        return (name_de == null || name_de.isEmpty()) ? name_en : name_de;
    }

    public String getNameEs() {
        return (name_es == null || name_es.isEmpty()) ? name_en : name_es;
    }

    public String getNameFr() {
        return (name_fr == null || name_fr.isEmpty()) ? name_en : name_fr;
    }

    public String getNamePl() {
        return (name_pl == null || name_pl.isEmpty()) ? name_en : name_pl;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getLocalizedName() {
        switch (Locale.getDefault().getISO3Language()) {
            case "eng":
                return getNameEn();
            case "por":
                return getNamePt();
            case "deu":
            case "ger":
                return getNameDe();
            case "spa":
                return getNameEs();
            case "fra":
            case "fre":
                return getNameFr();
            case "pol":
                return getNamePl();
        }
        return getNameEn();
    }

    public String getLocalizedType(Context c) {

        switch (getType()) {
            case Type.Solid:
                return c.getResources().getString(R.string.model_block_type_solid);
            case Type.NonSolid:
                return c.getResources().getString(R.string.model_block_type_non_solid);
            case Type.TileEntity:
                return c.getResources().getString(R.string.model_block_type_tile_entity);
            case Type.Food:
                return c.getResources().getString(R.string.model_block_type_food);
            case Type.Tool:
                return c.getResources().getString(R.string.model_block_type_tool);
            case Type.Plant:
                return c.getResources().getString(R.string.model_block_type_plant);
            case Type.Fluid:
                return c.getResources().getString(R.string.model_block_type_fluid);
        }

        return null;
    }

    public String getLocalizedCategory(Context c) {

        switch (getCategory()) {
            case Category.Natural:
                return c.getResources().getString(R.string.model_block_category_natural);
            case Category.Manufactured:
                return c.getResources().getString(R.string.model_block_category_manufactured);
            case Category.Ore:
                return c.getResources().getString(R.string.model_block_category_ore);
            case Category.Mineral:
                return c.getResources().getString(R.string.model_block_category_mineral);
            case Category.Utility:
                return c.getResources().getString(R.string.model_block_category_utility);
            case Category.Mechanism:
                return c.getResources().getString(R.string.model_block_category_mechanism);
            case Category.Plant:
                return c.getResources().getString(R.string.model_block_category_plant);
            case Category.Liquid:
                return c.getResources().getString(R.string.model_block_category_liquid);
            case Category.Nether:
                return c.getResources().getString(R.string.model_block_category_nether);
            case Category.End:
                return c.getResources().getString(R.string.model_block_category_end);
            case Category.CreativeOnly:
                return c.getResources().getString(R.string.model_block_category_creative_only);
            case Category.PocketEdition:
                return c.getResources().getString(R.string.model_block_category_pocket_edition);
            case Category.ConsoleEditionOnly:
                return c.getResources().getString(R.string.model_block_category_console_edition_only);
            case Category.Technical:
                return c.getResources().getString(R.string.model_block_category_technical);
            case Category.PocketEditionOnly:
                return c.getResources().getString(R.string.model_block_category_pocket_edition_only);
            case Category.Upcoming:
                return c.getResources().getString(R.string.model_block_category_upcoming);
            case Category.Removed:
                return c.getResources().getString(R.string.model_block_category_removed);
            case Category.Unimplemented:
                return c.getResources().getString(R.string.model_block_category_unimplemented);
        }

        return null;
    }

    public String getLocalizedPhysics(Context c) {
        if (physics == 0) {
            return c.getResources().getString(R.string.no);
        } else {
            return c.getResources().getString(R.string.yes);
        }
    }

    public String getLocalizedTransparent(Context c) {
        if (transparency == 0) {
            return c.getResources().getString(R.string.no);
        } else if (transparency == 1) {
            return c.getResources().getString(R.string.yes);
        } else {
            return c.getResources().getString(R.string.partial);
        }
    }

    public String getLocalizedLuminance(Context c) {
        if (luminance == 0) {
            return c.getResources().getString(R.string.no);
        } else {
            return Integer.toString(luminance);
        }
    }

    public String getLocalizedBlastResistance() {
        return Integer.toString(blastResistance);
    }

    public String getLocalizedStackable(Context c) {
        if (stackable == 0) {
            return c.getResources().getString(R.string.no);
        } else {
            return c.getResources().getString(R.string.yes) + ", " +Integer.toString(stackable);
        }
    }

    public String getLocalizedFlamable(Context c) {
        if (flamable == 0) {
            return c.getResources().getString(R.string.no);
        } else {
            return c.getResources().getString(R.string.yes);
        }
    }


    // =====================
    // Internal types
    // =====================

    public static class Type {

        private Type() {
        }

        public static final int Solid = 0;
        public static final int NonSolid = 1;
        public static final int TileEntity = 2;
        public static final int Food = 3;
        public static final int Tool = 4;
        public static final int Plant = 5;
        public static final int Fluid = 6;

    }

    public static class Category {

        private Category() {
        }

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
