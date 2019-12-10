package com.mingzhang.table.source.redis;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.impl.redis.RedisConnDescriptor;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.descriptors.ConnectTableDescriptor;
import org.apache.flink.table.descriptors.Json;

public class RedisConnectDemo {


    static String tableName = "testRedis";

    public static void main(String[] args) throws Exception {

        KafkaConnectDemo.registerKafkaSourceTable();
        String querySQL = " select * from " + KafkaConnectDemo.tableName;
        FlinkStreamInitDemo.printData(querySQL);
        String sql = "insert into " + tableName + querySQL;

        ConnectTableDescriptor connectTableDescriptor = FlinkStreamInitDemo.flinkTableEnv.connect(
                new RedisConnDescriptor("", 5, false))
                .withFormat(new Json().deriveSchema())
                .withSchema(SchemaUtil.getSchema(FlinkStreamInitDemo.getFieldsList()))
                .inUpsertMode();
        Table table = FlinkStreamInitDemo.flinkTableEnv.sqlQuery(querySQL);

        FlinkStreamInitDemo.flinkTableEnv.sqlUpdate(sql);

//        FlinkStreamInitDemo.flinkTableEnv.registerTableSink("",connectTableDescriptor);

        FlinkStreamInitDemo.streamEnv.execute("MyES_testJob");

    }

    static RedisSink getRedisSink() {
        //实例化Flink和Redis关联类FlinkJedisPoolConfig，设置Redis端口
        FlinkJedisPoolConfig jedisPoolConfig = new FlinkJedisPoolConfig.Builder()
                .setHost("")
                .setPassword("")
                .setMaxTotal(100)
                .setMaxIdle(100)
                .setMinIdle(100).build();
        //实例化RedisSink，并通过flink的addSink的方式将flink计算的结果插入到redis
        RedisSink<Tuple2<String, Integer>> redisSink = new RedisSink<>(jedisPoolConfig, new RedisExampleMapper());
        return redisSink;
    }


    //指定Redis key并将flink数据类型映射到Redis数据类型
    public static final class RedisExampleMapper implements RedisMapper<Tuple2<String, Integer>> {
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.HSET, "flink");
        }

        public String getKeyFromData(Tuple2<String, Integer> data) {
            return data.f0;
        }

        public String getValueFromData(Tuple2<String, Integer> data) {
            return data.f1.toString();
        }

    }
}
