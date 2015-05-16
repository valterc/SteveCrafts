package com.valterc.stevecrafts.drawer.viewholders;

import android.view.View;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.drawer.NavigationDrawerItem;

/**
 * Created by Valter on 15/05/2015.
 */
public class MenuItemViewHolder extends NavigationDrawerItemViewHolder implements View.OnClickListener {

    private TextView textView;

    public MenuItemViewHolder(View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void update(NavigationDrawerItem item) {
        this.textView.setText(item.getMenuItemTitle());
    }

    @Override
    public void onClick(View v) {

    }
}
