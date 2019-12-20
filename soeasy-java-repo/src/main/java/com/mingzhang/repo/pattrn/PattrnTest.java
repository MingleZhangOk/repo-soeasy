package com.mingzhang.repo.pattrn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2019-12-18 23:20
 */
public class PattrnTest {
    public static void main(String[] args) {
        demo5();
    }

    public static void demo1() {
        String content = "I am noob " +
                "from runoob.com.";

        String pattern = ".*runoob.*";


        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
    }


    public static void demo2() {

        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    public static void demo3() {

        // 按指定模式在字符串查找
        String line = "WHERE SYSSECOND BETWEEN NOWTIME AND NOWTIME-1H";
        String pattern = "NOWTIME-(\\d+)(\\D*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    public static void demo4() {

        // 按指定模式在字符串查找
        String line = "WHERE SYSSECOND BETWEEN NOWTIME AND NOWTIME-1H";
        String pattern = "SYS(\\D*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }


    public static void demo5() {
        String line = "WHERE   SYSSECOND(yyyyMMddHH)   BETWEEN    NOWTIME   AND     NOWTIME-1H";
        String pattern1 = "\\((\\D*)\\)";
        String pattern = " (\\D*)\\(";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(1).trim());
        } else {
            System.out.println("NO MATCH");
        }
    }

}
