package com.mingzhang.table.source.file;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.sources.CsvTableSource;
import org.apache.flink.types.Row;

public class HdfsConnectDemo2 {

    //rhel77-namsap02xt:9000
    private static String filePath = "hdfs:///file_path/test.csv";
    private static String hdfsTableName = "hdfsTable";

    public static void main(String[] args) throws Exception {
//        initHDFSConfig();
        BatchTableEnvironment batchTableEnvironment = BatchTableEnvironment.create(ExecutionEnvironment.getExecutionEnvironment());

        CsvTableSource.Builder builder = CsvTableSource.builder()
                .fieldDelimiter(",")
                .lineDelimiter("\n")
                .quoteCharacter('\"')
                .ignoreParseErrors();

        CsvTableSource.Builder soutce = builder.field("id", Types.STRING)
                .field("name", Types.STRING)
                .field("age", Types.STRING);

        CsvTableSource build = soutce.path(filePath).build();

        batchTableEnvironment.registerTableSource(hdfsTableName, build);

        Table table = batchTableEnvironment.sqlQuery("select * from " + hdfsTableName);

        DataSet<Row> rowDataSet = batchTableEnvironment.toDataSet(table, Row.class);
        rowDataSet.print();

    }

    private static void initHDFSConfig() {
        final Configuration config = new Configuration();
        config.setString("fs.defaultFS", "hdfs://ns1");
        config.setString("fs.dfs.nameservices", "ns1");
        config.setString("fs.dfs.ha.namenodes.ns1", "nn1,nn2");
        config.setString("fs.dfs.namenode.rpc-address.ns1.nn1", "rhel77-namsap01xt:9000");
        config.setString("fs.dfs.namenode.rpc-address.ns1.nn2", "rhel77-namsap02xt:9000");
        config.setString("fs.dfs.client.failover.proxy.provider.ns1", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        FileSystem.initialize(config);
    }

}
