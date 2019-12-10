package com.mingzhang.table.windows;

import com.mingzhang.function.udaf.CompMinuteLose_UDAF;
import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.side.mysql.MySQLConnectDemo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import org.apache.flink.table.api.Table;

public class OverWindowDemo {
    public static void main(String[] args) throws Exception {
//{"id":"234567890","name":"tome","age":23.56,"stat":"big"}
        KafkaConnectDemo.registerKafkaSourceTable();
        MySQLConnectDemo.registerFunction();

        FlinkStreamInitDemo.flinkTableEnv.registerFunction("TEST", new CompMinuteLose_UDAF());

        String overSQL = "select name,id,proctime,stat,count(stat) OVER (" +
                " PARTITION BY name,id" +
                " ORDER BY proctime" +
                " RANGE BETWEEN INTERVAL '5' SECOND preceding AND CURRENT ROW) as sumstat " +
                "from " + KafkaConnectDemo.tableName;

        Table table = FlinkStreamInitDemo.flinkTableEnv.sqlQuery(overSQL);
        FlinkStreamInitDemo.flinkTableEnv.registerTable("overTable", table);

        //TEST( String initStat, String nowStat, Long statCount )
        String searchOverSQL = "select name,id,TEST('PR13',stat,sumstat) from overTable  group by TUMBLE(proctime, INTERVAL '5' SECOND),name,id";

        FlinkStreamInitDemo.printData(searchOverSQL);

        FlinkStreamInitDemo.streamEnv.execute("MySLQ_testJob");
    }

}
