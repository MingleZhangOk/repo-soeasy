package com.mingzhang.table.enums;

public enum LoadType {
    APPEND("APPEND"),OVERWRITE("OVERWRITE");

    private String code;

    LoadType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
