package com.valterc.stevecrafts.main.fragment.viewholders;

import android.view.View;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.main.fragment.MainFragmentItem;
import com.valterc.stevecrafts.utils.DateFormatter;

/**
 * Created by Valter on 18/04/2015.
 */
public class MinecraftUpdateViewHolder extends MainViewHolder {

    private TextView textViewUpdateTitle;
    private TextView textViewUpdateDescription;
    private TextView textViewUpdateDate;

    public MinecraftUpdateViewHolder(View itemView) {
        super(itemView);
        this.textViewUpdateTitle = (TextView) itemView.findViewById(R.id.textViewUpdateTitle);
        this.textViewUpdateDescription = (TextView) itemView.findViewById(R.id.textViewUpdateDescription);
        this.textViewUpdateDate = (TextView) itemView.findViewById(R.id.textViewUpdateDate);
    }

    @Override
    public void update(MainFragmentItem item) {
        this.textViewUpdateTitle.setText(item.getTumblrPost().getTitle());
        this.textViewUpdateDescription.setText(item.getTumblrPost().getBody());
        this.textViewUpdateDate.setText(DateFormatter.format(item.getTumblrPost().getTimestamp()));
    }

}
