package com.mingzhang.function.udf;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TestDate {

    public final static DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        System.out.println(localDateTime.toInstant(ZoneOffset.ofHours(0)).toEpochMilli());
       // long utcLong = localDateTime.toInstant(ZoneOffset.ofHours(0)).toEpochMilli();
       // long startLong = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();

       long utcLong =1568798186455l;
        Instant instant = Instant.ofEpochMilli(utcLong);
        LocalDateTime  localDate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));

        System.err.println(localDate.format(dateTimeFormatter));



    }
}
