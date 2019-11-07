package com.harman.meeting_management.common;

/**
 * @author L.Willian
 * @date 10/25/2019 1:48 PM
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 10 byte timestamp to UTC
 *
 * @L.Willian
 */

public class TimeStamp {
    public static String getTimeStamp(String timeStamp) {
        //1566798922
        if (timeStamp.trim().length() >= 10)
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(timeStamp.trim())));
        else
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(timeStamp) * 1000));
    }
}

