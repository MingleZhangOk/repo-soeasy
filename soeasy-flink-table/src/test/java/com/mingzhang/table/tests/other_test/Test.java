/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: s
 * Author:   h
 * Date:     2018/11/29 19:54
 * Description: \
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.other_test;


import com.mingzhang.table.tests.entity_Package.FR_ENG_SQL_Rule_Info_Pojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<FR_ENG_SQL_Rule_Info_Pojo> pojoList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            String url = "jdbc:db2://172.16.60.138:50000/CDASDB";
            String user = "frms";
            String pwd = "frms";
            conn = DriverManager.getConnection(url, user, pwd);
            stmt = conn.createStatement();
            String sql = "select * from frms.FR_ENG_SQL_Rule_Info";
            stmt.executeQuery(sql);
            rs = stmt.getResultSet();
            while (rs.next()) {
                FR_ENG_SQL_Rule_Info_Pojo pojo = new FR_ENG_SQL_Rule_Info_Pojo();
                pojo.setSOURCE_SQL(rs.getString(6));
                pojo.setTARGET_SQL(rs.getString(7));
                pojo.setPROCESS_SQL(rs.getString(8));
                pojoList.add(pojo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(pojoList);
    }
}