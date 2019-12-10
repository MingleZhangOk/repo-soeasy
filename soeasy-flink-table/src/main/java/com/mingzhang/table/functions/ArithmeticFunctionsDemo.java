package com.mingzhang.table.functions;

/**
 * + numeric
 * 返回数字。
 *
 * - numeric
 * 返回负数值。
 *
 * numeric1 + numeric2
 * 返回numeric1加numeric2。
 *
 * numeric1 - numeric2
 * 返回numeric1减去numeric2。
 *
 * numeric1 * numeric2
 * 返回numeric1乘以numeric2。
 *
 * numeric1 / numeric2
 * 返回numeric1除以numeric2。
 *
 * POWER(numeric1, numeric2)
 * 返回提高到numeric2的幂的numeric1。
 *
 * ABS(numeric)
 * 返回numeric的绝对值。
 *
 * MOD(numeric1, numeric2)
 * 返回numeric1除以numerical2的余数（模数）。仅当numeric1为负数时，结果为负数。
 *
 * SQRT(numeric)
 * 返回numeric的平方根。
 *
 * LN(numeric)
 * 返回numeric的自然对数（以e为底）。
 *
 * LOG10(numeric)
 * 返回数字的以10为底的对数。
 *
 * LOG2(numeric)
 * 返回numeric的以2为底的对数。
 *
 * LOG(numeric2)
 * LOG(numeric1, numeric2)
 * 当使用一个参数调用时，返回numeric2的自然对数。当使用两个参数调用时，此函数将numeric2的对数返回到基本numeric1。
 *
 * 注意：当前，numeric2必须大于0，numeric1必须大于1。
 *
 * EXP(numeric)
 * 返回e升至numeric的幂。
 *
 * CEIL(numeric)
 * CEILING(numeric)
 * 将数字向上舍入，并返回大于或等于numeric的最小数字。
 *
 * FLOOR(numeric)
 * 将数字向下舍入，并返回小于或等于numeric的最大数字。
 *
 * SIN(numeric)
 * 返回numeric的正弦值。
 *
 * SINH(numeric)
 * 返回numeric的双曲正弦值。
 *
 * 返回类型为DOUBLE。
 *
 * COS(numeric)
 * 返回numeric的余弦值。
 *
 * TAN(numeric)
 * 返回numeric的切线。
 *
 * TANH(numeric)
 * 返回numeric的双曲正切值。
 *
 * 返回类型为DOUBLE。
 *
 * COT(numeric)
 * 返回数字的余切。
 *
 * ASIN(numeric)
 * 返回numeric的反正弦值。
 *
 * ACOS(numeric)
 * 返回numeric的反余弦值。
 *
 * ATAN(numeric)
 * 返回numeric的反正切。
 *
 * ATAN2(numeric1, numeric2)
 * 返回坐标的反正切（数字1，数字2）。
 *
 * COSH(numeric)
 * 返回NUMERIC的双曲余弦值。
 *
 * 返回值类型为DOUBLE。
 *
 * DEGREES(numeric)
 * 返回弧度数字的度数表示形式。
 *
 * RADIANS(numeric)
 * 返回度数值的弧度表示。
 *
 * SIGN(numeric)
 * 返回数字的符号。
 *
 * ROUND(numeric, integer)
 * 返回数字，四舍五入为整数的小数位数。
 *
 * PI
 * 返回一个比pi更接近其他值的值。
 *
 * E()
 * 返回一个比任何其他值都更接近e的值。
 *
 * RAND()
 * 返回介于0.0（含）和1.0（不含）之间的伪随机双精度值。
 *
 * RAND(integer)
 * 返回介于0.0（含）和1.0（不含）之间的带有初始种子整数的伪随机双精度值。如果两个RAND函数具有相同的初始种子，它们将返回相同的数字序列。
 *
 * RAND_INTEGER(integer)
 * 返回介于0（含）和整数（不含）之间的伪随机整数值。
 *
 * RAND_INTEGER(integer1, integer2)
 * 使用初始种子返回介于0（含）和指定值（不含）之间的伪随机整数值。如果两个RAND_INTEGER函数具有相同的初始种子和边界，它们将返回相同的数字序列。
 *
 * UUID()
 * 根据RFC 4122类型4（伪随机生成）UUID返回UUID（通用唯一标识符）字符串（例如，“ 3d3c68f7-f608-473f-b60c-b0c44ad4cc4e”）。使用加密强度高的伪随机数生成器生成UUID。
 *
 * BIN(integer)
 * 以二进制格式返回整数的字符串表示形式。如果integer为NULL，则返回NULL。
 *
 * 例如，BIN(4)返回“ 100”并BIN(12)返回“ 1100”。
 *
 * HEX(numeric)
 * HEX(string)
 *
 * 返回整数值或十六进制格式的字符串的字符串表示形式。如果参数为NULL，则返回NULL。
 *
 * 例如，数字20导致“ 14”，数字100导致“ 64”，字符串“ hello，world”导致“ 68656C6C6F2C776F726C64”。
 *
 * TRUNCATE(numeric1, integer2)
 * 返回一个数字的截断为整数2位小数。返回NULL如果numeric1或整数2是NULL.If 整数2为0，则结果没有小数点或小数部分。整数2可以是负的，以引起整数2左的值的小数点成为zero.This功能也可以通过仅在一个数字numeric1参数和没有设置整数2至使用。如果整数2没有被设置，功能截短仿佛整数2分别为0。
 *
 * 例如truncate(42.345, 2)至42.34。并truncate(42.345)达到42.0。
 *
 * PI()
 * 返回π（pi）的值。
 *
 * 仅在眨眼计划程序中受支持。
 */
public class ArithmeticFunctionsDemo {
    public static void main(String[] args) {
        String arithmeticSQL="";




        FunctionDemoMain.runFunction(arithmeticSQL);
    }
}
