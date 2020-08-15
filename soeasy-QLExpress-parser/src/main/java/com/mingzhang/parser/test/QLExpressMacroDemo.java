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
public class QLExpressMacroDemo {

    public static void test1(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        Object result = expressRunner.execute("是否优秀", context, null, false, false);
        System.out.println(result);
        //返回结果true
    }

}
