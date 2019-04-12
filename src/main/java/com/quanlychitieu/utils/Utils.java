package com.quanlychitieu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static Date getDate(String date) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(date);
    }

    public static Date getAnHourLateThanNowDate(int noHours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, noHours);
        return calendar.getTime();
    }
}
