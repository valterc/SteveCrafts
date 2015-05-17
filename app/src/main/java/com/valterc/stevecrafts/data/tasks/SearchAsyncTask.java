package com.valterc.stevecrafts.data.tasks;

import android.os.AsyncTask;

import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.model.GenericItem;

import java.util.ArrayList;

/**
 * Created by Valter on 16/05/2015.
 */
public class SearchAsyncTask extends AsyncTask<Void, Void, ArrayList<GenericItem>> {

    private SearchTaskListener listener;
    private String query;

    public SearchAsyncTask(SearchTaskListener listener, String query) {
        this.listener = listener;
        this.query = query;
    }

    @Override
    protected ArrayList<GenericItem> doInBackground(Void... params) {
        return SteveCraftsApp.getDataManager().search(this.query);
    }

    @Override
    protected void onPostExecute(ArrayList<GenericItem> items) {
        super.onPostExecute(items);
        if (listener != null) {
            listener.onSearchComplete(this.query, items);
        }
    }

    public interface SearchTaskListener {
        void onSearchComplete(String query, ArrayList<GenericItem> items);
    }
}