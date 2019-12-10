package com.mingzhang.table.functions;


import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.side.mysql.MySQLConnectDemo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;

/**
 * 内建功能
 * Flink Table API和SQL为用户提供了一组用于数据转换的内置函数。此页面简要概述了它们。如果尚不支持所需的功能，则可以实现用户定义的功能。如果您认为该功能足够通用，请为此打开Jira问题，并提供详细说明。
 *
 * 标量函数
 * 比较功能
 * 逻辑功能
 * 算术函数
 * 字符串函数
 * 时间功能
 * 条件函数
 * 类型转换功能
 * 收集功能
 * 价值建构功能
 * 价值访问功能
 * 分组功能
 * 散列函数
 * 辅助功能
 * 汇总功能
 * 时间间隔和点单位说明符
 * 列功能
 */
public class FunctionDemoMain {
    public static void runFunction(String functionSQL) {
        try {
            KafkaConnectDemo.registerKafkaSourceTable();
            MySQLConnectDemo.registerFunction();

            FlinkStreamInitDemo.printDataRetract(functionSQL);

            FlinkStreamInitDemo.streamEnv.execute("Functions_testJob");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
