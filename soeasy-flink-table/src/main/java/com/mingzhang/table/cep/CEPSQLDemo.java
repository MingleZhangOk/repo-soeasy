package com.mingzhang.table.cep;


import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.side.mysql.MySQLConnectDemo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;

public class CEPSQLDemo {
    public static void main(String[] args) throws Exception {
//{"id":"234567890","name":"tome","age":23.56,"stat":"big"}
        KafkaConnectDemo.registerKafkaSourceTable();
        MySQLConnectDemo.registerFunction();


        String cepSQL = "select * from " + KafkaConnectDemo.tableName + " MATCH_RECOGNIZE (" +
                " PARTITION BY name,id" +
                " ORDER BY `proctime`" +
                " MEASURES" +
                " e1.`stat` as `stat1`," +
                " e2.`stat` as `stat2`," +
                " e1.`proctime` as `start_timestamp`," +
                " LAST(e2.`proctime`) as `end_timestamp`" +
                " ONE ROW PER MATCH" +
//                " WITH TIMEOUT ROWS" +
                " AFTER MATCH SKIP PAST LAST ROW" +
//                " ALL ROWS PER MATCH WITH TIMEOUT ROWS" +
                " PATTERN (e1 e2+?) WITHIN INTERVAL '10' SECOND" +
                " DEFINE" +
                " e1 as e1.stat = 'PR013' ," +
                " e2 as e2.stat = 'null' or e2.stat = e1.stat " +
                ")";
//RANGE BETWEEN INTERVAL '6' SECOND preceding AND CURRENT ROW) from "+ KafkaConnectDemo.tableName ;

        FlinkStreamInitDemo.printData(cepSQL);

        FlinkStreamInitDemo.streamEnv.execute("MySLQ_testJob");

    }

}
