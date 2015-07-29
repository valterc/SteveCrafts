package com.valterc.stevecrafts.potion;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.breaks.MultipleBreaksFragment;
import com.valterc.stevecrafts.data.model.Brewing;
import com.valterc.stevecrafts.data.model.Potion;
import com.vcutils.views.PixelImageView;

import java.util.ArrayList;

/**
 * Created by Valter on 05/07/2015.
 */
public class PotionFragment extends Fragment {

    private static final String ARGUMENT_POTION_ID = "potion_id";

    public static PotionFragment newInstance(String itemId) {
        PotionFragment fragment = new PotionFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_POTION_ID, itemId);
        fragment.setArguments(arguments);
        return fragment;
    }

    private Potion potion;

    public PotionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String potionId = "1";

        if (savedInstanceState != null) {
            potionId = savedInstanceState.getString(ARGUMENT_POTION_ID);
        } else {
            potionId = getArguments().getString(ARGUMENT_POTION_ID);
        }

        this.potion = SteveCraftsApp.getDataManager().getPotion(potionId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_potion, container, false);

        PixelImageView imageViewPotionImage = (PixelImageView) view.findViewById(R.id.imageViewPotionImage);
        TextView textViewPotionName = (TextView) view.findViewById(R.id.textViewPotionName);
        TextView textViewPotionDuration = (TextView) view.findViewById(R.id.textViewPotionDuration);

        LinearLayout linearLayoutPotionHealth = (LinearLayout) view.findViewById(R.id.linearLayoutPotionHealth);
        TextView textViewPotionHealth = (TextView) view.findViewById(R.id.textViewPotionHealth);

        LinearLayout linearLayoutPotionSpeed = (LinearLayout) view.findViewById(R.id.linearLayoutPotionSpeed);
        TextView textViewPotionSpeed = (TextView) view.findViewById(R.id.textViewPotionSpeed);

        LinearLayout linearLayoutPotionAttack = (LinearLayout) view.findViewById(R.id.linearLayoutPotionAttack);
        TextView textViewPotionAttack = (TextView) view.findViewById(R.id.textViewPotionAttack);

        LinearLayout linearLayoutBrewing = (LinearLayout) view.findViewById(R.id.linearLayoutBrewing);
        LinearLayout linearLayoutCanBeUsedToBrew = (LinearLayout) view.findViewById(R.id.linearLayoutCanBeUsedToBrew);

        imageViewPotionImage.setImageBitmap(SteveCraftsApp.getDataManager().getPotionImage(potion.getId()));
        textViewPotionName.setText(potion.getLocalizedName());
        textViewPotionDuration.setText(potion.getDuration() == 0 ? getString(R.string.model_instant) : String.format(getString(R.string.model_x_seconds), potion.getDuration()));

        if (potion.getHealth() == 0) {
            linearLayoutPotionHealth.setVisibility(View.GONE);
        } else {
            textViewPotionHealth.setText(potion.getHealth() / 2 + " x");
        }

        if (potion.getSpeed() == 0) {
            linearLayoutPotionSpeed.setVisibility(View.GONE);
        } else {
            textViewPotionSpeed.setText((potion.getSpeed() > 0 ? "+" : "") + potion.getSpeed() + "%");
        }

        if (potion.getAttack() == 0) {
            linearLayoutPotionAttack.setVisibility(View.GONE);
        } else {
            textViewPotionAttack.setText((potion.getAttack() > 0 ? "+" : "") + potion.getAttack() + "%");
        }

        ArrayList<Brewing> brewings = SteveCraftsApp.getDataManager().getBrewing();
        if (brewings == null || brewings.isEmpty()) {
            linearLayoutBrewing.setVisibility(View.GONE);
        } else {

            String[] brewingId = new String[brewings.size()];
            for (int i = 0; i < brewings.size(); i++) {
                brewingId[i] = brewings.get(i).getId();
            }

            getChildFragmentManager().beginTransaction().replace(R.id.frameBrewing, MultipleBreaksFragment.newInstance(brewingId)).commit();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_POTION_ID, potion.getId());
        super.onSaveInstanceState(outState);
    }

}
