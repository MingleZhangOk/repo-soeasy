/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Flink_Windows_Demo
 * Author:   h
 * Date:     2018/11/29 11:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

package com.mingzhang.table.tests.flin_window;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.util.Properties;

public class Flink_Windows_Demo {
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
        DataStream rebalance = env.addSource(consumer).map(new MapFunction_Tuple10()).rebalance();

        WindowedStream stream = rebalance.keyBy(0).timeWindow(Time.seconds(10));

        tableEnv.registerDataStream("kafkaTable", rebalance, "a,b,c,d,e,f,g,h,i,j");

        Table table = tableEnv.sqlQuery("select * from kafkaTable where a = '1'");
        table.printSchema();
        DataStream<Row> rowDataStream = tableEnv.toAppendStream(table, Row.class);
        rowDataStream.print();

        env.execute("kafkaTable_Demo");
    }
}*/
