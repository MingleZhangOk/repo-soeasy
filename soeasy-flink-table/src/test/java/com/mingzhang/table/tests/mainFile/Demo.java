package com.mingzhang.table.tests.mainFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws Exception {
        String filePath = "E:\\JsonPojo777.java";
        String packagePath = "cn.com.frms.com.mingzhang.table.tests.mainFile.";
        String className = "JsonPojo777";
        String s = fileRead(filePath);
        MemoryClassLoader.getInstrance().registerJava(className, s);
        Class<?> aClass = MemoryClassLoader.getInstrance().findClass(packagePath + className);
        Field[] declaredFields = aClass.getDeclaredFields();
        SomePojo o = (SomePojo) aClass.newInstance();
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);//如果属性是私有的，那么就需要设置可访问
            declaredFields[i].set(o, "value"+i);
        }
        System.out.println(Arrays.toString(aClass.getDeclaredMethods()));

        System.out.println(o.toString());

        System.out.println(Arrays.toString(aClass.getDeclaredFields()));
    }

    public static String fileRead(String filePath) throws Exception {
        File file = new File(filePath);//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
            //System.out.println(s);
        }
        bReader.close();
        String str = sb.toString();
        //System.out.println(str);
        return str;
    }
}
