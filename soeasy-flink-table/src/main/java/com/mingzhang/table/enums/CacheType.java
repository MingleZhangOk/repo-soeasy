package com.mingzhang.table.enums;

public enum CacheType {

    ALL("ALL"),LRU("LRU");

    private String code;
    private CacheType(String code){
        this.code = code;
    }
}
