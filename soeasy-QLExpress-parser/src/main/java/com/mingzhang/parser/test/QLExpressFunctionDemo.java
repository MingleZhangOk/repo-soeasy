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
public class QLExpressFunctionDemo {

    /**
     * function add(int a,int b){
     * return a+b;
     * };
     *
     * function sub(int a,int b){
     * return a - b;
     * };
     *
     * a=10;
     * return add(a,4) + sub(a,9);
     */
    public static void test2() {

    }

    public static void test1(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        String express1 = "取绝对值(-100) ";
        String express2 = "转换为大写('hello world')";
        String express3 = "打印('你好吗？') ";
        String express4 = "contains(\"helloworld\",\"aeiou\")";
        String express5 = "取最大值(2, 2, 2, 2, 2, 3, 4, 5, 6, 1, 9, 123, 12, 32, 29, 1)";

        Object execute1 = expressRunner.execute(express1, context, null, false, false);
        Object execute2 = expressRunner.execute(express2, context, null, false, false);
        //此处运行时打印
        Object execute3 = expressRunner.execute(express3, context, null, false, false);
        Object execute4 = expressRunner.execute(express4, context, null, false, false);
        Object execute5 = expressRunner.execute(express5, context, null, false, false);

        System.out.println(express1);
        System.out.println(execute1);

        System.out.println(express2);
        System.out.println(execute2);

        System.out.println(express3);
        //运行时打印后 返回值为：null
        System.out.println(execute3);

        System.out.println(express4);
        System.out.println(execute4);

        System.out.println(express5);
        System.out.println(execute5);
    }

}
