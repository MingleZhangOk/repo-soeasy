package com.mingzhang.connectors.redis.service;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.LinkedHashSet;

/**
 * File Discriptor:
 *
 * @author MingZhang
 * @DATE 2020-01-02  18:09
 **/
public class RedisClusterTest {

    private static String redisNodes = "172.22.4.219:7000,172.22.4.220:7001,172.22.4.221:7002,172.22.4.219:7003,172.22.4.220:7004,172.22.4.221:7005";
    private static String redisNode = "172.22.4.220";
    private static String redis_pass_word = "123456";
    private static String key = "info";


    public static void main(String[] args) {
        clusterTest();
    }

    public static void easyTest() {
        Jedis jedis = new Jedis(redisNode, 7001);
        jedis.auth("123456");
        String test = jedis.set("info", "123");
        System.out.println(test);
        String test1 = jedis.get("info");
        System.out.println(test1);
    }


    public static void clusterTest() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数量
        jedisPoolConfig.setMaxTotal(1);
        //最大空闲数量
        jedisPoolConfig.setMaxIdle(1);
        //最大允许等待时间，如果超过这个时间未获取到连接，则会抛出JedisException异常
        jedisPoolConfig.setMaxWaitMillis(10000);


        LinkedHashSet<HostAndPort> nodes = new LinkedHashSet<>();
        String[] hostsAndPorts = redisNodes.split(",");
        for (int i = 0; i < hostsAndPorts.length; i++) {
            String[] hostAndPort = hostsAndPorts[i].split(":");
            String host = hostAndPort[0];
            String port = hostAndPort[1];
            nodes.add(new HostAndPort(host, Integer.parseInt(port)));
        }

        JedisCluster jedisCluster = new JedisCluster(nodes, 20000, 20000, 50, redis_pass_word, jedisPoolConfig);

//        String name = jedisCluster.get("name");
//        System.out.println(name);
//        String set = jedisCluster.set(key, "test");
//        System.out.println(set);

        Long expire = jedisCluster.expire(key, 0);
        System.out.println(expire);


        String info = jedisCluster.get(key);
        System.out.println(info);
//        Long del = jedisCluster.del(key);
//        System.out.println(del);


        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
