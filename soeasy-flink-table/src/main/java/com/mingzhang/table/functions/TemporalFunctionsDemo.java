package com.mingzhang.table.functions;

/**
 * DATE string
 * 返回以字符串 “ yyyy-MM-dd”的形式解析的SQL日期。
 * <p>
 * TIME string
 * 返回以字符串形式解析为“ HH：mm：ss” 的SQL时间。
 * <p>
 * TIMESTAMP string
 * 返回以字符串 “ yyyy-MM-dd HH：mm：ss [.SSS]”形式解析的SQL时间戳。
 * <p>
 * INTERVAL string range
 * 解析间隔字符串，格式为“ dd hh：mm：ss.fff”，表示毫秒的SQL间隔，或者表示“ yyyy-mm”，表示月份的SQL间隔。的间隔范围可以是DAY，MINUTE，DAY TO HOUR，或DAY TO SECOND的毫秒时间间隔; YEAR或YEAR TO MONTH间隔几个月。
 * <p>
 * 例如INTERVAL '10 00:00:00.004' DAY TO SECOND，INTERVAL '10' DAY或者INTERVAL '2-10' YEAR TO MONTH返回的时间间隔。
 * <p>
 * CURRENT_DATE
 * 返回UTC时区中的当前SQL日期。
 * <p>
 * CURRENT_TIME
 * 返回UTC时区中的当前SQL时间。
 * <p>
 * CURRENT_TIMESTAMP
 * 返回UTC时区中的当前SQL时间戳。
 * <p>
 * LOCALTIME
 * 返回本地时区的当前SQL时间。
 * <p>
 * LOCALTIMESTAMP
 * 返回本地时区的当前SQL时间戳。
 * <p>
 * EXTRACT(timeintervalunit FROM temporal)
 * 返回从temporal的timeintervalunit部分提取的长值。
 * <p>
 * 例如，EXTRACT(DAY FROM DATE '2006-06-05')返回5。
 * <p>
 * YEAR(date)
 * 从SQL date date返回年份。相当于EXTRACT（YEAR FROM date）。
 * <p>
 * 例如，YEAR(DATE '1994-09-27')返回1994。
 * <p>
 * QUARTER(date)
 * 从SQL date date返回一年的四分之一（介于1和4之间的整数）。等同于EXTRACT(QUARTER FROM date)。
 * <p>
 * 例如，QUARTER(DATE '1994-09-27')传回3。
 * <p>
 * MONTH(date)
 * 从SQL date date返回一年中的月份（1到12之间的整数）。等同于EXTRACT(MONTH FROM date)。
 * <p>
 * 例如，MONTH(DATE '1994-09-27')传回9。
 * <p>
 * WEEK(date)
 * 从SQL date date返回一年中的星期（1到53之间的整数）。等同于EXTRACT(WEEK FROM date)。
 * <p>
 * 例如，WEEK(DATE '1994-09-27')返回39。
 * <p>
 * DAYOFYEAR(date)
 * 从SQL date date返回一年中的一天（介于1和366之间的整数）。等同于EXTRACT(DOY FROM date)。
 * <p>
 * 例如，DAYOFYEAR(DATE '1994-09-27')传回270。
 * <p>
 * DAYOFMONTH(date)
 * 从SQL date date返回一个月中的某天（1到31之间的整数）。等同于EXTRACT(DAY FROM date)。
 * <p>
 * 例如，DAYOFMONTH(DATE '1994-09-27')返回27。
 * <p>
 * DAYOFWEEK(date)
 * 从SQL日期date返回星期几（1到7之间的整数； 1 = Sunday； 1）EXTRACT(DOW FROM date)。等效于。
 * <p>
 * 例如，DAYOFWEEK(DATE '1994-09-27')传回3。
 * <p>
 * HOUR(timestamp)
 * 从SQL timestamp timestamp返回一天中的小时（0到23之间的整数）。等同于EXTRACT(HOUR FROM timestamp)。
 * <p>
 * 例如，HOUR(TIMESTAMP '1994-09-27 13:14:15')传回13。
 * <p>
 * MINUTE(timestamp)
 * 返回距SQL timestamp timestamp一小时的分钟数（0到59之间的整数）。等同于EXTRACT(MINUTE FROM timestamp)。
 * <p>
 * 例如，MINUTE(TIMESTAMP '1994-09-27 13:14:15')传回14。
 * <p>
 * SECOND(timestamp)
 * 返回距SQL时间戳一分钟的秒数（0到59之间的整数）。等同于EXTRACT(SECOND FROM timestamp)。
 * <p>
 * 例如，SECOND(TIMESTAMP '1994-09-27 13:14:15')传回15。
 * <p>
 * FLOOR(timepoint TO timeintervalunit)
 * 返回一个将时间点向下舍入为时间单位timeintervalunit的值。
 * <p>
 * 例如，FLOOR(TIME '12:44:31' TO MINUTE)返回12:44:00。
 * <p>
 * CEIL(timepoint TO timeintervalunit)
 * 返回一个将时间点四舍五入到时间单位timeintervalunit的值。
 * <p>
 * 例如，CEIL(TIME '12:44:31' TO MINUTE)返回12:45:00。
 * <p>
 * (timepoint1, temporal1) OVERLAPS (timepoint2, temporal2)
 * 如果由（timepoint1，temporal1）和（timepoint2，temporal2）定义的两个时间间隔重叠，则返回TRUE 。时间值可以是时间点，也可以是时间间隔。
 * <p>
 * 例如，(TIME '2:55:00', INTERVAL '1' HOUR) OVERLAPS (TIME '3:30:00', INTERVAL '2' HOUR)返回TRUE；(TIME '9:00:00', TIME '10:00:00') OVERLAPS (TIME '10:15:00', INTERVAL '3' HOUR)返回FALSE。
 * <p>
 * DATE_FORMAT(timestamp, string)
 * 旧计划者注意此功能存在严重的错误，暂时不应使用。请改为实施自定义UDF或使用EXTRACT作为解决方法。
 * <p>
 * 对于眨眼计划器，这会将时间戳转换为日期格式string指定的格式的字符串值。格式字符串与Java的SimpleDateFormat兼容。
 * <p>
 * TIMESTAMPADD(timeintervalunit, interval, timepoint)
 * 返回一个新的时间值，该值向timepoint添加一个（有符号的）整数间隔。为单位的时间间隔是由单元参数，它应为以下值中的一个给定的：SECOND，MINUTE，HOUR，DAY，WEEK，MONTH，QUARTER，或YEAR。
 * <p>
 * 例如，TIMESTAMPADD(WEEK, 1, DATE '2003-01-02')返回2003-01-09。
 * <p>
 * TIMESTAMPDIFF(timepointunit, timepoint1, timepoint2)
 * 返回的（签约）号timepointunit之间timepoint1和timepoint2。某一间隔的单元由第一参数，它应为以下值中的一个给定的：SECOND，MINUTE，HOUR，DAY，MONTH，或YEAR。另请参见时间间隔和点单位说明符表。
 * <p>
 * 例如，TIMESTAMPDIFF(DAY, TIMESTAMP '2003-01-02 10:00:00', TIMESTAMP '2003-01-03 10:00:00')导致1。
 * <p>
 * CONVERT_TZ(string1, string2, string3)
 * 将日期时间字符串1（具有默认的ISO时间戳格式'yyyy-MM-dd HH：mm：ss'）从时区string2转换为时区string3。时区的格式应为缩写，如“ PST”，全名如“ America / Los_Angeles”，或自定义ID如“ GMT-8：00”。
 * <p>
 * 例如，CONVERT('1970-01-01 00:00:00', 'UTC', 'America/Los_Angeles')返回“ 1969-12-31 16:00:00”。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * FROM_UNIXTIME(numeric[, string])
 * 以字符串格式返回数值参数的表示形式（默认值为'YYYY-MM-DD hh：mm：ss'）。数字是一个内部时间戳记值，代表自1970年1月1日00:00:00 UTC以来的秒数，例如UNIX_TIMESTAMP（）函数产生的时间。返回值以会话时区表示（在TableConfig中指定）。
 * <p>
 * 例如，FROM_UNIXTIME(44)如果在UTC时区，则返回“ 1970-01-01 09:00:44”，但在“亚洲/东京”时区，则返回“ 1970-01-01 09:00:44”。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * UNIX_TIMESTAMP()
 * 以秒为单位获取当前的Unix时间戳。此功能不确定。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * UNIX_TIMESTAMP(string1[, string2])
 * 使用表config中指定的时区，将格式为string2的日期时间字符串string1（默认情况下：yyyy-MM-dd HH：mm：ss）转换为Unix时间戳（以秒为单位）。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * TO_DATE(string1[, string2])
 * 将格式为string2的日期字符串string1（默认为'yyyy-MM-dd'）转换为日期。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * TO_TIMESTAMP(string1[, string2])
 * 将会话时区（由TableConfig指定）下的格式为string2的日期时间字符串string1（默认：'yyyy-MM-dd HH：mm：ss'）转换为时间戳。
 * <p>
 * 仅在眨眼计划程序中受支持。
 * <p>
 * NOW()
 * 返回UTC时区中的当前SQL时间戳。此功能不确定。
 * <p>
 * 仅在眨眼计划程序中受支持。
 */
public class TemporalFunctionsDemo {

    public static void main(String[] args) {
        String temporalSQL = "";


        FunctionDemoMain.runFunction(temporalSQL);
    }
}
