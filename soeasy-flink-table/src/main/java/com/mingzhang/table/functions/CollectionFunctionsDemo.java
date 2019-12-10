package com.mingzhang.table.functions;

/**
 * CARDINALITY(array)
 * 返回array中的元素数。
 * <p>
 * array ‘[’ integer ‘]’
 * 在返回位置处的元素整数在阵列。索引从1开始。
 * <p>
 * ELEMENT(array)
 * 返回数组的唯一元素（其基数应为一）；如果数组为空，则返回NULL 。如果数组具有多个元素，则引发异常。
 * <p>
 * CARDINALITY(map)
 * 返回map中的条目数。
 * <p>
 * map ‘[’ value ‘]’
 * 返回键指定的值，值的地图。
 */
public class CollectionFunctionsDemo {
    public static void main(String[] args) {
        String collectionSQL = "";


        FunctionDemoMain.runFunction(collectionSQL);
    }
}
