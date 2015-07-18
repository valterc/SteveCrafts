package com.valterc.stevecrafts.breaks;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.Breaks;

public class BreaksFragment extends Fragment {

    private static final String ARGUMENT_BREAKS_ID = "breaks_id";

    public static BreaksFragment newInstance(String id) {
        BreaksFragment fragment = new BreaksFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_BREAKS_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    private Breaks breaks;

    public BreaksFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String breaksId = "0";

        if (savedInstanceState != null) {
            breaksId = savedInstanceState.getString(ARGUMENT_BREAKS_ID);
        } else {
            breaksId = getArguments().getString(ARGUMENT_BREAKS_ID);
        }

        this.breaks = SteveCraftsApp.getDataManager().getBreaks(breaksId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breaks, container, false);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_BREAKS_ID, breaks.getId());
        super.onSaveInstanceState(outState);
    }

}
