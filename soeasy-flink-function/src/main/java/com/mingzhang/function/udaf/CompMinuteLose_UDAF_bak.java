package com.mingzhang.function.udaf;

import com.mingzhang.function.udf.MyAll_UDATF;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.table.functions.AggregateFunction;

import java.sql.Timestamp;

public class CompMinuteLose_UDAF_bak extends AggregateFunction<Boolean, CompMinuteLose_UDAF_bak.MinuteAccum> implements MyAll_UDATF<CompMinuteLose_UDAF_bak> {


    //定义存放count UDAF状态的accumulator的数据的结构。
    public static class MinuteAccum {
        public boolean isMinuteLose;
        public long maxMinute;
        public String initStat;
        public Timestamp iniProcTime;
        public String nowStat;
        public Timestamp procTime;
    }

    @Override
    //getValue提供了，如何通过存放状态的accumulator，计算count UDAF的结果的方法。
    public Boolean getValue(MinuteAccum minuteAccum) {

        if (StringUtils.isEmpty(minuteAccum.initStat)) {
            return false;
        }
        if (StringUtils.isEmpty(minuteAccum.nowStat)) {
            System.out.println(System.currentTimeMillis());
            System.out.println(minuteAccum.iniProcTime.getTime());
            System.out.println(System.currentTimeMillis() - minuteAccum.iniProcTime.getTime());
//            System.out.println(minuteAccum.maxMinute * 60 * 1000 + 8 * 60 * 60 * 1000);
            if (System.currentTimeMillis() - minuteAccum.iniProcTime.getTime() > 25 * 1000 + 8 * 60 * 60 * 1000) {
                return true;
            } else {
                return false;
            }
        }
        if (minuteAccum.procTime.getTime() - minuteAccum.iniProcTime.getTime() < minuteAccum.maxMinute * 60 * 1000) {
            minuteAccum.isMinuteLose = false;
        } else {
            minuteAccum.isMinuteLose = true;
        }
        return minuteAccum.isMinuteLose;

    }

    //accumulate提供了，如何根据输入的数据，更新count UDAF存放状态的accumulator。
    public void accumulate(MinuteAccum minuteAccum, Timestamp procTime, String nowStat) {
        if (minuteAccum.initStat == null && "PR013".equalsIgnoreCase(nowStat)) {
            minuteAccum.initStat = nowStat;
            minuteAccum.iniProcTime = procTime;
        } else if (minuteAccum.initStat != null) {
            minuteAccum.nowStat = nowStat;
            minuteAccum.procTime = procTime;
        } else {
            minuteAccum.isMinuteLose = false;
        }
    }

    @Override
    //初始化count UDAF的accumulator。
    public MinuteAccum createAccumulator() {
        MinuteAccum minuteAccum = new MinuteAccum();
        minuteAccum.maxMinute = 5;
        return minuteAccum;
    }

}
