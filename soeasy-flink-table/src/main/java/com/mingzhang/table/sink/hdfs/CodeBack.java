package com.mingzhang.table.sink.hdfs;

import com.mingzhang.connectors.redis.service.TableRegisterService;
import com.mingzhang.table.pojo.TableInfoPo;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.sinks.CsvTableSink;
import org.apache.flink.table.sinks.TableSink;
import org.apache.flink.types.Row;

import java.util.Map;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-04-26 16:58
 */
public class CodeBack {


    public static void main(String[] args) {
        String str = "insert into select hdfs_sink select BIZSTS as ID,SYSSECOND as NAME,OrgName as ADDRESS from cdc_jcdt_002 where BIZSTS='PR07'";

        System.out.println(str.substring(str.indexOf("select")));

    }


//    @Override
//    public void sinkStreamRegister(StreamExecutionEnvironment fsEnv, StreamTableEnvironment tableEnvironment, TableInfoPo tableInfo, NodeParamInfo nodeParamInfo, Map<String, Object> mapperInfoMap, String nodeContent) {
//
//        String isHaFlag = mapperInfoMap.get(ConnectorParamCode.FILE_FTP_FLAG) != null ? mapperInfoMap.get(ConnectorParamCode.FILE_FTP_FLAG).toString() : "0";
//
//        String serverList = mapperInfoMap.get(ConnectorParamCode.FILE_SERVER_LIST) != null ? mapperInfoMap.get(ConnectorParamCode.FILE_SERVER_LIST).toString() : null;
//        if (StringUtils.isEmpty(serverList)) {
//            throw new CompCommonException("COMP_S100008");
//        }
//
//        String filePath = tableInfo.getESMappingName() != null ? tableInfo.getESMappingName() : "flink_engine";
//        String hdfsClusterName = mapperInfoMap.get(ConnectorParamCode.HDFS_CLUSTER_NAME) != null ? mapperInfoMap.get(ConnectorParamCode.HDFS_CLUSTER_NAME).toString() : "yuxin";
//        String fileName = nodeParamInfo.getParam2() != null ? nodeParamInfo.getParam2() : "flink";
//        String allPath = "";
//
//        if (HDFSType.NHA.getCode().equalsIgnoreCase(isHaFlag)) {
//            allPath = "hdfs://" + serverList + "/" + filePath + "/" + fileName;
//        } else if (HDFSType.HA.getCode().equalsIgnoreCase(isHaFlag)) {
//            hdfsInitLoad(serverList, hdfsClusterName);
//            allPath = "hdfs://" + hdfsClusterName + "/" + filePath + "/" + fileName;
//        } else {
//            throw new CompCommonException("COMP_S100009", allPath);
//        }
//
//        String fieldDelim = nodeParamInfo.getParam3() != null ? nodeParamInfo.getParam3() : ",";
//        int numFiles = Integer.parseInt(nodeParamInfo.getParam4() != null ? nodeParamInfo.getParam4() : "4");
//
//
////        ConnectTableDescriptor hdfsTableDescriptor = tableEnvironment.connect(new org.apache.flink.table.descriptors.FileSystem().path(""))
////                .withFormat(new CSVFileFormat().fieldDelimiter(","))
////                .withSchema(SchemaParse.getSchema(tableInfo.getTableFieldPoList()))
////                .inAppendMode();
//
////        TableSink<Object> andCreateTableSink = TableFactoryUtil.findAndCreateTableSink(hdfsTableDescriptor);
//
//        TableSink<Row> rowTableSink = new CsvTableSink(allPath,
//                fieldDelim,
//                numFiles,
//                FileSystem.WriteMode.OVERWRITE,
//                SchemaParse.getTableFieldNames(tableInfo.getTableFieldPoList()),
//                SchemaParse.getTableFieldTypes(tableInfo.getTableFieldPoList()));
//
////        CsvAppendTableSinkFactory csvAppendTableSinkFactory = new CsvAppendTableSinkFactory();
////        Map<String, String> properties = csvAppendTableSinkFactory.requiredContext();
////        properties.put("connector.path", allPath);
////        properties.put("schema", SchemaParse.getSchema(tableInfo.getTableFieldPoList()).toString());
////        System.out.println(properties);
////
////        StreamTableSink<Row> streamTableSink = csvAppendTableSinkFactory.createStreamTableSink(properties);
//
//
//        tableEnvironment.registerTableSink(tableInfo.getTableCode(), rowTableSink);
////        SimpleStringEncoder<Row> rowSimpleStringEncoder = new SimpleStringEncoder<>("UTF-8");
////        DefaultRollingPolicy<Object, Object> build = DefaultRollingPolicy.create()
////                .withRolloverInterval(TimeUnit.MINUTES.toMillis(15))
////                .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
////                .withMaxPartSize(1024 * 1024 * 1024)
////                .build();
////
////        final StreamingFileSink<Row> sink = StreamingFileSink
////                .forRowFormat(new Path("hdfs://engine:9000/flink_test"), rowSimpleStringEncoder)
////                .withBucketAssigner(new DateTimeBucketAssigner<>("yyyy-MM-dd--HH"))
////                .withRollingPolicy(DefaultRollingPolicy.create()
////                        .withRolloverInterval(TimeUnit.MINUTES.toMillis(15))
////                        .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
////                        .withMaxPartSize(1024 * 1024 * 1024)
////                        .build())
////                .withBucketCheckInterval(30000)
////                .build();
////
////        Table table = tableEnvironment.sqlQuery("");
////        table.printSchema();
////
////        DataStream<Row> rowDataStream = tableEnvironment.toAppendStream(table, Row.class);
////        rowDataStream.print();
////        rowDataStream.addSink(sink);
//        TableRegisterService.printSchema(tableEnvironment, "hdfsSink表", tableInfo.getTableCode(), TableRegisterService.SQL_ALL);
//    }
}
