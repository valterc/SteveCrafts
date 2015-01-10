package com.vcutils.tasks.download;

import com.vcutils.data.cache.Cache;

/**
 * Created by Valter on 12/11/2014.
 */
public class DownloadImageRequest {

    private String imageUrl;
    private DownloadImageListener listener;
    private Cache cache;

    public DownloadImageRequest(String imageUrl, DownloadImageListener listener) {
        this.imageUrl = imageUrl;
        this.listener = listener;
    }

    public DownloadImageRequest(String imageUrl, DownloadImageListener listener, Cache cache) {
        this.imageUrl = imageUrl;
        this.listener = listener;
        this.cache = cache;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DownloadImageListener getListener() {
        return listener;
    }

    public void setListener(DownloadImageListener listener) {
        this.listener = listener;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

}
