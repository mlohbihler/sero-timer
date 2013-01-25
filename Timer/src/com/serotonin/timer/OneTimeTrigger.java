package com.serotonin.timer;

import java.util.Date;

public class OneTimeTrigger extends AbstractTimerTrigger {
    public OneTimeTrigger(long delay) {
        super(delay);
    }

    public OneTimeTrigger(Date start) {
        super(start);
    }

    @Override
    protected long calculateNextExecutionTime() {
        return 0;
    }

    @Override
    final public long mostRecentExecutionTime() {
        return nextExecutionTime;
    }
}
