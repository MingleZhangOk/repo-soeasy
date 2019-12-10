/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Flink_Connect_DB2
 * Author:   h
 * Date:     2018/11/29 9:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.readData_Tools;


import com.mingzhang.table.tests.entity_Package.FR_ENG_SQL_Rule_Info_Pojo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SQL_Connect_DB2 {

    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static Map<String, FR_ENG_SQL_Rule_Info_Pojo> pojoMap = new HashMap<>();

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//oracle.jdbc.driver.OracleDriver___com.ibm.db2.jcc.DB2Driver
            String url = "jdbc:oracle:thin:@//172.16.60.20:1521/zhpoc";//jdbc:db2://172.16.60.138:50000/CDASDB
            String user = "yusp";
            String pwd = "yusp";
            conn = DriverManager.getConnection(url, user, pwd);
            String sql = "select * from FR_ENG_SQL_Rule_Info";
            ps = conn.prepareStatement(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    public static Map<String, FR_ENG_SQL_Rule_Info_Pojo> init() throws SQLException {

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String source_sql = rs.getString("SOURCE_SQL");
            String target_sql = rs.getString("TARGET_SQL");
            String process_sql = rs.getString("PROCESS_SQL");
            if (process_sql.contains("KAFKA_TABLE") || process_sql.contains("FR_BUSIRULE_INFO")) {
                FR_ENG_SQL_Rule_Info_Pojo pojo = new FR_ENG_SQL_Rule_Info_Pojo();
                pojo.setSOURCE_SQL(source_sql);
                pojo.setTARGET_SQL(target_sql);
                pojo.setPROCESS_SQL(process_sql);
                pojoMap.put(target_sql, pojo);
            }
        }
        return pojoMap;
    }
}