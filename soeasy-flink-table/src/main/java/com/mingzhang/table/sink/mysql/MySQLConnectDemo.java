package com.mingzhang.table.sink.mysql;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.pojo.TableFieldPojo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.api.java.io.jdbc.JDBCLookupOptions;
import org.apache.flink.api.java.io.jdbc.JDBCOptions;
import org.apache.flink.api.java.io.jdbc.JDBCTableSource;
import org.apache.flink.table.api.TableSchema;

import java.util.List;

public class MySQLConnectDemo {

    public static String jdbcUrl = "jdbc:mysql://192.168.56.102:3306/nams_factory?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
    public static String username = "root";
    public static String password = "123456";
    public static String tableName = "student";
    public static String funcName = "getStudent";
    public static String driverName = "com.mysql.jdbc.Driver";

    public static void main(String[] args) throws Exception {
        KafkaConnectDemo.registerKafkaSourceTable();
        registerFunction();
        String sql = "select * from " + KafkaConnectDemo.tableName + " , LATERAL TABLE(" + funcName + "(id)) AS S(id1 ,name1,age1,stat1)";
        //select * from log_142_source, LATERAL TABLE(engine_orgtable_view(FinCode)) AS S(FinCode1 ,OrgId,OrgName)
        FlinkStreamInitDemo.printData(sql);
        FlinkStreamInitDemo.streamEnv.execute("MySQL_testJob");
    }

    public static void registerFunction() {
        List<TableFieldPojo> list = FlinkStreamInitDemo.getFieldsList();

        JDBCTableSource.Builder builder = JDBCTableSource.builder()
                .setOptions(JDBCOptions.builder()
                        .setDBUrl(jdbcUrl)
                        .setUsername(username)
                        .setPassword(password)
                        .setTableName(tableName)
                        .build())
                .setSchema(TableSchema.builder().fields(
                        SchemaUtil.getTableFieldNames(list),
                        SchemaUtil.getTableFieldTypes(list))
                        .build());

        //是否缓存 param2
        //缓存大小： param3
        //过期时间分钟： param4
        builder.setLookupOptions(JDBCLookupOptions.builder()
                .setCacheMaxSize(Long.valueOf("100"))
                .setCacheExpireMs(Long.valueOf("5000")).build());

        //关键字段: 用于查询的关键字段param1
        String keyFields = "id";

        FlinkStreamInitDemo.flinkTableEnv.registerFunction(funcName,
                builder.build().getLookupFunction(keyFields.split(",")));
        //todo 20191018测试 维表数据
        System.err.println("注册函数：" + funcName);
        System.err.println(keyFields);
    }

}
