package com.mingzhang.parser.test;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-10 21:56
 */
public class QLExpressParserDemo {

    public static void test1(ExpressRunner expressRunner, DefaultContext<String, Object> context) throws Exception {
        //注意以下脚本int和没有int的区别
        String express = "int 平均分 = (语文+数学+英语+综合考试.科目2)/4.0;return 平均分";
        String express1 = "平均分 = (语文+数学+英语+综合考试.科目2)/4.0;return 平均分";
        String[] names = expressRunner.getOutVarNames(express);
        for (String s : names) {
            System.out.println("var : " + s);
        }
        //输出结果：
        //var : 数学
        //var : 综合考试
        //var : 英语
        //var : 语文
    }

}
