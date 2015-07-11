package com.valterc.stevecrafts.drawer;

import android.view.View;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.data.model.GenericItem;

/**
 * Created by Valter on 15/05/2015.
 */
public class NavigationDrawerItem {

    public static final int TYPE_SEARCH = R.layout.item_drawer_search;
    public static final int TYPE_SEARCH_RESULT = R.layout.item_drawer_search_result;
    public static final int TYPE_SEARCH_NO_RESULTS = R.layout.item_drawer_search_no_results;
    public static final int TYPE_MENU_ITEM = R.layout.item_drawer_menu_item;

    private int type;
    private GenericItem genericItem;
    private String menuItemTitle;
    private int menuItemImageResource;
    private View.OnClickListener clickListener;
    private boolean clearSearch;

    public NavigationDrawerItem(View.OnClickListener clickListener) {
        this.type = TYPE_SEARCH;
        this.clickListener = clickListener;
    }

    public NavigationDrawerItem(Boolean noResults) {
        if (noResults) {
            this.type = TYPE_SEARCH_NO_RESULTS;
        } else {
            this.type = TYPE_SEARCH;
        }
    }

    public NavigationDrawerItem(GenericItem item, View.OnClickListener clickListener) {
        this.type = TYPE_SEARCH_RESULT;
        this.genericItem = item;
        this.clickListener = clickListener;
    }

    public NavigationDrawerItem(String menuItemTitle, int imageResource) {
        this(menuItemTitle, imageResource, null);
    }

    public NavigationDrawerItem(String menuItemTitle, int imageResource, View.OnClickListener clickListener) {
        this.type = TYPE_MENU_ITEM;
        this.menuItemTitle = menuItemTitle;
        this.menuItemImageResource = imageResource;
        this.clickListener = clickListener;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public GenericItem getGenericItem() {
        return genericItem;
    }

    public void setGenericItem(GenericItem genericItem) {
        this.genericItem = genericItem;
    }

    public String getMenuItemTitle() {
        return menuItemTitle;
    }

    public void setMenuItemTitle(String menuItemTitle) {
        this.menuItemTitle = menuItemTitle;
    }

    public int getMenuItemImageResource() {
        return menuItemImageResource;
    }

    public void setMenuItemImageResource(int menuItemImageResource) {
        this.menuItemImageResource = menuItemImageResource;
    }

    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public boolean isClearSearch() {
        return clearSearch;
    }

    public void setClearSearch(boolean clearSearch) {
        this.clearSearch = clearSearch;
    }
}
