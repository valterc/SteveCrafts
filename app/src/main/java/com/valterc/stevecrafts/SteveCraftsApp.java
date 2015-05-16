package com.valterc.stevecrafts;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;

import com.valterc.stevecrafts.data.DataManager;

import java.util.Locale;

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

        //TODO: Load settings
        //TODO: Set language
        setLocale();
    }

    private void setLocale(){
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        }
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}
