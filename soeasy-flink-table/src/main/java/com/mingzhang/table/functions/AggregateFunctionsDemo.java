package com.mingzhang.table.functions;

import com.mingzhang.table.source.kafka.KafkaConnectDemo;

/**
 * COUNT([ ALL ] expression | DISTINCT expression1 [, expression2]*)
 * 默认情况下或使用ALL，返回表达式不为NULL 的输入行数。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * COUNT(*)
 * COUNT(1)
 * 返回输入行数。
 * <p>
 * AVG([ ALL | DISTINCT ] expression)
 * 默认情况下或使用关键字ALL，返回所有输入行中表达式的平均值（算术平均值）。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * SUM([ ALL | DISTINCT ] expression)
 * 默认情况下或使用关键字ALL，返回所有输入行的表达式总和。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * MAX([ ALL | DISTINCT ] expression)
 * 默认情况下或使用关键字ALL，返回所有输入行中表达式的最大值。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * MIN([ ALL | DISTINCT ] expression)
 * 默认情况下或使用关键字ALL，返回所有输入行中表达式的最小值。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * STDDEV_POP([ ALL | DISTINCT ] expression)
 * 默认情况下或使用关键字ALL，返回所有输入行中表达式的总体标准偏差。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * STDDEV_SAMP([ ALL | DISTINCT ] expression)
 * 默认情况下或使用关键字ALL，返回所有输入行中表达式的样本标准偏差。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * VAR_POP([ ALL | DISTINCT ] expression)
 * 默认情况下或使用关键字ALL，返回所有输入行中表达式的总体方差（总体标准差的平方）。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * VAR_SAMP([ ALL | DISTINCT ] expression)
 * 默认情况下或使用关键字ALL，返回所有输入行中表达式的样本方差（样本标准差的平方）。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * COLLECT([ ALL | DISTINCT ] expression)
 * 默认情况下或使用关键字ALL，在所有输入行中返回表达式的多集。NULL值将被忽略。对每个值的一个唯一实例使用DISTINCT。
 * <p>
 * VARIANCE([ ALL | DISTINCT ] expression)
 * VAR_SAMP（）的同义词。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * RANK()
 * 返回值在一组值中的排名。结果是分区顺序中当前行之前或等于当前行的行数加一。值将在序列中产生间隙。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * DENSE_RANK()
 * 返回值在一组值中的排名。结果是一个加上先前分配的等级值。与函数等级不同，density_rank不会在等级序列中产生间隙。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * ROW_NUMBER()
 * 根据窗口分区中各行的顺序，为每一行分配一个唯一的顺序号（从1开始）。
 * <p>
 * ROW_NUMBER和RANK是相似的。ROW_NUMBER按顺序对所有行编号（例如1、2、3、4、5）。RANK为领带提供相同的数值（例如1、2、2、4、5）。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * LEAD(expression [, offset] [, default] )
 * 返回的值表达在偏移的当前行中的窗口之后，第i行。的默认值的偏移是1和默认值默认为NULL。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * LAG(expression [, offset] [, default])
 * 返回的值表达在偏移的当前行中的窗口之后，第i行。的默认值的偏移是1和默认值默认为NULL。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * FIRST_VALUE(expression)
 * 返回有序值集中的第一个值。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * LAST_VALUE(expression)
 * 返回有序值集中的最后一个值。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * LISTAGG(expression [, separator])
 * 连接字符串表达式的值，并在它们之间放置分隔符值。分隔符未添加到字符串的末尾。分隔符的默认值为“，”。
 * <p>
 * 仅在眨眼计划程序中受支持。
 */
public class AggregateFunctionsDemo {
    public static void main(String[] args) {
        //{"id":"234567890","name":"tome","age":23.56,"stat":"big"}
        String aggregateSQL = "select id,name,count(1) from " + KafkaConnectDemo.tableName + " group by id,name";


        FunctionDemoMain.runFunction(aggregateSQL);
    }
}
