package com.valterc.stevecrafts.main.fragment;

import com.valterc.external.tumblr.TumblrPost;
import com.valterc.stevecrafts.R;

/**
 * Created by Valter on 18/04/2015.
 */
public class MainFragmentItem {

    public static final int TYPE_TITLE = R.layout.item_main_title;
    public static final int TYPE_AD = R.layout.item_main_ad;
    public static final int TYPE_BLOCK = R.layout.item_main_block;
    public static final int TYPE_NEW_UPDATE = R.layout.item_main_new_update;
    public static final int TYPE_MINECRAFT_UPDATE = R.layout.item_main_minecraft_update;

    private int type;
    private String title;
    private TumblrPost tumblrPost;

    public MainFragmentItem() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TumblrPost getTumblrPost() {
        return tumblrPost;
    }

    public void setTumblrPost(TumblrPost tumblrPost) {
        this.tumblrPost = tumblrPost;
    }
}
