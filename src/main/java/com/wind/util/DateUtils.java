package com.wind.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author wind
 *
 */
public class DateUtils {
    /**
     * 日期按照指定格式转换成字符串
     * @param date  日期
     * @param pattern 默认 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateStr(Date date, String pattern){
        pattern = pattern == null ? "yyyy-MM-dd HH:mm:ss" : pattern;
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 返回当前时间month个月之后（month>0）或month个月之前（month<0）的时间
     *
     * @param date
     * @param month
     * @return
     */
    public static Date getDateM(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }
}
