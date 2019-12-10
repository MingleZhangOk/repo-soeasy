package com.mingzhang.table.enums;

public enum FileMethod {

    APPEND("APPEND"),REPLACEALL("REPLACEALL");

    private String name;

    private FileMethod(String name){
        this.name = name;
    }

}
