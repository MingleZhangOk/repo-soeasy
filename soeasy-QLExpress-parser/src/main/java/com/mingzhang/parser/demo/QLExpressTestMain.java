package com.mingzhang.parser.demo;

import com.mingzhang.parser.test.*;
import com.ql.util.express.ExpressRunner;

import java.util.Date;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-10 17:32
 */
public class QLExpressTestMain {

    private static ExpressRunner expressRunner = QLExpressParent.getOrCreateRunner();
    private static int breakNum = 1;
    private static int breakFlag = 0;
    private static int recursionNum = 0;
    private static int recursionFlag = 0;

    /**
     * 由阿里的电商业务规则、表达式（布尔组合）、特殊数学公式计算（高精度）、语法分析、脚本二次定制等强需求而设计的一门动态脚本引擎解析工具。 在阿里集团有很强的影响力，同时为了自身不断优化、发扬开源贡献精神，于2012年开源。
     * <p>
     * QLExpress脚本引擎被广泛应用在阿里的电商业务场景，具有以下的一些特性:
     * <p>
     * 1、线程安全，引擎运算过程中的产生的临时变量都是threadlocal类型。
     * 2、高效执行，比较耗时的脚本编译过程可以缓存在本地机器，运行时的临时变量创建采用了缓冲池的技术，和groovy性能相当。
     * 3、弱类型脚本语言，和groovy，javascript语法类似，虽然比强类型脚本语言要慢一些，但是使业务的灵活度大大增强。
     * 4、安全控制,可以通过设置相关运行参数，预防死循环、高危系统api调用等情况。
     * 5、代码精简，依赖最小，250k的jar包适合所有java的运行环境，在android系统的低端pos机也得到广泛运用。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Date date = new Date();
        while (true) {
            startTest();
            recursionRule(recursionFlag);
            breakFlag++;
            if (breakFlag == breakNum) {
                break;
            }
        }
        printTps(date);

        //TODO 20200810测试tps
        // 测试一(计算规则3判断规则6函数5操作符2宏定义1解析变量1)
        // startTime=====Mon Aug 10 23:30:09 CST 2020
        // endTime=====Mon Aug 10 23:30:21 CST 2020
        // 总耗时为：11s , 处理事件量为：i=10000  TPS:i/t=909
        // 测试二(计算规则3判断规则6函数5操作符2宏定义1解析变量1)
        // startTime=====Mon Aug 10 23:17:43 CST 2020
        // endTime=====Mon Aug 10 23:19:25 CST 2020
        // 总耗时为：102s , 处理事件量为：i=100000  TPS:i/t=980
        // 测试三(计算规则3判断规则6函数5操作符2宏定义1解析变量1)*100
        // startTime=====Tue Aug 11 00:45:02 CST 2020
        // endTime=====Tue Aug 11 00:59:56 CST 2020
        // 总耗时为：893s , 处理事件量为：i=10000  TPS:i/t=11
    }

    private static void startTest() throws Exception {
        System.out.println("==============================计算测试==============================");
        QLExpressCompDemo.test1(expressRunner, QLExpressParent.getOrCreateContext());
        System.out.println("==============================判断测试1==============================");
        QLExpressIfElseDemo.test1(expressRunner, QLExpressParent.getOrCreateContext());
        System.out.println("==============================判断测试2==============================");
        QLExpressIfElseDemo.test2(expressRunner, QLExpressParent.getOrCreateContext());
        System.out.println("==============================函数测试1==============================");
        QLExpressFunctionDemo.test1(expressRunner, QLExpressParent.getOrCreateContext());
        System.out.println("==============================操作符测试1==============================");
        QLExpressOperatorDemo.test1(expressRunner, QLExpressParent.getOrCreateContext());
        System.out.println("==============================操作符测试2==============================");
//        QLExpressOperatorDemo.test2(expressRunner, context);
        System.out.println("==============================操作符测试3==============================");
        try {
            QLExpressOperatorDemo.test3(expressRunner, QLExpressParent.getOrCreateContext());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("==============================宏定义测试1==============================");
        QLExpressMacroDemo.test1(expressRunner, QLExpressParent.getOrCreateContext());
        System.out.println("==============================解析变量和函数测试1==============================");
        QLExpressParserDemo.test1(expressRunner, QLExpressParent.getOrCreateContext());
        System.out.println("==============================集合的快捷测试1==============================");
        QLExpressCollectionDemo.test1(expressRunner, QLExpressParent.getOrCreateContext());
        System.out.println("==============================集合的遍历测试1==============================");
        QLExpressCollectionDemo.test2(expressRunner, QLExpressParent.getOrCreateContext());
        System.out.println();
    }

    private static void recursionRule(int recursionFlag) throws Exception {
        if (recursionNum == 0) {
            return;
        }
        recursionFlag++;
        startTest();
        if (recursionFlag == recursionNum) {
            recursionFlag = 0;
            return;
        } else {
            recursionRule(recursionFlag);
        }
    }

    private static void printTps(Date date) {
        System.out.println("==============================时间记录器，error是正常的==============================");
        long startTime = date.getTime();
        System.out.println("startTime=====" + date);
        date = new Date();
        System.out.println("endTime=====" + date);
        long endTime = date.getTime();
        long t = (endTime - startTime) / 1000;
        System.out.println("总耗时为：" + t + "s , 处理事件量为：i=" + breakNum + "  TPS:i/t=" + (breakNum / t));
    }

}
