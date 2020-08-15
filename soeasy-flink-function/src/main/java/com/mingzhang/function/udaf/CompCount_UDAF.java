package com.mingzhang.function.udaf;

import com.mingzhang.function.udf.MyAll_UDATF;
import org.apache.flink.table.functions.AggregateFunction;

public class CompCount_UDAF extends AggregateFunction<Long, CompCount_UDAF.CountAccum> implements MyAll_UDATF<CompCount_UDAF> {
    //定义存放count UDAF状态的accumulator的数据的结构。
    public static class CountAccum {
        public long total;
    }

    //初始化count UDAF的accumulator。
    @Override
    public CountAccum createAccumulator() {
        CountAccum acc = new CountAccum();
        acc.total = 0;
        return acc;
    }

    //getValue提供了，如何通过存放状态的accumulator，计算count UDAF的结果的方法。
    @Override
    public Long getValue(CountAccum accumulator) {
        return accumulator.total;
    }

    //accumulate提供了，如何根据输入的数据，更新count UDAF存放状态的accumulator。
    public void accumulate(CountAccum accumulator, Object iValue) {
        accumulator.total++;
    }

    public void merge(CountAccum accumulator, Iterable<CountAccum> its) {
        for (CountAccum other : its) {
            accumulator.total += other.total;
        }
    }

}
