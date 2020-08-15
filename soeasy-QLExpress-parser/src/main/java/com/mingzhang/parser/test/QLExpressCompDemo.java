package com.mingzhang.parser.test;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-10 17:32
 */
public class QLExpressCompDemo {

    public static void test1(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        System.out.println(context);
        String express1 = "(a+b)*c-(a+b)-c";
        String express2 = "(a-b)*c+(a-b)+c";
        String express3 = "a+b+c+a*b*c";
        System.out.println(express1);
        System.out.println(express2);
        System.out.println(express3);
        Object result1 = expressRunner.execute(express1, context, null, true, false);
        Object result2 = expressRunner.execute(express2, context, null, true, false);
        Object result3 = expressRunner.execute(express3, context, null, true, false);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

}
