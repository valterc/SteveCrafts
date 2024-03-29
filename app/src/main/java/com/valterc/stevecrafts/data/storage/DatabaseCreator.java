package com.valterc.stevecrafts.data.storage;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.valterc.stevecrafts.R;
import com.vcutils.utils.DebugLog;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Valter on 18/05/2014.
 */
public class DatabaseCreator {

    private static final String CREATE_TABLE_ANDROID_METADATA = "CREATE TABLE IF NOT EXISTS \"android_metadata\" (\"locale\" TEXT DEFAULT 'en_US');";
    private static final String INSERT_TABLE_ANDROID_METADATA = "INSERT INTO \"android_metadata\" VALUES ('en_US');";


    public static void CreateDatabase(Context context, SQLiteDatabase database) {

        String sql = getSqlCode(context);

        String[] statements = sql.split(";");
        for(String statement : statements){
            statement = statement.trim();
            if (statement.length() > 1) {
                database.execSQL(statement);
            }
        }

        database.execSQL(CREATE_TABLE_ANDROID_METADATA);
        database.execSQL(INSERT_TABLE_ANDROID_METADATA);

    }


    private static String getSqlCode(Context context) {

        String sql = null;

        try {
            Resources res = context.getResources();
            InputStream inputStream = res.openRawResource(R.raw.sql);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            sql = new String(b);
            inputStream.close();
        } catch (Exception e) {
            DebugLog.e(e.getMessage());
        }

        return sql;
    }

    public static void CopyDatabase(Context context, String path) {
        Resources res = context.getResources();
        InputStream inputStream = res.openRawResource(R.raw.data);
        byte[] buffer = new byte[1024];
        FileOutputStream fileOutputStream = null;

        try {

            fileOutputStream = new FileOutputStream(path, false);
            int read = inputStream.read(buffer);
            while(read > 0){
                fileOutputStream.write(buffer);
                read = inputStream.read(buffer);
            }
            fileOutputStream.close();
            inputStream.close();

        } catch (Exception e) {
            DebugLog.e(e.getMessage());
        }

    }
}
