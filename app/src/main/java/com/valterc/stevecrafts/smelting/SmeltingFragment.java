package com.valterc.stevecrafts.smelting;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.Smelting;
import com.vcutils.views.PixelImageView;

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

        PixelImageView imageViewSmeltingFire = (PixelImageView) view.findViewById(R.id.imageViewSmeltingFire);
        PixelImageView imageViewSmeltingArrow = (PixelImageView) view.findViewById(R.id.imageViewSmeltingArrow);
        PixelImageView imageViewInput = (PixelImageView) view.findViewById(R.id.imageViewSmeltingInput);
        PixelImageView imageViewResult = (PixelImageView) view.findViewById(R.id.imageViewSmeltingResult);

        imageViewSmeltingFire.setImageResource(R.drawable.minecraft_furnace_fire);
        imageViewSmeltingArrow.setImageResource(R.drawable.minecraft_arrow);

        if (smelting.getIngredientType() == 0){
            imageViewInput.setImageBitmap(SteveCraftsApp.getDataManager().getBlockImage(smelting.getIngredientId()));
        } else {
            imageViewInput.setImageBitmap(SteveCraftsApp.getDataManager().getItemImage(smelting.getIngredientId()));
        }

        if (smelting.getResultType() == 0){
            imageViewResult.setImageBitmap(SteveCraftsApp.getDataManager().getBlockImage(smelting.getResultId()));
        } else {
            imageViewResult.setImageBitmap(SteveCraftsApp.getDataManager().getItemImage(smelting.getResultId()));
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_SMELTING_ID, smelting.getId());
        super.onSaveInstanceState(outState);
    }

}