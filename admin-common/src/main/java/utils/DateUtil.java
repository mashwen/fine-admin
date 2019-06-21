/*
 * 文件名：DateUtil.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：gavin
 * 修改时间：2016年7月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date convertStringToDateFull(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateStr);
        return date;
    }

    public static Date convertStringToDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        return date;
    }



    public static SimpleDateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static String formatDateStr(String format, String dateStr) throws ParseException {
        SimpleDateFormat sdf = getDateFormat(format);
        return sdf.format(convertStringToDate(dateStr));
    }

    public static String nextDay(String day,String format) {
        Date  date = parseDate(day,format);
        Date nextDate = add(date,1,5);
        return valueOf(nextDate,format);
    }

    public static String lastDate(String format) {
        Date  date = new Date();
        Date lastDate = add(date,-1,5);
        return valueOf(lastDate,format);
    }
    public static String valueOf(Date date, String format) {
        if (date == null) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String today(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    public static Date add(Date date, int interval, int intervalType) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(intervalType, interval);
        return c.getTime();
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static Date parseDate(String dateStr, String format) {
        Date date = null;

        try {
            date = (new SimpleDateFormat(format)).parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Failed to parse date: " + dateStr);
        }

        return date;
    }
}
