package com.mingzhang.function.udf;

import org.apache.flink.table.functions.ScalarFunction;

import java.sql.Timestamp;

public class UTC2Local_UDF extends ScalarFunction implements MyAll_UDATF<UTC2Local_UDF> {


    public Timestamp eval(Timestamp s) {

        long timestamp = s.getTime() + 28800000;
        return new Timestamp(timestamp);
    }

}
