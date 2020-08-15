package com.mingzhang.parser.test;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.DynamicParamsUtil;
import com.ql.util.express.ExpressRunner;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-10 17:32
 */
public class QLExpressParent {

    private static final ExpressRunner expressRunner = new ExpressRunner();
    private static final DefaultContext<String, Object> context = new DefaultContext<String, Object>();

    static {
        try {
            expressRunner.addOperatorWithAlias("如果", "if", null);
            expressRunner.addOperatorWithAlias("则", "then", null);
            expressRunner.addOperatorWithAlias("否则", "else", null);
            expressRunner.addOperatorWithAlias("或者", "or", null);
            expressRunner.addOperatorWithAlias("并且", "and", null);


            expressRunner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs", new String[]{"double"}, null);
            expressRunner.addFunctionOfClassMethod("取最大值", JavaBeanDemo.class.getName(), "getMax", new Class[]{Integer[].class}, null);
            //(1)默认的不定参数可以使用数组来代替
            //取最大值([2, 2, 2, 2, 2, 3, 4, 5, 6, 1, 9, 123, 12, 32, 29, 1])
            //(2)像java一样,支持函数动态参数调用,需要打开以下全局开关,否则以下调用会失败
            //取最大值(2, 2, 2, 2, 2, 3, 4, 5, 6, 1, 9, 123, 12, 32, 29, 1)

            //TODO 20200810异常标记
            // 使用（2）必须开启全局动态参数调用：  DynamicParamsUtil.supportDynamicParams = true;否则报错：Caused by: com.ql.util.express.exception.QLException: 定义的参数长度与运行期传入的参数长度不一致
            DynamicParamsUtil.supportDynamicParams = true;
//        expressRunner.addFunctionOfClassMethod("取最大值", JavaBeanDemo.class.getName(), "getMax", new Class[]{int[].class}, null);
            expressRunner.addFunctionOfClassMethod("转换为大写", JavaBeanDemo.class.getName(), "upper", new String[]{"String"}, null);
            expressRunner.addFunctionOfServiceMethod("打印", System.out, "println", new String[]{"Object"}, null);
            expressRunner.addFunctionOfServiceMethod("contains", new JavaBeanDemo(), "anyContains", new Class[]{String.class, String.class}, null);

            expressRunner.addOperator("join", new JoinOperatorDemo());

            expressRunner.addMacro("计算平均成绩", "( 语文 + 数学 + 英语 ) / 3.0");
            expressRunner.addMacro("是否优秀", "计算平均成绩 > 90");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void initCollect() {
        context.clear();
        context.put("a", 2);
        context.put("b", 5);
        context.put("c", 9);

        context.put("语文", 88);
        context.put("数学", 99);
        context.put("英语", 95);
    }

    public static ExpressRunner getOrCreateRunner() {
        return expressRunner;
    }

    public static DefaultContext<String, Object> getOrCreateContext() {
        initCollect();
        return context;
    }


}
