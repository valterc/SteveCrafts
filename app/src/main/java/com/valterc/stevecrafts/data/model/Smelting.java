package com.valterc.stevecrafts.data.model;

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
}
