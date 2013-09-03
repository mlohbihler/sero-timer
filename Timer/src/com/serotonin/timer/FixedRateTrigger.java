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
    protected long calculateNextExecutionTimeImpl() {
        return nextExecutionTime + period;
    }

    @Override
    protected long calculateNextExecutionTimeImpl(long after) {
        long d = after - nextExecutionTime;
        if (d < 0)
            return nextExecutionTime + period;

        long periods = d / period;
        return nextExecutionTime + period * (periods + 1);
    }

    @Override
    public long mostRecentExecutionTime() {
        return nextExecutionTime - period;
    }
}
