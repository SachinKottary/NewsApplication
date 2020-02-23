package com.sachin.newsapplication.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *  Class used for all operations related to date and time
 */
public class DateAndTimeUtils {

    public static final String YEARS = " Years ";
    public static final String MONTHS = " Months ";
    public static final String SECONDS = " Seconds ";
    public static final String MINUTES = " Minutes ";
    public static final String HOURS = " Hours ";
    public static final String WEEKS = " Week ";
    public static final String DAYS = " Days ";
    private static final String AGO = " Ago ";


    public static String getDate(long oldTimeInMillis) {
        String convTime = null;
        String suffix = AGO;
            Calendar calendar = Calendar.getInstance();
            Date oldDate = calendar.getTime();
            oldDate.setTime(oldTimeInMillis);
            Date nowTime = new Date();

            long dateDiff = nowTime.getTime() - oldDate.getTime();

            long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
            long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
            long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
            long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

            if (second < 60) {
                convTime = second + SECONDS + suffix;
            } else if (minute < 60) {
                convTime = minute + MINUTES + suffix;
            } else if (hour < 24) {
                convTime = hour + HOURS + suffix;
            } else if (day >= 7) {
                if (day > 360) {
                    convTime = (day / 30) + YEARS + suffix;
                } else if (day > 30) {
                    convTime = (day / 360) + MONTHS + suffix;
                } else {
                    convTime = (day / 7) + WEEKS + suffix;
                }
            } else if (day < 7) {
                convTime = day + DAYS + suffix;
            }

        return convTime;

    }

}