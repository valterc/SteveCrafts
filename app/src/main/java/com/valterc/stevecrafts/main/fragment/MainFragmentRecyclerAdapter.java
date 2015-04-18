package com.valterc.stevecrafts.main.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.main.fragment.viewholders.MainViewHolder;

import java.util.ArrayList;

/**
 * Created by Valter on 18/04/2015.
 */
public class MainFragmentRecyclerAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private Context context;
    private ArrayList<MainFragmentItem> items;

    public MainFragmentRecyclerAdapter(Context context, ArrayList<MainFragmentItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //viewType is equal to layout ID
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);
        return MainViewHolder.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder viewHolder, int position) {
        viewHolder.update(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}