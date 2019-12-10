/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FR_Busirule_Info_FlinkConnect
 * Author:   h
 * Date:     2018/11/29 10:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.flink_SinkSourceImpls;

import com.mingzhang.table.tests.entity_Package.FR_Busirule_Info_Pojo;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FR_Busirule_Info_FlinkSourceFunction extends RichSourceFunction<FR_Busirule_Info_Pojo> {
    private static Connection conn = null;
    private static PreparedStatement ps = null;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@//172.16.60.20:1521/zhpoc";
        String user = "yusp";
        String pwd = "yusp";
        conn = (Connection) DriverManager.getConnection(url, user, pwd);
        String sql = "select * from FR_BUSIRULE_INFO";
        ps = conn.prepareStatement(sql);
    }

    @Override
    public void run(SourceContext<FR_Busirule_Info_Pojo> ctx) throws Exception {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FR_Busirule_Info_Pojo frbip = new FR_Busirule_Info_Pojo();
            frbip.setRULE_ID(rs.getString("RULE_ID"));
            frbip.setRULE_NAME(rs.getString("RULE_NAME"));
            frbip.setRULE_DESC(rs.getString("RULE_DESC"));
            frbip.setRULE_STATE(rs.getString("RULE_STATE"));
            frbip.setRULE_TYPE(rs.getString("RULE_TYPE"));
            frbip.setRULE_CLASS(rs.getString("RULE_CLASS"));
            frbip.setRULE_LEVEL(rs.getString("RULE_LEVEL"));
            frbip.setRULE_PRIORITY(rs.getString("RULE_PRIORITY"));
            frbip.setOP_TIME(rs.getString("OP_TIME"));
            frbip.setLOGIN_NO(rs.getString("LOGIN_NO"));
            frbip.setOP_ORG(rs.getString("OP_ORG"));
            frbip.setBAK(rs.getString("BAK"));
            frbip.setRULE_KIND(rs.getString("RULE_KIND"));
            frbip.setRULE_REASON(rs.getString("RULE_REASON"));
            frbip.setRISK_TYPE(rs.getString("RISK_TYPE"));
            frbip.setBUIS_TYPE(rs.getString("BUIS_TYPE"));
            frbip.setAPP_CLASS(rs.getString("APP_CLASS"));
            frbip.setRULE_RESOURCE(rs.getString("RULE_RESOURCE"));
            frbip.setHANDLE_FLOW(rs.getString("HANDLE_FLOW"));
            frbip.setWARNING_RESULT(rs.getString("WARNING_RESULT"));
            frbip.setCHECK_PRO(rs.getString("CHECK_PRO"));
            frbip.setREM_SIGN(rs.getString("REM_SIGN"));
            frbip.setREM_STRATEGY(rs.getString("REM_STRATEGY"));
            frbip.setWARNING_SORT(rs.getString("WARNING_SORT"));
            frbip.setOPER_CYSLE(rs.getString("OPER_CYSLE"));
            frbip.setWHAT_STRATEGY(rs.getString("WHAT_STRATEGY"));
            frbip.setINTERVEN_REASON(rs.getString("INTERVEN_REASON"));
            frbip.setORG_ID(rs.getString("ORG_ID"));
            frbip.setPUB_STAT(rs.getString("PUB_STAT"));
            frbip.setID(rs.getString("ID"));
            ctx.collect(frbip);
        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public void close() throws Exception {
        super.close();
        if (conn != null) {
            conn.close();
        }
        if (ps != null) {
            ps.close();
        }
    }
}