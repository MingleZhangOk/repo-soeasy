package com.mingzhang.table.common;

import com.mingzhang.table.pojo.TableFieldPojo;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.util.ArrayList;
import java.util.List;

public class FlinkStreamInitDemo {

    public static final StreamExecutionEnvironment streamEnv = StreamExecutionEnvironment.getExecutionEnvironment();
    private static final EnvironmentSettings flinkEnvSettings = EnvironmentSettings.newInstance().useOldPlanner().inStreamingMode().build();
    private static final EnvironmentSettings blinkEnvSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
    private static final EnvironmentSettings anyEnvSettings = EnvironmentSettings.newInstance().useAnyPlanner().inStreamingMode().build();
    public static final StreamTableEnvironment flinkTableEnv = StreamTableEnvironment.create(streamEnv, flinkEnvSettings);
    //  static final StreamTableEnvironment blinkTableEnv = StreamTableEnvironment.create(streamEnv, blinkEnvSettings);
//    static final StreamTableEnvironment anyTableEnv = StreamTableEnvironment.create(streamEnv, anyEnvSettings);

    public static void printData(String sql) {
        //env.setParallelism(1);
        Table sqlQuery = FlinkStreamInitDemo.flinkTableEnv.sqlQuery(sql);
        sqlQuery.printSchema();
        DataStream<Row> rowDataStream = FlinkStreamInitDemo.flinkTableEnv.toAppendStream(sqlQuery, Row.class);
        rowDataStream.print();
    }

    public static void printDataRetract(String sql) {
        //env.setParallelism(1);
        Table sqlQuery = FlinkStreamInitDemo.flinkTableEnv.sqlQuery(sql);
        sqlQuery.printSchema();
        DataStream<Tuple2<Boolean, Row>> tuple2DataStream = FlinkStreamInitDemo.flinkTableEnv.toRetractStream(sqlQuery, Row.class);
        tuple2DataStream.print();
    }

    public static void printSchema(String sql){
        Table sqlQuery = FlinkStreamInitDemo.flinkTableEnv.sqlQuery(sql);
        sqlQuery.printSchema();
    }

    public static List<TableFieldPojo> getFieldsList() {
        List<TableFieldPojo> list = new ArrayList();
        TableFieldPojo tableFieldPojo01 = new TableFieldPojo();
        tableFieldPojo01.setFieldCode("id");
        tableFieldPojo01.setFieldType("STRING");
        TableFieldPojo tableFieldPojo02 = new TableFieldPojo();
        tableFieldPojo02.setFieldCode("name");
        tableFieldPojo02.setFieldType("STRING");
        TableFieldPojo tableFieldPojo03 = new TableFieldPojo();
        tableFieldPojo03.setFieldCode("age");
        tableFieldPojo03.setFieldType("DECIMAL");
        TableFieldPojo tableFieldPojo04 = new TableFieldPojo();
        tableFieldPojo04.setFieldCode("stat");
        tableFieldPojo04.setFieldType("STRING");
        list.add(tableFieldPojo01);
        list.add(tableFieldPojo02);
        list.add(tableFieldPojo03);
        list.add(tableFieldPojo04);
        return list;
    }

}
