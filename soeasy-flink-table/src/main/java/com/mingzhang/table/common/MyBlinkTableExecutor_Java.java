package com.mingzhang.table.common;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.java.StreamTableEnvironment;

public class MyBlinkTableExecutor_Java {


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        EnvironmentSettings environmentSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();

        StreamTableEnvironment streamTableEnvironment = StreamTableEnvironment.create(executionEnvironment, environmentSettings);

        streamTableEnvironment.sqlUpdate("create table test  (`user` BIGINT, product VARCHAR, amount INT) ");

        streamTableEnvironment.sqlQuery("select * from test");



        executionEnvironment.execute();


    }

}
