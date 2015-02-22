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
public class CraftingRecipe {

    private String id;

    /**
     * 0 - Block; 1 - Item
     */
    private int type;
    private String craftId;
    private int count;
    private Slot[] slots;
    private long timestamp;

    public CraftingRecipe() {

    }

    public CraftingRecipe(JSONObject json) throws JSONException {
        this.id = json.getString("id");
        this.type = json.getInt("type");
        this.craftId = json.getString("craft_id");
        this.count = json.getInt("count");

        this.slots = new Slot[9];
        for (int i = 0; i < 9; i++) {
            if (!json.isNull(String.format("slot_%s_id", i))) {
                this.slots[i] = new Slot(
                        json.getString(String.format("slot_%s_id", i)),
                        json.getInt(String.format("slot_%s_type", i)),
                        json.getInt(String.format("slot_%s_count", i))
                );
            }
        }

        try {
            this.timestamp = SteveCraftsApi.getDateFormat().parse(json.getString("timestamp")).getTime();
        } catch (ParseException e) {
            this.timestamp = Calendar.getInstance().getTimeInMillis();
        }
    }

    public CraftingRecipe(Cursor cursor) {
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.type = cursor.getInt(cursor.getColumnIndex("type"));
        this.craftId = cursor.getString(cursor.getColumnIndex("craft_id"));
        this.count = cursor.getInt(cursor.getColumnIndex("count"));

        this.slots = new Slot[9];
        for (int i = 0; i < 9; i++) {
            if (!cursor.isNull(cursor.getColumnIndex(String.format("slot_%s_id", i)))) {
                this.slots[i] = new Slot(
                        cursor.getString(cursor.getColumnIndex(String.format("slot_%s_id", i))),
                        cursor.getInt(cursor.getColumnIndex(String.format("slot_%s_type", i))),
                        cursor.getInt(cursor.getColumnIndex(String.format("slot_%s_count", i)))
                );
            }
        }

        this.timestamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
    }


    // =====================
    // Internal types
    // =====================

    public static class Slot {

        private String id;
        private int type;
        private int count;

        public Slot(String id, int type, int count) {
            this.setId(id);
            this.setType(type);
            this.setCount(count);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return 0 - Block; 1 - Item
         */
        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

}
