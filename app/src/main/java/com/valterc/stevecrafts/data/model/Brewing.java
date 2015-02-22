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
public class Brewing {

    private String id;
    private String ingredientId;

    /**
     * 1 - Item; 2 - Potion
     */
    private int beginItemType;
    private String beginItemId;
    private String resultItemId;
    private long timestamp;

    public Brewing() {

    }

    public Brewing(JSONObject json) throws JSONException {
        this.id = json.getString("id");
        this.ingredientId = json.getString("ingredient_id");
        this.beginItemType = json.getInt("begin_item_type");
        this.beginItemId = json.getString("begin_item_id");
        this.resultItemId = json.getString("result_item_id");

        try {
            this.timestamp = SteveCraftsApi.getDateFormat().parse(json.getString("timestamp")).getTime();
        } catch (ParseException e) {
            this.timestamp = Calendar.getInstance().getTimeInMillis();
        }
    }

    public Brewing(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.ingredientId = cursor.getString(cursor.getColumnIndex("ingredient_id"));
        this.beginItemType = cursor.getInt(cursor.getColumnIndex("begin_item_type"));
        this.beginItemId = cursor.getString(cursor.getColumnIndex("begin_item_id"));
        this.resultItemId = cursor.getString(cursor.getColumnIndex("result_item_id"));
        this.timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
    }

}
