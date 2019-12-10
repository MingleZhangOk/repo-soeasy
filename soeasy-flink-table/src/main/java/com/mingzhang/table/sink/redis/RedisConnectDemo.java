package com.mingzhang.table.sink.redis;

import com.mingzhang.connectors.redis.sink.Codis;
import com.mingzhang.connectors.redis.sink.RedisProperties;
import com.mingzhang.connectors.redis.sink.RedisTableSink;
import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import org.apache.flink.table.sinks.TableSink;
import org.apache.flink.types.Row;

import java.util.ArrayList;
import java.util.List;

public class RedisConnectDemo {

    static String tableName = "testRedis";

    public static void main(String[] args) throws Exception {
//{"id":"123","name":"title","age":"16","stat":"jedis"}
//{"id":"13","name":"tom","age":"16","stat":"redis"}
//{"id":"18","name":"tom","age":16,"stat":"redis"}
//{"id":"18","name":"jerry","age":16,"stat":"kafka"}
        KafkaConnectDemo.registerKafkaSourceTable();
//        MySQLConnectDemo.registerFunction();
        String querySQL = " select id,name,age,stat from " + KafkaConnectDemo.tableName;
//        FlinkStreamInitDemo.printData(querySQL);
        String sql = "insert into " + tableName + querySQL;

//        Table table = FlinkStreamInitDemo.flinkTableEnv.sqlQuery(querySQL);
//        ConnectTableDescriptor set = FlinkStreamInitDemo.flinkTableEnv.connect(new RedisConnDescriptor("SET", 5, false)).withFormat(new Json().deriveSchema())
//                .withSchema(SchemaUtil.getSchema(FlinkStreamInitDemo.getFieldsList()))
//                .inUpsertMode();
//        final Map<String, String> propertiesMap = set.toProperties();

        RedisProperties redisProperties = new RedisProperties();
        redisProperties.setHost("192.168.56.102");
        redisProperties.setPort(6379);
        redisProperties.setPassword("123456");
        redisProperties.setDatabase(2);
        Codis codis = new Codis();
        codis.setCodisProxyName("codis");
        codis.setZkAddrs("192.168.56.102");
//        redisProperties.setCodis(codis);
//        Sentinel sentinel = new Sentinel();
//        sentinel.setMaster("192.168.56.102:6379");
//        sentinel.setSentinels("192.168.56.102:6379");
//        redisProperties.setSentinel(sentinel);
        redisProperties.setCommand("SET");
        redisProperties.setName("flink");
        List list = new ArrayList();
//        list.add("id");
        list.add("name");
//        list.add("age");
//        list.add("stat");
//        list.add("proctime");
        redisProperties.setKeys(list);
//        String[] fields = new String[]{"id", "name", "age", "stat", "proctime"};
        String[] fields = new String[]{"id", "name", "age", "stat"};
        TypeInformation[] typeInformations = new TypeInformation[fields.length];
        typeInformations[0] = Types.STRING;
        typeInformations[1] = Types.STRING;
        typeInformations[2] = Types.BIG_DEC;
        typeInformations[3] = Types.STRING;
//        typeInformations[4] = Types.SQL_TIMESTAMP;
        RedisTableSink redisTableSink = new RedisTableSink(redisProperties);
        TableSink<Tuple2<Boolean, Row>> configure = redisTableSink.configure(fields, typeInformations);
        redisTableSink.setIsAppendOnly(true);
        redisTableSink.setKeyFields(fields);

        FlinkStreamInitDemo.flinkTableEnv.registerTableSink(tableName, configure);
        FlinkStreamInitDemo.flinkTableEnv.sqlUpdate(sql);
        FlinkStreamInitDemo.printSchema("select * from " + tableName);

        System.out.println(FlinkStreamInitDemo.streamEnv.getExecutionPlan());
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
