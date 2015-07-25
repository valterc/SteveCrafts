package com.valterc.stevecrafts.item;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.Item;
import com.vcutils.views.RepeatingImageView;

/**
 * Created by Valter on 05/07/2015.
 */
public class ItemFragment extends Fragment {

    private static final String ARGUMENT_ITEM_ID = "item_id";

    public static ItemFragment newInstance(String itemId) {
        ItemFragment fragment = new ItemFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_ITEM_ID, itemId);
        fragment.setArguments(arguments);
        return fragment;
    }

    private Item item;

    public ItemFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String itemId = "1";

        if (savedInstanceState != null) {
            itemId = savedInstanceState.getString(ARGUMENT_ITEM_ID);
        } else {
            itemId = getArguments().getString(ARGUMENT_ITEM_ID);
        }

        this.item = SteveCraftsApp.getDataManager().getItem(itemId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        ImageView imageViewItemImage = (ImageView) view.findViewById(R.id.imageViewItemImage);
        TextView textViewItemName = (TextView) view.findViewById(R.id.textViewItemName);
        TextView textViewItemType = (TextView) view.findViewById(R.id.textViewItemType);
        TextView textViewItemStackable = (TextView) view.findViewById(R.id.textViewItemStackable);
        LinearLayout linearLayoutDamage = (LinearLayout) view.findViewById(R.id.linearLayoutDamage);
        LinearLayout linearLayoutArmor = (LinearLayout) view.findViewById(R.id.linearLayoutArmor);
        RepeatingImageView repeatImageViewDamage = (RepeatingImageView) view.findViewById(R.id.repeatImageViewDamage);
        RepeatingImageView repeatImageViewArmor = (RepeatingImageView) view.findViewById(R.id.repeatImageViewArmor);

        textViewItemName.setText(item.getLocalizedName());

        Bitmap itemImage = SteveCraftsApp.getDataManager().getItemImage(item.getId());
        imageViewItemImage.setImageBitmap(itemImage);

        if (item.getStackable() == 0) {
            textViewItemStackable.setText(R.string.no);
        } else {
            textViewItemStackable.setText(String.format(getString(R.string.yes_more), item.getStackable()));
        }

        if (item.getArmor() == 0) {
            linearLayoutArmor.setVisibility(View.GONE);
        } else {
            repeatImageViewArmor.setDrawableCount(item.getArmor());
        }

        if (item.getDamage() == 0) {
            linearLayoutDamage.setVisibility(View.GONE);
        } else {
            repeatImageViewDamage.setDrawableCount(item.getDamage());
        }


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_ITEM_ID, item.getId());
        super.onSaveInstanceState(outState);
    }

}
