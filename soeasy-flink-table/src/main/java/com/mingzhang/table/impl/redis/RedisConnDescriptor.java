package com.mingzhang.table.impl.redis;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import org.apache.flink.table.descriptors.ConnectorDescriptor;

import java.util.HashMap;
import java.util.Map;

public class RedisConnDescriptor extends ConnectorDescriptor implements RedisMapper<Tuple2<String, Integer>> {


    public RedisConnDescriptor(String type, int version, boolean formatNeeded) {
        super(type, version, formatNeeded);
    }

    public RedisCommandDescription getCommandDescription() {
        return new RedisCommandDescription(RedisCommand.HSET, "flink");
    }

    public String getKeyFromData(Tuple2<String, Integer> data) {
        return data.f0;
    }

    public String getValueFromData(Tuple2<String, Integer> data) {
        return data.f1.toString();
    }

    @Override
    protected Map<String, String> toConnectorProperties() {
        return new HashMap<>();
    }
}
