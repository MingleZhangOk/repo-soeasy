package com.mingzhang.table.tests.mainFile;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class ClassTestFile {
    public static void main(String[] args) {
        String filePath = "file://E:\\JsonPojo.class";
        String className = "JsonPojo";
        Class<? extends URLClassLoader> aClass = null;
        try {
            URL url = new URL(filePath);
            URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[]{url});
            aClass = urlClassLoader.getClass();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(aClass);
        if (aClass != null) {
            Field[] fields = aClass.getFields();
            String s = Arrays.toString(fields);
            System.out.println(s);
        }
    }
}
