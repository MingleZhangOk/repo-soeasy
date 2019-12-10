package com.mingzhang.repo.exception.runtime;

import java.io.Serializable;
import java.util.List;

public class KafkaConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bootstrapServers;
    private String zkServer;
    private String offsetReset;
    private String groupId;
    private String autoCommit;
    private List<String> topics;
    private long offsetCommitPeriodMs;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getOffsetReset() {
        return offsetReset;
    }

    public void setOffsetReset(String offsetReset) {
        this.offsetReset = offsetReset;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    private String getZkServer() {
        return zkServer;
    }

    public void setZkServer(String zkServer) {
        this.zkServer = zkServer;
    }

    private long getOffsetCommitPeriodMs() {
        return offsetCommitPeriodMs;
    }

    public void setOffsetCommitPeriodMs(long offsetCommitPeriodMs) {
        this.offsetCommitPeriodMs = offsetCommitPeriodMs;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n----------kafka config begin-------------------------");
        stringBuilder.append("\nkafka.bootstrapServers=")
                .append(getBootstrapServers())
                .append("\nkafka.zkServer=")
                .append(getZkServer())
                .append("\nkafka.groupId=")
                .append(groupId)
                .append("\nkafka.offsetReset=")
                .append(getOffsetReset())
                .append("\nkafka.autoCommit=")
                .append(getAutoCommit())
                .append("\nkafka.offsetCommitPeriodMs=")
                .append(getOffsetCommitPeriodMs())
                .append("\nkafka.topics=")
                .append(getTopics())
                .append("\n-----------------kafka config end---------------------");
        return stringBuilder.toString();
    }
}
