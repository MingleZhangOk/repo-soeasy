package com.mingzhang.table.enums;

public enum FlagType {

    YES("1"),NO("0");
    private String code;

    FlagType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
