/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FR_ENG_SQL_Rule_Info_Pojo
 * Author:   h
 * Date:     2018/11/29 17:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.entity_Package;

import java.io.Serializable;

public class FR_ENG_SQL_Rule_Info_Pojo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String RULE_ID;
    private String SQL_RULE_ID;
    private String SQL_RULE_TYPE;
    private String SQL_RULE_NAME;
    private String OPERATION_CYCLE;
    private String SOURCE_SQL;
    private String TARGET_SQL;
    private String PROCESS_SQL;
    private String OPER_USER;
    private String OPER_DT;
    private String ORG_ID;
    private String LOCK_FLAG;
    private String STATUS;

    public FR_ENG_SQL_Rule_Info_Pojo() {
    }

    public FR_ENG_SQL_Rule_Info_Pojo(String RULE_ID, String SQL_RULE_ID, String SQL_RULE_TYPE, String SQL_RULE_NAME, String OPERATION_CYCLE, String SOURCE_SQL, String TARGET_SQL, String PROCESS_SQL, String OPER_USER, String OPER_DT, String ORG_ID, String LOCK_FLAG, String STATUS) {
        this.RULE_ID = RULE_ID;
        this.SQL_RULE_ID = SQL_RULE_ID;
        this.SQL_RULE_TYPE = SQL_RULE_TYPE;
        this.SQL_RULE_NAME = SQL_RULE_NAME;
        this.OPERATION_CYCLE = OPERATION_CYCLE;
        this.SOURCE_SQL = SOURCE_SQL;
        this.TARGET_SQL = TARGET_SQL;
        this.PROCESS_SQL = PROCESS_SQL;
        this.OPER_USER = OPER_USER;
        this.OPER_DT = OPER_DT;
        this.ORG_ID = ORG_ID;
        this.LOCK_FLAG = LOCK_FLAG;
        this.STATUS = STATUS;
    }

    public String getRULE_ID() {
        return RULE_ID;
    }

    public void setRULE_ID(String RULE_ID) {
        this.RULE_ID = RULE_ID;
    }

    public String getSQL_RULE_ID() {
        return SQL_RULE_ID;
    }

    public void setSQL_RULE_ID(String SQL_RULE_ID) {
        this.SQL_RULE_ID = SQL_RULE_ID;
    }

    public String getSQL_RULE_TYPE() {
        return SQL_RULE_TYPE;
    }

    public void setSQL_RULE_TYPE(String SQL_RULE_TYPE) {
        this.SQL_RULE_TYPE = SQL_RULE_TYPE;
    }

    public String getSQL_RULE_NAME() {
        return SQL_RULE_NAME;
    }

    public void setSQL_RULE_NAME(String SQL_RULE_NAME) {
        this.SQL_RULE_NAME = SQL_RULE_NAME;
    }

    public String getOPERATION_CYCLE() {
        return OPERATION_CYCLE;
    }

    public void setOPERATION_CYCLE(String OPERATION_CYCLE) {
        this.OPERATION_CYCLE = OPERATION_CYCLE;
    }

    public String getSOURCE_SQL() {
        return SOURCE_SQL;
    }

    public void setSOURCE_SQL(String SOURCE_SQL) {
        this.SOURCE_SQL = SOURCE_SQL;
    }

    public String getTARGET_SQL() {
        return TARGET_SQL;
    }

    public void setTARGET_SQL(String TARGET_SQL) {
        this.TARGET_SQL = TARGET_SQL;
    }

    public String getPROCESS_SQL() {
        return PROCESS_SQL;
    }

    public void setPROCESS_SQL(String PROCESS_SQL) {
        this.PROCESS_SQL = PROCESS_SQL;
    }

    public String getOPER_USER() {
        return OPER_USER;
    }

    public void setOPER_USER(String OPER_USER) {
        this.OPER_USER = OPER_USER;
    }

    public String getOPER_DT() {
        return OPER_DT;
    }

    public void setOPER_DT(String OPER_DT) {
        this.OPER_DT = OPER_DT;
    }

    public String getORG_ID() {
        return ORG_ID;
    }

    public void setORG_ID(String ORG_ID) {
        this.ORG_ID = ORG_ID;
    }

    public String getLOCK_FLAG() {
        return LOCK_FLAG;
    }

    public void setLOCK_FLAG(String LOCK_FLAG) {
        this.LOCK_FLAG = LOCK_FLAG;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public String toString() {
        return "FR_ENG_SQL_Rule_Info_Pojo{" +
                "RULE_ID='" + RULE_ID + '\'' +
                ", SQL_RULE_ID='" + SQL_RULE_ID + '\'' +
                ", SQL_RULE_TYPE='" + SQL_RULE_TYPE + '\'' +
                ", SQL_RULE_NAME='" + SQL_RULE_NAME + '\'' +
                ", OPERATION_CYCLE='" + OPERATION_CYCLE + '\'' +
                ", SOURCE_SQL='" + SOURCE_SQL + '\'' +
                ", TARGET_SQL='" + TARGET_SQL + '\'' +
                ", PROCESS_SQL='" + PROCESS_SQL + '\'' +
                ", OPER_USER='" + OPER_USER + '\'' +
                ", OPER_DT='" + OPER_DT + '\'' +
                ", ORG_ID='" + ORG_ID + '\'' +
                ", LOCK_FLAG='" + LOCK_FLAG + '\'' +
                ", STATUS='" + STATUS + '\'' +
                '}';
    }
}