package com.example.vaccnow.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class VaccNowUtils {

    public static Date getDate(Long dateLong) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTimeInMillis(dateLong);
        return c.getTime();
    }

    public static Long getTimeInMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    public static String getRandomScheduledVaccinationRequest() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;

        return new Random().ints(leftLimit, rightLimit + 1).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

    }

    public static Date add15MinuteToDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 15);
        return calendar.getTime();
    }

}
