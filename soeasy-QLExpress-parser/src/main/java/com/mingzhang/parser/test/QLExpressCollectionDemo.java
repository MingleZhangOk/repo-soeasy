package com.mingzhang.parser.test;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-10 22:17
 */
public class QLExpressCollectionDemo {

    public static void test1(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        String express = "abc = NewMap(1:1,2:2); return abc.get(1) + abc.get(2);";
        Object r = expressRunner.execute(express, context, null, false, false);
        System.out.println(r);
        express = "abc = NewList(1,2,3); return abc.get(1)+abc.get(2)";
        r = expressRunner.execute(express, context, null, false, false);
        System.out.println(r);
        express = "abc = [1,2,3]; return abc[1]+abc[2];";
        r = expressRunner.execute(express, context, null, false, false);
        System.out.println(r);
    }

    /**
     * 其实类似java的语法，只是ql不支持for(obj:list){}的语法，只能通过下标访问。
     *
     * @param expressRunner
     * @param context
     * @throws Exception
     */
    public static void test2(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        //遍历map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", "a_value");
        map.put("b", "b_value");
        Set<String> keySet = map.keySet();
        Object[] array = keySet.toArray();
        for (int i = 0; i < array.length; i++) {
            Object key = array[i];
            System.out.println(map.get(key.toString()));
        }
    }


}
