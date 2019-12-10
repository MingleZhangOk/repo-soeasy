/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FR_PD_Alarmmain_Pojo
 * Author:   h
 * Date:     2018/11/30 9:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.entity_Package;

import java.io.Serializable;

public class FR_PD_Alarmmain_Pojo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ALARM_ID;
    private String ALARM_NO;
    private String ALARM_DTM;
    private String BRANCH_ID;
    private String TX_LOG_NO;
    private String ORG_ID;
    private String TELLER_ID;
    private String TX_DT;
    private String TX_TM;
    private String TX_KEYF;
    private String SUBRULE_ID;
    private String RULE_ID;
    private String RULE_STAR;
    private String RULE_NAME;
    private String RULE_TYPE;
    private String BUS_TYPE;
    private String RULE_LEV;
    private String CHANNEL_TYPE;
    private String RULE_PROCESS;
    private String RULE_ORIGIN;
    private String DYNAMIC_VALUE;
    private String ALARM_STAT;
    private String OPRATOR_ID;
    private String TASK_STAT;
    private String PDEF_ID;
    private String ALARM_FIELD1;
    private String ALARM_FIELD2;

    public FR_PD_Alarmmain_Pojo() {
    }

    public FR_PD_Alarmmain_Pojo(String ALARM_ID, String ALARM_NO, String ALARM_DTM, String BRANCH_ID, String TX_LOG_NO, String ORG_ID, String TELLER_ID, String TX_DT, String TX_TM, String TX_KEYF, String SUBRULE_ID, String RULE_ID, String RULE_STAR, String RULE_NAME, String RULE_TYPE, String BUS_TYPE, String RULE_LEV, String CHANNEL_TYPE, String RULE_PROCESS, String RULE_ORIGIN, String DYNAMIC_VALUE, String ALARM_STAT, String OPRATOR_ID, String TASK_STAT, String PDEF_ID, String ALARM_FIELD1, String ALARM_FIELD2) {
        this.ALARM_ID = ALARM_ID;
        this.ALARM_NO = ALARM_NO;
        this.ALARM_DTM = ALARM_DTM;
        this.BRANCH_ID = BRANCH_ID;
        this.TX_LOG_NO = TX_LOG_NO;
        this.ORG_ID = ORG_ID;
        this.TELLER_ID = TELLER_ID;
        this.TX_DT = TX_DT;
        this.TX_TM = TX_TM;
        this.TX_KEYF = TX_KEYF;
        this.SUBRULE_ID = SUBRULE_ID;
        this.RULE_ID = RULE_ID;
        this.RULE_STAR = RULE_STAR;
        this.RULE_NAME = RULE_NAME;
        this.RULE_TYPE = RULE_TYPE;
        this.BUS_TYPE = BUS_TYPE;
        this.RULE_LEV = RULE_LEV;
        this.CHANNEL_TYPE = CHANNEL_TYPE;
        this.RULE_PROCESS = RULE_PROCESS;
        this.RULE_ORIGIN = RULE_ORIGIN;
        this.DYNAMIC_VALUE = DYNAMIC_VALUE;
        this.ALARM_STAT = ALARM_STAT;
        this.OPRATOR_ID = OPRATOR_ID;
        this.TASK_STAT = TASK_STAT;
        this.PDEF_ID = PDEF_ID;
        this.ALARM_FIELD1 = ALARM_FIELD1;
        this.ALARM_FIELD2 = ALARM_FIELD2;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getALARM_ID() {
        return ALARM_ID;
    }

    public void setALARM_ID(String ALARM_ID) {
        this.ALARM_ID = ALARM_ID;
    }

    public String getALARM_NO() {
        return ALARM_NO;
    }

    public void setALARM_NO(String ALARM_NO) {
        this.ALARM_NO = ALARM_NO;
    }

    public String getALARM_DTM() {
        return ALARM_DTM;
    }

    public void setALARM_DTM(String ALARM_DTM) {
        this.ALARM_DTM = ALARM_DTM;
    }

    public String getBRANCH_ID() {
        return BRANCH_ID;
    }

    public void setBRANCH_ID(String BRANCH_ID) {
        this.BRANCH_ID = BRANCH_ID;
    }

    public String getTX_LOG_NO() {
        return TX_LOG_NO;
    }

    public void setTX_LOG_NO(String TX_LOG_NO) {
        this.TX_LOG_NO = TX_LOG_NO;
    }

    public String getORG_ID() {
        return ORG_ID;
    }

    public void setORG_ID(String ORG_ID) {
        this.ORG_ID = ORG_ID;
    }

    public String getTELLER_ID() {
        return TELLER_ID;
    }

    public void setTELLER_ID(String TELLER_ID) {
        this.TELLER_ID = TELLER_ID;
    }

    public String getTX_DT() {
        return TX_DT;
    }

    public void setTX_DT(String TX_DT) {
        this.TX_DT = TX_DT;
    }

    public String getTX_TM() {
        return TX_TM;
    }

    public void setTX_TM(String TX_TM) {
        this.TX_TM = TX_TM;
    }

    public String getTX_KEYF() {
        return TX_KEYF;
    }

    public void setTX_KEYF(String TX_KEYF) {
        this.TX_KEYF = TX_KEYF;
    }

    public String getSUBRULE_ID() {
        return SUBRULE_ID;
    }

    public void setSUBRULE_ID(String SUBRULE_ID) {
        this.SUBRULE_ID = SUBRULE_ID;
    }

    public String getRULE_ID() {
        return RULE_ID;
    }

    public void setRULE_ID(String RULE_ID) {
        this.RULE_ID = RULE_ID;
    }

    public String getRULE_STAR() {
        return RULE_STAR;
    }

    public void setRULE_STAR(String RULE_STAR) {
        this.RULE_STAR = RULE_STAR;
    }

    public String getRULE_NAME() {
        return RULE_NAME;
    }

    public void setRULE_NAME(String RULE_NAME) {
        this.RULE_NAME = RULE_NAME;
    }

    public String getRULE_TYPE() {
        return RULE_TYPE;
    }

    public void setRULE_TYPE(String RULE_TYPE) {
        this.RULE_TYPE = RULE_TYPE;
    }

    public String getBUS_TYPE() {
        return BUS_TYPE;
    }

    public void setBUS_TYPE(String BUS_TYPE) {
        this.BUS_TYPE = BUS_TYPE;
    }

    public String getRULE_LEV() {
        return RULE_LEV;
    }

    public void setRULE_LEV(String RULE_LEV) {
        this.RULE_LEV = RULE_LEV;
    }

    public String getCHANNEL_TYPE() {
        return CHANNEL_TYPE;
    }

    public void setCHANNEL_TYPE(String CHANNEL_TYPE) {
        this.CHANNEL_TYPE = CHANNEL_TYPE;
    }

    public String getRULE_PROCESS() {
        return RULE_PROCESS;
    }

    public void setRULE_PROCESS(String RULE_PROCESS) {
        this.RULE_PROCESS = RULE_PROCESS;
    }

    public String getRULE_ORIGIN() {
        return RULE_ORIGIN;
    }

    public void setRULE_ORIGIN(String RULE_ORIGIN) {
        this.RULE_ORIGIN = RULE_ORIGIN;
    }

    public String getDYNAMIC_VALUE() {
        return DYNAMIC_VALUE;
    }

    public void setDYNAMIC_VALUE(String DYNAMIC_VALUE) {
        this.DYNAMIC_VALUE = DYNAMIC_VALUE;
    }

    public String getALARM_STAT() {
        return ALARM_STAT;
    }

    public void setALARM_STAT(String ALARM_STAT) {
        this.ALARM_STAT = ALARM_STAT;
    }

    public String getOPRATOR_ID() {
        return OPRATOR_ID;
    }

    public void setOPRATOR_ID(String OPRATOR_ID) {
        this.OPRATOR_ID = OPRATOR_ID;
    }

    public String getTASK_STAT() {
        return TASK_STAT;
    }

    public void setTASK_STAT(String TASK_STAT) {
        this.TASK_STAT = TASK_STAT;
    }

    public String getPDEF_ID() {
        return PDEF_ID;
    }

    public void setPDEF_ID(String PDEF_ID) {
        this.PDEF_ID = PDEF_ID;
    }

    public String getALARM_FIELD1() {
        return ALARM_FIELD1;
    }

    public void setALARM_FIELD1(String ALARM_FIELD1) {
        this.ALARM_FIELD1 = ALARM_FIELD1;
    }

    public String getALARM_FIELD2() {
        return ALARM_FIELD2;
    }

    public void setALARM_FIELD2(String ALARM_FIELD2) {
        this.ALARM_FIELD2 = ALARM_FIELD2;
    }

    @Override
    public String toString() {
        return "FR_PD_Alarmmain_Pojo{" +
                "ALARM_ID='" + ALARM_ID + '\'' +
                ", ALARM_NO='" + ALARM_NO + '\'' +
                ", ALARM_DTM='" + ALARM_DTM + '\'' +
                ", BRANCH_ID='" + BRANCH_ID + '\'' +
                ", TX_LOG_NO='" + TX_LOG_NO + '\'' +
                ", ORG_ID='" + ORG_ID + '\'' +
                ", TELLER_ID='" + TELLER_ID + '\'' +
                ", TX_DT='" + TX_DT + '\'' +
                ", TX_TM='" + TX_TM + '\'' +
                ", TX_KEYF='" + TX_KEYF + '\'' +
                ", SUBRULE_ID='" + SUBRULE_ID + '\'' +
                ", RULE_ID='" + RULE_ID + '\'' +
                ", RULE_STAR='" + RULE_STAR + '\'' +
                ", RULE_NAME='" + RULE_NAME + '\'' +
                ", RULE_TYPE='" + RULE_TYPE + '\'' +
                ", BUS_TYPE='" + BUS_TYPE + '\'' +
                ", RULE_LEV='" + RULE_LEV + '\'' +
                ", CHANNEL_TYPE='" + CHANNEL_TYPE + '\'' +
                ", RULE_PROCESS='" + RULE_PROCESS + '\'' +
                ", RULE_ORIGIN='" + RULE_ORIGIN + '\'' +
                ", DYNAMIC_VALUE='" + DYNAMIC_VALUE + '\'' +
                ", ALARM_STAT='" + ALARM_STAT + '\'' +
                ", OPRATOR_ID='" + OPRATOR_ID + '\'' +
                ", TASK_STAT='" + TASK_STAT + '\'' +
                ", PDEF_ID='" + PDEF_ID + '\'' +
                ", ALARM_FIELD1='" + ALARM_FIELD1 + '\'' +
                ", ALARM_FIELD2='" + ALARM_FIELD2 + '\'' +
                '}';
    }
}