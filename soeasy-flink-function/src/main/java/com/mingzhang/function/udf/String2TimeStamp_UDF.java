package com.mingzhang.function.udf;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.table.functions.ScalarFunction;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class String2TimeStamp_UDF extends ScalarFunction implements MyAll_UDATF<String2TimeStamp_UDF> {
    private static final long serialVersionUID = 5208518909504766234L;

    public Timestamp eval(String dateVal, String dataFormat) {

        if(StringUtils.isEmpty(dateVal)){
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dataFormat);
        LocalDateTime localDateTime =  LocalDateTime.parse(dateVal,dateTimeFormatter);
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return new Timestamp(instant.toEpochMilli());
    }

}
