package com.mingzhang.parser.demo;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-11 00:37
 */
public class recursionTest {

    private static int recursionNum = 100;
    private static int recursionFlag = 0;

    public static void main(String[] args) {
        recursionMethod(recursionFlag);
        System.out.println("recursionFlag===" + recursionFlag);
    }

    private static void recursionMethod(int recursionFlag) {
        recursionFlag++;
        System.out.println("recursionFlag===" + recursionFlag);
        if (recursionFlag == recursionNum) {
            recursionFlag = 0;
            return;
        } else {
            recursionMethod(recursionFlag);
        }
    }

}
