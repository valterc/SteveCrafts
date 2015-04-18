package com.valterc.external.tumblr.minecraft;

import android.content.Context;
import android.os.AsyncTask;

import com.valterc.external.tumblr.TumblrAPI;
import com.valterc.external.tumblr.TumblrPost;
import com.valterc.stevecrafts.R;

import java.util.ArrayList;

/**
 * Created by Valter on 19/04/2015.
 */
public class GetMinecraftUpdatesAsyncTask extends AsyncTask<GetMinecraftUpdatesAsyncTask.GetMinecraftUpdatesInfo, Void, ArrayList<TumblrPost>> {

    private GetMinecraftUpdatesListener listener;

    @Override
    protected ArrayList<TumblrPost> doInBackground(GetMinecraftUpdatesInfo... params) {
        this.listener = params[0].listener;
        TumblrAPI tumblrAPI = new TumblrAPI(params[0].context.getString(R.string.key_tumblr_api));
        return tumblrAPI.GetPostsText("mcupdate.tumblr.com");
    }

    @Override
    protected void onPostExecute(ArrayList<TumblrPost> posts) {
        super.onPostExecute(posts);
        if (listener != null){
            listener.onGetMinecraftUpdatesComplete(posts);
        }
    }

    public static class GetMinecraftUpdatesInfo {

        private Context context;
        private GetMinecraftUpdatesListener listener;

        public GetMinecraftUpdatesInfo(Context context, GetMinecraftUpdatesListener listener) {
            this.context = context;
            this.listener = listener;
        }

    }

    public interface GetMinecraftUpdatesListener {
        void onGetMinecraftUpdatesComplete(ArrayList<TumblrPost> posts);
    }

}
