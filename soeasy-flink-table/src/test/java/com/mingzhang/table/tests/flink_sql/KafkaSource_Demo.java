/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: KafkaSource_Demo
 * Author:   h
 * Date:     2018/11/28 14:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

package com.mingzhang.table.tests.flink_sql;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple10;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.util.Properties;

public class KafkaSource_Demo {
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
        FlinkKafkaConsumer011 consumer = new FlinkKafkaConsumer011(topic, new SimpleStringSchema(), prop);
        DataStreamSource<String> lines = env.addSource(consumer);
        DataStream<Tuple10<String, String, String, String, String, String, String, String, String, String>> dataStream = lines.map(new MapFunction<String, Tuple10<String, String, String, String, String, String,
                String, String, String, String>>() {
            @Override
            public Tuple10<String, String, String, String, String, String, String, String, String, String> map(String value) throws Exception {
                String[] split = value.split(",");
                Tuple10 tuple10 = new Tuple10();
                tuple10.f0 = split[0];
                tuple10.f1 = split[1];
                tuple10.f2 = split[2];
                tuple10.f3 = split[3];
                tuple10.f4 = split[4];
                tuple10.f5 = split[5];
                tuple10.f6 = split[6];
                tuple10.f7 = split[7];
                tuple10.f8 = split[8];
                tuple10.f9 = split[9];
                return tuple10;
            }
        }).rebalance();

        tableEnv.registerDataStream("kafkaTable", dataStream);

        Table table = tableEnv.sqlQuery("select f3 from kafkaTable");

        table.printSchema();

        DataStream<Row> rowDataStream = tableEnv.toAppendStream(table, Row.class);
        rowDataStream.print();

        env.execute("kafkaTable_Demo");
    }
}*/
