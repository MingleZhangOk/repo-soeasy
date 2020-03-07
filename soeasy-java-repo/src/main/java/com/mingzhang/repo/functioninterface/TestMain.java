package com.mingzhang.repo.functioninterface;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-03-07 11:41
 */
public class TestMain {
    public static void main(String[] args) {
        MyTest myTest = getFunction1();
        myTest.test1();
        myTest.test2("hello void!");
    }

    private static MyTest getFunction1() {
        MyTest myTest = () -> System.out.println("hello world!");
        return myTest;
    }

//    private static MyTest getFunction2() {
//        MyTest myTest = x -> System.out.println("" + x);
//        return myTest;
//    }

}
