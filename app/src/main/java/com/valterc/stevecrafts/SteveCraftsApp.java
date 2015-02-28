package com.valterc.stevecrafts;

import android.app.Application;

import com.valterc.stevecrafts.data.DataManager;

/**
 * Created by Valter on 14/02/2015.
 */
public class SteveCraftsApp extends Application {

    private static DataManager dataManager;
    public static DataManager getDataManager(){
        return dataManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dataManager = new DataManager(getApplicationContext());

    }


}
