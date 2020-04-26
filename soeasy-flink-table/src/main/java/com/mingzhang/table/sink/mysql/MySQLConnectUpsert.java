package com.mingzhang.table.sink.mysql;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.pojo.TableFieldPojo;
import com.mingzhang.table.side.mysql.MySQLConnectDemo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.api.java.io.jdbc.JDBCAppendTableSink;
import org.apache.flink.api.java.io.jdbc.JDBCOptions;
import org.apache.flink.api.java.io.jdbc.JDBCUpsertTableSink;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableSchema;

import java.util.ArrayList;
import java.util.List;

public class MySQLConnectUpsert {

    public static String mysqlTableName = "Mysql_Table";


    /**
     * {"id":"C1122131000031","age":666,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000032","age":999,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000033","age":999,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000031","age":555,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000037","age":555,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000038","age":555,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000039","age":666,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000039","age":666,"name":"NPS.403.001.02","stat":"1949"}
     * <p>
     * {"id":"C1122131000052","age":666,"name":"NPS.403.001.02","stat":"1949"}
     * {"id":"C1122131000052","age":777,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000051","age":888,"name":"NPS.403.001.02","stat":"1949"}
     * {"id":"C1122131000052","age":999,"name":"NPS.403.001.02","stat":"1979"}
     * <p>
     * <p>
     * <p>
     * <p>
     * {"id":"C1122131000061","age":111,"name":"NPS.403.001.02","stat":"1949"}
     * {"id":"C1122131000062","age":222,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000063","age":333,"name":"NPS.403.001.02","stat":"1949"}
     * {"id":"C1122131000064","age":444,"name":"NPS.403.001.02","stat":"1979"}
     * {"id":"C1122131000061","age":555,"name":"NPS.403.001.01","stat":"1949"}
     * {"id":"C1122131000062","age":666,"name":"NPS.403.001.02","stat":"1949"}
     * {"id":"C1122131000063","age":777,"name":"NPS.403.001.02","stat":"1949"}
     * {"id":"C1122131000064","age":888,"name":"NPS.403.001.02","stat":"1979"}
     * {"id":"C1122131000062","age":999,"name":"NPS.403.001.02","stat":"1949"}
     * {"id":"C1122131000063","age":963,"name":"NPS.403.001.02","stat":"1949"}
     * <p>
     * //        return "update student set name=?,age=?,stat=? where id=? ";
     * //        return "replace into student (id,name,age,stat) values(?,?,?,?)";
     * //        return "insert into student (id,name,age,stat) values(?,?,?,?) on duplicate key update id=values(id) ";
     * //insert into student (id,name,age,stat)values(?,?,?,?) ON DUPLICATE KEY UPDATE id=values(id),name=values(name),age=values(age),stat=values(stat)
     * //insert into student (id,name,age,stat)values(?,?,?,?) ON DUPLICATE KEY UPDATE id=values(id),name=values(name),age=values(age),stat=values(stat)
     * //        upsertSQL = "insert into student (id,name,age,stat) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE id=values(id),name=values(name),age=values(age),stat=concat(values(stat),stat)";
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        KafkaConnectDemo.registerKafkaSourceTable();
        registerMySQLTable3();
        FlinkStreamInitDemo.streamEnv.execute("mysql_testJob");
    }

    public static void registerMySQLTable1() throws Exception {
        List<TableFieldPojo> list = FlinkStreamInitDemo.getFieldsList();
        TableSchema tableSchema = TableSchema.builder().fields(
                SchemaUtil.getTableFieldNames(list),
                SchemaUtil.getTableFieldTypes(list))
                .build();

        JDBCOptions jdbcOption = JDBCOptions.builder()
                .setDBUrl(MySQLConnectDemo.jdbcUrl)
                .setTableName(MySQLConnectDemo.tableName)
                .setDriverName(MySQLConnectDemo.driverName)
                .setUsername(MySQLConnectDemo.username)
                .setPassword(MySQLConnectDemo.password)
                .build();

//                JDBCDialects.get(String.valueOf(mapperInfoMap.get(SourceKeyMapping.SQL_DATABASE_DRIVER))).get()

        JDBCUpsertTableSink sink = JDBCUpsertTableSink.builder()
                .setTableSchema(tableSchema)
                .setFlushIntervalMills(1000)
                .setFlushMaxSize(1)
                .setMaxRetryTimes(3000)
                .setOptions(jdbcOption)
                .build();


//        JDBCAppendTableSink sink = JDBCAppendTableSink.builder()
//                .setDrivername(String.valueOf(mapperInfoMap.get(SourceKeyMapping.SQL_DATABASE_DRIVER)))
//                .setDBUrl(String.valueOf(mapperInfoMap.get(SourceKeyMapping.SQL_JDBC_URL)))
//                .setQuery(buildSql(tableInfo.getTableFieldPoList(), tableInfo.getRdbTable()))
//                .setParameterTypes(tableSchema.getFieldTypes())
//                .setUsername(String.valueOf(mapperInfoMap.get(SourceKeyMapping.SQL_DB_USERNAME)))
//                .setPassword(String.valueOf(mapperInfoMap.get(SourceKeyMapping.SQL_DB_PASSWORD)))
//                .setBatchSize(1)
//                .build();

        FlinkStreamInitDemo.flinkTableEnv.registerTableSink(
                mysqlTableName,
//                tableSchema.getFieldNames(),
//                tableSchema.getFieldTypes(),
                sink);

        System.out.println("注册mysql表，表名:" + mysqlTableName);
        Table sqlQuery = FlinkStreamInitDemo.flinkTableEnv.sqlQuery("select * from " + mysqlTableName);
        sqlQuery.printSchema();
//        DataStream<Row> rowDataStream = FlinkStreamInitDemo.flinkTableEnv.toAppendStream(sqlQuery, Row.class);
//        rowDataStream.print();

        FlinkStreamInitDemo.flinkTableEnv.sqlUpdate("upsert into " + mysqlTableName + " select name,age,stat,id from " + KafkaConnectDemo.tableName);
    }

    public static void registerMySQLTable2() throws Exception {
        List<TableFieldPojo> list = getFieldsList();
        TableSchema tableSchema = TableSchema.builder().fields(
                SchemaUtil.getTableFieldNames(list),
                SchemaUtil.getTableFieldTypes(list))
                .build();

        JDBCAppendTableSink sink = JDBCAppendTableSink.builder()
                .setDrivername(MySQLConnectDemo.driverName)
                .setDBUrl(MySQLConnectDemo.jdbcUrl)
                .setQuery(buildUpsertSql(getFieldsList(), MySQLConnectDemo.tableName))
                .setParameterTypes(tableSchema.getFieldTypes())
                .setUsername(MySQLConnectDemo.username)
                .setPassword(MySQLConnectDemo.password)
                .setBatchSize(10)
                .build();


//        JDBCAppendTableSink sink = JDBCAppendTableSink.builder()
//                .setDrivername(String.valueOf(mapperInfoMap.get(SourceKeyMapping.SQL_DATABASE_DRIVER)))
//                .setDBUrl(String.valueOf(mapperInfoMap.get(SourceKeyMapping.SQL_JDBC_URL)))
//                .setQuery(buildSql(tableInfo.getTableFieldPoList(), tableInfo.getRdbTable()))
//                .setParameterTypes(tableSchema.getFieldTypes())
//                .setUsername(String.valueOf(mapperInfoMap.get(SourceKeyMapping.SQL_DB_USERNAME)))
//                .setPassword(String.valueOf(mapperInfoMap.get(SourceKeyMapping.SQL_DB_PASSWORD)))
//                .setBatchSize(1)
//                .build();

        FlinkStreamInitDemo.flinkTableEnv.registerTableSink(
                mysqlTableName,
                tableSchema.getFieldNames(),
                tableSchema.getFieldTypes(),
                sink);

        System.out.println("注册mysql表，表名:" + mysqlTableName);
        Table sqlQuery = FlinkStreamInitDemo.flinkTableEnv.sqlQuery("select * from " + mysqlTableName);
        sqlQuery.printSchema();
//        DataStream<Row> rowDataStream = FlinkStreamInitDemo.flinkTableEnv.toAppendStream(sqlQuery, Row.class);
//        rowDataStream.print();

        FlinkStreamInitDemo.flinkTableEnv.sqlUpdate("insert into " + mysqlTableName + " select id,name,age,stat from " + KafkaConnectDemo.tableName);
    }

    public static void registerMySQLTable3() throws Exception {
        List<TableFieldPojo> list = getFieldsList();
        TableSchema tableSchema = TableSchema.builder().fields(
                SchemaUtil.getTableFieldNames(list),
                SchemaUtil.getTableFieldTypes(list))
                .build();

        JDBCAppendTableSink sink = JDBCAppendTableSink.builder()
                .setDrivername(MySQLConnectDemo.driverName)
                .setDBUrl(MySQLConnectDemo.jdbcUrl)
                .setQuery(buildUpdateSQL(getFieldsList(), MySQLConnectDemo.tableName))
                .setParameterTypes(tableSchema.getFieldTypes())
                .setUsername(MySQLConnectDemo.username)
                .setPassword(MySQLConnectDemo.password)
                .setBatchSize(1)
                .build();

        FlinkStreamInitDemo.flinkTableEnv.registerTableSink(
                mysqlTableName,
                tableSchema.getFieldNames(),
                tableSchema.getFieldTypes(),
                sink);

        System.out.println("注册mysql表，表名:" + mysqlTableName);
        Table sqlQuery = FlinkStreamInitDemo.flinkTableEnv.sqlQuery("select * from " + mysqlTableName);
        sqlQuery.printSchema();

        FlinkStreamInitDemo.flinkTableEnv.sqlUpdate("insert into " + mysqlTableName + " select id,name,age,stat from " + KafkaConnectDemo.tableName);
    }

    private static String buildInsertSql(List<TableFieldPojo> list, String table) {
        boolean isHeader = true;
        final StringBuilder headerSb = new StringBuilder("insert into " + table + " (");
        final StringBuilder endSb = new StringBuilder("values(");

        for (TableFieldPojo tableFieldPo : list) {
            if (!isHeader) {
                headerSb.append("," + tableFieldPo.getFieldCode());
                endSb.append("," + "?");
            } else {
                headerSb.append(tableFieldPo.getFieldCode());
                endSb.append("?");
                isHeader = false;
            }
        }
        return headerSb.append(")").toString() + endSb.append(")").toString();
    }

    private static String buildUpsertSql(List<TableFieldPojo> list, String table) {
        boolean isHeader = true;
        String upsertSQL = "";
        final StringBuilder headerSb = new StringBuilder("insert into " + table + " (");
        final StringBuilder endSb = new StringBuilder("values(");
        final StringBuilder duplicaseSb = new StringBuilder(" ON DUPLICATE KEY UPDATE ");

        for (TableFieldPojo tableFieldPo : list) {
            if (!isHeader) {
                headerSb.append("," + tableFieldPo.getFieldCode());
                endSb.append("," + "?");
            } else {
                headerSb.append(tableFieldPo.getFieldCode());
                endSb.append("?");
                isHeader = false;
            }
            duplicaseSb.append(tableFieldPo.getFieldCode() + "=values(" + tableFieldPo.getFieldCode() + "),");
        }
        upsertSQL = headerSb.append(")").toString() + endSb.append(")").toString() + duplicaseSb.substring(0, duplicaseSb.length() - 1);
//        return upsertSQL;
//        return "update student set name=?,age=?,stat=? where id=? ";
//        return "replace into student (id,name,age,stat) values(?,?,?,?)";
//        return "insert into student (id,name,age,stat) values(?,?,?,?) on duplicate key update id=values(id) ";
        //insert into student (id,name,age,stat)values(?,?,?,?) ON DUPLICATE KEY UPDATE id=values(id),name=values(name),age=values(age),stat=values(stat)
        //insert into student (id,name,age,stat)values(?,?,?,?) ON DUPLICATE KEY UPDATE id=values(id),name=values(name),age=values(age),stat=values(stat)
//        upsertSQL = "insert into student (id,name,age,stat) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE id=values(id),name=values(name),age=values(age),stat=concat(values(stat),stat)";
        //INSERT INTO fr_data_alarmmain(`ALARM_ID`, `ALARM_NO`, `JOB_NO`, `ALARM_LEVEL`, `ORG_ID`, `ORG_NAME`, `SYS_DATE`, `SYS_TIME`, `MONI_TYPE`, `MONI_ID`, `BUS_TYPE`, `NOTICE_TYPE`, `CHANNEL_CONTENT`, `ALARM_STAT`, `ALARM_FELID1`, `ALARM_FELID2`, `ALARM_FELID3`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE ALARM_ID=values(ALARM_ID),ALARM_NO=values(ALARM_NO,JOB_NO=values(JOB_NO),ALARM_LEVEL=values(ALARM_LEVEL),ORG_ID=values(ORG_ID),ORG_NAME=values(ORG_NAME),SYS_DATE=values(SYS_DATE),SYS_TIME=values(SYS_TIME),MONI_TYPE=values(MONI_TYPE),MONI_ID=values(MONI_ID),BUS_TYPE=values(BUS_TYPE),NOTICE_TYPE=values(NOTICE_TYPE),CHANNEL_CONTENT=concat(values(CHANNEL_CONTENT),CHANNEL_CONTENT),ALARM_STAT=values(ALARM_STAT),ALARM_FELID1=values(ALARM_FELID1),ALARM_FELID2=values(ALARM_FELID2),ALARM_FELID3=values(ALARM_FELID3);
        return upsertSQL;
    }

    private static String buildUpdateSQL(List<TableFieldPojo> list, String table) {
        boolean isHeader = true;
        String upsertSQL = "";
        final StringBuilder headerSb = new StringBuilder("insert into " + table + " (");
        final StringBuilder endSb = new StringBuilder("values(");
        final StringBuilder duplicaseSb = new StringBuilder(" ON DUPLICATE KEY UPDATE ");

        for (TableFieldPojo tableFieldPo : list) {
            if (!isHeader) {
                headerSb.append("," + tableFieldPo.getFieldCode());
                endSb.append("," + "?");
            } else {
                headerSb.append(tableFieldPo.getFieldCode());
                endSb.append("?");
                isHeader = false;
            }
            duplicaseSb.append(tableFieldPo.getFieldCode() + "=values(" + tableFieldPo.getFieldCode() + "),");
        }
        upsertSQL = headerSb.append(")").toString() + endSb.append(")").toString() + duplicaseSb.substring(0, duplicaseSb.length() - 1);

//        upsertSQL = "update student set id=?,name=? where age=? and stat=?";
        upsertSQL = "update student set id=?,name=? where age=?";
        //{"id":"111","age":223,"name":"222","stat":"19591959195919591959"}
        //{"id":"333","age":223,"name":"999","stat":"19591959195919591959"}
        //{"id":"7777777","age":223,"name":"123456789","stat":"19591959195919591959"}
        return upsertSQL;
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
