package com.valterc.stevecrafts.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return NavigationDrawerItemViewHolder.createViewHolder(viewType, itemView);
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

}
