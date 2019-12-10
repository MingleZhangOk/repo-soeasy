package com.mingzhang.table.functions;

/**
 * GROUP_ID()
 * 返回一个唯一标识分组键组合的整数。
 *
 * GROUPING(expression1 [, expression2]* )
 * GROUPING_ID(expression1 [, expression2]* )
 * 返回给定分组表达式的位向量。
 */
public class GroupingFunctionsDemo {
    public static void main(String[] args) {
        String groupingSQL="";


        FunctionDemoMain.runFunction(groupingSQL);
    }
}
