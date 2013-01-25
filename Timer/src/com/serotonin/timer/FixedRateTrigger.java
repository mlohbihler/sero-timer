package com.serotonin.timer;

import java.util.Date;

public class FixedRateTrigger extends AbstractTimerTrigger {
    private final long period;

    public FixedRateTrigger(long delay, long period) {
        super(delay);
        this.period = period;
    }

    public FixedRateTrigger(Date start, long period) {
        super(start);
        this.period = period;
    }

    @Override
    protected long calculateNextExecutionTime() {
        return nextExecutionTime + period;
    }

    @Override
    public long mostRecentExecutionTime() {
        return nextExecutionTime - period;
    }
}
