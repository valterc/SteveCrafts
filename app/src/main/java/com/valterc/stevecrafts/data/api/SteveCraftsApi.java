package com.valterc.stevecrafts.data.api;

import android.graphics.Bitmap;

import com.vcutils.DownloadResponse;
import com.vcutils.Downloader;

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

    public SteveCraftsApi(){

    }

    private String getKey(){
        return "0";
    }

    public void getData(){

    }

    public void getNewData(Date lastDataDate){

    }

    public Bitmap getImage(String type, String id) throws IOException {
        DownloadResponse<Bitmap> response = Downloader.downloadImage(String.format(API_URL_BASE + API_GET_IMAGE, getKey(), type, id));
        if (response.getResponse() != null){
            return response.getResponse();
        } else if (response.getStatusCode() == 404) {
            return null;
        } else {
            throw new IOException("Error downloading image.");
        }
    }

}
