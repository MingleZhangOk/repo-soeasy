package com.mingzhang.table.pojo;

public class KafkaPojo {

    private String keyDeserializer = "org.apache.kafka.common.serialization.ByteArrayDeserializer";
    private String valueDeserializer = "org.apache.kafka.comñmon.serialization.ByteArrayDeserializer";
    //    private String zookeeperConnect;
    private String bootstrapServers;
    private String groupId = "Kafka_Group";
    private String enableAutoCommit = "false";
    private String transactionTimeoutMs = "35000";
    private String zookeeperConnectionTimeoutMs = "5000";
    private String autoOffsetReset = "latest"; // read from the beginning. (earliest is kafka 0.11 value)
    private String maxPartitionFetchBytes = "256";// make a lot of fetches (MESSAGES MUST BE SMALLER!)

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getEnableAutoCommit() {
        return enableAutoCommit;
    }

    public void setEnableAutoCommit(String enableAutoCommit) {
        this.enableAutoCommit = enableAutoCommit;
    }

    public String getTransactionTimeoutMs() {
        return transactionTimeoutMs;
    }

    public void setTransactionTimeoutMs(String transactionTimeoutMs) {
        this.transactionTimeoutMs = transactionTimeoutMs;
    }

    public String getZookeeperConnectionTimeoutMs() {
        return zookeeperConnectionTimeoutMs;
    }

    public void setZookeeperConnectionTimeoutMs(String zookeeperConnectionTimeoutMs) {
        this.zookeeperConnectionTimeoutMs = zookeeperConnectionTimeoutMs;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public void setAutoOffsetReset(String autoOffsetReset) {
        this.autoOffsetReset = autoOffsetReset;
    }

    public String getMaxPartitionFetchBytes() {
        return maxPartitionFetchBytes;
    }

    public void setMaxPartitionFetchBytes(String maxPartitionFetchBytes) {
        this.maxPartitionFetchBytes = maxPartitionFetchBytes;
    }
}
