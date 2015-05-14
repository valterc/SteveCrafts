package com.valterc.stevecrafts.main.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valterc.external.tumblr.TumblrPost;
import com.valterc.external.tumblr.minecraft.GetMinecraftUpdatesAsyncTask;
import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.GenericItem;

import java.util.ArrayList;

public class MainFragment extends Fragment implements GetMinecraftUpdatesAsyncTask.GetMinecraftUpdatesListener {

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private View layoutLoading;
    private View layoutErrorLoading;
    private MainFragmentRecyclerAdapter recyclerAdapter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_main, container, false);

        layoutLoading = baseView.findViewById(R.id.relativeLayoutLoading);
        layoutErrorLoading = baseView.findViewById(R.id.relativeLayoutErrorLoading);

        recyclerView = (RecyclerView) baseView.findViewById(R.id.recyclerViewMinecraftUpdates);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<MainFragmentItem> mainFragmentItems = new ArrayList<>();
        mainFragmentItems.add(new MainFragmentItem(SteveCraftsApp.getDataManager().getRandomItem(), true));
        mainFragmentItems.add(new MainFragmentItem("Latest updates"));
        for (GenericItem item : SteveCraftsApp.getDataManager().getMostRecentItems()){
            mainFragmentItems.add(new MainFragmentItem(item));
        }

        recyclerAdapter = new MainFragmentRecyclerAdapter(getActivity(), mainFragmentItems);
        recyclerView.setAdapter(recyclerAdapter);

        new GetMinecraftUpdatesAsyncTask().execute(new GetMinecraftUpdatesAsyncTask.GetMinecraftUpdatesInfo(getActivity(), this));

        layoutLoading.setVisibility(View.INVISIBLE);

        return baseView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            //throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onGetMinecraftUpdatesComplete(ArrayList<TumblrPost> posts) {
        if (posts != null){
            recyclerAdapter.addItem(new MainFragmentItem("Latest Minecraft updates"));
            for (TumblrPost post : posts){
                recyclerAdapter.addItem(new MainFragmentItem(post));
            }
            recyclerAdapter.notifyItemRangeInserted(recyclerAdapter.getItemCount() - posts.size() - 1, posts.size() + 1);
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
