package com.mingzhang.table.enums;

public enum NoSqlType {

    ELASTIC("ELASTIC"),REDIS("REDIS");

    private String code;

    public String getCode() {
        return code;
    }

    private NoSqlType(String code){
        this.code = code;
    }

}
