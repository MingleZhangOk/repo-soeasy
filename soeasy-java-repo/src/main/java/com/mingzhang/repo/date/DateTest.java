package com.mingzhang.repo.date;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * File Discriptor:
 *
 * @author MingZhang
 * @DATE 2020-01-09  0:02
 **/
public class DateTest {

    public static void main(String[] args) {

        String dateFmat = "yyyyMMddHHmm";
//
//
//        long millis = System.currentTimeMillis();
//        Timestamp timestamp = new Timestamp(millis - millis % (24 * 60 * 60 * 1000));
//        System.out.println(timestamp);
//
//        Instant instant = Instant.ofEpochMilli(timestamp.getTime() - 24 * 60 * 60 * 1000);
//        LocalDateTime dateTimeate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));
//        String startDateStr = dateTimeate.format(DateTimeFormatter.ofPattern(dateFmat));
//        System.out.println(startDateStr);
//
//        instant = Instant.ofEpochMilli(timestamp.getTime());
//        dateTimeate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));
//        String endDateStr = dateTimeate.format(DateTimeFormatter.ofPattern(dateFmat));
//        System.out.println(endDateStr);
//
//
//        Calendar instance = Calendar.getInstance();
//        instance.set(Calendar.HOUR_OF_DAY, -24);
//        instance.set(Calendar.MINUTE, 0);
//        instance.set(Calendar.SECOND, 0);
//        instance.set(Calendar.MILLISECOND, 0);
//        long timeInMillis = instance.getTimeInMillis();
//        timestamp = new Timestamp(timeInMillis);
//        System.out.println(timestamp);

//        long l = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
//        Timestamp timestamp1 = new Timestamp(l - 24 * 60 * 60 * 1000);
//        System.out.println("yestoday===" + timestamp1);
//        Instant instant = Instant.ofEpochMilli(l - 24 * 60 * 60 * 1000);
//        LocalDateTime dateTimeate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));
//        String startDateStr = dateTimeate.format(DateTimeFormatter.ofPattern(dateFmat));
//        System.out.println(startDateStr);
//
//
//        Timestamp timestamp = new Timestamp(l);
//        System.out.println("today===" + timestamp);
//        instant = Instant.ofEpochMilli(l);
//        dateTimeate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));
//        startDateStr = dateTimeate.format(DateTimeFormatter.ofPattern(dateFmat));
//        System.out.println(startDateStr);
//
//        Timestamp timestamp2 = new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
//        System.out.println("now===" + timestamp2);


        long currentTimeMillis = LocalDateTime.of(LocalDate.now(), LocalTime.of(LocalDateTime.now().getHour(), 0)).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        System.out.println("当前时间整点" + timestamp);
        Instant instant = Instant.ofEpochMilli(currentTimeMillis);
        LocalDateTime dateTimeate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));
        String startDateStr = dateTimeate.format(DateTimeFormatter.ofPattern(dateFmat));
        System.out.println(startDateStr);

//         currentTimeMillis = LocalDateTime.of(LocalDate.now(),LocalTime.of(LocalDateTime.now().getHour()-1,0)).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        instant = Instant.ofEpochMilli(currentTimeMillis - Integer.parseInt("1") * 60 * 60 * 1000);
        dateTimeate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));
        startDateStr = dateTimeate.format(DateTimeFormatter.ofPattern(dateFmat));
        System.out.println("当前时间整点前1小时" + timestamp);
        System.out.println(startDateStr);
    }
}
