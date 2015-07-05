package com.valterc.stevecrafts.drawer.viewholders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.data.model.GenericItem;
import com.valterc.stevecrafts.data.tasks.SearchAsyncTask;
import com.valterc.stevecrafts.drawer.NavigationDrawerAdapter;
import com.valterc.stevecrafts.drawer.NavigationDrawerItem;

import java.util.ArrayList;

/**
 * Created by Valter on 15/05/2015.
 */
public class SearchViewHolder extends NavigationDrawerItemViewHolder implements TextWatcher, SearchAsyncTask.SearchTaskListener {

    private NavigationDrawerAdapter adapter;
    private String query;
    private View.OnClickListener clickListener;

    public SearchViewHolder(View itemView, NavigationDrawerAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        EditText editTextSearch = (EditText) itemView.findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(this);
    }

    @Override
    public void update(NavigationDrawerItem item) {
        this.clickListener = item.getClickListener();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //ignore
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //ignore
    }

    @Override
    public void afterTextChanged(Editable s) {
        this.query = s.toString();
        if (this.query.isEmpty()) {
            adapter.clearSearchResults();
        } else {
            new SearchAsyncTask(this, this.query).execute();
        }
    }

    @Override
    public void onSearchComplete(String query, ArrayList<GenericItem> items) {
        if (query.equals(this.query)) {
            adapter.clearSearchResults();
            if (items.isEmpty()) {
                adapter.addSearchNoResults();
            } else {
                adapter.addSearchResults(items, this.clickListener);
            }
        }
    }
}
