package com.mingzhang.table.enums;

public enum  BusiType {

    COMPUTE("1");

    private String code;

    BusiType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
