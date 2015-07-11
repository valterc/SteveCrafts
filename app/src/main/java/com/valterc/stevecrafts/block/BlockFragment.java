package com.valterc.stevecrafts.block;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.Block;
import com.vcutils.utils.DebugLog;

/**
 * Created by Valter on 05/07/2015.
 */
public class BlockFragment extends Fragment {

    private static final String ARGUMENT_BLOCK_ID = "block_id";

    public static BlockFragment newInstance(String blockId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_BLOCK_ID, blockId);

        BlockFragment fragment = new BlockFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    private Block block;

    public BlockFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String blockId = "0";

        if (savedInstanceState != null) {
            blockId = savedInstanceState.getString(ARGUMENT_BLOCK_ID);
        } else {
            blockId = getArguments().getString(ARGUMENT_BLOCK_ID);
        }

        this.block = SteveCraftsApp.getDataManager().getBlock(blockId);
        DebugLog.d(this.block.getLocalizedName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_block, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARGUMENT_BLOCK_ID, block.getId());
        super.onSaveInstanceState(outState);
    }
}
