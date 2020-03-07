package com.mingzhang.repo.functioninterface;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-03-07 11:37
 */

// 正确的函数式接口
@FunctionalInterface
public interface TestInterface {


    // 抽象方法
    public void sub();

    // java.lang.Object中的方法不是抽象方法
    @Override
    public boolean equals(Object var1);

    // default不是抽象方法
    public default void defaultMethod() {

    }

    // static不是抽象方法
    public static void staticMethod() {

    }
}
