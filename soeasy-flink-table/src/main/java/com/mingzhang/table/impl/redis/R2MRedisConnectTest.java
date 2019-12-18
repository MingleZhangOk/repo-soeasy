package com.mingzhang.table.impl.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * File Discriptor:
 *
 * @author MingZhang
 * @DATE 2019-12-17  19:25
 **/
public class R2MRedisConnectTest {
    public static void main(String[] args) {

        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),
                "",
                6379,
                Protocol.DEFAULT_TIMEOUT,
                "",
                Integer.valueOf("nams"));
        Jedis jedis = jedisPool.getResource();
        String a = jedis.set("a", "13");
        System.out.println(a);
        String s = jedis.get("a");
        System.out.println(s);
    }
}
