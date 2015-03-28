package com.valterc.stevecrafts.data.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;

import com.valterc.stevecrafts.data.model.Block;
import com.valterc.stevecrafts.data.model.Breaks;
import com.valterc.stevecrafts.data.model.Brewing;
import com.valterc.stevecrafts.data.model.CraftingRecipe;
import com.valterc.stevecrafts.data.model.Item;
import com.valterc.stevecrafts.data.model.Potion;
import com.valterc.stevecrafts.data.model.Smelting;
import com.vcutils.utils.DebugLog;
import com.vcutils.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by Valter on 18/05/2014.
 */
public class DataSource {

    private DataSQLiteHelper dataSQLiteHelper;

    public DataSource(Context c) {
        dataSQLiteHelper = new DataSQLiteHelper(c);
    }

    private SQLiteDatabase getDatabase() {
        return dataSQLiteHelper.getDatabase();
    }

    public void dispose() {
        dataSQLiteHelper.disposeDatabase();
    }


    public ArrayList<Block> getBlocks() {

        ArrayList<Block> blocks = new ArrayList<Block>();

        Cursor c = getDatabase().query(
                "blocks",
                new String[]{
                        "id",
                        "minecraft_block_id",
                        "minecraft_data_value",
                        "minecraft_id",
                        "type",
                        "category",
                        "physics",
                        "transparency",
                        "luminance",
                        "blast_resistance",
                        "stackable",
                        "flamable",
                        //"image",
                        "name_en",
                        "name_pt",
                        "name_de",
                        "name_es",
                        "name_fr",
                        "name_pl",
                        "timestamp",
                }, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                Block block = new Block(c);
                blocks.add(block);
            } while (c.moveToNext());
        }

        c.close();

