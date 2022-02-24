package com.breeze.core.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date addTime(int addType, int time) {
        return addTime(new Date(), addType, time);
    }

    public static Date addTime(Date date, int addType, int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(addType, time);
        return calendar.getTime();
    }
}
