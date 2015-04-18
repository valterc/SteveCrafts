package com.valterc.external.tumblr;

import com.vcutils.DownloadResponse;
import com.vcutils.Downloader;
import com.vcutils.utils.DebugLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Valter on 17/04/2015.
 */
public class TumblrAPI {

    private static final String URI_GET_POSTS_TEXT = "http://api.tumblr.com/v2/blog/%s/posts?api_key=%s&filter=text";
    private static final String URI_GET_POSTS_HTML = "http://api.tumblr.com/v2/blog/%s/posts?api_key=%s";

    private String key;

    public TumblrAPI(String apiKey){
        this.key = apiKey;
    }

    public ArrayList<TumblrPost> GetPostsText(String hostname) {

        DebugLog.d("before api");
        DownloadResponse<String> apiResponse = Downloader.downloadGet(String.format(URI_GET_POSTS_TEXT, hostname, this.key));
        DebugLog.d("after api");

        if (apiResponse.getResult() != DownloadResponse.DownloadResult.Ok || apiResponse.getResponse() == null) {
            return null;
        }

        ArrayList<TumblrPost> posts = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(apiResponse.getResponse());

            JSONArray jsonArray = jsonResponse.getJSONObject("response").getJSONArray("posts");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonPost = jsonArray.getJSONObject(i);

                TumblrPost post = new TumblrPost();
                post.setBlogName(jsonPost.getString("blog_name"));
                post.setId(jsonPost.getString("id"));
                post.setUrl(jsonPost.getString("post_url"));
                post.setSlug(jsonPost.getString("slug"));
                post.setType(jsonPost.getString("type"));
                post.setTimestamp(jsonPost.getLong("timestamp"));
                post.setShortUrl(jsonPost.getString("short_url"));
                post.setTitle(jsonPost.getString("title"));
                post.setBody(jsonPost.getString("body"));

                posts.add(post);
            }
            DebugLog.d("return posts");
            return posts;
        } catch (JSONException e) {
            //Ignore exceptions
        }

        return null;
    }

}
