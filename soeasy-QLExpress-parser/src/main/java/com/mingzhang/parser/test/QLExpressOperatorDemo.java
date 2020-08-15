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
public class QLExpressOperatorDemo {

    /**
     * addOperator
     *
     * @param expressRunner
     * @param context
     * @throws Exception
     */
    public static void test1(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        String express = "1 join 2 join 3";
        System.out.println(express);
        Object r = expressRunner.execute(express, context, null, false, false);
        System.out.println(r);
        //返回结果  [1, 2, 3]
    }

    /**
     * replaceOperator
     *
     * @param expressRunner
     * @param context
     * @throws Exception
     */
    public static void test2(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        expressRunner.replaceOperator("+", new JoinOperatorDemo());
        String express = "1 + 2 + 3";
        System.out.println(express);
        Object r = expressRunner.execute(express, context, null, false, false);
        System.out.println(r);
        //返回结果  [1, 2, 3]
    }

    /**
     * addFunction
     *
     * @param expressRunner
     * @param context
     * @throws Exception
     */
    public static void test3(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        expressRunner.addFunction("join", new JoinOperatorDemo());
        String express1 = "join(1,2,3,4)";
        String express = "join(3,4)";
        System.out.println(express);
        Object r = expressRunner.execute(express, context, null, false, false);
        System.out.println(r);
        //返回结果  [1, 2, 3]
    }


}
