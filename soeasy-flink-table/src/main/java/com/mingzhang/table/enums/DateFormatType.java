package com.mingzhang.table.enums;

public enum DateFormatType {

    YYYY_MM_DD("yyyy-MM-dd"),YYYYMMDD("yyyyMMdd");

    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    private DateFormatType(String format){
        this.format =format;
    }

}
