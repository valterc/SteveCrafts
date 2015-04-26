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
 * Created by Valter on 09/01/2015.
 */
public class Potion {

    private String id;

    /**
     * 0 - Instant; Other - Seconds
     */
    private double duration;
    private int health;

    /**
     * Percentage
     */
    private int speed;

    /**
     * Percentage
     */
    private int attack;
    private Bitmap image;
    private String name_en;
    private String name_pt;
    private String name_de;
    private String name_es;
    private String name_fr;
    private String name_pl;
    private long timestamp;

    public Potion(JSONObject json) throws JSONException {
        this.id = json.getString("id");
        this.duration = json.getDouble("duration");
        this.health = json.getInt("health");
        this.speed = json.getInt("speed");
        this.attack = json.getInt("attack");
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

    public Potion(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.duration = cursor.getDouble(cursor.getColumnIndex("duration"));
        this.health = cursor.getInt(cursor.getColumnIndex("health"));
        this.speed = cursor.getInt(cursor.getColumnIndex("speed"));
        this.attack = cursor.getInt(cursor.getColumnIndex("attack"));
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

    public double getDuration() {
        return duration;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return attack;
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
}
