package com.valterc.stevecrafts.data.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vcutils.utils.DebugLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Valter on 22/02/2015.
 */
public class DataSQLiteHelper {


    // ===========================================================
    // Constants
    // ===========================================================

    private final static String DB_FILE_NAME = "data";
    private final static int DB_VERSION = 1;
    private final static String DB_GET_VERSION = "pragma user_version;";
    private final static String DB_SET_VERSION = "pragma user_version = " + DB_VERSION + ";";
    private final static Boolean FORCE_UPDATE = true;
    private final static Boolean USE_DATABASE_FILE = false;

    // ===========================================================
    // Fields
    // ===========================================================

    private SQLiteDatabase database;
    private Context context;
    private String mDataPath;

    // ===========================================================
    // Constructor
    // ===========================================================

    public DataSQLiteHelper(Context c) {
        this.context = c;
        mDataPath = c.getApplicationContext().getExternalFilesDir(null) + "/";//c.getApplicationContext().getFilesDir().getPath() + "/";

        try {
            openDatabaseReadWrite(mDataPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ===========================================================
    // Methods
    // ===========================================================

    private void openDatabaseReadWrite(String path) throws Exception {
        try {

            if (database != null)
                database.close();

            if (!new File(path + DB_FILE_NAME).exists()) {
                Log.d(getClass().getSimpleName(), "Creating database...");
                createDatabase(path);
            }

            database = SQLiteDatabase.openDatabase(path + DB_FILE_NAME, null, SQLiteDatabase.OPEN_READWRITE);

            if (isDatabaseOutdated()) {
                Log.d(getClass().getSimpleName(), "Database is outdated and will be replaced.");
                closeDatabase();
                createDatabase(path);
            }

        } catch (Exception e) {
            if (database != null)
                database.close();
            database = null;
            throw e;
        }
    }

    private boolean isDatabaseOutdated() {

        if (FORCE_UPDATE) return true;

        int localVersion;

        try {
            Cursor c = database.rawQuery(DB_GET_VERSION, null);
            c.moveToFirst();
            localVersion = c.getInt(0);
            c.close();
        } catch (Exception e) {
            return true;
        }

        return localVersion < DB_VERSION;
    }

    private void createDatabase(String path) throws IOException {
        Log.d(getClass().getSimpleName(), "Database will be created");

        deleteDatabaseFile(path);

        File f = new File(path);
        f.mkdirs();

        if (USE_DATABASE_FILE) {
            DatabaseCreator.CopyDatabase(context, path + DB_FILE_NAME);
            database = SQLiteDatabase.openOrCreateDatabase(path + DB_FILE_NAME, null);
        } else {
            database = SQLiteDatabase.openOrCreateDatabase(path + DB_FILE_NAME, null);
            DatabaseCreator.CreateDatabase(context, database);
            database.execSQL(DB_SET_VERSION);
        }

        Log.d(getClass().getSimpleName(), "Database created!");
    }

    private void closeDatabase() {
        if (database == null)
            return;

        try {
            database.close();
        } catch (Exception ignored) {
        }

        database = null;
    }

    private void deleteDatabaseFile(String path) {
        closeDatabase();
        File f = new File(path + DB_FILE_NAME);
        try {
            boolean result = false;
            if (f.exists())
                result = f.delete();

            Log.d(getClass().getSimpleName(), "Delete database file, result: " + result);
        } catch (Exception e) {
            Log.d(getClass().getSimpleName(), "Error deleting database file! : " + e.getMessage());
        }
    }


    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void disposeDatabase() {
        closeDatabase();
        context = null;
    }

    public void backupDatabaseTo(String path){

        FileInputStream fileInputStream = null;
        byte[] buffer = new byte[1024];
        FileOutputStream fileOutputStream = null;

        try {

            fileInputStream = new FileInputStream(mDataPath + DB_FILE_NAME);
            fileOutputStream = new FileOutputStream(path, false);
            int read = fileInputStream.read(buffer);
            while(read > 0){
                fileOutputStream.write(buffer);
                read = fileInputStream.read(buffer);
            }
            fileOutputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            DebugLog.e(e.getMessage());
        }
    }

}
