package com.mingzhang.function.udaf;

import com.mingzhang.function.udf.MyAll_UDATF;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.table.functions.AggregateFunction;

public class CompMinuteLose_UDAF extends AggregateFunction<Boolean, CompMinuteLose_UDAF.MinuteAccum> implements MyAll_UDATF<CompMinuteLose_UDAF> {


//    //定义存放count UDAF状态的accumulator的数据的结构。
//    public static class MinuteAccum {
//        public boolean isMinuteLose;
//        public String initStat;
//        public String nowStat;
//        public Timestamp topStartTime;
//        public Timestamp eventStartTime;
//    }

    public static class MinuteAccum {
        public Boolean isLose = true;
        public String initStat;
        public String updateStat;
        public Long count = 0l;
    }

//    @Override
//    //getValue提供了，如何通过存放状态的accumulator，计算count UDAF的结果的方法。
//    public Boolean getValue(MinuteAccum minuteAccum) {
//
//
//        if (minuteAccum.eventStartTime.getTime() - minuteAccum.topStartTime.getTime() < 60000) {
//            minuteAccum.isMinuteLose = false;
//        } else if (minuteAccum.nowStat.equalsIgnoreCase(minuteAccum.initStat)) {
//            minuteAccum.isMinuteLose = true;
//        } else {
//            minuteAccum.isMinuteLose = false;
//        }
//        return minuteAccum.isMinuteLose;
//
//    }

    @Override
    public Boolean getValue(MinuteAccum minuteAccum) {

        if (StringUtils.isEmpty(minuteAccum.updateStat)) {
            minuteAccum.isLose = true;
        } else if (minuteAccum.count < 2) {
            minuteAccum.isLose = true;
        } else {
            minuteAccum.isLose = false;
        }
        return minuteAccum.isLose;
    }

//    //accumulate提供了，如何根据输入的数据，更新count UDAF存放状态的accumulator。
//    public void accumulate(MinuteAccum minuteAccum, Timestamp eventTime, Timestamp topStartTime, String nowStat, String initStat) {
//        minuteAccum.topStartTime = minuteAccum.topStartTime == null ? topStartTime : minuteAccum.topStartTime;
//        minuteAccum.eventStartTime = minuteAccum.eventStartTime == null ? eventTime : minuteAccum.eventStartTime;
//        minuteAccum.nowStat = nowStat;
//        minuteAccum.initStat = initStat;
//    }

    public void accumulate(MinuteAccum minuteAccum, String initStat, String nowStat, Long statCount) {
        if (StringUtils.isEmpty(minuteAccum.initStat)) {
            minuteAccum.initStat = initStat;
        }
        if (!minuteAccum.initStat.equalsIgnoreCase(nowStat)) {
            minuteAccum.updateStat = nowStat;
            minuteAccum.count += statCount;
        }
    }

//    @Override
//    //初始化count UDAF的accumulator。
//    public MinuteAccum createAccumulator() {
//        return new MinuteAccum();
//    }

    @Override
    public MinuteAccum createAccumulator() {
        MinuteAccum minuteAccum = new MinuteAccum();
        minuteAccum.initStat = "PR13";
        return minuteAccum;
    }

}
