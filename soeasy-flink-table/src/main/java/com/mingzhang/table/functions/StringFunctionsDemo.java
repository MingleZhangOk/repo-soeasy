package com.mingzhang.table.functions;

/**
 *string1 || string2
 * 返回string1和string2的串联。
 *
 * CHAR_LENGTH(string)
 * CHARACTER_LENGTH(string)
 * 返回string中的字符数。
 *
 * UPPER(string)
 * 返回大写的字符串。
 *
 * LOWER(string)
 * 返回小写的字符串。
 *
 * POSITION(string1 IN string2)
 * 返回的第一次出现的位置（从1开始）字符串1在字符串2 ; 如果在string2中找不到string1，则返回0 。
 *
 * TRIM([ BOTH | LEADING | TRAILING ] string1 FROM string2)
 * 返回一个字符串，该字符串从string2中删除开头和/或结尾字符string1。默认情况下，两侧的空格都将被删除。
 *
 * LTRIM(string)
 * 返回一个字符串，该字符串从string中删除左空格。
 *
 * 例如，LTRIM(' This is a test String.')返回“这是一个测试字符串”。
 *
 * RTRIM(string)
 * 返回一个字符串，该字符串从string中删除正确的空格。
 *
 * 例如，RTRIM('This is a test String. ')返回“这是一个测试字符串”。
 *
 * REPEAT(string, integer)
 * 返回一个字符串，该字符串重复基本字符串 整数倍。
 *
 * 例如，REPEAT('This is a test String.', 2)返回“这是一个测试字符串。这是一个测试字符串。”。
 *
 * REGEXP_REPLACE(string1, string2, string3)
 * 从string1返回一个字符串，其中所有与正则表达式string2匹配的子字符串都将连续替换为string3。
 *
 * 例如，REGEXP_REPLACE('foobar', 'oo|ar', '')返回“ fb”。
 *
 * OVERLAY(string1 PLACING string2 FROM integer1 [ FOR integer2 ])
 * 返回一个字符串，该字符串从位置integer1处将string1的integer2（默认为string2的长度）字符替换为string2。
 *
 * 例如，OVERLAY('This is an old string' PLACING ' new' FROM 10 FOR 5)返回“这是一个新字符串”
 *
 * SUBSTRING(string FROM integer1 [ FOR integer2 ])
 * 返回字符串的子字符串，该字符串的子字符串从位置integer1开始，长度为integer2（默认为结尾）。
 *
 * REPLACE(string1, string2, string3)
 * 返回一个新字符串替换其中出现的所有字符串2与STRING3（非重叠）从字符串1
 *
 * 例如，REPLACE('hello world', 'world', 'flink')返回“ hello flink”；REPLACE('ababab', 'abab', 'z')返回“ zab”。
 *
 * REGEXP_EXTRACT(string1, string2[, integer])
 * 从string1返回一个字符串，该字符串使用指定的正则表达式string2和一个正则表达式匹配组索引integer提取。
 *
 * 注意： regex匹配组索引从1和0开始，表示匹配整个regex。另外，正则表达式匹配组索引不应超过定义的组数。
 *
 * 例如，REGEXP_EXTRACT('foothebar', 'foo(.*?)(bar)', 2)"返回“ bar”。
 *
 * INITCAP(string)
 * 返回一种新的字符串形式，每个单词的第一个字符转换为大写，其余字符转换为小写。这里的单词表示字母数字字符序列。
 *
 * CONCAT(string1, string2,...)
 * 返回连接string1，string2，...的字符串。如果任何参数为NULL，则返回NULL。
 *
 * 例如，CONCAT('AA', 'BB', 'CC')返回“ AABBCC”。
 *
 * CONCAT_WS(string1, string2, string3,...)
 * 返回一个字符串，该字符串将string2，string3，...与分隔符string1连接起来。分隔符被添加到要连接的字符串之间。如果string1为NULL，则返回NULL。与相比CONCAT()，会CONCAT_WS()自动跳过NULL参数。
 *
 * 例如，CONCAT_WS('~', 'AA', NULL, 'BB', '', 'CC')返回“ AA〜BB ~~ CC”。
 *
 * LPAD(string1, integer, string2)
 * 从用string2左填充的string1中返回一个新字符串，该字符串的长度为整数。如果string1的长度短于integer，则返回缩短为整数字符的string1。
 *
 * 例如，LPAD('hi',4,'??')返回“ ?? hi”；LPAD('hi',1,'??')返回“ h”。
 *
 * RPAD(string1, integer, string2)
 * 从用string2右填充的string1返回一个新字符串，该字符串的长度为整数字符。如果string1的长度短于integer，则返回缩短为整数字符的string1。
 *
 * 例如，RPAD('hi',4,'??')返回“ hi ??”，RPAD('hi',1,'??')返回“ h”。
 *
 * FROM_BASE64(string)
 * 返回string的base64解码结果；如果string为NULL，则返回NULL。
 *
 * 例如，FROM_BASE64('aGVsbG8gd29ybGQ=')返回“ hello world”。
 *
 * TO_BASE64(string)
 * 从string返回base64编码的结果；如果string为NULL，则返回NULL。
 *
 * 例如，TO_BASE64('hello world')返回“ aGVsbG8gd29ybGQ =“。
 *
 * ASCII(string)
 * 返回string的第一个字符的数值。如果string为NULL，则返回NULL。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * 例如，ascii('abc')返回97，然后ascii(CAST(NULL AS VARCHAR))返回NULL。
 *
 * CHR(integer)
 * 返回与二进制整数等效的ASCII字符。如果整数大于255，我们将首先获得整数模数除以255，然后返回模数的CHR。如果integer为NULL，则返回NULL。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * 例如，chr(97)返回a，chr(353)返回a，然后ascii(CAST(NULL AS VARCHAR))返回NULL。
 *
 * DECODE(binary, string)
 * 使用提供的字符集（“ US-ASCII”，“ ISO-8859-1”，“ UTF-8”，“ UTF-16BE”，“ UTF-16LE”，“ UTF- 16'）。如果任一参数为null，则结果也将为null。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * ENCODE(string1, string2)
 * 编码字符串1使用提供成二进制字符串2字符集（的'US-ASCII'一个， 'ISO-8859-1'， 'UTF-8'， 'UTF-16BE'， 'UTF-16LE'，“UTF- 16'）。如果任一参数为null，则结果也将为null。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * INSTR(string1, string2)
 * 返回第一次出现的位置字符串2中字符串1。如果任何参数为NULL，则返回NULL。</ p>
 * 仅在眨眼计划程序中受支持。
 *
 * LEFT(string, integer)
 * 返回字符串中最左边的整数字符。如果整数为负，则返回EMPTY String 。如果任何参数为NULL，则返回NULL。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * RIGHT(string, integer)
 * 返回字符串中最右边的整数字符。如果整数为负，则返回EMPTY String 。如果任何参数为NULL，则返回NULL。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * LOCATE(string1, string2[, integer])
 * 返回第一次出现的位置字符串1在字符串2位置后的整数。如果找不到，则返回0。如果任何参数为NULL，则返回NULL。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * PARSE_URL(string1, string2[, string3])
 * 从URL返回指定的部分。string2的有效值包括“ HOST”，“ PATH”，“ QUERY”，“ REF”，“ PROTOCOL”，“ AUTHORITY”，“ FILE”和“ USERINFO”。如果任何参数为NULL，则返回NULL。
 *
 * 例如，parse_url('http://facebook.com/path1/p.php?k1=v1&k2=v2#Ref1', 'HOST')返回“ facebook.com”。
 *
 * 通过提供键作为第三自变量string3，还可以提取QUERY中特定键的值。
 *
 * 例如，parse_url('http://facebook.com/path1/p.php?k1=v1&k2=v2#Ref1', 'QUERY', 'k1')返回“ v1”。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * REGEXP(string1, string2)
 * 如果string1的任何（可能为空）子字符串与Java正则表达式string2匹配，则返回TRUE ，否则返回FALSE。如果任何参数为NULL，则返回NULL。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * REVERSE(string)
 * 返回反转的字符串。如果string为NULL，则返回NULL。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * SPLIT_INDEX(string1, string2, integer1)
 * 用定界符string2分割string1，返回分割字符串的整数 th（从零开始）。如果integer为负，则返回NULL 。如果任何参数为NULL，则返回NULL。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * STR_TO_MAP(string1[, string2, string3]])
 * 使用定界符将string1分成键/值对后，返回一个映射。string2是线对分隔符，默认为“，”。而且string3是键值分隔符，默认为'='。
 *
 * 仅在眨眼计划程序中受支持。
 *
 * SUBSTR(string[, integer1[, integer2]])
 * 返回字符串的子字符串，该字符串的子字符串从位置integer1开始，长度为integer2（默认为结尾）。
 *
 * 仅在眨眼计划程序中受支持。
 */
public class StringFunctionsDemo {
    public static void main(String[] args) {

        String stringSQL = "";


        FunctionDemoMain.runFunction(stringSQL);

    }
}
