/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LineSplitter
 * Author:   h
 * Date:     2018/11/28 9:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.flink_sql;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.util.Collector;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author h
 * @create 2018/11/28
 * @since 1.0.0
 */
public class LineSplitter implements FlatMapFunction<String, Tuple3<String, String, String>> {

    @Override
    public void flatMap(String value, Collector<Tuple3<String, String, String>> out) throws Exception {
        Tuple3<String, String, String> tuple = new Tuple3<String, String, String>();
        String[] split = value.split(",");
        for (int i = 1; i < split.length; i++) {
            tuple.setField(split[i], i);
        }
        out.collect(tuple);
    }
}