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

import com.mingzhang.table.tests.entity_Package.FR_PD_Alarmmain_Pojo;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author h
 * @create 2018/11/29
 * @since 1.0.0
 */
public class FR_PD_Alarmmain_FlinkSinkFunction extends RichSinkFunction<FR_PD_Alarmmain_Pojo> {
    int i = 300;
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
        String sql = "insert into FR_PD_ALARMMAIN values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        ps = connection.prepareStatement(sql);
    }

    @Override
    public void invoke(FR_PD_Alarmmain_Pojo pojo, Context context) throws Exception {
        i++;
        try {
            ps.setLong(1, i);
            ps.setString(2, pojo.getALARM_NO().substring(0, 5));
            ps.setTimestamp(3, new Timestamp(Long.parseLong(pojo.getALARM_DTM()
                    .trim().substring(0, 5))));
            ps.setString(4, pojo.getBRANCH_ID().substring(0, 5));
            ps.setString(5, pojo.getTX_LOG_NO().substring(0, 5));
            ps.setString(6, pojo.getORG_ID().substring(0, 5));
            ps.setString(7, pojo.getTELLER_ID().substring(0, 5));
            ps.setString(8, pojo.getTX_DT().substring(0, 5));
            ps.setString(9, pojo.getTX_TM().substring(0, 5));
            ps.setString(10, pojo.getTX_KEYF().substring(0, 5));
            ps.setString(11, pojo.getSUBRULE_ID().substring(0, 5));
            ps.setString(12, pojo.getRULE_ID().substring(0, 5));
            ps.setString(13, pojo.getRULE_STAR().substring(0, 5));
            ps.setString(14, pojo.getRULE_NAME().substring(0, 5));
            ps.setString(15, pojo.getRULE_TYPE().substring(0, 5));
            ps.setString(16, pojo.getBUS_TYPE().substring(0, 5));
            ps.setString(17, "");
            ps.setString(18, pojo.getCHANNEL_TYPE().substring(0, 5));
            ps.setString(19, pojo.getRULE_PROCESS().substring(0, 5));
            ps.setString(20, pojo.getRULE_ORIGIN().substring(0, 5));
            ps.setString(21, pojo.getDYNAMIC_VALUE().substring(0, 5));
            ps.setString(22, "");
            ps.setString(23, pojo.getOPRATOR_ID().substring(0, 5));
            ps.setString(24, "");
            ps.setString(25, pojo.getPDEF_ID().substring(0, 5));
            ps.setString(26, pojo.getALARM_FIELD1().substring(0, 5));
            ps.setString(27, pojo.getALARM_FIELD2().substring(0, 5));
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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