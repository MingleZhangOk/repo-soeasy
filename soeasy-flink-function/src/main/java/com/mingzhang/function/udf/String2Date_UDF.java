package com.mingzhang.function.udf;


import org.apache.commons.lang3.StringUtils;
import org.apache.flink.table.functions.ScalarFunction;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class String2Date_UDF extends ScalarFunction implements MyAll_UDATF<String2Date_UDF> {

    private static final long serialVersionUID = -3226793638557856384L;
    public Date eval(String dateVal, String dataFormat) {

        if(StringUtils.isEmpty(dateVal)){
            return null;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dataFormat);
        LocalDate localDate =  LocalDate.parse(dateVal,dateTimeFormatter);
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
         return new Date(instant.toEpochMilli());
    }
}
