package com.valterc.stevecrafts.main.fragment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.valterc.stevecrafts.main.fragment.MainFragmentItem;

/**
 * Created by Valter on 18/04/2015.
 */
public abstract class MainViewHolder extends RecyclerView.ViewHolder {

    public static MainViewHolder createViewHolder(int type, View itemView) {
        switch (type){
            case MainFragmentItem.TYPE_TITLE: return new TitleViewHolder(itemView);
            case MainFragmentItem.TYPE_AD: return new AdViewHolder(itemView);
            case MainFragmentItem.TYPE_BLOCK: return new BlockViewHolder(itemView);
            case MainFragmentItem.TYPE_NEW_UPDATE: return new UpdateViewHolder(itemView);
            case MainFragmentItem.TYPE_MINECRAFT_UPDATE: return new MinecraftUpdateViewHolder(itemView);
        }

        return null;
    }

    public MainViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void update(MainFragmentItem item);


}
