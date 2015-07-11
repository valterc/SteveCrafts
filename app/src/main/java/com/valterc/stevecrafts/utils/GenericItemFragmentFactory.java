package com.valterc.stevecrafts.utils;

import android.app.Fragment;

import com.valterc.stevecrafts.block.BlockFragment;
import com.valterc.stevecrafts.data.model.GenericItem;
import com.valterc.stevecrafts.item.ItemFragment;
import com.valterc.stevecrafts.potion.PotionFragment;

/**
 * Created by Valter on 11/07/2015.
 */
public abstract class GenericItemFragmentFactory {

    public static Fragment GetFragment(GenericItem item) {

        switch (item.getType()) {
            case GenericItem.TYPE_BLOCK:
                return BlockFragment.newInstance(item.getId());
            case GenericItem.TYPE_ITEM:
                return ItemFragment.newInstance();
            case GenericItem.TYPE_POTION:
                return PotionFragment.newInstance();
        }

        return null;
    }

}
