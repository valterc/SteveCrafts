package com.valterc.stevecrafts.data.api;

import android.graphics.Bitmap;

import com.valterc.stevecrafts.data.model.Block;
import com.valterc.stevecrafts.data.model.Breaks;
import com.valterc.stevecrafts.data.model.Brewing;
import com.valterc.stevecrafts.data.model.CraftingRecipe;
import com.valterc.stevecrafts.data.model.Item;
import com.valterc.stevecrafts.data.model.Potion;
import com.valterc.stevecrafts.data.model.Smelting;
import com.vcutils.DownloadResponse;
import com.vcutils.Downloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Valter on 08/01/2015.
 */
public class SteveCraftsApi {

    private static final String API_URL_BASE = "http://valterc.com/sc/";
    private static final String API_GET_DATA = "getData.php?key=%s";
    private static final String API_GET_NEW_DATA = "getNewData.php?key=%s&time=%s";
    private static final String API_GET_IMAGE = "getImage.php?key=%s&type=%s&id=%s";

    private static SimpleDateFormat DATE_FORMAT;

    public static SimpleDateFormat getDateFormat() {
        if (DATE_FORMAT == null) {
            DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        }
        return DATE_FORMAT;
    }

    public SteveCraftsApi() {
    }


    public SteveCraftsData getData() throws JSONException {
        DownloadResponse<String> response = Downloader.downloadGet(String.format(API_URL_BASE + API_GET_DATA, getKey()));
        if (response.getResult() != DownloadResponse.DownloadResult.Ok) {
            return null;
        }
        return parseData(response.getResponse());
    }

    public SteveCraftsData getNewData(Date lastDataDate) throws JSONException {
        DownloadResponse<String> response = Downloader.downloadGet(String.format(API_URL_BASE + API_GET_NEW_DATA, getKey(), lastDataDate.getTime()));
        if (response.getResult() != DownloadResponse.DownloadResult.Ok) {
            return null;
        }
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

        jsonBase = null;

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < blocksArray.length(); i++) {
            JSONObject jsonBlock = blocksArray.getJSONObject(i);

            try {
                Block block = new Block(jsonBlock);
                blocks.add(block);
            } catch (JSONException e) {
                e.printStackTrace();
                //ignored
            }
        }
        blocksArray = null;
        System.gc();

        ArrayList<Breaks> breaks = new ArrayList<>();
        for (int i = 0; i < breaksArray.length(); i++) {
            JSONObject jsonBreaks = breaksArray.getJSONObject(i);

            try {
                Breaks breaks_ = new Breaks(jsonBreaks);
                breaks.add(breaks_);
            } catch (JSONException e) {
                e.printStackTrace();
                //ignored
            }
        }
        breaksArray = null;
        System.gc();

        ArrayList<Brewing> brewings = new ArrayList<>();
        for (int i = 0; i < brewingArray.length(); i++) {
            JSONObject jsonBrewing = brewingArray.getJSONObject(i);

            try {
                Brewing brewing = new Brewing(jsonBrewing);
                brewings.add(brewing);
            } catch (JSONException e) {
                e.printStackTrace();
                //ignored
            }
        }
        brewingArray = null;
        System.gc();

        ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<>();
        for (int i = 0; i < craftingArray.length(); i++) {
            JSONObject jsonCraft = craftingArray.getJSONObject(i);

            try {
                CraftingRecipe craftingRecipe = new CraftingRecipe(jsonCraft);
                craftingRecipes.add(craftingRecipe);
            } catch (JSONException e) {
                e.printStackTrace();
                //ignored
            }
        }
        craftingArray = null;
        System.gc();

        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject jsonItem = itemsArray.getJSONObject(i);

            try {
                Item item = new Item(jsonItem);
                items.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
                //ignored
            }
        }
        itemsArray = null;
        System.gc();

        ArrayList<Potion> potions = new ArrayList<>();
        for (int i = 0; i < potionsArray.length(); i++) {
            JSONObject jsonPotion = potionsArray.getJSONObject(i);

            try {
                Potion potion = new Potion(jsonPotion);
                potions.add(potion);
            } catch (JSONException e) {
                e.printStackTrace();
                //ignored
            }
        }
        potionsArray = null;
        System.gc();

        ArrayList<Smelting> smeltings = new ArrayList<>();
        for (int i = 0; i < smeltingArray.length(); i++) {
            JSONObject jsonSmelting = smeltingArray.getJSONObject(i);

            try {
                Smelting smelting = new Smelting(jsonSmelting);
                smeltings.add(smelting);
            } catch (JSONException e) {
                e.printStackTrace();
                //ignored
            }
        }
        smeltingArray = null;
        System.gc();

        return new SteveCraftsData(blocks, breaks, brewings, craftingRecipes, items, potions, smeltings);
    }

}
