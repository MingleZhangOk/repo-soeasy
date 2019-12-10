package com.mingzhang.table.enums;

public enum CompType {

    BATCH("1"),REALTIME("2");

    private String code;

    CompType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
