package com.mingzhang.api.common;

/**
 * @author MingZhang
 * @date 2019/5/23
 */
public enum  CacheType {
    LRU("LRU"),
    ALL("ALL")

    ;


    private String type;

    CacheType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
