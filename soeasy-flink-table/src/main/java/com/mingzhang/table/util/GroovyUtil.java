package com.mingzhang.table.util;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroovyUtil {

    private static Map<String, Script> cacheScript = new ConcurrentHashMap<>();
    private static GroovyShell groovyShell = new GroovyShell();

    public static Script createScript(String scriptText) {
        Script script = cacheScript.get(scriptText);
        if (null == script) {
            script = groovyShell.parse(scriptText);
            cacheScript.put(scriptText, script);
        }
        return script;
    }

    public static Script createScriptNoCache(String scriptText) {
        return groovyShell.parse(scriptText);
    }

    /**
     * 列值特殊处理
     * @param columnValue
     * @param scriptText
     * @return
     */
    public static String columnValueFormat(String columnValue, String scriptText) {
        Script script = createScript(scriptText);
        Binding binding = new Binding();
        binding.setVariable("column", columnValue);
        script.setBinding(binding);
        return script.run().toString();
    }
}
