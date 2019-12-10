package com.mingzhang.table.source.mysql;

import com.mingzhang.table.common.FlinkBatchInitDemo;
import com.mingzhang.table.impl.jdbc.JDBCTable;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.TableSchema;

import java.sql.ResultSet;

public class MySQLReader {

    static String jdbcUrl = "jdbc:mysql://192.168.56.102:3306/nams_batch?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
    static String username = "root";
    static String password = "123456";
    static String tableName = "org_table";
    public static String registerTableName = "MyStudent";

    public static void main(String[] args) throws Exception {
        registerMySQLSourceTable();
    }

    public static void registerMySQLSourceTable() throws Exception {
        TableSchema tableSchema = SchemaUtil.getTableSchema(FlinkBatchInitDemo.getFieldsList());

        String sql = "";

        sql = "select * from " + tableName;

        JDBCInputFormat jdbcInputFormat = JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername("com.mysql.jdbc.Driver")
                .setDBUrl(jdbcUrl)
                .setQuery(sql)
                .setResultSetType(ResultSet.TYPE_FORWARD_ONLY)
                .setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY)
                .setRowTypeInfo(new RowTypeInfo(tableSchema.getFieldTypes(), tableSchema.getFieldNames()))
                .setUsername(username)
                .setPassword(password)
                .finish();

        JDBCTable jdbcTable = new JDBCTable(jdbcInputFormat, tableSchema);
        FlinkBatchInitDemo.tableEnvironment.registerTableSource(registerTableName, jdbcTable);

        System.out.println("注册mysql表"+registerTableName);
        sql = "select * from " + registerTableName;
        FlinkBatchInitDemo.printData(sql);

    }
}
