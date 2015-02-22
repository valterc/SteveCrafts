package com.vcutils.utils;

import android.util.Log;

import com.vcutils.BuildConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class DebugLog {

    private DebugLog() {

    }

    private static Boolean sDebug;

    /**
     * Is {@link BuildConfig#DEBUG} still broken for library projects? If so, use this.</p>
     * <p/>
     * See: https://code.google.com/p/android/issues/detail?id=52962</p>
     *
     * @return {@code true} if this is a debug build, {@code false} if it is a production build.
     */
    public static boolean isDebugBuild() {
        if (sDebug == null) {
            try {
                final Class<?> activityThread = Class.forName("android.app.ActivityThread");
                final Method currentPackage = activityThread.getMethod("currentPackageName");
                final String packageName = (String) currentPackage.invoke(null, (Object[]) null);
                final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
                final Field DEBUG = buildConfig.getField("DEBUG");
                DEBUG.setAccessible(true);
                sDebug = DEBUG.getBoolean(null);
            } catch (final Throwable t) {
                final String message = t.getMessage();
                if (message != null && message.contains("BuildConfig")) {
                    // Proguard obfuscated build. Most likely a production build.
                    sDebug = false;
                } else {
                    sDebug = BuildConfig.DEBUG;
                }
            }
        }
        return sDebug;
    }

    private static String getTagBasedOnStack() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String className = stackTraceElements[4].getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        return className + "::" + stackTraceElements[4].getMethodName() + "@" + stackTraceElements[4].getLineNumber();
    }


    public static void e(String text, Throwable t) {
        e(getTagBasedOnStack(), text, t);
    }

    public static void e(String tag, String text, Throwable t) {
        if (isDebugBuild()) {
            Log.e(tag, text, t);
        }
    }

    public static void e(String tag, String text) {
        if (isDebugBuild()) {
            Log.e(tag, text);
        }
    }

    public static void e(String text) {
        e(getTagBasedOnStack(), text);
    }

    public static void i(String tag, String text) {
        if (isDebugBuild()) {
            Log.i(tag, text);
        }
    }

    public static void i(String text) {
        i(getTagBasedOnStack(), text);
    }

    public static void d(String tag, String text) {
        if (isDebugBuild()) {
            Log.d(tag, text);
        }
    }

    public static void d(String text) {
        d(getTagBasedOnStack(), text);
    }

}
