package com.mingzhang.repo.date;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-01-10 14:21
 */
public class Data_TestNow {
    public static void main(String[] args) {
        System.out.println("获取系统当前时间:当天零点");
        printTimestamp(getToday());

        System.out.println("获取系统当前时间:昨天零点");
        printTimestamp(getYesToday());

        System.out.println("获取系统当前时间：当前整点");
        printTimestamp(getNowTime());

        System.out.println("获取系统当前时间：上一小时整点");
        printTimestamp(getLastHours());
    }

    private static void printTimestamp(long time) {
        System.out.println(time);
        Timestamp timestamp = new Timestamp(time);
        System.out.println(timestamp);
        System.out.println(getDateFormat(time, "yyyyMMddHHmm"));
    }

    private static long getToday() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    private static long getYesToday() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli() - 24 * 60 * 60 * 1000;
    }

    private static long getNowTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(LocalTime.now().getHour(), 0)).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    private static long getLastHours() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(LocalTime.now().getHour(), 0)).toInstant(ZoneOffset.ofHours(8)).toEpochMilli() - 60 * 60 * 1000;
    }

    private static String getDateFormat(long timestamp, String format) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }
}
