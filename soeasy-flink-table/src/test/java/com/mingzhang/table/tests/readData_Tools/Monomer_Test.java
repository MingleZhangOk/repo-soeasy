/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Monomer_Test
 * Author:   h
 * Date:     2018/11/29 10:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

package com.mingzhang.table.tests.readData_Tools;

import com.mingzhang.table.tests.entity_Package.FR_Busirule_Info_Pojo;
import com.mingzhang.table.tests.flink_SinkSourceImpls.FR_Busirule_Info_FlinkSourceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.util.Properties;

public class Monomer_Test {
    public static void main(String[] args) throws Exception {
        Monomer_Test mt = new Monomer_Test();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //env.setParallelism(2);
        //env.enableCheckpointing(5000);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        EnvironmentSettings environmentSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, environmentSettings);

        mt.test2(env, tableEnv);

        env.execute("monomer_test");
    }

    public void test1(StreamExecutionEnvironment env, StreamTableEnvironment tableEnv) {
        DataStreamSource<FR_Busirule_Info_Pojo> streamSource = env.addSource(new FR_Busirule_Info_FlinkSourceFunction());
        tableEnv.registerDataStream("FR_BUSIRULE_INFO", streamSource, "RULE_ID,RULE_NAME,RULE_DESC,RULE_STATE,RULE_TYPE," +
                "RULE_CLASS,RULE_LEVEL,RULE_PRIORITY,OP_TIME,LOGIN_NO,OP_ORG,BAK,RULE_KIND,RULE_REASON,RISK_TYPE,BUIS_TYPE," +
                "APP_CLASS,RULE_RESOURCE,HANDLE_FLOW,WARNING_RESULT,CHECK_PRO,REM_SIGN,REM_STRATEGY,WARNING_SORT,OPER_CYSLE," +
                "WHAT_STRATEGY,INTERVEN_REASON,ORG_ID,PUB_STAT,ID");

        //String tmp = "select RULE_ID, APP_CLASS,ORG_ID orgList from FR_BUSIRULE_INFO where APP_CLASS  in( '1','3')  and PUB_STAT  ='1' AND RULE_STATE = '1'   union all select RULE_ID, APP_CLASS,LISTAGG(ORG_ID,',') as orgList  from FR_BUSIRULE_INFO where APP_CLASS ='2' and  ORG_ID!='100000' and RULE_STATE='1'  group by RULE_ID, APP_CLASS";
        String all = "select * from FR_BUSIRULE_INFO";
        Table table = tableEnv.sqlQuery(all);
        table.printSchema();

        DataStream<Tuple2<Boolean, FR_Busirule_Info_Pojo>> tuple2DataStream = tableEnv.toRetractStream(table, FR_Busirule_Info_Pojo.class);
        tuple2DataStream.print();
        tuple2DataStream.writeAsText("D:\\testData\\");
    }

    public void test2(StreamExecutionEnvironment env, StreamTableEnvironment tableEnv) {
        String zookeeper_host = "bigdata01:2181,bigdata02:2181,bigdata03:2181";
        String kafka_broker = "bigdata01:9092,bigdata02:9092,bigdata03:9092";
        String transaction_group = "kafka_SQL_Test";
        String topic = "createTest";
        Properties prop = new Properties();
        prop.setProperty("zookeeper.connect", zookeeper_host);
        prop.setProperty("bootstrap.servers", kafka_broker);
        prop.setProperty("group.id", transaction_group);

        FlinkKafkaConsumer011 consumer = new FlinkKafkaConsumer011(topic, new SimpleStringSchema(), prop);
        DataStreamSource<String> lines = env.addSource(consumer);
       */
/* DataStream<Tuple10<String, String, String, String, String, String, String, String, String, String>> dataStream =
                lines.map(new MapFunction_Tuple10()).rebalance();*//*

        tableEnv.registerDataStream("kafkaTable", lines, "CUSTOMLABEL,DTL__CAPXRESTART2," +
                "DTL__CAPXTIMESTAMP,DTL__CAPXUOW,INFA_OP_TYPE,INFA_TABLE_NAME,PASSWORD,FIRSTFLAGE,FIRSTLOGINTIME," +
                "LASTLOGINTIME");

        Table table = tableEnv.sqlQuery("select * from kafkaTable");
        table.printSchema();
        DataStream<Row> tuple2DataStream = tableEnv.toAppendStream(table, Row.class);
        //DataStream<Tuple2<Boolean, Row>> tuple2DataStream = tableEnv.toRetractStream(table, Row.class);
        tuple2DataStream.print();
    }

    public void test3(StreamExecutionEnvironment env, StreamTableEnvironment tableEnv) {

    }

    public void test4(StreamExecutionEnvironment env, StreamTableEnvironment tableEnv) {


    }

    public void test5(StreamExecutionEnvironment env, StreamTableEnvironment tableEnv) {


    }


}*/
