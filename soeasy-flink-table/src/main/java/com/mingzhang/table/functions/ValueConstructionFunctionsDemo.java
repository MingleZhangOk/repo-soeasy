package com.mingzhang.table.functions;

/**
 *ROW(value1, [, value2]*)
 * (value1, [, value2]*)
 * 返回从值列表（值1，值2， ...）创建的行。
 *
 * ARRAY ‘[’ value1 [, value2 ]* ‘]’
 * 返回从值列表创建的数组（value1，value2，...）。
 *
 * MAP ‘[’ value1, value2 [, value3, value4 ]* ‘]’
 * 返回从键值对列表（（value1，value2），（value3，value4），...）创建的映射。
 */
public class ValueConstructionFunctionsDemo {
    public static void main(String[] args) {
        String valueConstructionSQL = "";


        FunctionDemoMain.runFunction(valueConstructionSQL);
    }
}
