package com.mingzhang.table.impl.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import javax.sound.midi.Soundbank;

/**
 * File Discriptor:
 *
 * @author MingZhang
 * @DATE 2019-12-17  19:25
 **/
public class R2MRedisConnectTest {
    public static void main(String[] args) {

        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),
                "192.168.56.102",
                6379,
                Protocol.DEFAULT_TIMEOUT,
                "123456",
                Integer.valueOf("9"));
        Jedis jedis = jedisPool.getResource();
        System.out.println("testIncr====" + jedis.incr("testIncr"));
        System.out.println("putTestAdd====" + jedis.incrBy("testAdd", 100));
        System.out.println("a=====" + jedis.set("a", "13"));
        System.out.println("s====" + jedis.get("a"));
        System.out.println("testAdd====" + jedis.get("testAdd"));
    }
}
