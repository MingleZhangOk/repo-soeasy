package com.mingzhang.stream.common;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-15 10:21
 */
public class FlinkAPIDemo {
    public static void main(String[] args) throws Exception {

        List<String> list = new ArrayList<String>();
        list.add("");

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> stringDataStreamSource = executionEnvironment.addSource(new FlinkDefStringSource());
//        SingleOutputStreamOperator<String> map = stringDataStreamSource.map(new MapFunction<String, String>() {
//            @Override
//            public String map(String s) throws Exception {
//                System.out.println(s);
//                return s;
//            }
//        });
        SingleOutputStreamOperator<String> filter1 = stringDataStreamSource.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                return false;
            }
        });
        filter1.print();
        SingleOutputStreamOperator<String> filter2 = stringDataStreamSource.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                return false;
            }
        });
        KeyedStream<String, Tuple> stringTupleKeyedStream = stringDataStreamSource.keyBy("");

        filter2.print();
        stringDataStreamSource.print();
        executionEnvironment.execute("test");

    }
}
