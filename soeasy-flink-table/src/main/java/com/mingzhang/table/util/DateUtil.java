package com.mingzhang.table.util;


import com.mingzhang.table.common.CodeString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateUtil {


    public static boolean   compareCurrentDateIsAfter(String  compareDate){

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentFormatDate = LocalDate.now().format(df);
        return compreDateAfter(currentFormatDate,compareDate);
    }


    public static boolean   compareCurrentDateIsBefore(String compareDate){

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentFormatDate = LocalDate.now().format(df);
        return compreDateBefore(currentFormatDate,compareDate);
    }

    public static boolean compreDateAfter(String compareDate1,String compareDate2){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(compareDate1,df).isAfter( LocalDate.parse(compareDate2,df));
    }

    public static boolean compreDateBefore(String compareDate1,String compareDate2){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(compareDate1,df).isBefore( LocalDate.parse(compareDate2,df));
    }


    public static LocalDate getFormatDate(String formatType, String dateval)  {
        DateTimeFormatter   dateTimeFormatter =  DateTimeFormatter.ofPattern(formatType);
        return  LocalDate.parse(dateval,dateTimeFormatter);
    }

    public static String getFormatDateStr(String formatType,LocalDate localDate){
        DateTimeFormatter   dateTimeFormatter =  DateTimeFormatter.ofPattern(formatType);
        return  localDate.format(dateTimeFormatter);
    }


    public static  LocalDateTime getFormatDatetime(String formatType, String date)  {
        DateTimeFormatter   dateTimeFormatter =  DateTimeFormatter.ofPattern(formatType);
        return LocalDateTime.parse(date,dateTimeFormatter);
    }

    public static String getFormatDatetimeStr(String formatType,LocalDateTime localDateTime){
        DateTimeFormatter   dateTimeFormatter =  DateTimeFormatter.ofPattern(formatType);
        return localDateTime.format(dateTimeFormatter);
    }

    public static LocalTime getFormatTime(String formatType, String date)  {
        DateTimeFormatter   dateTimeFormatter =  DateTimeFormatter.ofPattern(formatType);
        return  LocalTime.parse(date,dateTimeFormatter);
    }

    public static String getFormatTimeStr(String formatType, LocalTime localTime)  {
        DateTimeFormatter   dateTimeFormatter =  DateTimeFormatter.ofPattern(formatType);
        return localTime.format(dateTimeFormatter);
    }

    public static String getFormatTimeStr(String formatType, Date date){
        DateFormat sdf = new SimpleDateFormat( formatType);
        return sdf.format(date);

    }

    public static String convertUtcToLocal(String dateFormat, String value) {
        LocalDateTime localDateTime =   getFormatDatetime(CodeString.DATE_FORMAT18_YYYYMMDDHHMMSS,value);
        return getFormatDatetimeStr(dateFormat,localDateTime.plusHours(8));
    }

     public static String getNextDay(String formatDate,String dateVal){
        return getNextDates(formatDate,dateVal,1);
     }

    public static  String getNextDates(String formatDate,String dateVal,long interVal){
        DateTimeFormatter   dateTimeFormatter =  DateTimeFormatter.ofPattern(formatDate);
        LocalDate dealDate =  LocalDate.parse(dateVal,dateTimeFormatter);
        LocalDate newDate = dealDate.plusDays(interVal);
        return   newDate.format(dateTimeFormatter);
    }

}
