package com.mingzhang.table.enums;

public enum FileType {
    LOCALFILE("LOCALFILE"),HDFS("HDFS");
    private String code;

    private FileType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
