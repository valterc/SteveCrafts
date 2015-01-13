package com.valterc.stevecrafts.data.api;

import android.graphics.Bitmap;

import com.vcutils.DownloadResponse;
import com.vcutils.Downloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Valter on 08/01/2015.
 */
public class SteveCraftsApi {

    private static final String API_URL_BASE = "http://valterc.com/sc/";
    private static final String API_GET_DATA = "getData.php?key=%s";
    private static final String API_GET_NEW_DATA = "getNewData.php?key=%s&time=%s";
    private static final String API_GET_IMAGE = "getImage.php?key=%s&type=%s&id=%s";

    public SteveCraftsApi() {

    }


    public SteveCraftsData getData() throws JSONException {
        DownloadResponse<String> response = Downloader.downloadGet(String.format(API_URL_BASE + API_GET_DATA, getKey()));
        return parseData(response.getResponse());
    }

    public SteveCraftsData getNewData(Date lastDataDate) throws JSONException {
        DownloadResponse<String> response = Downloader.downloadGet(String.format(API_URL_BASE + API_GET_NEW_DATA, getKey(), lastDataDate.getTime()));
        return parseData(response.getResponse());
    }

    public Bitmap getImage(String type, String id) throws IOException {
        DownloadResponse<Bitmap> response = Downloader.downloadImage(String.format(API_URL_BASE + API_GET_IMAGE, getKey(), type, id));
        if (response.getResponse() != null) {
            return response.getResponse();
        } else if (response.getStatusCode() == 404) {
            return null;
        } else {
            throw new IOException("Error downloading image.");
        }
    }

    private String getKey() {
        return "0";
    }

    private SteveCraftsData parseData(String jsonResponse) throws JSONException {
        JSONObject jsonBase = new JSONObject(jsonResponse);
        JSONArray blocksArray = jsonBase.getJSONArray("blocks");
        JSONArray breaksArray = jsonBase.getJSONArray("breaks");
        JSONArray brewingArray = jsonBase.getJSONArray("brewing");
        JSONArray craftingArray = jsonBase.getJSONArray("crafting");
        JSONArray itemsArray = jsonBase.getJSONArray("items");
        JSONArray potionsArray = jsonBase.getJSONArray("potions");
        JSONArray smeltingArray = jsonBase.getJSONArray("smelting");



        return null;
    }

}
