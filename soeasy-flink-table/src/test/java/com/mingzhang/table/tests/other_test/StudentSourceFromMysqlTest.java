/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StudentSourceFromMysqlTest_Java
 * Author:   h
 * Date:     2018/11/8 17:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.other_test;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StudentSourceFromMysqlTest {
    public static void main(String[] args) throws Exception {
        //1.创建流执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        StudentSourceFromMysql studentSourceFromMysql = new StudentSourceFromMysql();
        DataStream<Student> students1 = env.addSource(studentSourceFromMysql);
        //students1.print();
        int count = studentSourceFromMysql.count;
        //System.out.print(count);
        while (true) {
            DataStream<Student> students2 = env.addSource(studentSourceFromMysql);
            if (count != studentSourceFromMysql.count) {
                students2.print();
            } else {
                //2.从自定义source中读取数据
                DataStream<Student> students = env.addSource(studentSourceFromMysql);
                //3.显示结果
                students.print();
                count++;
            }
            env.execute("hll");
            //Thread.sleep(5000);
        }
        //4.触发流执行
        //env.execute("hll");
    }
}