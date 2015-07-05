package com.valterc.stevecrafts.drawer.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.data.model.GenericItem;
import com.valterc.stevecrafts.drawer.NavigationDrawerItem;

/**
 * Created by Valter on 15/05/2015.
 */
public class SearchResultViewHolder extends NavigationDrawerItemViewHolder implements View.OnClickListener {

    private ImageView imageView;
    private TextView textView;
    private View.OnClickListener clickListener;
    private GenericItem genericItem;

    public SearchResultViewHolder(View itemView) {
        super(itemView);
        this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
        this.textView = (TextView) itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void update(NavigationDrawerItem item) {
        this.imageView.setImageBitmap(item.getGenericItem().getImage());
        this.textView.setText(item.getGenericItem().getLocalizedName());
        this.clickListener = item.getClickListener();
        this.genericItem = item.getGenericItem();
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null){
            v.setTag(R.id.id_view_search_result_item, genericItem);
            clickListener.onClick(v);
        }
    }
}
