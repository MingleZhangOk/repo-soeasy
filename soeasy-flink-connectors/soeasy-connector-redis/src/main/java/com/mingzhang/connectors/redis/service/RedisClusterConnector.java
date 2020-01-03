package com.mingzhang.connectors.redis.service;

import java.util.*;

import redis.clients.jedis.*;

/**
 * File Discriptor:
 *
 * @author MingZhang
 * @DATE 2020-01-02  18:12
 **/
public class RedisClusterConnector {
    /**
     * 切片链接池
     */
    private ShardedJedisPool shardedJedisPool;


    private String redisNodes = "172.22.4.219:7000,172.22.4.220:7001,172.22.4.221:7002,172.22.4.219:7003,172.22.4.220:7004,172.22.4.221:7005";
    private String redis_pass_word = "123456";
    private String redisMaster = "mytestmaster";

    private int timeout = 2000;


    /**
     * 构造函数
     */
    public RedisClusterConnector() {
        initialShardedPool();
    }


    /**
     * @param jedis
     * @Description: 关闭连接
     */
    private void returnResource(ShardedJedis jedis) {
        if (jedis != null) {
            shardedJedisPool.returnResource(jedis);
        }
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool() {
        JedisPoolConfig config = getJedisPoolConfig();
        String[] serverArray = redisNodes.split(",");
        List<JedisShardInfo> shards = new ArrayList<>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            JedisShardInfo jedisShardInfo = new JedisShardInfo(ipPortPair[0].trim(), ipPortPair[1].trim());
            jedisShardInfo.setPassword(redis_pass_word);
            shards.add(jedisShardInfo);
        }
        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    private JedisPoolConfig getJedisPoolConfig() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        //是否启用后进先出, 默认true
        config.setLifo(true);
        //最大空闲连接数, 默认8个
        config.setMaxIdle(8);
        //最大连接数, 默认8个
        config.setMaxTotal(8);
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        config.setMaxWaitMillis(-1);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        config.setMinEvictableIdleTimeMillis(1800000);
        //最小空闲连接数, 默认0
        config.setMinIdle(0);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        config.setNumTestsPerEvictionRun(3);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        config.setSoftMinEvictableIdleTimeMillis(1800000);
        //在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(false);
        //在空闲时检查有效性, 默认false
        config.setTestWhileIdle(false);
        return config;
    }


    // 获取连接(1)
    private ShardedJedis getResource() {
        return shardedJedisPool.getResource();
    }


    // 获取连接(2)
    private JedisCluster getJedisCluster() {
        String[] serverArray = redisNodes.split(",");
        //可以设置单节点即可访问所有节点数据
        Set<HostAndPort> set = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            HostAndPort hp = new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim()));
            set.add(hp);
        }
        return new JedisCluster(set, getJedisPoolConfigCluster());
    }

    private JedisPoolConfig getJedisPoolConfigCluster() {
        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }


    /**
     * redis存储字符串
     */
    private static String testString(ShardedJedis jedis, String key) {
        return jedis.get(key);
    }

    // 连接方式2 哨兵模式
    public JedisSentinelPool getJedisSentinel() {

        String[] nodes = redisNodes.split(",");
        Set<String> set = new LinkedHashSet<>();
        for (String host : nodes) {
            set.add(host);
        }
        JedisSentinelPool redisSentinelJedisPool = new JedisSentinelPool(redisMaster, set, redis_pass_word);
        return redisSentinelJedisPool;
    }


    //test
    public static void main(String[] args) {
        RedisClusterConnector service = new RedisClusterConnector();

        String key = "mytestkey";

        //方式1 集群
//        JedisCluster cluster = service.getJedisCluster();
//        cluster.set(key, "test");
//        String name = cluster.get(key);
//        System.out.println("JedisCluster-name：" + name);


        // 方式2：ShardedJedis是通过一致性哈希来实现分布式缓存的，通过一定的策略把不同的key分配到不同的redis server上，达到横向扩展的目的。
        ShardedJedis jedis = service.getResource();
        String keyTag = jedis.getKeyTag(key);
        System.out.println(keyTag);
        System.out.println(jedis.exists(key));
        // 关闭连接
        service.returnResource(jedis);

        // 方式3： 哨兵模式
//        JedisSentinelPool jedisSentinel = service.getJedisSentinel();
//        Jedis resource = jedisSentinel.getResource();
//        String before = resource.get(key);
//        System.out.println("set before: " + before);
//        resource.set(key, "jedisSentinel");
//        String after = resource.get(key);
//        System.out.println("set after: " + after);

//        resource.close();
//        jedisSentinel.close();
    }
}