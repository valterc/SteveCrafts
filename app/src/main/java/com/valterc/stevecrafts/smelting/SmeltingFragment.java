package com.valterc.stevecrafts.smelting;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.Smelting;

/**
 * Created by Valter on 19/07/2015.
 */
public class SmeltingFragment extends Fragment {

    private static final String ARGUMENT_SMELTING_ID = "smelting_id";

    public static SmeltingFragment newInstance(String id) {
        SmeltingFragment fragment = new SmeltingFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_SMELTING_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    private Smelting smelting;

    public SmeltingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String smeltingId = "0";

        if (savedInstanceState != null) {
            smeltingId = savedInstanceState.getString(ARGUMENT_SMELTING_ID);
        } else {
            smeltingId = getArguments().getString(ARGUMENT_SMELTING_ID);
        }

        this.smelting = SteveCraftsApp.getDataManager().getSmelting(smeltingId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smelting, container, false);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_SMELTING_ID, smelting.getId());
        super.onSaveInstanceState(outState);
    }

}