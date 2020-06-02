package com.mingzhang.table.source.udfsocket;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-03-09 18:40
 */
public class UserDefSourceTest {
    public static void main(String[] args) throws Exception {

        test1();

    }

    private static void test1() {
        //nc -l 6868
        //zhangsan,list,111
        StreamExecutionEnvironment fsEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings fsSettings = EnvironmentSettings.newInstance().useOldPlanner().inStreamingMode().build();
        StreamTableEnvironment fsTableEnv = StreamTableEnvironment.create(fsEnv, fsSettings);
        fsTableEnv.registerTableSource("testTable", new UserDefAPISource());
        Table table = fsTableEnv.sqlQuery("select * from testTable");
        table.printSchema();

        DataStream<Row> rowDataStream = fsTableEnv.toAppendStream(table, Row.class);

        rowDataStream.print();
//        fsTableEnv.sqlUpdate(kafkaTableCreateSql);
//        TableRegisterService.printStreamTableData(fsTableEnv, "kafka表", sinkTable);
        try {
            fsEnv.execute("test2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
