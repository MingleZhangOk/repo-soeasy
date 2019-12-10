package com.mingzhang.table.functions;

import com.mingzhang.table.source.kafka.KafkaConnectDemo;

public class DateFunctionsDemo {
    public static void main(String[] args) {
        String dateSQL ="select INTERVAL '1' DAY from "+ KafkaConnectDemo.tableName;
        FunctionDemoMain.runFunction(dateSQL);
    }
}
