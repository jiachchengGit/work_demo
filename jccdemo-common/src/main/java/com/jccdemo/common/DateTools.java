package com.jccdemo.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenjiacheng on 2016-12-16.
 */
public class DateTools {

    public static String format(Date date, FormatEnum format) {
        SimpleDateFormat sf = new SimpleDateFormat(format.getValue());
        return sf.format(date);
    }

    public  static  Date yearOffset(Date date,int year){
        return dateOffset(date,year,0,0,0,0,0,0);
    }
    public  static  Date monthOffset(Date date,int month){
        return dateOffset(date,0,month,0,0,0,0,0);
    }
    public  static  Date dayOffset(Date date,int day){
        return dateOffset(date,0,0,day,0,0,0,0);
    }
    public  static  Date hourOffset(Date date,int hour){
        return dateOffset(date,0,0,0,hour,0,0,0);
    }
    public  static  Date minutOffset(Date date,int minut){
        return dateOffset(date,0,0,0,0,minut,0,0);
    }
    public  static  Date secondOffset(Date date,int second){
        return dateOffset(date,0,0,0,0,0,second,0);
    }

    public static Date dateOffset(Date date, int year, int month, int day, int hour, int minute, int second, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        calendar.add(Calendar.MILLISECOND, milliSecond);
        return calendar.getTime();
    }

    public static enum FormatEnum {
        yyyy_MM_dd("yyyy-MM-dd"),
        HH$mm$ss("HH:mm:ss"),
        yyyy_MM_dd__HH$mm$ss("yyyy-MM-dd HH:mm:ss"),
        yyyyMMddHHmmss("yyyyMMddHHmmss");

        private String value;

        private FormatEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
