package com.mingzhang.table.side.mysql;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.pojo.TableFieldPojo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.api.java.io.jdbc.JDBCLookupOptions;
import org.apache.flink.api.java.io.jdbc.JDBCOptions;
import org.apache.flink.api.java.io.jdbc.JDBCTableSource;
import org.apache.flink.table.api.TableSchema;

import java.util.ArrayList;
import java.util.List;

public class MySQLviewDemo {

    public static String jdbcUrl = "jdbc:mysql://192.168.56.102:3306/nams_batch?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
    public static String username = "root";
    public static String driverName = "com";
    public static String password = "123456";
    public static String tableName = "student_view";
    public static String funcName = "getStudent";

    public static void main(String[] args) throws Exception {
        //{"id":"1","name":"123"}
        //{"id":"2","name":"123"}
        //{"id":"4","name":"123"}
        KafkaConnectDemo.registerKafkaSourceTable();
        registerFunction();
        String sql = "select * from " + KafkaConnectDemo.tableName + " , LATERAL TABLE(" + funcName + "(id)) AS S(id1 ,stu_count)";
        //select * from log_142_source, LATERAL TABLE(engine_orgtable_view(FinCode)) AS S(FinCode1 ,OrgId,OrgName)
        FlinkStreamInitDemo.printData(sql);
        System.out.println(FlinkStreamInitDemo.streamEnv.getExecutionPlan());
        FlinkStreamInitDemo.streamEnv.execute("MySQL_testJob");
    }

    public static void registerFunction() {
        List<TableFieldPojo> list = getFieldsList();

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

    public static List<TableFieldPojo> getFieldsList() {
        List<TableFieldPojo> list = new ArrayList();
        TableFieldPojo tableFieldPojo01 = new TableFieldPojo();
        tableFieldPojo01.setFieldCode("id");
        tableFieldPojo01.setFieldType("STRING");
        TableFieldPojo tableFieldPojo04 = new TableFieldPojo();
        tableFieldPojo04.setFieldCode("stu_count");
        tableFieldPojo04.setFieldType("STRING");
        list.add(tableFieldPojo01);
        list.add(tableFieldPojo04);
        return list;
    }

}
