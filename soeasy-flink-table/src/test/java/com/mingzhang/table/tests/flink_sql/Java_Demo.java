/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Java_Demo
 * Author:   h
 * Date:     2018/11/28 9:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.flink_sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Java_Demo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");//oracle.jdbc.driver.OracleDriver___com.ibm.db2.jcc.DB2Driver__oracle.jdbc.OracleDriver
            String url = "jdbc:oracle:thin:@//172.16.60.20:1521/zhpoc";//jdbc:db2://172.16.60.138:50000/CDASDB
            String user = "yusp";
            String pwd = "yusp";
            conn = DriverManager.getConnection(url, user, pwd);
            String sql = "select * from FR_ENG_SQL_Rule_Info";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String source_sql = rs.getString("SOURCE_SQL");
                String target_sql = rs.getString("TARGET_SQL");
                String process_sql = rs.getString("PROCESS_SQL");
                System.out.println(source_sql);
                System.out.println(target_sql);
                System.out.println(process_sql);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

    }

}