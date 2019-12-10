package com.mingzhang.table.controller;

import com.mingzhang.table.parameter.KafkaParameter;
import com.mingzhang.table.pojo.KafkaPojo;
import org.apache.flink.table.descriptors.Descriptor;

import java.util.Map;
import java.util.Properties;

public class KafkaController {

    private static KafkaController KafkaController;
    private static Map<String, String> kafkaConns;

    public static KafkaController getInstance() {
        if (null == KafkaController) {
            KafkaController = new KafkaController();
        }
        return KafkaController;
    }

    private KafkaController() {

    }

    private static Properties getKafkapro() {
        Properties props = new Properties();
        KafkaPojo kafkaPojo = new KafkaPojo();
        props.setProperty(KafkaParameter.keyDeserializer, kafkaPojo.getKeyDeserializer());
        props.setProperty(KafkaParameter.valueDeserializer, kafkaPojo.getValueDeserializer());
        props.setProperty(KafkaParameter.bootstrapServers, kafkaPojo.getBootstrapServers());
        props.setProperty(KafkaParameter.groupId, kafkaPojo.getGroupId());
        props.setProperty(KafkaParameter.enableAutoCommit, kafkaPojo.getEnableAutoCommit());
        props.setProperty(KafkaParameter.transactionTimeoutMs, kafkaPojo.getTransactionTimeoutMs());

        props.setProperty(KafkaParameter.zookeeperConnectionTimeoutMs, kafkaPojo.getZookeeperConnectionTimeoutMs());
        props.setProperty(KafkaParameter.autoOffsetReset, kafkaPojo.getAutoOffsetReset());
        props.setProperty(KafkaParameter.maxPartitionFetchBytes, kafkaPojo.getMaxPartitionFetchBytes());
        return props;
    }

    public static Descriptor getSourceDescriptor() {
        return null;
    }

}
