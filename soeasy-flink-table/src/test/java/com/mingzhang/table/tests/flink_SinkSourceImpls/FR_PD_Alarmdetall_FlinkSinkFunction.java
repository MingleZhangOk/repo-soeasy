/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FR_PD_Alarmdetall_Pojo_FlinkSinkFunction
 * Author:   h
 * Date:     2018/11/29 17:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.flink_SinkSourceImpls;

import com.mingzhang.table.tests.entity_Package.FR_PD_Alarmdetall_Pojo;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author h
 * @create 2018/11/29
 * @since 1.0.0
 */
public class FR_PD_Alarmdetall_FlinkSinkFunction extends RichSinkFunction<FR_PD_Alarmdetall_Pojo> {

    private Connection connection = null;
    private PreparedStatement ps = null;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@//172.16.60.20:1521/zhpoc";
        String username = "yusp";
        String password = "yusp";
        connection = DriverManager.getConnection(url, username, password);
        String sql = "insert into FR_PD_ALARMDETAIL values (?,?,?,?,?)";
        ps = connection.prepareStatement(sql);
    }

    @Override
    public void invoke(FR_PD_Alarmdetall_Pojo pojo, Context context) throws Exception {
        try {

            ps.setLong(1, pojo.getALARM_DETID().length());
            ps.setString(2, pojo.getALARM_NO().substring(0,17));
            ps.setString(3, ""+new Random().nextInt());
            String header = "[{\"label\":\"测试账号\",\"prop\":\"DW_ACNO\",\"width\":200}," +
                    "{\"label\":\"测试交易金额\",\"prop\":\"DW_AMT\",\"width\":300}," +
                    "{\"label\":\"交易日期\",\"prop\":\"PU_TRDT\",\"width\":150}," +
                    "{\"label\":\"测试字段一\",\"prop\":\"DW_TEST01\",\"width\":200}]";
            StringReader readerHEADER = new StringReader(header);
            ps.setCharacterStream(4, readerHEADER, header.length());
            String content = "[{" + pojo.getALARM_DETID() + "," + pojo.getALARM_NO() + "," +
                    pojo.getORIGIN_TAB() + "," + pojo.getHEADER() + "," + pojo.getCONTENT() + "}]";
            StringReader readerCONTENT = new StringReader(content);
            ps.setCharacterStream(5, readerCONTENT, content.length());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        super.close();
        if (connection != null) {
            connection.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

}