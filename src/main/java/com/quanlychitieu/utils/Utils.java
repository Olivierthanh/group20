package com.quanlychitieu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public static List<Date> createDateListByYearMonth(YearMonth yearMonth){
        List<Date> dateList = new ArrayList<>();
        for(int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            dateList.add(Date.from(yearMonth.atDay(i).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        return dateList;
    }
}
