package com.valterc.stevecrafts.drawer.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.drawer.NavigationDrawerItem;
import com.vcutils.utils.DebugLog;

/**
 * Created by Valter on 15/05/2015.
 */
public class MenuItemViewHolder extends NavigationDrawerItemViewHolder implements View.OnClickListener {

    private TextView textView;
    private ImageView imageView;

    public MenuItemViewHolder(View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.textView);
        this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void update(NavigationDrawerItem item) {
        this.textView.setText(item.getMenuItemTitle());
        this.imageView.setImageResource(item.getMenuItemImageResource());
    }

    @Override
    public void onClick(View v) {
        DebugLog.d("Click menu item");
    }
}
