package com.mingzhang.table.functions;

import com.mingzhang.table.source.kafka.KafkaConnectDemo;

/**
 * 条件函数	描述
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
 */
public class CaseWhenFunctionDemo {
    public static void main(String[] args) {

        //{"id":"234567890","name":"tome","age":23.56,"stat":"big"}  0
        //{"id":"234567890","name":"tome","age":20,"stat":"big"} 2

        String caseWhenSQL1 = "select id,name,LOCALTIME,(CASE  WHEN 20<=age and age<30 THEN 2 WHEN 30<=age and age<40 THEN 3 WHEN 40<=age and age<50 THEN 4 ELSE 0 END) as test  from " + KafkaConnectDemo.tableName;

        String caseWhenSQL2 = "select id,name,LOCALTIME,(CASE age  WHEN 20,30 THEN 2 WHEN 31,32,40 THEN 3 WHEN 40,45 THEN 4 ELSE 0 END) as test  from " + KafkaConnectDemo.tableName;

//        String caseWhenSQL = "select id,name,IF( age > 20 , 2 , 3 ) as test  from " + KafkaConnectDemo.tableName + " ";


        FunctionDemoMain.runFunction(caseWhenSQL1);
    }
}
