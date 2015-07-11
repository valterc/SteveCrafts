package com.valterc.stevecrafts.drawer;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.about.AboutFragment;
import com.valterc.stevecrafts.data.model.GenericItem;
import com.valterc.stevecrafts.main.IMainFragmentController;
import com.valterc.stevecrafts.utils.GenericItemFragmentFactory;

import java.util.ArrayList;


/**
 * Created by Valter on 07/01/2015.
 */
public class NavigationDrawerFragment extends Fragment {

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final String STATE_TITLE = "title";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learn";

    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private NavigationDrawerAdapter mAdapter;
    private View mFragmentContainerView;
    private IMainFragmentController mainFragmentController;

    private int mCurrentSelectedPosition = 1;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;
    private CharSequence mTitle;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);
        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mTitle = savedInstanceState.getCharSequence(STATE_TITLE);
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drawer, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(android.R.id.list);
        mRecyclerView.setClipToPadding(false);

        ArrayList<NavigationDrawerItem> recyclerViewItems = new ArrayList<>();
        createDrawerItems(recyclerViewItems);

        mAdapter = new NavigationDrawerAdapter(getActivity(), recyclerViewItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    private void createDrawerItems(ArrayList<NavigationDrawerItem> recyclerViewItems) {
        recyclerViewItems.add(new NavigationDrawerItem(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clearSearchResults();
                mAdapter.clearSearchText();
                GenericItem item = (GenericItem) v.getTag(R.id.id_view_search_result_item);
                openFragment(GenericItemFragmentFactory.GetFragment(item));
            }
        }));

        recyclerViewItems.add(new NavigationDrawerItem("Blocks", R.drawable.stone, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));

        recyclerViewItems.add(new NavigationDrawerItem("Items", R.drawable.stone_pick));
        recyclerViewItems.add(new NavigationDrawerItem("Potions", R.drawable.potion));
        recyclerViewItems.add(new NavigationDrawerItem("Settings", R.drawable.settings));
        recyclerViewItems.add(new NavigationDrawerItem("About", R.drawable.info, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(AboutFragment.newInstance());
            }
        }));
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar actionBarToolbar) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        actionBarToolbar.setNavigationIcon(R.drawable.ic_menu_white);
        actionBarToolbar.setNavigationOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                if (!isAdded()) {
                    return;
                }

                mTitle = getActivity().getTitle();

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }
            }


            public void onDrawerClosed(View drawerView) {
                if (!isAdded()) {
                    return;
                }
                getActivity().setTitle(mTitle);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

    }

    private void openFragment(Fragment fragment) {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

        if (mainFragmentController != null && fragment != null) {
            mainFragmentController.openFragment(fragment);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
        outState.putCharSequence(STATE_TITLE, mTitle);
    }

    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IMainFragmentController) {
            mainFragmentController = (IMainFragmentController) activity;
        }
    }
}
