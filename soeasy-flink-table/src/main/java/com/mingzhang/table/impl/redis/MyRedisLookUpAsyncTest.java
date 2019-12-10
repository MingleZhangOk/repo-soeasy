package com.mingzhang.table.impl.redis;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;

public class MyRedisLookUpAsyncTest {


    public static String tableName = "testRedis";
    public static String funcName = "getRedis";

    public static void main(String[] args) throws Exception {
//{"id":"234567890","name":"tome","age":23.56,"stat":"big"}
        KafkaConnectDemo.registerKafkaSourceTable();
        registerRedisFunction();
//        FlinkStreamInitDemo.flinkTableEnv.registerFunction("getRedis", new MyRedisLookupFunction());
        String sql = "select id," +
                " from user_click_name as t1" +
                " join info FOR SYSTEM_TIME AS OF t1.proctime as t2" +
                " on t1.id = t2.id";

        String joinSQL = "select t1.id,t2.name,t1.age,t1.stat from " + KafkaConnectDemo.tableName +
                " as t1 join " + MyRedisLookUpAsyncTest.tableName + " as t2 on t1.id=t2.id";

        String querySQL = "select id,name1,age,stat from " + KafkaConnectDemo.tableName +
                ", LATERAL TABLE(" + MyRedisLookUpAsyncTest.funcName + "(id)) AS S(name1)";

        FlinkStreamInitDemo.printData(querySQL);

//        DataStream<Row> result = FlinkStreamInitDemo.flinkTableEnv.toAppendStream(table, Row.class);
//
////        result.print().setParallelism(1);
//
//        DataStream<String> printStream = result.map(new MapFunction<Row, String>() {
//            @Override
//            public String map(Row value) throws Exception {
//                return value.toString();
//            }
//        });
//
//
//        printStream.print();

        FlinkStreamInitDemo.flinkTableEnv.execute(Thread.currentThread().getStackTrace()[1].getClassName());
    }


    static void registerRedisFunction() {
        String keyFields = "id";

        RedisAsyncLookupTableSource tableSource = RedisAsyncLookupTableSource.Builder.newBuilder()
                .withFieldNames(new String[]{keyFields, "name"})
                .withFieldTypes(new TypeInformation[]{Types.STRING, Types.STRING})
                .build();

//        FlinkStreamInitDemo.flinkTableEnv.registerTableSource(tableName, tableSource);
        FlinkStreamInitDemo.flinkTableEnv.registerFunction(funcName, tableSource.getLookupFunction(keyFields.split(",")));
    }
}
