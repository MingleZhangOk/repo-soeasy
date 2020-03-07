package com.mingzhang.repo.functioninterface;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-03-07 11:40
 */
@FunctionalInterface
public interface MyTest {
    void test1();

    default void test2(String x) {
        System.out.println(x);
    }

}
