package com.valterc.stevecrafts.data.model;

import android.graphics.Bitmap;

/**
 * Created by Valter on 25/04/2015.
 */
public class GenericItem {

    public static final int TYPE_BLOCK = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_POTION = 2;

    private String id;
    private String name;
    private int type;
    private Bitmap image;

    public GenericItem() {
    }

    public GenericItem(String id, String name, int type, Bitmap image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
