package com.valterc.stevecrafts.block;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.R;

/**
 * Created by Valter on 05/07/2015.
 */
public class BlockFragment extends Fragment {

    public static BlockFragment newInstance() {
        BlockFragment fragment = new BlockFragment();
        return fragment;
    }

    public BlockFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_block, container, false);
    }

}
