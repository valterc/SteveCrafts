package com.valterc.stevecrafts.drawer.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.valterc.stevecrafts.drawer.NavigationDrawerAdapter;
import com.valterc.stevecrafts.drawer.NavigationDrawerItem;

/**
 * Created by Valter on 07/01/2015.
 */
public abstract class NavigationDrawerItemViewHolder extends RecyclerView.ViewHolder {

    public static NavigationDrawerItemViewHolder createViewHolder(int type, View itemView, NavigationDrawerAdapter adapter) {
        switch (type){
            case NavigationDrawerItem.TYPE_SEARCH: return new SearchViewHolder(itemView, adapter);
            case NavigationDrawerItem.TYPE_SEARCH_RESULT: return new SearchResultViewHolder(itemView);
            case NavigationDrawerItem.TYPE_SEARCH_NO_RESULTS: return new SearchNoResultsViewHolder(itemView);
            case NavigationDrawerItem.TYPE_MENU_ITEM: return new MenuItemViewHolder(itemView);
        }

        return null;
    }

    public NavigationDrawerItemViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void update(NavigationDrawerItem item);
}
