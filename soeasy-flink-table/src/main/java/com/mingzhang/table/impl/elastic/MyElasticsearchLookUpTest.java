package com.mingzhang.table.impl.elastic;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;

public class MyElasticsearchLookUpTest {

    public static String tableName = "testElasticsearch";
    public static String funcName = "getElasticsearch";

    public static void main(String[] args) throws Exception {

//{"id":"001","nams":"jerry","age":12.34,"stat":"sha"}
        KafkaConnectDemo.registerKafkaSourceTable();
        registerElasticsearchFunction();

        String joinSQL = "select * from " + KafkaConnectDemo.tableName +
                " as t1  join " + MyElasticsearchLookUpTest.funcName + " FOR SYSTEM_TIME AS OF t1.proctime as t2  on t1.id=t2.id";

        String querySQL = "select id,name1,age,stat from " + KafkaConnectDemo.tableName +
                ", LATERAL TABLE(" + MyElasticsearchLookUpTest.funcName + "('hTDZbG4BWTzmRqS2O6tg','name',id)) AS S(name1)";
        FlinkStreamInitDemo.printData(querySQL);
        FlinkStreamInitDemo.flinkTableEnv.execute(Thread.currentThread().getStackTrace()[1].getClassName());
    }

    static void registerElasticsearchFunction() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        String keyFields = "id,key,type";
        ElasticsearchLookupTableSource tableSource = ElasticsearchLookupTableSource.Builder.newBuilder()
                .withFieldNames(new String[]{"id", "key", "values", "type"})
                .withFieldTypes(new TypeInformation[]{Types.STRING, Types.STRING, Types.STRING, Types.STRING})
                .build();
//        FlinkStreamInitDemo.flinkTableEnv.registerTableSource(tableName, tableSource);
        FlinkStreamInitDemo.flinkTableEnv.registerFunction(funcName, tableSource.getLookupFunction(keyFields.split(",")));
    }

}
