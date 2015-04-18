package com.valterc.stevecrafts.main.fragment.viewholders;

import android.view.View;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.main.fragment.MainFragmentItem;

/**
 * Created by Valter on 18/04/2015.
 */
public class TitleViewHolder extends MainViewHolder {

    private TextView textViewTitle;

    public TitleViewHolder(View itemView) {
        super(itemView);
        this.textViewTitle = (TextView) itemView.findViewById(R.id.textView);
    }

    @Override
    public void update(MainFragmentItem item) {
        this.textViewTitle.setText(item.getTitle());
    }

}
