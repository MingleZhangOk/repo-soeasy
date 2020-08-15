package com.mingzhang.stream.common;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-15 17:32
 */
enum ArithmeticOperators {
    //MAP算子
    MAP("map", 0),
    //FILTER算子
    FILTER("filter", 0),
    //KEYBY算子
    KEYBY("keyBy", 0),
    //FLATMAP算子
    FLATMAP("flatMap", 0),
    //BROADCAST算子
    BROADCAST("broadcast", 0);

    //name
    private String name;
    //type
    private Integer type;

    ArithmeticOperators(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }
}
