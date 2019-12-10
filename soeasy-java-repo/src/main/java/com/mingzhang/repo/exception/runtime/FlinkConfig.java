package com.mingzhang.repo.exception.runtime;

import java.io.Serializable;

public class FlinkConfig  implements Serializable {

    private static final long serialVersionUID = 4425814317869285145L;

    private String replayFlag;

    private long checkPoitTime;

    private int replayTimes;

    private long delayIntervalSeconds;

    public String getReplayFlag() {
        return replayFlag;
    }

    public void setReplayFlag(String replayFlag) {
        this.replayFlag = replayFlag;
    }

    public long getCheckPoitTime() {
        return checkPoitTime;
    }

    public void setCheckPoitTime(long checkPoitTime) {
        this.checkPoitTime = checkPoitTime;
    }

    public int getReplayTimes() {
        return replayTimes;
    }

    public void setReplayTimes(int replayTimes) {
        this.replayTimes = replayTimes;
    }

    public long getDelayIntervalSeconds() {
        return delayIntervalSeconds;
    }

    public void setDelayIntervalSeconds(long delayIntervalSeconds) {
        this.delayIntervalSeconds = delayIntervalSeconds;
    }
}
