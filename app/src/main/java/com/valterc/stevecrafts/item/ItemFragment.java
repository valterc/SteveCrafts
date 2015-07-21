package com.valterc.stevecrafts.item;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.Item;

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

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_ITEM_ID, item.getId());
        super.onSaveInstanceState(outState);
    }

}
