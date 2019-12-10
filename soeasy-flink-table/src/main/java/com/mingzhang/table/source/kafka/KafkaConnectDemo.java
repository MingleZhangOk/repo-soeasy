package com.mingzhang.table.source.kafka;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.pojo.TableFieldPojo;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.streaming.connectors.kafka.KafkaTableSourceSinkFactory;
import org.apache.flink.table.descriptors.Descriptor;
import org.apache.flink.table.descriptors.Json;
import org.apache.flink.table.descriptors.Kafka;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.table.factories.TableFactoryService;
import org.apache.flink.table.sources.TableSource;
import org.apache.flink.types.Row;

import java.util.List;
import java.util.Properties;

public class KafkaConnectDemo {

    static String topics = "yusysdb";
    static String groupID = "testConn";
    static String transactionTimeOut = "35000";
    static String zkTimeOut = "5000";
    static String fetchSize = "256";
    public static String tableName = "testKafka";
    static String servers = "192.168.56.102:9092";

    public static void main(String[] args) throws Exception {
//{"id":"123","name":"123"}
        registerKafkaSourceTable();
        FlinkStreamInitDemo.streamEnv.execute("kafka_testJob");

    }


    public static void registerKafkaSourceTable() {
        List<TableFieldPojo> list = FlinkStreamInitDemo.getFieldsList();
        Properties props = new Properties();
        props.setProperty("key.deserializer",
                "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.setProperty("value.deserializer",
                "org.apache.kafka.comñmon.serialization.ByteArrayDeserializer");
        props.setProperty("bootstrap.servers", servers);
        props.setProperty("group.id", groupID);
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("transaction.timeout.ms", transactionTimeOut);
        props.setProperty("zookeeper.connection.timeout.ms", zkTimeOut);
        props.setProperty("auto.offset.reset", "latest");
        props.setProperty("max.partition.fetch.bytes", fetchSize);

        Descriptor sourceDescriptor = FlinkStreamInitDemo.flinkTableEnv.connect(
                new Kafka().version("universal")
                        //     new Kafka().version("11")
                        .topic(topics)
                        .properties(props)
        ).withFormat(new Json().deriveSchema())
                .withSchema(new Schema().schema(SchemaUtil.getTableSchema1(list)).proctime())
                .inAppendMode();

        System.err.println(sourceDescriptor.toProperties());

        final TableSource<Row> actualSource = TableFactoryService.find(KafkaTableSourceSinkFactory.class, sourceDescriptor)
                .createStreamTableSource(sourceDescriptor.toProperties());

        FlinkStreamInitDemo.flinkTableEnv.registerTableSource(tableName, actualSource);
        FlinkStreamInitDemo.printData("select * from " + tableName);
    }


}
