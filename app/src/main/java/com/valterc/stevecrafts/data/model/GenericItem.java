package com.valterc.stevecrafts.data.model;

import android.graphics.Bitmap;

import com.valterc.stevecrafts.SteveCraftsApp;

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
    private long timestamp;

    public GenericItem() {
    }

    public GenericItem(Block block) {
        this.id = block.getId();
        this.name = block.getNameEn(); //TODO: Localize app
        this.type = TYPE_BLOCK;
        this.image = block.getImage();
        this.timestamp = block.getTimestamp();
    }

    public GenericItem(Item item) {
        this.id = item.getId();
        this.name = item.getNameEn(); //TODO: Localize app
        this.type = TYPE_ITEM;
        this.image = item.getImage();
        this.timestamp = item.getTimestamp();
    }

    public GenericItem(Potion potion) {
        this.id = potion.getId();
        this.name = potion.getNameEn(); //TODO: Localize app
        this.type = TYPE_POTION;
        this.image = potion.getImage();
        this.timestamp = potion.getTimestamp();
    }

    public GenericItem(String id, String name, int type, Bitmap image, long timestamp) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
        this.timestamp = timestamp;
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
        if (image == null){
            image = SteveCraftsApp.getDataManager().getGenericItemImage(this);
        }
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
