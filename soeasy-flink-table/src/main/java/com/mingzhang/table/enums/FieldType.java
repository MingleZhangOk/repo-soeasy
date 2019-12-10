package com.mingzhang.table.enums;

public enum FieldType {

    VARCHAR("VARCHAR"),
    DECIMAL("DECIMAL"),
    LONG("LONG"),
    DATE("DATE"),
    TIME("TIME"),
    INTEGER("INTEGER"),
    TIMESTAMP("TIMESTAMP");

    private String code;
    FieldType(String code){
        this.code = code;
    }
    public String getCode() {
        return code;
    }



}


