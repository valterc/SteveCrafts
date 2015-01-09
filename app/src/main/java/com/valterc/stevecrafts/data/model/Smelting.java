package com.valterc.stevecrafts.data.model;

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

}
