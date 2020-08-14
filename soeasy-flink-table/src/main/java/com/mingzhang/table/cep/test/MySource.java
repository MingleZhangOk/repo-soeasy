package com.mingzhang.table.cep.test;

import com.alibaba.fastjson.JSON;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-13 18:57
 */
public class MySource extends RichSourceFunction<String> {

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
//        while (true) {
        Map<String, String> map = new HashMap<>();
        map.put("userid", "1");
        map.put("orderid", "2222");
        map.put("behave", "order");
        ctx.collect(JSON.toJSONString(map));

        Thread.sleep(1000);

        map.put("userid", "1");
        map.put("orderid", "3333");
        map.put("behave", "pay");
        ctx.collect(JSON.toJSONString(map));
        Thread.sleep(1000);

        map.put("userid", "1");
        map.put("orderid", "4444");
        map.put("behave", "pay");
        ctx.collect(JSON.toJSONString(map));
        Thread.sleep(1000);

        map.put("userid", "1");
        map.put("orderid", "5555");
        map.put("behave", "order");
        ctx.collect(JSON.toJSONString(map));
        Thread.sleep(4000);

        map.put("userid", "1");
        map.put("orderid", "6666");
        map.put("behave", "order");
        ctx.collect(JSON.toJSONString(map));
        Thread.sleep(4000);
//        }
    }

    @Override
    public void cancel() {

    }
}
