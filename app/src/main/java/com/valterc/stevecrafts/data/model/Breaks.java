package com.valterc.stevecrafts.data.model;

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

}
