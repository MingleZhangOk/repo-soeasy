package com.mingzhang.repo.date;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2019-12-18 23:52
 */
public class DateFormatTest {
    public static void main(String[] args) {

        demo1();
    }

    public static void demo1() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = Instant.ofEpochMilli(timestamp.getTime() + 8 * 60 * 60 * 1000);
        LocalDateTime localDate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));

        String dateStr = localDate.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        System.out.println(dateStr);
    }

    public static void demo2() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        LocalDateTime localDate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));

        String dateStr = localDate.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        System.out.println(dateStr);
    }
}
