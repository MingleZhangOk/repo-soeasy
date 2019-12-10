package com.mingzhang.table.source.file;

import com.mingzhang.table.common.FlinkBatchInitDemo;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.sources.CsvTableSource;
import org.apache.flink.types.Row;

public class HAHDFSConnectDemo {

    private static String filePath = "hdfs://engine:9000/file_path/test.csv";
    private static String HDFSTableName = "hdfsTable";

    public static void main(String[] args) {
//        hdfsInitLoad();

        CsvTableSource.Builder builder = CsvTableSource.builder()
                .fieldDelimiter(",")
                .lineDelimiter("\n")
//                .ignoreParseErrors()
                .quoteCharacter('\"');

//        FlinkBatchInitDemo.tableEnvironment.connect(new FileSystem())

        builder.field("id", Types.STRING).field("name", Types.STRING).field("birthday", Types.STRING);

        CsvTableSource tableSource = builder.path(filePath).build();

        FlinkBatchInitDemo.tableEnvironment.registerTableSource(HDFSTableName, tableSource);

        printBatchTableData(FlinkBatchInitDemo.tableEnvironment, "HDFSFileSource表", HDFSTableName);
    }

    static void printBatchTableData(BatchTableEnvironment tableEnvironment, String tableType, String tableName) {
        System.out.println("注册" + tableType + "，表名:" + tableName);
        Table sqlQuery = tableEnvironment.sqlQuery("select * from " + tableName);
        sqlQuery.printSchema();
        DataSet<Row> rowDataSet = tableEnvironment.toDataSet(sqlQuery, Row.class);
        try {
            rowDataSet.print();
        } catch (Exception e) {
            System.err.println();
        }
    }

    //
    static void hdfsInitLoad() {
        final Configuration config = new Configuration();
        config.setString("fs.defaultFS", "hdfs://yuxin");
//        //  serverList="hdfs://engine:9000,engine:9000";
//        //        String[] hosts = serverList.substring(7).split(",");
//        // config.setString("fs.defaultFS", "hdfs://yuxin");
        config.setString("fs.dfs.nameservices", "yuxin");
        config.setString("fs.dfs.ha.namenodes.yuxin", "nn1,nn2");
        config.setString("fs.dfs.namenode.rpc-address.yuxin.nn1", "engine:9000");
        config.setString("fs.dfs.namenode.rpc-address.yuxin.nn2", "engine:9000");
        config.setString("fs.dfs.client.failover.proxy.provider.yuxin", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        FileSystem.initialize(config);
    }
}