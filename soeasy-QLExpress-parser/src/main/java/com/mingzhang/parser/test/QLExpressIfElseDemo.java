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
public class QLExpressIfElseDemo {

    public static void test1(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        String express1 = "如果 a+b >= c 则 a+b 否则 c-a";
        String express2 = "如果 a+b < c and a != 0  则 a 否则 c";
        String express3 = "如果 a+b >= c 并且 a != 0  则 a+c 否则 c+b";

        Object execute1 = expressRunner.execute(express1, context, null, true, false);
        Object execute2 = expressRunner.execute(express2, context, null, true, false);
        Object execute3 = expressRunner.execute(express3, context, null, true, false);

        System.out.println(context);
        System.out.println(express1);
        System.out.println(execute1);

        System.out.println(express2);
        System.out.println(execute2);

        System.out.println(express3);
        System.out.println(execute3);

        System.out.println("调整数据a=0,b=12");
        context.put("a", 0);
        context.put("b", 12);

        execute1 = expressRunner.execute(express1, context, null, true, false);
        execute2 = expressRunner.execute(express2, context, null, true, false);
        execute3 = expressRunner.execute(express3, context, null, true, false);

        System.out.println(context);
        System.out.println(express1);
        System.out.println(execute1);

        System.out.println(express2);
        System.out.println(execute2);

        System.out.println(express3);
        System.out.println(execute3);
    }

    public static void test2(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        String express1 = "如果 a>1 则 a";
        String express2 = "如果 a+b < c 或者 a != 0 则 a 否则 c";
        String express3 = "如果 a+b>=c 并且 a!=0 则 a+c 否则 c+b";

        Object execute1 = expressRunner.execute(express1, context, null, true, false);
        Object execute2 = expressRunner.execute(express2, context, null, true, false);
        Object execute3 = expressRunner.execute(express3, context, null, true, false);

        System.out.println(context);
        System.out.println(express1);
        System.out.println(execute1);

        System.out.println(express2);
        System.out.println(execute2);

        System.out.println(express3);
        System.out.println(execute3);
    }


    }
