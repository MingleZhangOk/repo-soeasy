package com.mingzhang.table.enums;

public enum MqOprType {

    PRODUCER("PRODUCER"),COSUMER("COSUMER");

    private String code;

      MqOprType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
