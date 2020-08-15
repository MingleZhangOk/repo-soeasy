package com.vova.parser.demo;

import java.util.HashMap;
import java.util.Map;

public class SymbolMap {
    /** 数学运算映射关系*/
    public final static Map<String, String> mathSym = new HashMap<String, String>() {
        {
            put("+", "+");
            put("-", "-");
            put("*", "*");
            put("/", "/");
        }
    };

    /** 条件运算映射关系*/
    public final static Map<String, String> compareSym = new HashMap<String, String>() {
        {
            put(">", ">");
            put(">=", ">=");
            put("<", "<");
            put("<=", "<=");
            put("=", "=");
            put("!=", "!=");
        }
    };

    public final static Map<String, String> logicalSym = new HashMap<String, String>() {
        {
            put("||", "||");
            put("&&", "&&");
        }
    };

    public final static Map<String, String> NoneSym = new HashMap<String, String>() {
        {
            put("isNull", "isNull");
            put("isNotNull", "isNotNull");

        }
    };


}
