package com.mingzhang.table.sink.mysql;

import com.mingzhang.function.udaf.CompMinuteLose_UDAF_bak;
import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.side.mysql.MySQLConnectDemo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;

public class TestSQLDemo {
    public static void main(String[] args) throws Exception {

        KafkaConnectDemo.registerKafkaSourceTable();
        MySQLConnectDemo.registerFunction();

        //TEST(Timestamp procTime, String nowStat)
        FlinkStreamInitDemo.flinkTableEnv.registerFunction("TEST", new CompMinuteLose_UDAF_bak());
        String sql = "select name,id,count(*)," +
                "HOP_START(proctime, INTERVAL '5' SECOND, INTERVAL '30' SECOND)," +
                "HOP_END(proctime, INTERVAL '5' SECOND, INTERVAL '30' SECOND)," +
                "TEST(proctime,stat) " +
                "from " + KafkaConnectDemo.tableName +
                " group by " +
                "HOP(proctime, INTERVAL '5' SECOND, INTERVAL '30' SECOND)," +
                "name,id";
        //HOP_START(proctime, INTERVAL '1' SECOND, INTERVAL '6' SECOND)

        FlinkStreamInitDemo.printData(sql);
        FlinkStreamInitDemo.flinkTableEnv.execute("HOP_SQL_testJob");

    }

}
