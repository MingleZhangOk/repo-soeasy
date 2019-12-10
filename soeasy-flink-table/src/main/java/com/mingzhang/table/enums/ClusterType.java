package com.mingzhang.table.enums;

public enum ClusterType {

    SINGLE("SINGLE"),CLUSTER("CLUSTER");

    private String code;

    private ClusterType(String code){
        this.code = code;
    }
}
