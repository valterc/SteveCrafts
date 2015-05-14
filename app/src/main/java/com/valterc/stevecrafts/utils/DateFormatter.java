package com.valterc.stevecrafts.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Valter on 21/12/2014.
 */
public abstract class DateFormatter {

    private static DateFormat dateFormat;

    static {
        dateFormat = SimpleDateFormat.getDateInstance();
    }

    public static String format(long millis) {
        return format(new Date(millis));
    }

    public static String format(Date date) {
        long totalMillis = Calendar.getInstance().getTimeInMillis() - date.getTime();

        int seconds = (int) (totalMillis / 1000) % 60;
        int minutes = ((int) (totalMillis / 1000) / 60) % 60;
        int hours = (int) (totalMillis / 1000) / 3600;

        if (hours > 48) {
            return dateFormat.format(date);
        } else if (hours > 24) {
            return "1 day ago";
        } else if (hours > 0) {
            if (hours == 1) {
                return hours + " hour ago";
            }
            return hours + " hours ago";
        } else if (minutes > 0) {
            if (minutes == 1) {
                return minutes + " minute ago";
            }
            return minutes + " minutes ago";
        } else {
            return "a moment ago";
        }
    }
}
