package com.zfc.study.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author zufeichao
 * @ProjectName springboot-easypoi
 * @Description TODO
 * @Date 2019-06-10 14:26
 **/
public class DateUtils {

    private static final String FORMAT_DATE_STR = "yyyy-MM-dd";

    private static final String FORMAT_DATE_TIME_STR = "yyyy-MM-dd HH:mm:ss";

    private static final String FORMAT_DATE_TIME_STR2 = "yyyyMMddHHmmss";

    public static String formatToString(LocalDate ld){
        return format(ld,FORMAT_DATE_STR);
    }

    public static String formatDateTimeToString(LocalDateTime localDateTime){
        return formatDateTime(localDateTime,FORMAT_DATE_TIME_STR);
    }

    public static String formatDateTimeToString2(LocalDateTime localDateTime){
        return formatDateTime(localDateTime,FORMAT_DATE_TIME_STR2);
    }

    /**
     * 根据指定日期格式格式化日期为String型
     */
    public static String format(LocalDate localDate, String formatter) {

        String s = StringUtils.EMPTY;
       if (localDate == null) {
            return s;
        }

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatter);
            s = dtf.format(localDate);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return s;
    }

    /**
     * 根据指定日期格式格式化日期为String型
     */
    public static String formatDateTime(LocalDateTime localDateTime, String formatter) {

        String s = StringUtils.EMPTY;
        if (localDateTime == null) {
            return s;
        }

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatter);
            s = dtf.format(localDateTime);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return s;
    }

/*    public static void main(String[] args) {
       String s =  formatToString(LocalDate.now());
       System.out.println(s);
       String s2 = formatDateTimeToString(LocalDateTime.now());
        System.out.println(s2);
   }*/

}
