package com.mingzhang.table.sink.hdfs;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.pojo.TableFieldPojo;
import com.mingzhang.table.sink.mysql.MySQLConnectUpsert;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.DateTimeBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.OnCheckpointRollingPolicy;
import org.apache.flink.streaming.connectors.fs.StringWriter;
import org.apache.flink.streaming.connectors.fs.bucketing.BucketingSink;
import org.apache.flink.streaming.connectors.fs.bucketing.DateTimeBucketer;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.descriptors.ConnectTableDescriptor;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.factories.TableFactoryUtil;
import org.apache.flink.table.sinks.CsvTableSink;
import org.apache.flink.table.sinks.TableSink;
import org.apache.flink.types.Row;

import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-04-24 9:18
 */
public class HDFSConnectDemo {

    private static String hdfsName = "yuxin";
    private static String servers1 = "172.16.60.41:9000";
    private static String servers2 = "172.16.60.42:9000";
    private static String path = "/test_tmp";
    private static String hdfsTableName = "hdfsTable";
    private static String sql = "insert into " + hdfsTableName + " select id,name,age,stat from " + KafkaConnectDemo.tableName;
    private static String sqlQuery = " select * from " + KafkaConnectDemo.tableName;

    public static void main(String[] args) throws Exception {
        KafkaConnectDemo.servers = "172.16.60.41:9092,172.16.60.42:9092,172.16.60.43:9092";
        KafkaConnectDemo.topics = "yusysdb";
        System.out.println(KafkaConnectDemo.tableName);
        KafkaConnectDemo.registerKafkaSourceTable();

        registerHDFSTable0(sql);
        FlinkStreamInitDemo.streamEnv.setParallelism(1);
        FlinkStreamInitDemo.streamEnv.enableCheckpointing(30000, CheckpointingMode.EXACTLY_ONCE);
        FlinkStreamInitDemo.streamEnv.execute("hdfs_testJob");

    }

    private static void registerHDFSTable0(String sql) throws Exception {
        List<TableFieldPojo> list = MySQLConnectUpsert.getFieldsList();

//        OutputFileConfig config = OutputFileConfig
//                .builder()
//                .withPartPrefix("prefix")
//                .withPartSuffix(".ext")
//                .build();

        SimpleStringEncoder<Row> rowSimpleStringEncoder = new SimpleStringEncoder<>("UTF-8");
        DefaultRollingPolicy<Object, Object> build = DefaultRollingPolicy.create()
                .withRolloverInterval(TimeUnit.MINUTES.toMillis(15))
                .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
                .withMaxPartSize(1024 * 1024 * 1024)
                .build();
//        OnCheckpointRollingPolicy<Object, Object> objectObjectOnCheckpointRollingPolicy = new OnCheckpointRollingPolicy<>();
//        ParquetWriterFactory

//        StreamingFileSink.forBulkFormat(new Path(""),new MyFactory());

        final StreamingFileSink<Row> sink = StreamingFileSink
                .forRowFormat(new Path("hdfs://engine:9000/flink_test"), rowSimpleStringEncoder)
                .withBucketAssigner(new DateTimeBucketAssigner<>("yyyy-MM-dd--HH"))
                .withRollingPolicy(DefaultRollingPolicy.create()
                        .withRolloverInterval(TimeUnit.MINUTES.toMillis(15))
                        .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
                        .withMaxPartSize(1024 * 1024 * 1024)
                        .build())
                .withBucketCheckInterval(30000)
                .build();

        Table table = FlinkStreamInitDemo.flinkTableEnv.sqlQuery(sqlQuery);
        table.printSchema();

        DataStream<Row> rowDataStream = FlinkStreamInitDemo.flinkTableEnv.toAppendStream(table, Row.class);
        rowDataStream.print();
        rowDataStream.addSink(sink);
//        FlinkStreamInitDemo.flinkTableEnv.registerTableSink(
//                hdfsTableName,
//                tableSchema.getFieldNames(),
//                tableSchema.getFieldTypes(),
//                sink);
    }

    private static void registerHDFSTable1() throws Exception {

        ConnectTableDescriptor hdfsTableDescriptor = FlinkStreamInitDemo.flinkTableEnv.connect(new org.apache.flink.table.descriptors.FileSystem().path("hdfs://172.16.60.42:9000/test_tmp"))
                .withFormat(new Csv().deriveSchema().fieldDelimiter(',').ignoreParseErrors())
                .withSchema(SchemaUtil.getSchema(FlinkStreamInitDemo.getFieldsList()))
                .inAppendMode();

        TableSink<Object> andCreateTableSink = TableFactoryUtil.findAndCreateTableSink(hdfsTableDescriptor);

        FlinkStreamInitDemo.flinkTableEnv.registerTableSink(
                hdfsTableName,
//                tableSchema.getFieldNames(),
//                tableSchema.getFieldTypes(),
                andCreateTableSink);

        System.out.println("注册hdfs表，表名:" + hdfsTableName);
        Table sqlQuery = FlinkStreamInitDemo.flinkTableEnv.sqlQuery("select * from " + hdfsTableName);
        sqlQuery.printSchema();
//        DataStream<Row> rowDataStream = FlinkStreamInitDemo.flinkTableEnv.toAppendStream(sqlQuery, Row.class);
//        rowDataStream.print();

        FlinkStreamInitDemo.flinkTableEnv.sqlUpdate("upsert into " + hdfsTableName + " select name,age,stat,id from " + KafkaConnectDemo.tableName);

    }

