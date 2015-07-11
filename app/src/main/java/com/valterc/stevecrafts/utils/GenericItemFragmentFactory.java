package com.valterc.stevecrafts.utils;

import android.app.Fragment;

import com.valterc.stevecrafts.block.BlockFragment;
import com.valterc.stevecrafts.data.model.GenericItem;

/**
 * Created by Valter on 11/07/2015.
 */
public abstract class GenericItemFragmentFactory {

    public static Fragment GetFragment(GenericItem item) {

        switch (item.getType()) {
            case GenericItem.TYPE_BLOCK:
                return BlockFragment.newInstance();
            case GenericItem.TYPE_ITEM:
            case GenericItem.TYPE_POTION:
        }

        return null;
    }

}
