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
public class Smelting {

    private String id;

    /**
     * 0 - Block; 1 - Item
     */
    private int ingredientType;
    private String ingredientId;

    /**
     * 0 - Block; 1 - Item
     */
    private int resultType;
    private String resultId;
    private int resultCount;
    private double experience;
    private int dontRecommend;
    private long timestamp;

    public Smelting(JSONObject json) throws JSONException {
        this.id = json.getString("id");
        this.ingredientType = json.getInt("ingredient_type");
        this.ingredientId = json.getString("ingredient_id");
        this.resultType = json.getInt("result_type");
        this.resultId = json.getString("result_id");
        this.resultCount = json.getInt("result_count");
        this.experience = json.getDouble("exp");
        this.dontRecommend = json.getInt("dont_recommend");

        try {
            this.timestamp = SteveCraftsApi.getDateFormat().parse(json.getString("timestamp")).getTime();
        } catch (ParseException e) {
            this.timestamp = Calendar.getInstance().getTimeInMillis();
        }
    }

    public Smelting(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.ingredientType = cursor.getInt(cursor.getColumnIndex("ingredient_type"));
        this.ingredientId = cursor.getString(cursor.getColumnIndex("ingredient_id"));
        this.resultType = cursor.getInt(cursor.getColumnIndex("result_type"));
        this.resultId = cursor.getString(cursor.getColumnIndex("result_id"));
        this.resultCount = cursor.getInt(cursor.getColumnIndex("result_count"));
        this.experience = cursor.getDouble(cursor.getColumnIndex("experience"));
        this.dontRecommend = cursor.getInt(cursor.getColumnIndex("dont_recommend"));
        this.timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));

    }

    public String getId() {
        return id;
    }

    public int getIngredientType() {
        return ingredientType;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public int getResultType() {
        return resultType;
    }

    public String getResultId() {
        return resultId;
    }

    public int getResultCount() {
        return resultCount;
    }

    public double getExperience() {
        return experience;
    }

    public int getDontRecommend() {
        return dontRecommend;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
