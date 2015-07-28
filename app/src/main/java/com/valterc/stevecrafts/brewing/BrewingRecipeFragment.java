package com.valterc.stevecrafts.brewing;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.Brewing;
import com.valterc.stevecrafts.main.IMainFragmentController;

/**
 * Created by Valter on 19/07/2015.
 */
public class BrewingRecipeFragment extends Fragment {

    private static final String ARGUMENT_BREWING_ID = "crafting_recipe_id";

    public static BrewingRecipeFragment newInstance(String id) {
        BrewingRecipeFragment fragment = new BrewingRecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_BREWING_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    private IMainFragmentController mainFragmentController;
    private Brewing brewing;

    public BrewingRecipeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String brewingId = "0";

        if (savedInstanceState != null) {
            brewingId = savedInstanceState.getString(ARGUMENT_BREWING_ID);
        } else {
            brewingId = getArguments().getString(ARGUMENT_BREWING_ID);
        }

        this.brewing = SteveCraftsApp.getDataManager().getBrewing(brewingId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brewing, container, false);



        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_BREWING_ID, brewing.getId());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IMainFragmentController) {
            mainFragmentController = (IMainFragmentController) activity;
        }
    }

}