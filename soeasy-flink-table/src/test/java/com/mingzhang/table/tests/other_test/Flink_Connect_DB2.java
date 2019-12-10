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
package com.mingzhang.table.tests.other_test;


import java.sql.*;

public class Flink_Connect_DB2 {
    /**设置参数**/
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public  Flink_Connect_DB2() {
        try{
            System.out.println("正在连接数据库..........");
            Class.forName("com.ibm.db2.jcc.DB2Driver");//加载mysql驱动程序类
            String url = "jdbc:db2://172.16.60.138:50000/CDASDB";//url为连接字符串
            String user = "frms";//数据库用户名
            String pwd = "frms";//数据库密码
            conn=(Connection) DriverManager.getConnection(url,user,pwd);
            System.out.println("数据库连接成功！！！");
        }catch(Exception e){
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Flink_Connect_DB2 a = new Flink_Connect_DB2();//实例化对象，作用是调用构造方法
        String sql="select * from frms.FR_BUSIRULE_INFO";
        stmt = conn.createStatement();
        stmt.executeQuery(sql);//执行select语句用executeQuery()方法，执行insert、update、delete语句用executeUpdate()方法。
        rs=stmt.getResultSet();
        while(rs.next()){ //当前记录指针移动到下一条记录上
            String i = rs.getString(1);//得到当前记录的第一个字段(id)的值
            String name =rs.getString(2);//得到第二个字段(name)的值
            String psw = rs.getString(3);//得到(password)的值
            System.out.println(i+" "+name+" "+psw);
        }
        rs.close();//后定义，先关闭
        stmt.close();
        conn.close();//先定义，后关闭
    }

}