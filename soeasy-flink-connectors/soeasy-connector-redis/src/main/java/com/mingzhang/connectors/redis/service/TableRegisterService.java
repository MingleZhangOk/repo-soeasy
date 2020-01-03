package com.mingzhang.connectors.redis.service;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

public class TableRegisterService {

    public static final String SQL_ALL = "A";
    public static final String SQL_COUNT = "C";
    public static final String SQL_SUM = "S";
    public static boolean testFlag = false;

    public static void pringSome(String some) {
        if (!testFlag) return;
        System.out.println(some);
    }

    public static void printSchema(TableEnvironment tableEnvironment, String tableType, String tableName, String queryType) {
        if (!testFlag) return;
        System.out.println("注册" + tableType + "，表名:" + tableName);
        Table sqlQuery = tableEnvironment.sqlQuery(getTestQuerySQL(tableName, queryType));
        sqlQuery.printSchema();
    }

    public static void printSqlUpdate(String sqlUpdate) {
        if (!testFlag) return;
        System.out.println("SqlUpdate：" + sqlUpdate);
    }

    public static void printFunctionData(String funcName, String funcFields) {
        if (!testFlag) return;
        System.out.println("注册函数：" + funcName);
        System.out.println("函数字段：" + funcFields);
    }

    public static void printBatchTableData(TableEnvironment tableEnvironment, String tableType, String tableName, String queryType) {
        if (!testFlag) return;
        System.out.println("注册" + tableType + "，表名:" + tableName);
        Table sqlQuery = tableEnvironment.sqlQuery(getTestQuerySQL(tableName, queryType));
        sqlQuery.printSchema();
//        DataSet<Row> rowDataSet = tableEnvironment.toDataSet(sqlQuery, Row.class);
//        try {
//            rowDataSet.print();
//        } catch (Exception e) {
//            System.err.println();
//        }
    }

    public static void printStreamTableData(StreamTableEnvironment tableEnvironment, String tableType, String tableName, String queryType) {
        if (!testFlag) return;
        System.out.println("注册" + tableType + "，表名:" + tableName);
        Table sqlQuery = tableEnvironment.sqlQuery(getTestQuerySQL(tableName, queryType));
        sqlQuery.printSchema();
        DataStream<Row> rowDataStream = tableEnvironment.toAppendStream(sqlQuery, Row.class);
        rowDataStream.print();
    }

    public static void printBatchTableData(BatchTableEnvironment tableEnvironment, String tableType, String tableName) {
        if (!testFlag) return;
        System.out.println("注册" + tableType + "，表名:" + tableName);
        Table sqlQuery = tableEnvironment.sqlQuery(getTestQuerySQL(tableName, ""));
        sqlQuery.printSchema();
        DataSet<Row> rowDataSet = tableEnvironment.toDataSet(sqlQuery, Row.class);
        try {
            rowDataSet.print();
        } catch (Exception e) {
            System.err.println();
        }
    }

    public static void printStreamTableData(StreamTableEnvironment tableEnvironment, String tableType, String tableName) {
        if (!testFlag) return;
        System.out.println("注册" + tableType + "，表名:" + tableName);
        Table sqlQuery = tableEnvironment.sqlQuery(getTestQuerySQL(tableName, ""));
        sqlQuery.printSchema();
        DataStream<Row> rowDataStream = tableEnvironment.toAppendStream(sqlQuery, Row.class);
        rowDataStream.print();
    }


    private static String getTestQuerySQL(String tableName, String queryType) {
        String sql = "";
        switch (queryType) {
            case SQL_ALL:
                sql = "select * from " + tableName;
                break;
            case SQL_COUNT:
                sql = "select count(*) from " + tableName;
                break;
            case SQL_SUM:
                sql = "select sum(OrgId) from " + tableName;
            default:
                sql = "select * from " + tableName;
        }
        return sql;
    }

}
