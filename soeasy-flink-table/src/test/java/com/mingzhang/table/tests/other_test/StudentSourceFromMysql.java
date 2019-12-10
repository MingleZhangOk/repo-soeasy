/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StudentSourceFromMysql_Java
 * Author:   h
 * Date:     2018/11/8 17:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.other_test;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class StudentSourceFromMysql extends RichSourceFunction<Student> {
    private PreparedStatement ps;
    private Connection connection;
    public static Integer count = 1;

    /**
     * 一、open()方法中建立连接，这样不用每次invoke的时候都要建立连接和释放连接。
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "root";
        //1.加载驱动
        Class.forName(driver);
        //2.创建连接
        connection = DriverManager.getConnection(url, username, password);
        //3.获得执行语句
        String sql = "select * from Student;";
       /* ps = connection.prepareStatement(sql);
        ResultSet countSet = ps.executeQuery();
        while (countSet.next()) {
            count = countSet.getInt("count(1)");
        }
        sql = "select * from Student where id = " + count + ";";*/
        ps = connection.prepareStatement(sql);
    }

    /**
     * 二、DataStream调用一次run()方法用来获取数据
     */
    @Override
    public void run(SourceContext<Student> sourceContext) throws Exception {
        try {
            //4.执行查询，封装数据
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name").trim(),
                        resultSet.getString("sex").trim(),
                        resultSet.getString("address").trim());
                student.setCount(count);
                sourceContext.collect(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancel() {

    }

    /**
     * 三、 程序执行完毕就可以进行，关闭连接和释放资源的动作了
     */
    @Override
    public void close() throws Exception {
        //5.关闭连接和释放资源
        super.close();
        if (connection != null) {
            connection.close();
        }
        if (ps != null) {
            ps.close();
        }
    }
}