package com.mingzhang.table.enums;

public enum MqType {

    KAFKA("KAFKA");

    private String code;

    private MqType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