    private static void registerHDFSTable2(String sql) {
        List<TableFieldPojo> list = MySQLConnectUpsert.getFieldsList();
        TableSchema tableSchema = TableSchema.builder().fields(
                SchemaUtil.getTableFieldNames(list),
                SchemaUtil.getTableFieldTypes(list))
                .build();

//        ConnectTableDescriptor hdfsTableDescriptor = FlinkStreamInitDemo.flinkTableEnv.connect(new org.apache.flink.table.descriptors.FileSystem().path("hdfs://172.16.60.42:9000/test_tmp"))
//                .withFormat(new Csv().deriveSchema())
//                .withSchema(SchemaUtil.getSchema(FlinkStreamInitDemo.getFieldsList()))
//                .inAppendMode();
        TableSink csvSink = new CsvTableSink("hdfs://engine:9000/flink_test", "|", 1, org.apache.flink.core.fs.FileSystem.WriteMode.NO_OVERWRITE).configure(tableSchema.getFieldNames(), tableSchema.getFieldTypes());


//        hdfsTableDescriptor.registerTableSink(hdfsTableName);

//        TableSink<Row> andCreateTableSink = TableFactoryUtil.findAndCreateTableSink(hdfsTableDescriptor);

        FlinkStreamInitDemo.flinkTableEnv.registerTableSink(
                hdfsTableName,
                csvSink);

        System.out.println("注册hdfs表，表名:" + hdfsTableName);
        Table sqlQuery = FlinkStreamInitDemo.flinkTableEnv.sqlQuery("select * from " + hdfsTableName);
        sqlQuery.printSchema();
//        DataStream<Row> rowDataStream = FlinkStreamInitDemo.flinkTableEnv.toAppendStream(sqlQuery, Row.class);
//        rowDataStream.print();

        FlinkStreamInitDemo.flinkTableEnv.sqlUpdate(sql);

    }

    private static void registerHDFSTable3(String sql) {
        hdfsInitLoad(servers1 + "," + servers2, hdfsName);
        List<TableFieldPojo> list = MySQLConnectUpsert.getFieldsList();
        TableSchema tableSchema = TableSchema.builder().fields(
                SchemaUtil.getTableFieldNames(list),
                SchemaUtil.getTableFieldTypes(list))
                .build();
//        ConnectTableDescriptor hdfsTableDescriptor = FlinkStreamInitDemo.flinkTableEnv.connect(new org.apache.flink.table.descriptors.FileSystem().path("hdfs://172.16.60.42:9000/test_tmp"))
//                .withFormat(new Csv().deriveSchema())
//                .withSchema(SchemaUtil.getSchema(FlinkStreamInitDemo.getFieldsList()))
//                .inAppendMode();
        TableSink csvSink = new CsvTableSink("hdfs://engine:9000/flink_test", "|", 6, FileSystem.WriteMode.OVERWRITE).configure(tableSchema.getFieldNames(), tableSchema.getFieldTypes());



//        hdfsTableDescriptor.registerTableSink(hdfsTableName);

//        TableSink<Row> andCreateTableSink = TableFactoryUtil.findAndCreateTableSink(hdfsTableDescriptor);

        FlinkStreamInitDemo.flinkTableEnv.registerTableSink(
                hdfsTableName,
                csvSink);

        System.out.println("注册hdfs表，表名:" + hdfsTableName);
        Table sqlQuery = FlinkStreamInitDemo.flinkTableEnv.sqlQuery("select * from " + hdfsTableName);
        sqlQuery.printSchema();
//        DataStream<Row> rowDataStream = FlinkStreamInitDemo.flinkTableEnv.toAppendStream(sqlQuery, Row.class);
//        rowDataStream.print();

        FlinkStreamInitDemo.flinkTableEnv.sqlUpdate(sql);

    }

    private static void hdfsInitLoad(String serverList, String hdfsName) {
        String[] hosts = serverList.split(",");
        final Configuration config = new Configuration();
        config.setString("fs.defaultFS", "hdfs://" + hdfsName);
        config.setString("fs.dfs.nameservices", hdfsName);
        config.setString("fs.dfs.ha.namenodes." + hdfsName, "nn1,nn2");
        config.setString("fs.dfs.namenode.rpc-address." + hdfsName + ".nn1", hosts[0]);
        config.setString("fs.dfs.namenode.rpc-address." + hdfsName + ".nn2", hosts[1]);
        config.setString("fs.dfs.client.failover.proxy.provider." + hdfsName, "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        FileSystem.initialize(config);
    }

    private static BucketingSink hdfsConfigs(String path) {

        //write to hdfs sink
        BucketingSink<Row> sink = new BucketingSink<>(path);
        sink.setUseTruncate(false);
//        sink.setBucketer(new Bucketer<UI>() {
//            @Override
//            public Path getBucketPath(Clock clock, Path basePath, UI element) {
//                String newDateTimeString = dateTimeFormatter.format(Instant.ofEpochMilli(element.getTimestamp()));
//                return new Path(basePath + "/" + newDateTimeString);
//            }
//        });
        sink.setBucketer(new DateTimeBucketer<>("yyyy-MM-dd--HHmm", ZoneId.of("UTC+8")));
        sink.setWriter(new StringWriter<>());
        sink.setBatchSize(1024 * 1024 * 10); // this is 10 MB,
        sink.setBatchRolloverInterval(60 * 1000); // this is 1 min
        return sink;
    }
}
