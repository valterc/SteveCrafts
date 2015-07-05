package com.valterc.stevecrafts.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.data.model.GenericItem;
import com.valterc.stevecrafts.drawer.viewholders.NavigationDrawerItemViewHolder;

import java.util.ArrayList;

/**
 * Created by Valter on 07/01/2015.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerItemViewHolder> {

    private Context context;
    private ArrayList<NavigationDrawerItem> items;

    public NavigationDrawerAdapter(Context context, ArrayList<NavigationDrawerItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public NavigationDrawerItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //viewType is equal to layout ID
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);
        return NavigationDrawerItemViewHolder.createViewHolder(viewType, itemView, this);
    }

    @Override
    public void onBindViewHolder(NavigationDrawerItemViewHolder viewHolder, int position) {
        viewHolder.update(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(NavigationDrawerItem item) {
        this.items.add(item);
    }

    public void clearSearchResults() {
        int count = this.items.size();

        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getType() == NavigationDrawerItem.TYPE_SEARCH_RESULT || this.items.get(i).getType() == NavigationDrawerItem.TYPE_SEARCH_NO_RESULTS) {
                this.items.remove(i--);
            }
        }

        count -= this.items.size();
        notifyItemRangeRemoved(1, count);
    }

    public void addSearchResults(ArrayList<GenericItem> searchResultItems, View.OnClickListener clickListener){
        int index = 0;
        for (GenericItem item : searchResultItems){
            this.items.add(++index, new NavigationDrawerItem(item, clickListener));
        }
        notifyItemRangeInserted(1, index);
    }

    public void addSearchNoResults(){
        this.items.add(1, new NavigationDrawerItem(true));
        notifyItemInserted(1);
    }
}
