package com.mingzhang.repo.redis;

import redis.clients.jedis.Jedis;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2019-12-23 21:51
 */
public class RedisTest {

    static Jedis jedis = RedisUtil.getJedis();

    public static void test1() {
        Double a = jedis.incrByFloat("a", 100.3);
        System.out.println(a);
        Double a1 = jedis.incrByFloat("a", -200.3);
        System.out.println(a1);
    }
    public static void  test2(){
        System.out.println(jedis.get("a"));
    }

    public static void  test3(){
        System.out.println(jedis.set("a","0"));
    }

    public static void main(String[] args) {
        test1();
    }
}