        return blocks;
    }

    public ArrayList<Breaks> getBreaks() {

        ArrayList<Breaks> breaks = new ArrayList<Breaks>();

        Cursor c = getDatabase().query(
                "breaks",
                new String[]{
                        "id",
                        "item_id",
                        "block_id",
                        "silktouch",
                        "anytool",
                        "drop_id",
                        "drop_type",
                        "drop_count",
                        "drop_count_min",
                        "drop_count_max",
                        "timestamp",
                }, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                Breaks breaks_ = new Breaks(c);
                breaks.add(breaks_);
            } while (c.moveToNext());
        }

        c.close();

        return breaks;
    }

    public ArrayList<Brewing> getBrewings() {

        ArrayList<Brewing> brewings = new ArrayList<Brewing>();

        Cursor c = getDatabase().query(
                "brewings",
                new String[]{
                        "id",
                        "ingredient_id",
                        "begin_item_type",
                        "begin_item_id",
                        "result_item_id",
                        "timestamp",
                }, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                Brewing brewing = new Brewing(c);
                brewings.add(brewing);
            } while (c.moveToNext());
        }

        c.close();

        return brewings;
    }

    public ArrayList<CraftingRecipe> getCraftingRecipes() {

        ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<CraftingRecipe>();

        Cursor c = getDatabase().query(
                "crafting_recipes",
                new String[]{
                        "id",
                        "type",
                        "craft_id",
                        "count",
                        "slot_0_id",
                        "slot_0_type",
                        "slot_0_count",
                        "slot_1_id",
                        "slot_1_type",
                        "slot_1_count",
                        "slot_2_id",
                        "slot_2_type",
                        "slot_2_count",
                        "slot_3_id",
                        "slot_3_type",
                        "slot_3_count",
                        "slot_4_id",
                        "slot_4_type",
                        "slot_4_count",
                        "slot_5_id",
                        "slot_5_type",
                        "slot_5_count",
                        "slot_6_id",
                        "slot_6_type",
                        "slot_6_count",
                        "slot_7_id",
                        "slot_7_type",
                        "slot_7_count",
                        "slot_8_id",
                        "slot_8_type",
                        "slot_8_count",
                        "timestamp",
                }, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                CraftingRecipe craftingRecipe = new CraftingRecipe(c);
                craftingRecipes.add(craftingRecipe);
            } while (c.moveToNext());
        }

        c.close();

        return craftingRecipes;
    }

    public ArrayList<Item> getItems() {

        ArrayList<Item> items = new ArrayList<Item>();

        Cursor c = getDatabase().query(
                "items",
                new String[]{
                        "id",
                        "minecraft_id",
                        "minecraft_data_value",
                        "durability",
                        "stackable",
                        "damage",
                        "armor",
                        "type",
                        //"image",
                        "name_en",
                        "name_pt",
                        "name_de",
                        "name_es",
                        "name_fr",
                        "name_pl",
                        "timestamp",
                }, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                Item item = new Item(c);
                items.add(item);
            } while (c.moveToNext());
        }

        c.close();

        return items;
    }

    public ArrayList<Potion> getPotions() {

        ArrayList<Potion> potions = new ArrayList<Potion>();

        Cursor c = getDatabase().query(
                "potions",
                new String[]{
                        "id",
                        "duration",
                        "health",
                        "speed",
                        "attack",
                        //"image",
                        "name_en",
                        "name_pt",
                        "name_de",
                        "name_es",
                        "name_fr",
                        "name_pl",
                        "timestamp",
                }, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                Potion potion = new Potion(c);
                potions.add(potion);
            } while (c.moveToNext());
        }

        c.close();

        return potions;
    }

    public ArrayList<Smelting> getSmeltings() {

        ArrayList<Smelting> smeltings = new ArrayList<Smelting>();

        Cursor c = getDatabase().query(
                "smeltings",
                new String[]{
                        "id",
                        "ingredient_type",
                        "ingredient_id",
                        "result_type",
                        "result_id",
                        "result_count",
                        "experience",
                        "dont_recommend",
                        "timestamp",
                }, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                Smelting smelting = new Smelting(c);
                smeltings.add(smelting);
            } while (c.moveToNext());
        }

        c.close();

        return smeltings;
    }

    public Bitmap getBlockImage(String blockId) {
        return getImage("blocks", blockId);
    }

    public Bitmap getItemImage(String itemId) {
        return getImage("items", itemId);
    }

    public Bitmap getPotionImage(String potionId) {
        return getImage("potions", potionId);
    }


    public void insertBlocks(ArrayList<Block> blocks) {

        getDatabase().beginTransaction();

        SQLiteStatement statement = getDatabase().compileStatement("INSERT OR REPLACE INTO blocks VALUES ( " +
                "?, " + /*id*/
                "?, " + /*minecraft_block_id*/
                "?, " + /*minecraft_data_value*/
                "?, " + /*minecraft_id*/
                "?, " + /*type*/
                "?, " + /*category*/
                "?, " + /*physics*/
                "?, " + /*transparency*/
                "?, " + /*luminance*/
                "?, " + /*blast_resistance*/
                "?, " +  /*stackable*/
                "?, " +  /*flamable*/
                "?, " +  /*image*/
                "?, " +  /*name_en*/
                "?, " +  /*name_pt*/
                "?, " +  /*name_de*/
                "?, " +  /*name_es*/
                "?, " +  /*name_fr*/
                "?, " +  /*name_pl*/
                "? " +  /*timestamp*/
                ")");


        try {

            for (int i = 0; i < blocks.size(); i++) {
                Block block = blocks.get(i);

                statement.clearBindings();

                int index = 1;
                statement.bindString(index++, block.getId());
                statement.bindLong(index++, block.getMinecraftBlockId());
                statement.bindLong(index++, block.getMinecraftDataValue());
                statement.bindString(index++, block.getMinecraftId());
                statement.bindLong(index++, block.getType());
                statement.bindLong(index++, block.getCategory());
                statement.bindLong(index++, block.getPhysics());
                statement.bindLong(index++, block.getTransparency());
                statement.bindLong(index++, block.getLuminance());
                statement.bindLong(index++, block.getBlastResistance());
                statement.bindLong(index++, block.getStackable());
                statement.bindLong(index++, block.getFlamable());

                if (block.getImage() != null) {
                    statement.bindBlob(index++, ImageUtils.bitmapToByteArray(block.getImage()));
                } else {
                    statement.bindNull(index++);
                }

                statement.bindString(index++, block.getNameEn());

                if (block.getNamePt() != null) {
                    statement.bindString(index++, block.getNamePt());
                } else {
                    statement.bindNull(index++);
                }

                if (block.getNameDe() != null) {
                    statement.bindString(index++, block.getNameDe());
                } else {
                    statement.bindNull(index++);
                }

                if (block.getNameEs() != null) {
                    statement.bindString(index++, block.getNameEs());
                } else {
                    statement.bindNull(index++);
                }

                if (block.getNameFr() != null) {
                    statement.bindString(index++, block.getNameFr());
                } else {
                    statement.bindNull(index++);
                }

                if (block.getNamePl() != null) {
                    statement.bindString(index++, block.getNamePl());
                } else {
                    statement.bindNull(index++);
                }

                statement.bindLong(index++, block.getTimestamp());

                long result = statement.executeInsert();
                DebugLog.d("Block inserted into DB, result: " + result);
            }

        } catch (Exception e) {
            DebugLog.d(e.getMessage());
        }

        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();
    }

    public void insertBreaks(ArrayList<Breaks> breaks) {

        getDatabase().beginTransaction();

        SQLiteStatement statement = getDatabase().compileStatement("INSERT OR REPLACE INTO breaks VALUES ( " +
                "?, " + /*id*/
                "?, " + /*item_id*/
                "?, " + /*block_id*/
                "?, " + /*silktouch*/
                "?, " + /*anytool*/
                "?, " + /*drop_id*/
                "?, " + /*drop_type*/
                "?, " + /*drop_count*/
                "?, " + /*drop_count_min*/
                "?, " + /*drop_count_max*/
                "? " +  /*timestamp*/
                ")");


        try {

            for (int i = 0; i < breaks.size(); i++) {
                Breaks break_ = breaks.get(i);

                statement.clearBindings();

                int index = 1;
                statement.bindString(index++, break_.getId());
                statement.bindString(index++, break_.getItemId());
                statement.bindString(index++, break_.getBlockId());
                statement.bindLong(index++, break_.getSilktouch());
                statement.bindLong(index++, break_.getAnytool());
                statement.bindString(index++, break_.getDropId());
                statement.bindLong(index++, break_.getDropType());
                statement.bindLong(index++, break_.getDropCount());
                statement.bindLong(index++, break_.getDropCountMin());
                statement.bindLong(index++, break_.getDropCountMax());
                statement.bindLong(index++, break_.getTimestamp());

                long result = statement.executeInsert();
                DebugLog.d("Breaks inserted into DB, result: " + result);
            }

        } catch (Exception e) {
            DebugLog.d(e.getMessage());
        }

        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();
    }

    public void insertBrewings(ArrayList<Brewing> brewings) {

        getDatabase().beginTransaction();

        SQLiteStatement statement = getDatabase().compileStatement("INSERT OR REPLACE INTO brewings VALUES ( " +
                "?, " + /*id*/
                "?, " + /*ingredient_id*/
                "?, " + /*begin_item_type*/
                "?, " + /*begin_item_id*/
                "?, " + /*result_item_id*/
                "? " +  /*timestamp*/
                ")");


        try {

            for (int i = 0; i < brewings.size(); i++) {
                Brewing brewing = brewings.get(i);

                statement.clearBindings();

                int index = 1;
                statement.bindString(index++, brewing.getId());
                statement.bindString(index++, brewing.getIngredientId());
                statement.bindLong(index++, brewing.getBeginItemType());
                statement.bindString(index++, brewing.getBeginItemId());
                statement.bindString(index++, brewing.getResultItemId());
                statement.bindLong(index++, brewing.getTimestamp());

                long result = statement.executeInsert();
                DebugLog.d("Brewing inserted into DB, result: " + result);
            }

        } catch (Exception e) {
            DebugLog.d(e.getMessage());
        }

        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();
    }

    public void insertCraftingRecipes(ArrayList<CraftingRecipe> craftingRecipes) {

        getDatabase().beginTransaction();

        SQLiteStatement statement = getDatabase().compileStatement("INSERT OR REPLACE INTO crafting_recipes VALUES ( " +
                "?, " + /*id*/
                "?, " + /*type*/
                "?, " + /*craft_id*/
                "?, " + /*count*/

                "?, " + /*slot_0_id*/
                "?, " + /*slot_0_type*/
                "?, " + /*slot_0_count*/

                "?, " + /*slot_1_id*/
                "?, " + /*slot_1_type*/
                "?, " + /*slot_1_count*/

                "?, " + /*slot_2_id*/
                "?, " + /*slot_2_type*/
                "?, " + /*slot_2_count*/

                "?, " + /*slot_3_id*/
                "?, " + /*slot_3_type*/
                "?, " + /*slot_3_count*/

                "?, " + /*slot_4_id*/
                "?, " + /*slot_4_type*/
                "?, " + /*slot_4_count*/

                "?, " + /*slot_5_id*/
                "?, " + /*slot_5_type*/
                "?, " + /*slot_5_count*/

                "?, " + /*slot_6_id*/
                "?, " + /*slot_6_type*/
                "?, " + /*slot_6_count*/

                "?, " + /*slot_7_id*/
                "?, " + /*slot_7_type*/
                "?, " + /*slot_7_count*/

                "?, " + /*slot_8_id*/
                "?, " + /*slot_8_type*/
                "?, " + /*slot_8_count*/

                "? " +  /*timestamp*/
                ")");


        try {

            for (int i = 0; i < craftingRecipes.size(); i++) {
                CraftingRecipe craftingRecipe = craftingRecipes.get(i);

                statement.clearBindings();

                int index = 1;
                statement.bindString(index++, craftingRecipe.getId());
                statement.bindLong(index++, craftingRecipe.getType());
                statement.bindString(index++, craftingRecipe.getCraftId());
                statement.bindLong(index++, craftingRecipe.getCount());

                for (int j = 0; j < craftingRecipe.getSlots().length; j++) {
                    CraftingRecipe.Slot slot = craftingRecipe.getSlots()[j];
                    if (slot == null) {
                        statement.bindNull(index++);
                        statement.bindNull(index++);
                        statement.bindNull(index++);
                    } else {
                        statement.bindString(index++, slot.getId());
                        statement.bindLong(index++, slot.getType());
                        statement.bindLong(index++, slot.getCount());
                    }
                }

                statement.bindLong(index++, craftingRecipe.getTimestamp());

                long result = statement.executeInsert();
                DebugLog.d("Crafting recipe inserted into DB, result: " + result);
            }

        } catch (Exception e) {
            e.printStackTrace();
            DebugLog.d(e.getMessage());
        }

        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();
    }

    public void insertItems(ArrayList<Item> items){

        getDatabase().beginTransaction();

        SQLiteStatement statement = getDatabase().compileStatement("INSERT OR REPLACE INTO items VALUES ( " +
                "?, " + /*id*/
                "?, " + /*minecraft_id*/
                "?, " + /*minecraft_data_value*/
                "?, " + /*durability*/
                "?, " + /*stackable*/
                "?, " + /*damage*/
                "?, " + /*armor*/
                "?, " + /*type*/
                "?, " +  /*image*/
                "?, " +  /*name_en*/
                "?, " +  /*name_pt*/
                "?, " +  /*name_de*/
                "?, " +  /*name_es*/
                "?, " +  /*name_fr*/
                "?, " +  /*name_pl*/
                "? " +  /*timestamp*/
                ")");


        try {

            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);

                statement.clearBindings();

                int index = 1;
                statement.bindString(index++, item.getId());
                statement.bindString(index++, item.getMinecraftId());
                statement.bindLong(index++, item.getMinecraftDataValue());
                statement.bindLong(index++, item.getDurability());
                statement.bindLong(index++, item.getStackable());
                statement.bindLong(index++, item.getDamage());
                statement.bindLong(index++, item.getArmor());
                statement.bindLong(index++, item.getType());

                if (item.getImage() != null) {
                    statement.bindBlob(index++, ImageUtils.bitmapToByteArray(item.getImage()));
                } else {
                    statement.bindNull(index++);
                }

                statement.bindString(index++, item.getNameEn());

                if (item.getNamePt() != null) {
                    statement.bindString(index++, item.getNamePt());
                } else {
                    statement.bindNull(index++);
                }

                if (item.getNameDe() != null) {
                    statement.bindString(index++, item.getNameDe());
                } else {
                    statement.bindNull(index++);
                }

                if (item.getNameEs() != null) {
                    statement.bindString(index++, item.getNameEs());
                } else {
                    statement.bindNull(index++);
                }

                if (item.getNameFr() != null) {
                    statement.bindString(index++, item.getNameFr());
                } else {
                    statement.bindNull(index++);
                }

                if (item.getNamePl() != null) {
                    statement.bindString(index++, item.getNamePl());
                } else {
                    statement.bindNull(index++);
                }

                statement.bindLong(index++, item.getTimestamp());

                long result = statement.executeInsert();
                DebugLog.d("Item inserted into DB, result: " + result);
            }

        } catch (Exception e) {
            DebugLog.d(e.getMessage());
        }

        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();

    }

    public void insertPotions(ArrayList<Potion> potions){

        getDatabase().beginTransaction();

        SQLiteStatement statement = getDatabase().compileStatement("INSERT OR REPLACE INTO potions VALUES ( " +
                "?, " + /*id*/
                "?, " + /*duration*/
                "?, " + /*health*/
                "?, " + /*speed*/
                "?, " + /*attack*/
                "?, " +  /*image*/
                "?, " +  /*name_en*/
                "?, " +  /*name_pt*/
                "?, " +  /*name_de*/
                "?, " +  /*name_es*/
                "?, " +  /*name_fr*/
                "?, " +  /*name_pl*/
                "? " +  /*timestamp*/
                ")");


        try {

            for (int i = 0; i < potions.size(); i++) {
                Potion potion = potions.get(i);

                statement.clearBindings();

                int index = 1;
                statement.bindString(index++, potion.getId());
                statement.bindDouble(index++, potion.getDuration());
                statement.bindLong(index++, potion.getHealth());
                statement.bindLong(index++, potion.getSpeed());
                statement.bindLong(index++, potion.getAttack());

                if (potion.getImage() != null) {
                    statement.bindBlob(index++, ImageUtils.bitmapToByteArray(potion.getImage()));
                } else {
                    statement.bindNull(index++);
                }

                statement.bindString(index++, potion.getNameEn());

                if (potion.getNamePt() != null) {
                    statement.bindString(index++, potion.getNamePt());
                } else {
                    statement.bindNull(index++);
                }

                if (potion.getNameDe() != null) {
                    statement.bindString(index++, potion.getNameDe());
                } else {
                    statement.bindNull(index++);
                }

                if (potion.getNameEs() != null) {
                    statement.bindString(index++, potion.getNameEs());
                } else {
                    statement.bindNull(index++);
                }

                if (potion.getNameFr() != null) {
                    statement.bindString(index++, potion.getNameFr());
                } else {
                    statement.bindNull(index++);
                }

                if (potion.getNamePl() != null) {
                    statement.bindString(index++, potion.getNamePl());
                } else {
                    statement.bindNull(index++);
                }

                statement.bindLong(index++, potion.getTimestamp());

                long result = statement.executeInsert();
                DebugLog.d("Potion inserted into DB, result: " + result);
            }

        } catch (Exception e) {
            DebugLog.d(e.getMessage());
        }

        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();

    }

    public void insertSmeltings(ArrayList<Smelting> smeltings){

        getDatabase().beginTransaction();

        SQLiteStatement statement = getDatabase().compileStatement("INSERT OR REPLACE INTO smeltings VALUES ( " +
                "?, " + /*id*/
                "?, " + /*ingredient_type*/
                "?, " + /*ingredient_id*/
                "?, " + /*result_type*/
                "?, " + /*result_id*/
                "?, " + /*result_count*/
                "?, " + /*experience*/
                "?, " + /*dont_recommend*/
                "? " +  /*timestamp*/
                ")");


        try {

            for (int i = 0; i < smeltings.size(); i++) {
                Smelting smelting = smeltings.get(i);

                statement.clearBindings();

                int index = 1;
                statement.bindString(index++, smelting.getId());
                statement.bindLong(index++, smelting.getIngredientType());
                statement.bindString(index++, smelting.getIngredientId());
                statement.bindLong(index++, smelting.getResultType());
                statement.bindString(index++, smelting.getResultId());
                statement.bindLong(index++, smelting.getResultCount());
                statement.bindDouble(index++, smelting.getExperience());
                statement.bindLong(index++, smelting.getDontRecommend());
                statement.bindLong(index++, smelting.getTimestamp());

                long result = statement.executeInsert();
                DebugLog.d("Smelting inserted into DB, result: " + result);
            }

        } catch (Exception e) {
            DebugLog.d(e.getMessage());
        }

        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();

    }


    private Bitmap getImage(String typeTableName, String blockId) {
        Bitmap image = null;

        Cursor c = getDatabase().query(
                typeTableName,
                new String[]{
                        "id",
                        "image"
                }, "id == ?", new String[]{blockId}, null, null, null, null);

        if (c.moveToFirst()) {
            image = ImageUtils.byteArrayToBitmap(c.getBlob(c.getColumnIndex("image")));
        }

        c.close();

        return image;
    }



    /*
    private Mindcracker cursorToMindcracker(Cursor c) {
        Mindcracker mindcracker = null;

        String id = null;
        try {
            id = c.getString(c.getColumnIndex("id"));
        } catch (Exception e) {
            return null;
        }

        String name = c.getString(c.getColumnIndex("name"));
        String youtubeName = c.getString(c.getColumnIndex("youtube_name"));
        String youtubeId = c.getString(c.getColumnIndex("youtube_id"));
        String youtubePlaylistId = c.getString(c.getColumnIndex("youtube_plist_id"));
        String twitchId = c.getString(c.getColumnIndex("twitch_id"));
        int showTitleOnList = c.getInt(c.getColumnIndex("show_title_on_list"));
        int notifications = c.getInt(c.getColumnIndex("notifications"));
        int unseenVideoCount = c.getInt(c.getColumnIndex("unseen_video_count"));
        long hits = c.getLong(c.getColumnIndex("hits"));
        String lastVideoId = c.getString(c.getColumnIndex("last_video_id"));
        String lastVideoDateString = c.getString(c.getColumnIndex("last_video_date"));

        Date lastVideoDate = null;
        if (lastVideoDateString != null) {
            try {                                    //2014-04-24T16:01:15.000Z
                lastVideoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH).parse(lastVideoDateString);
            } catch (ParseException e) {
                lastVideoDate = GregorianCalendar.getInstance().getTime();
            }
        }

        mindcracker = new Mindcracker(id, name, youtubeName, youtubeId, youtubePlaylistId, twitchId, showTitleOnList == 1, notifications == 1, unseenVideoCount, hits, lastVideoId, lastVideoDateString == null ? null : lastVideoDate);

        return mindcracker;
    }


    public ArrayList<Mindcracker> getMindcrackers() {

        ArrayList<Mindcracker> mindcrackers = new ArrayList<Mindcracker>();

        Cursor c = getDatabase().query(
                "mindcrackers",
                new String[]{"id",
                        "name",
                        "youtube_name",
                        "youtube_id",
                        "youtube_plist_id",
                        "twitch_id",
                        "show_title_on_list",
                        "notifications",
                        "unseen_video_count",
                        "hits",
                        "last_video_id",
                        "last_video_date"
                }, null, null, null, null, "name COLLATE NOCASE", null);

        if (c.moveToFirst()) {
            do {
                Mindcracker m = cursorToMindcracker(c);
                if (m != null)
                    mindcrackers.add(m);
            } while (c.moveToNext());
        }

        c.close();

        return mindcrackers;
    }

    public ArrayList<Mindcracker> getFavoriteMindcrackers() {
        ArrayList<Mindcracker> mindcrackers = new ArrayList<Mindcracker>();

        Cursor c = getDatabase().rawQuery("SELECT * FROM mindcrackers m JOIN favorites f ON m.id = f.mindcracker_id", null);

        if (c.moveToFirst()) {
            do {
                Mindcracker m = cursorToMindcracker(c);
                if (m != null)
                    mindcrackers.add(m);
            } while (c.moveToNext());
        }

        c.close();

        return mindcrackers;
    }

    public Boolean updateMindcrackers(ArrayList<Mindcracker> mindcrackers) {

        Boolean result = true;

        SQLiteStatement statement = getDatabase().compileStatement("UPDATE mindcrackers SET " +
                "name = ?, " +
                "youtube_name = ?, " +
                "youtube_id = ?, " +
                "youtube_plist_id = ?, " +
                "twitch_id = ?, " +
                "show_title_on_list = ?, " +
                "notifications = ?, " +
                "unseen_video_count = ?, " +
                "hits = ?, " +
                "last_video_id = ?, " +
                "last_video_date = ? " +
                "WHERE id = ?");


        try {

            for (int i = 0; i < mindcrackers.size(); i++) {
                Mindcracker m = mindcrackers.get(i);

                statement.clearBindings();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);

                int index = 1;
                statement.bindString(index++, m.getName());
                statement.bindString(index++, m.getYoutubeName());
                statement.bindString(index++, m.getYoutubeId());
                statement.bindString(index++, m.getYoutubePlaylistId());
                statement.bindString(index++, m.getTwitchId());
                statement.bindLong(index++, m.getShowTitleOnList() ? 1 : 0);
                statement.bindLong(index++, m.getNotificationsEnabled() ? 1 : 0);
                statement.bindLong(index++, m.getUnseenVideoCount());
                statement.bindLong(index++, m.getHits());

                if (m.getLastVideoId() != null) {
                    statement.bindString(index++, m.getLastVideoId());
                }
                else {
                    statement.bindNull(index++);
                }

                if (m.getLastVideoDate() != null) {
                    statement.bindString(index++, sdf.format(m.getLastVideoDate()));
                }
                else {
                    statement.bindNull(index++);
                }

                statement.bindString(index++, m.getId());


                if (android.os.Build.VERSION.SDK_INT >= 11)
                    statement.executeUpdateDelete();
                else
                    statement.execute();
            }

        } catch (Exception e) {
            Log.e("DataSource", e.getMessage());
            result = false;
        }


        return result;
    }

    public Boolean updateFavoriteMindcrackers(ArrayList<Mindcracker> favoriteMindcrackers) {

        Boolean result = true;

        SQLiteStatement deleteStatement = getDatabase().compileStatement("DELETE FROM favorites");

        if (android.os.Build.VERSION.SDK_INT >= 11)
            deleteStatement.executeUpdateDelete();
        else
            deleteStatement.execute();

        deleteStatement.close();

        SQLiteStatement statement = getDatabase().compileStatement("INSERT INTO favorites VALUES (?, ?)");

        try {

            for (int i = 0; i < favoriteMindcrackers.size(); i++) {
                Mindcracker m = favoriteMindcrackers.get(i);

                int index = 1;
                statement.bindLong(index++, i);
                statement.bindString(index++, m.getId());

                statement.executeInsert();
            }

            statement.close();

        } catch (Exception e) {
            Log.e("DataSource", e.getMessage());
            result = false;
        }

        return result;
    }
*/


}
