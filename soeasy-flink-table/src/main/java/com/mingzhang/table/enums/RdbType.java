package com.mingzhang.table.enums;

/**
 * 关系数据库类型
 */
public enum RdbType {
    ORACLE("ORACLE"),MYSQL("MYSQL"),DB2("DB2");

    private String name;

    private RdbType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
