/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Flink_SQL_DemoTwo
 * Author:   h
 * Date:     2018/11/28 11:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

package com.mingzhang.table.tests.flink_sql;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple10;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;
import org.apache.flink.util.Collector;

import java.util.Properties;

public class Flink_SQL_DemoTwo {

    private static String zookeeper_host = "bigdata01:2181,bigdata02:2181,bigdata03:2181";
    private static String kafka_broker = "bigdata01:9092,bigdata02:9092,bigdata03:9092";
    private static String transaction_group = "kafka_SQL_Test";
    private static String topic = "createTest";

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);

        Properties prop = new Properties();
        prop.setProperty("zookeeper.connect", zookeeper_host);
        prop.setProperty("bootstrap.servers", kafka_broker);
        prop.setProperty("group.id", transaction_group);
        FlinkKafkaConsumer011 consumer011 = new FlinkKafkaConsumer011(topic, new SimpleStringSchema(), prop);
        DataStreamSource source = env.addSource(consumer011);
        SingleOutputStreamOperator map = source.map(new MapFunction<String, Row>() {
            @Override
            public Row map(String value) throws Exception {
                String[] strings = value.toString().split(",");
                return Row.of(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], strings[7], strings[8], strings[9]);
            }
        });
        SingleOutputStreamOperator operator1 = source.flatMap(new FlatMapFunction() {
            @Override
            public void flatMap(Object value, Collector out) throws Exception {
                String[] strings = value.toString().split(",");
                out.collect(Row.of(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], strings[7], strings[8], strings[9]));
        }
        });



        tableEnv.registerDataStream("kafkaTable", map, "one,two,three,four,five,six,seven,eight,nine,ten");
        Table table = tableEnv.sqlQuery("select * from kafkaTable");
        table.printSchema();
        TypeInformation<Tuple10<String, String, String, String, String,
                String, String, String, String, String>> typeInformation =
                TypeInformation.of(new TypeHint<Tuple10<String, String, String, String,
                        String, String, String, String, String, String>>() {
                });
        DataStream<Tuple10<String, String, String, String, String, String,
                String, String, String, String>> dataStream = tableEnv.toAppendStream(table, typeInformation);

        dataStream.print();

        env.execute("kafka_Table1");
    }
}*/
