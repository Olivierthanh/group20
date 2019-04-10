package com.quanlychitieu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static Date getDate(String date) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(date);
    }
}
