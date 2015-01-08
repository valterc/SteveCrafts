package com.vcutils;

import android.util.Log;

public final class DebugLog {

    private DebugLog() {

    }

    public static void e(String tag, String text, Throwable t) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, text, t);
        }
    }

    public static void e(String tag, String text) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, text);
        }
    }

    public static void i(String tag, String text) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, text);
        }
    }

    public static void d(String tag, String text) {
        if (BuildConfig.DEBUG) {

            Log.d(tag, text);
        }
    }


}
