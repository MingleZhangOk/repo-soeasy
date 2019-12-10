package com.mingzhang.table.enums;

public enum TableAppType {

    EXT("EXT"),SRC("SOURCE"),SINK("SINK");

    private String code;
     TableAppType(String code) {
          this.code = code;
    }

    public String getCode() {
        return code;
    }
}
