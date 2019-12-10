/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FR_PD_Alarmdetall_Pojo
 * Author:   h
 * Date:     2018/11/30 9:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.entity_Package;

import java.io.Serializable;

public class FR_PD_Alarmdetall_Pojo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ALARM_DETID;
    private String ALARM_NO;
    private String ORIGIN_TAB;
    private String HEADER;
    private String CONTENT;

    public FR_PD_Alarmdetall_Pojo() {
    }

    public FR_PD_Alarmdetall_Pojo(String ALARM_DETID, String ALARM_NO, String ORIGIN_TAB, String HEADER, String CONTENT) {
        this.ALARM_DETID = ALARM_DETID;
        this.ALARM_NO = ALARM_NO;
        this.ORIGIN_TAB = ORIGIN_TAB;
        this.HEADER = HEADER;
        this.CONTENT = CONTENT;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getALARM_DETID() {
        return ALARM_DETID;
    }

    public void setALARM_DETID(String ALARM_DETID) {
        this.ALARM_DETID = ALARM_DETID;
    }

    public String getALARM_NO() {
        return ALARM_NO;
    }

    public void setALARM_NO(String ALARM_NO) {
        this.ALARM_NO = ALARM_NO;
    }

    public String getORIGIN_TAB() {
        return ORIGIN_TAB;
    }

    public void setORIGIN_TAB(String ORIGIN_TAB) {
        this.ORIGIN_TAB = ORIGIN_TAB;
    }

    public String getHEADER() {
        return HEADER;
    }

    public void setHEADER(String HEADER) {
        this.HEADER = HEADER;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    @Override
    public String toString() {
        return "FR_PD_Alarmdetall_Pojo{" +
                "ALARM_DETID='" + ALARM_DETID + '\'' +
                ", ALARM_NO='" + ALARM_NO + '\'' +
                ", ORIGIN_TAB='" + ORIGIN_TAB + '\'' +
                ", HEADER='" + HEADER + '\'' +
                ", CONTENT='" + CONTENT + '\'' +
                '}';
    }
}