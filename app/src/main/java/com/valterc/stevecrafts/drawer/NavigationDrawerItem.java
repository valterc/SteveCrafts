package com.valterc.stevecrafts.drawer;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.data.model.GenericItem;

/**
 * Created by Valter on 15/05/2015.
 */
public class NavigationDrawerItem {

    public static final int TYPE_SEARCH = R.layout.item_drawer_search;
    public static final int TYPE_SEARCH_RESULT = R.layout.item_drawer_search_result;
    public static final int TYPE_MENU_ITEM = R.layout.item_drawer_menu_item;

    private int type;
    private GenericItem genericItem;
    private String menuItemTitle;

    public NavigationDrawerItem() {
        this.type = TYPE_SEARCH;
    }

    public NavigationDrawerItem(GenericItem item) {
        this.type = TYPE_SEARCH_RESULT;
        this.genericItem = item;
    }

    public NavigationDrawerItem(String menuItemTitle) {
        this.type = TYPE_MENU_ITEM;
        this.menuItemTitle = menuItemTitle;
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
}