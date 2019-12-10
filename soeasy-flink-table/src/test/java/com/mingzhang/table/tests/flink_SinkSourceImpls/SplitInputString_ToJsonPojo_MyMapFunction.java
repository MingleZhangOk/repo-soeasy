package com.mingzhang.table.tests.flink_SinkSourceImpls;

import com.mingzhang.table.tests.mainFile.JsonPojo;
import org.apache.flink.api.common.functions.MapFunction;

public class SplitInputString_ToJsonPojo_MyMapFunction implements MapFunction<String, JsonPojo> {
    @Override
    public JsonPojo map(String value) throws Exception {
        String[] strings = value.split("\\s+");
        if (strings.length != 5) {

        }
        return null;
    }
}
