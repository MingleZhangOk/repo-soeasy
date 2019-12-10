package com.mingzhang.table.enums;

public enum TextFormat {

    JSON("JSON","JSON格式","标准json格式"),
    CVS("CVS","CVS格式","标准CVS格式"),
    TXT("TXT","纯文本格式","纯文本格式"),
    AVRO("AVRO","AVRO格式","主要用于批量文件格式"),
    PARQUET("PARQUET","PARQUET格式","主要用于批量文件");

    private String code;
    private String name;
    private String desc;

    private TextFormat(String code,String name,String desc){
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
