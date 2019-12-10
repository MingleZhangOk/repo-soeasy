package com.mingzhang.table.enums;

public enum SqlQueryType {

    TABLE("1"),SQL("2");

    private String code;

    private SqlQueryType(String code){
        this.code = code;
    }

}
