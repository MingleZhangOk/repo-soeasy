package com.mingzhang.function.udf;

import org.apache.flink.table.functions.ScalarFunction;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class UTC2DateString_UDF extends ScalarFunction implements MyAll_UDATF<UTC2DateString_UDF> {

    public final static DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyyMMddHHmm");


    public String eval(Timestamp timestamp) {


        Instant instant = Instant.ofEpochMilli(timestamp.getTime()+8*60*60*1000);
        LocalDateTime  localDate = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));

       return localDate.format(dateTimeFormatter);
    }
}
