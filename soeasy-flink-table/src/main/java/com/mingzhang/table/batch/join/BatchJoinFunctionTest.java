package com.mingzhang.table.batch.join;

import com.mingzhang.table.common.FlinkBatchInitDemo;
import com.mingzhang.table.impl.jdbc.JDBCTable;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableSchema;

import java.sql.ResultSet;

public class BatchJoinFunctionTest {


    static String jdbcUrl = "jdbc:mysql://192.168.56.102:3306/nams_batch?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
    static String username = "root";
    static String password = "123456";
    static String tableName = "test1";
    public static String registerTableName = "MyStudent";

    public static void main(String[] args) throws Exception {
        registerMySQLSourceTable();
    }

    public static void registerMySQLSourceTable() throws Exception {
        TableSchema tableSchema = SchemaUtil.getTableSchema(FlinkBatchInitDemo.getFieldsList());
        String querySQL = "select * from " + tableName;
        String tranceSQL = "SELECT DECODE(DECODE(B1.ID,NULL,A1.ID,B1.ID),NULL,C1.ID,DECODE(B1.ID,NULL,A1.ID,B1.ID)) AS ID,\n" +
                "A1.CZGCOUNT,A1.CZCOUNT,B1.BZGCOUNT,B1.BZCOUNT,C1.TZGCOUNT,C1.TZCOUNT\n" +
                "\n" +
                "FROM\n" +
                "\n" +
                "(\n" +
                "SELECT DECODE(B.ID,NULL,A.ID,B.ID) AS ID,A.CZGCOUNT,B.CZCOUNT FROM \n" +
                "(SELECT ID,COUNT(1) AS CZGCOUNT FROM TEST1 WHERE NAME='E11' AND STAT='4' GROUP BY ID ) A\n" +
                "FULL JOIN\n" +
                "(SELECT ID,COUNT(1) AS CZCOUNT FROM TEST1 WHERE NAME='E11' AND STAT='1' GROUP BY ID ) B\n" +
                "ON A.ID=B.ID\n" +
                ") A1\n" +
                "\n" +
                "FULL JOIN \n" +
                "\n" +
                "(\n" +
                "SELECT DECODE(B.ID,NULL,A.ID,B.ID) AS ID,A.BZGCOUNT,B.BZCOUNT FROM \n" +
                "(SELECT ID,COUNT(1) AS BZGCOUNT FROM TEST1 WHERE NAME='E12' AND STAT!='1' GROUP BY ID ) A\n" +
                "FULL JOIN\n" +
                "(SELECT ID,COUNT(1) AS BZCOUNT FROM TEST1 WHERE NAME='E12' AND STAT='1' GROUP BY ID ) B\n" +
                "ON A.ID=B.ID\n" +
                ") B1\n" +
                "\n" +
                "ON A1.ID=B1.ID\n" +
                "\n" +
                "FULL JOIN \n" +
                "\n" +
                "(\n" +
                "SELECT DECODE(B.ID,NULL,A.ID,B.ID) AS ID,A.TZGCOUNT,B.TZCOUNT FROM \n" +
                "(SELECT ID,COUNT(1) AS TZGCOUNT FROM TEST1 WHERE NAME='E15' AND STAT='0' GROUP BY ID ) A\n" +
                "FULL JOIN\n" +
                "(SELECT ID,COUNT(1) AS TZCOUNT FROM TEST1 WHERE NAME='E15' AND STAT='1' GROUP BY ID ) B\n" +
                "ON A.ID=B.ID\n" +
                ") C1 \n" +
                "\n" +
                "ON A1.ID=C1.ID";

        JDBCInputFormat jdbcInputFormat = JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername("com.mysql.jdbc.Driver")
                .setDBUrl(jdbcUrl)
                .setQuery(querySQL)
                .setResultSetType(ResultSet.TYPE_FORWARD_ONLY)
                .setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY)
                .setRowTypeInfo(new RowTypeInfo(tableSchema.getFieldTypes(), tableSchema.getFieldNames()))
                .setUsername(username)
                .setPassword(password)
                .finish();

        JDBCTable jdbcTable = new JDBCTable(jdbcInputFormat, tableSchema);
        FlinkBatchInitDemo.tableEnvironment.registerTableSource(registerTableName, jdbcTable);

        System.out.println("注册mysql表" + registerTableName);

        Table table = FlinkBatchInitDemo.tableEnvironment.sqlQuery(tranceSQL);
        

    }
}
