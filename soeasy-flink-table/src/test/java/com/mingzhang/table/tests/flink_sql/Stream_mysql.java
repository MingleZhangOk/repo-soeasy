/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Stream_mysql
 * Author:   h
 * Date:     2018/11/28 16:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

package com.mingzhang.table.tests.flink_sql;


import com.mingzhang.table.tests.other_test.Student;
import com.mingzhang.table.tests.other_test.StudentSourceFromMysql;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;

public class Stream_mysql {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);
        env.enableCheckpointing(5000);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        DataStreamSource<Student> studentDataStreamSource = env.addSource(new StudentSourceFromMysql());
        tableEnv.registerDataStream("student", studentDataStreamSource);
        Table table = tableEnv.sqlQuery("select count(*) from student");
        */
/*while (true) {
            studentDataStreamSource.print();
            env.execute("student_Test");
        }*//*

        studentDataStreamSource.print();
        env.execute("student_Test");
    }
}*/
