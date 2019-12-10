package com.mingzhang.table.functions;

/**
 * CASE value
 * WHEN value1_1 [, value1_2 ]* THEN result1
 * [ WHEN value2_1 [, value2_2 ]* THEN result2 ]*
 * [ ELSE resultZ ]
 * END
 * 当第一个时间值包含在（valueX_1，valueX_2，...）中时，返回resultX。如果没有值匹配，则返回resultZ（如果提供），否则返回NULL。
 * <p>
 * CASE
 * WHEN condition1 THEN result1
 * [ WHEN condition2 THEN result2 ]*
 * [ ELSE resultZ ]
 * END
 * 满足第一个conditionX时返回resultX。如果不满足任何条件，则返回resultZ（如果提供），否则返回NULL。
 * <p>
 * NULLIF(value1, value2)
 * 如果value1等于value2，则返回NULL；否则返回NULL 。否则返回value1。
 * <p>
 * 例如，NULLIF(5, 5)返回NULL；NULLIF(5, 0)返回5。
 * <p>
 * COALESCE(value1, value2 [, value3 ]* )
 * 从value1，value2，...返回第一个不为NULL的值。
 * <p>
 * 例如，COALESCE(NULL, 5)返回5。
 * <p>
 * IF(condition, true_value, false_value)
 * 如果满足条件，则返回true_value，否则返回false_value。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * 例如，IF(5 > 3, 5, 3)返回5。
 * <p>
 * IS_ALPHA(string)
 * 如果字符串中的所有字符均为字母，则返回true ，否则返回false。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * IS_DECIMAL(string)
 * 如果可以将字符串解析为有效数字，则返回true ，否则返回false。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * IS_DIGIT(string)
 * 如果字符串中的所有字符均为数字，则返回true ，否则返回false。
 * <p>
 * 仅在眨眼计划程序中受支持。
 */
public class ConditionalFunctionsDemo {
    public static void main(String[] args) {
        String conditionalSQL = "";


        FunctionDemoMain.runFunction(conditionalSQL);
    }
}
