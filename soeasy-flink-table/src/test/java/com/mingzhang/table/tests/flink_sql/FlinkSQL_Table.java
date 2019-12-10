/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FlinkSQL
 * Author:   h
 * Date:     2018/11/28 9:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

package com.mingzhang.table.tests.flink_sql;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

*/
/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author h
 * @create 2018/11/28
 * @since 1.0.0
 *//*

public class FlinkSQL_Table {

    private static Properties Properties;
    private static String zookeeper_host;
    private static String kafka_broker;
    private static String transaction_group;
    private static String topics;
    private static String appName;

    static {
        try {
            Properties.load(new FileInputStream("/bip.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Properties != null) {
            zookeeper_host = Properties.getProperty("ZOOKEEPER_HOST").trim();
            kafka_broker = Properties.getProperty("KAFKA_BROKER").trim();
            transaction_group = Properties.getProperty("TRANSACTION_GROUP").trim();
            topics = Properties.getProperty("TOPICS").trim();
            appName = Properties.getProperty("FLINK_APP_NAME").trim();
        }
    }

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        StreamTableEnvironment tenv = StreamTableEnvironment.getTableEnvironment(env);
        Properties prop = new Properties();
        prop.setProperty("zookeeper.connect", zookeeper_host);
        prop.setProperty("bootstrap.servers", kafka_broker);
        prop.setProperty("group.id", transaction_group);
        FlinkKafkaConsumer011 consumer011 = new FlinkKafkaConsumer011(topics, new SimpleStringSchema(), prop);
        //DataStreamSource source = env.addSource(consumer011);

        SingleOutputStreamOperator<Tuple3<String, String, String>> source = env.socketTextStream("bigdata01", 9999).flatMap(new LineSplitter());
        tenv.registerDataStream("myTable", source, "id,name,age");
        Table sqlQuery = tenv.sqlQuery("select name from myTable");
        DataStream<Row> stream = tenv.toAppendStream(sqlQuery, Row.class);
        stream.print();
        env.execute(appName);
    }
}*/
