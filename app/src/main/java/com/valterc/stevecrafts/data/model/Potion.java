package com.valterc.stevecrafts.data.model;

import android.graphics.Bitmap;

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

}
