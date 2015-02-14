package com.valterc.stevecrafts.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+1"));

        try {
            this.timestamp = dateFormat.parse(json.getString("timestamp")).getTime();
        } catch (ParseException e) {
            this.timestamp = Calendar.getInstance().getTimeInMillis();
        }
    }
}