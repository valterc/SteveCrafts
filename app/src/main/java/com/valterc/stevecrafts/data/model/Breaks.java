package com.valterc.stevecrafts.data.model;

import android.database.Cursor;

import com.valterc.stevecrafts.data.api.SteveCraftsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by Valter on 09/01/2015.
 */
public class Breaks {

    private String id;
    private String itemId;
    private String blockId;

    /**
     * 0 - No; 1 - Yes
     */
    private int silktouch;

    /**
     * 0 - No; 1 - Yes, but this is quicker
     */
    private int anytool;
    private String dropId;

    /**
     * 0 - Block; 1 - Item
     */
    private int dropType;
    private int dropCount;
    private int dropCountMin;
    private int dropCountMax;
    private long timestamp;

    public Breaks() {

    }

    public Breaks(JSONObject json) throws JSONException {
        this.id = json.getString("id");
        this.itemId = json.getString("item_id");
        this.blockId = json.getString("block_id");
        this.silktouch = json.getInt("silktouch");
        this.anytool = json.getInt("anytool");
        this.dropId = json.getString("drop_id");
        this.dropType = json.getInt("drop_type");
        this.dropCount = json.getInt("drop_count");
        this.dropCountMin = json.getInt("drop_count_min");
        this.dropCountMax = json.getInt("drop_count_max");

        try {
            this.timestamp = SteveCraftsApi.getDateFormat().parse(json.getString("timestamp")).getTime();
        } catch (ParseException e) {
            this.timestamp = Calendar.getInstance().getTimeInMillis();
        }
    }

    public Breaks(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.itemId = cursor.getString(cursor.getColumnIndex("item_id"));
        this.blockId = cursor.getString(cursor.getColumnIndex("block_id"));
        this.silktouch = cursor.getInt(cursor.getColumnIndex("silktouch"));
        this.anytool = cursor.getInt(cursor.getColumnIndex("anytool"));
        this.dropId = cursor.getString(cursor.getColumnIndex("drop_id"));
        this.dropType = cursor.getInt(cursor.getColumnIndex("drop_type"));
        this.dropCount = cursor.getInt(cursor.getColumnIndex("drop_count"));
        this.dropCountMin = cursor.getInt(cursor.getColumnIndex("drop_count_min"));
        this.dropCountMax = cursor.getInt(cursor.getColumnIndex("drop_count_max"));
        this.timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
    }

    public String getId() {
        return id;
    }

    public String getItemId() {
        return itemId;
    }

    public String getBlockId() {
        return blockId;
    }

    public int getSilktouch() {
        return silktouch;
    }

    public int getAnytool() {
        return anytool;
    }

    public String getDropId() {
        return dropId;
    }

    public int getDropType() {
        return dropType;
    }

    public int getDropCount() {
        return dropCount;
    }

    public int getDropCountMin() {
        return dropCountMin;
    }

    public int getDropCountMax() {
        return dropCountMax;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
