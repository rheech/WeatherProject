package com.lg.weather.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateLoader {
    public String DateLoader(){
        Date date = new Date();
        SimpleDateFormat sformat = new SimpleDateFormat("yyyyMMdd HHmm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.HOUR, -1);
        String now = sformat.format(calendar.getTime());

        return now;
    }
}
