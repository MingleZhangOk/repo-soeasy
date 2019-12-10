/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Flink_Connect_KafkaTable
 * Author:   h
 * Date:     2018/11/28 15:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

package com.mingzhang.table.tests.other_test;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple10;
import org.apache.flink.api.java.tuple.Tuple4;
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

public class Flink_Connect_KafkaTable {
    private static String zookeeper_host = "bigdata01:2181,bigdata02:2181,bigdata03:2181";
    private static String kafka_broker = "bigdata01:9092,bigdata02:9092,bigdata03:9092";
    private static String transaction_group = "kafka_SQL_Test";
    private static String topic = "createTest";

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);
        env.enableCheckpointing(5000);
        Properties prop = new Properties();
        prop.setProperty("zookeeper.connect", zookeeper_host);
        prop.setProperty("bootstrap.servers", kafka_broker);
        prop.setProperty("group.id", transaction_group);
        FlinkKafkaConsumer011 consumer = new FlinkKafkaConsumer011(topic, new SimpleStringSchema(), prop);
        DataStreamSource<String> lines = env.addSource(consumer);
        DataStream<Tuple10<String, String, String, String, String, String, String, String, String, String>> dataStream = lines.map(new MapFunction_Tuple10()).rebalance();

        tableEnv.registerDataStream("kafkaTable", dataStream);

        DataStream<Tuple4<String, String, String, String>> dataStream1 = env.addSource(new StudentSourceFromMysql()).map(new MapFunction<Student, Tuple4<String, String, String, String>>() {
            @Override
            public Tuple4<String, String, String, String> map(Student value) throws Exception {
                Tuple4 Tuple4 = new Tuple4();
                Tuple4.f0 = "" + value.stuid;
                Tuple4.f1 = value.stuname;
                Tuple4.f2 = value.stusex;
                Tuple4.f3 = value.stuaddr;
                return Tuple4;
            }
        }).rebalance();

        tableEnv.registerDataStream("studentTable", dataStream1);

        //Table table = tableEnv.sqlQuery("select * from kafkaTable");

       // Table table = tableEnv.sqlQuery("select * from studentTable");

        Table table = tableEnv.sqlQuery("select * from studentTable stu,kafkaTable kafka where kafka.f4 = stu.f0");

        table.printSchema();

        DataStream<Row> rowDataStream = tableEnv.toAppendStream(table, Row.class);
        rowDataStream.print();

        env.execute("kafkaTable_Demo");
    }
}*/
