package com.mingzhang.function.udf;

import org.apache.flink.table.functions.ScalarFunction;
import scala.math.BigDecimal;
import scala.math.BigInt;

import java.sql.Timestamp;


public class All2Long_UDF extends ScalarFunction implements MyAll_UDATF<All2Long_UDF> {
    public Long eval(Integer number) {
        return new Long(number);
    }

    public Long eval(Float number) {
        return number.longValue();
    }

    public Long eval(Double number) {
        return number.longValue();
    }

    public Long eval(BigInt number) {
        return number.longValue();
    }

    public Long eval(Long number) {
        return number;
    }

    public Long eval(BigDecimal number) {
        return number.longValue();
    }

    public Long eval(Timestamp timestamp) {
        return timestamp.getTime();
    }

}