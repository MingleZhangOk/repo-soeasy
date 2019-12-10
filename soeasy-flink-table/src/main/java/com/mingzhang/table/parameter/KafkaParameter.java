package com.mingzhang.table.parameter;

public class KafkaParameter {
    public final static String keyDeserializer = "key.deserializer";
    public final static String valueDeserializer = "value.deserializer";
    public final static String bootstrapServers = "bootstrap.servers";
    public final static String groupId = "group.id";
    public final static String enableAutoCommit = "enable.auto.commit";
    public final static String transactionTimeoutMs = "transaction.timeout.ms";
    public final static String zookeeperConnectionTimeoutMs = "zookeeper.connection.timeout.ms";
    public final static String autoOffsetReset = "auto.offset.reset"; // read from the beginning. (earliest is kafka 0.11 value)
    public final static String maxPartitionFetchBytes = "max.partition.fetch.bytes";// make a lot of fetches (MESSAGES MUST BE SMALLER!)
}
