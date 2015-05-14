package com.valterc.stevecrafts.main.fragment.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.data.model.GenericItem;
import com.valterc.stevecrafts.main.fragment.MainFragmentItem;
import com.vcutils.views.PixelImageView;

/**
 * Created by Valter on 18/04/2015.
 */
public class BlockViewHolder extends MainViewHolder{

    private Context context;
    private TextView textViewItemName;
    private TextView textViewItemType;
    private PixelImageView imageViewItem;

    public BlockViewHolder(View itemView) {
        super(itemView);
        this.textViewItemName = (TextView) itemView.findViewById(R.id.textViewItemName);
        this.imageViewItem = (PixelImageView) itemView.findViewById(R.id.imageView);
        this.textViewItemType = (TextView) itemView.findViewById(R.id.textViewItemDescription);
        this.context = itemView.getContext();
    }

    @Override
    public void update(MainFragmentItem item) {
        GenericItem genericItem = item.getGenericItem();
        this.textViewItemType.setText(genericItem.getTypeName(this.context));
        this.textViewItemName.setText(genericItem.getName());
        this.imageViewItem.setImageBitmap(genericItem.getImage());
    }

}
