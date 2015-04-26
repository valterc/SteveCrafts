package com.valterc.stevecrafts.data.model;

import android.database.Cursor;
import android.graphics.Bitmap;

import com.valterc.stevecrafts.data.api.SteveCraftsApi;
import com.vcutils.utils.ImageUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;

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

    public Item() {

    }

    public Item(JSONObject json) throws JSONException {
        this.id = json.getString("id");
        this.minecraftId = json.getString("minecraft_id");
        this.minecraftDataValue = json.getInt("minecraft_datavalue");
        this.durability = json.getInt("durability");
        this.stackable = json.getInt("stackable");
        this.damage = json.getInt("damage");
        this.armor = json.getInt("armor");
        this.type = json.getInt("type");
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

    public Item(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.minecraftId = cursor.getString(cursor.getColumnIndex("minecraft_id"));
        this.minecraftDataValue = cursor.getInt(cursor.getColumnIndex("minecraft_datavalue"));
        this.durability = cursor.getInt(cursor.getColumnIndex("durability"));
        this.stackable = cursor.getInt(cursor.getColumnIndex("stackable"));
        this.damage = cursor.getInt(cursor.getColumnIndex("damage"));
        this.armor = cursor.getInt(cursor.getColumnIndex("armor"));
        this.type = cursor.getInt(cursor.getColumnIndex("type"));
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

    public String getMinecraftId() {
        return minecraftId;
    }

    public int getMinecraftDataValue() {
        return minecraftDataValue;
    }

    public int getDurability() {
        return durability;
    }

    public int getStackable() {
        return stackable;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public int getType() {
        return type;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getNameEn() {
        return name_en;
    }

    public String getNamePt() {
        return name_pt;
    }

    public String getNameDe() {
        return name_de;
    }

    public String getNameEs() {
        return name_es;
    }

    public String getNameFr() {
        return name_fr;
    }

    public String getNamePl() {
        return name_pl;
    }

    public long getTimestamp() {
        return timestamp;
    }


    // =====================
    // Internal types
    // =====================

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
