/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Flink_SQL_Demo
 * Author:   h
 * Date:     2018/11/28 10:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

package com.mingzhang.table.tests.flink_sql;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
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

public class Flink_SQL_Demo {
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
        tableEnv.registerDataStream("myTable", source);
        //tableEnv.registerTableSource("myTable", (TableSource<?>) source);
        Table sqlQuery = tableEnv.sqlQuery("select * from myTable");
        //DataStream<Tuple2<Boolean, Bean_Demo>> stream = tableEnv.toRetractStream(sqlQuery, new Bean_Demo_impl());
        DataStream<Tuple2<Boolean, Row>> stream = tableEnv.toRetractStream(sqlQuery, Row.class);
        //DataStream<Row> rowDataStream = tableEnv.toAppendStream(sqlQuery, Row.class);
        stream.print();

        env.execute("kafka_Table");
    }
}*/
