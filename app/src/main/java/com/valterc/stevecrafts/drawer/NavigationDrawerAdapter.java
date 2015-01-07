package com.valterc.stevecrafts.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Valter on 07/01/2015.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerItemViewHolder> {

    private Context context;

    public NavigationDrawerAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public NavigationDrawerItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(NavigationDrawerItemViewHolder navigationDrawerItemViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
