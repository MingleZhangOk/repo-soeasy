package com.mingzhang.table.functions;

/**
 * value1 = value2
 * 如果value1等于value2，则返回TRUE ；如果value1或value2为NULL，则返回UNKNOWN 。
 * value1 <> value2
 * 如果value1不等于value2，则返回TRUE ；如果value1或value2为NULL，则返回UNKNOWN 。
 *
 * value1 > value2
 * 如果value1大于value2，则返回TRUE ；如果value1或value2为NULL，则返回UNKNOWN 。
 *
 * value1 >= value2
 * 如果value1大于或等于value2，则返回TRUE ；如果value1或value2为NULL，则返回UNKNOWN 。
 *
 * value1 < value2
 * 如果value1小于value2，则返回TRUE ；如果value1或value2为NULL，则返回UNKNOWN 。
 *
 * value1 <= value2
 * 如果value1小于或等于value2，则返回TRUE ；如果value1或value2为NULL，则返回UNKNOWN 。
 *
 * value IS NULL
 * 如果值为 NULL，则返回TRUE 。
 *
 * value IS NOT NULL
 * 如果value不为NULL，则返回TRUE 。
 *
 * value1 IS DISTINCT FROM value2
 * 如果两个值不相等，则返回TRUE。NULL值在此处视为相同。
 *
 * 例如，1 IS DISTINCT FROM NULL返回TRUE； NULL IS DISTINCT FROM NULL返回FALSE。
 *
 * value1 IS NOT DISTINCT FROM value2
 * 如果两个值相等，则返回TRUE。NULL值在此处视为相同。
 *
 * 例如，1 IS NOT DISTINCT FROM NULL返回FALSE； NULL IS NOT DISTINCT FROM NULL返回TRUE。
 *
 * value1 BETWEEN [ ASYMMETRIC | SYMMETRIC ] value2 AND value3
 * 默认情况下（或使用ASYMMETRIC关键字），如果value1大于或等于value2且小于或等于value3，则返回TRUE 。使用SYMMETRIC关键字，如果value1包含在value2和value3之间，则返回TRUE 。当value2或value3为NULL时，返回FALSE或UNKNOWN。
 *
 * 例如，12 BETWEEN 15 AND 12返回FALSE； 12 BETWEEN SYMMETRIC 15 AND 12返回TRUE; 12 BETWEEN 10 AND NULL返回UNKNOWN； 12 BETWEEN NULL AND 10返回FALSE； 12 BETWEEN SYMMETRIC NULL AND 12返回UNKNOWN。
 *
 * value1 NOT BETWEEN [ ASYMMETRIC | SYMMETRIC ] value2 AND value3
 * 默认情况下（或使用ASYMMETRIC关键字），如果value1小于value2或大于value3，则返回TRUE 。使用SYMMETRIC关键字，如果value1不包括在value2和value3之间，则返回TRUE 。当value2或value3为NULL时，返回TRUE或UNKNOWN。
 *
 * 例如，12 NOT BETWEEN 15 AND 12返回TRUE； 12 NOT BETWEEN SYMMETRIC 15 AND 12返回FALSE； 12 NOT BETWEEN NULL AND 15返回UNKNOWN； 12 NOT BETWEEN 15 AND NULL返回TRUE; 12 NOT BETWEEN SYMMETRIC 12 AND NULL返回UNKNOWN。
 *
 * string1 LIKE string2 [ ESCAPE char ]
 * 如果string1与模式string2匹配，则返回TRUE ；如果string1或string2为NULL，则返回UNKNOWN 。如有必要，可以定义一个转义字符。
 *
 * 注意：尚不支持转义字符。
 *
 * string1 NOT LIKE string2 [ ESCAPE char ]
 * 如果string1与模式string2不匹配，则返回TRUE ；如果string1或string2为NULL，则返回UNKNOWN 。如有必要，可以定义一个转义字符。
 *
 * 注意：尚不支持转义字符。
 *
 * string1 SIMILAR TO string2 [ ESCAPE char ]
 * 如果string1与SQL正则表达式string2匹配，则返回TRUE ；如果string1或string2为NULL，则返回UNKNOWN 。如有必要，可以定义一个转义字符。
 *
 * 注意：尚不支持转义字符。
 *
 * string1 NOT SIMILAR TO string2 [ ESCAPE char ]
 * 如果string1与SQL正则表达式string2不匹配，则返回TRUE ；如果string1或string2为NULL，则返回UNKNOWN 。如有必要，可以定义一个转义字符。
 *
 * 注意：尚不支持转义字符。
 *
 * value1 IN (value2 [, value3]* )
 * 如果value1存在于给定列表中（value2，value3，...），则返回TRUE 。当（value2，value3，...）。包含NULL，如果可以找到该元素，则返回TRUE，否则返回UNKNOWN。如果value1为NULL，则始终返回UNKNOWN 。
 *
 * 例如，4 IN (1, 2, 3)返回FALSE； 1 IN (1, 2, NULL)返回TRUE; 4 IN (1, 2, NULL)返回UNKNOWN。
 *
 * value1 NOT IN (value2 [, value3]* )
 * 如果value1在给定列表（value2，value3，...）中不存在，则返回TRUE 。当（value2，value3，...）。包含NULL，如果可以找到value1，则返回FALSE ，否则返回UNKNOWN。如果value1为NULL，则始终返回UNKNOWN 。
 *
 * 例如，4 NOT IN (1, 2, 3)返回TRUE； 1 NOT IN (1, 2, NULL)返回FALSE； 4 NOT IN (1, 2, NULL)返回UNKNOWN。
 *
 * EXISTS (sub-query)
 * 如果子查询返回至少一行，则返回TRUE 。仅当可以在联接和组操作中重写操作时才支持。
 *
 * 注意：对于流查询，该操作将在联接和组操作中重写。根据不同输入行的数量，计算查询结果所需的状态可能会无限增长。请提供具有有效保留间隔的查询配置，以防止出现过多的状态。有关详细信息，请参见查询配置。
 *
 * value IN (sub-query)
 * 如果value等于子查询返回的行，则返回TRUE 。
 *
 * 注意：对于流查询，该操作将在联接和组操作中重写。根据不同输入行的数量，计算查询结果所需的状态可能会无限增长。请提供具有有效保留间隔的查询配置，以防止出现过多的状态。有关详细信息，请参见查询配置。
 *
 * value NOT IN (sub-query)
 * 如果value不等于sub-query返回的每一行，则返回TRUE 。
 *
 * 注意：对于流查询，该操作将在联接和组操作中重写。根据不同输入行的数量，计算查询结果所需的状态可能会无限增长。请提供具有有效保留间隔的查询配置，以防止出现过多的状态。有关详细信息，请参见查询配置。
 */
public class ComparisonFunctionsDemo {
    public static void main(String[] args) {
        String comparisonSQL = "";

        FunctionDemoMain.runFunction(comparisonSQL);
    }
}
