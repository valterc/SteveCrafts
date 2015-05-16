package com.valterc.stevecrafts.main.fragment.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.main.fragment.MainFragmentItem;

/**
 * Created by Valter on 18/04/2015.
 */
public class UpdateViewHolder extends MainViewHolder {

    private TextView textViewName;
    private ImageView imageView;

    public UpdateViewHolder(View itemView) {
        super(itemView);
        this.textViewName = (TextView) itemView.findViewById(R.id.textView);
        this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }

    @Override
    public void update(MainFragmentItem item) {
        textViewName.setText(item.getGenericItem().getLocalizedName());
        imageView.setImageBitmap(item.getGenericItem().getImage());
    }

}
