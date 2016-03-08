package it.max.android.customroomsview.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseUtils {
    public DatabaseUtils() {
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
