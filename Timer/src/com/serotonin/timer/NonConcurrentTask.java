package com.serotonin.timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Extends the timer task to prevent tasks from running concurrently. This is useful when a job is run regularly on a
 * schedule, and there may be cases where a particular run could go on so long that is not finished before the next is
 * scheduled to begin. In such cases, the next job will be aborted, and a warning message will be written to the log.
 * 
 * @author Matthew Lohbihler
 */
abstract public class NonConcurrentTask extends TimerTask {
    private static final Log LOG = LogFactory.getLog(NonConcurrentTask.class);

    private Thread thread;

    public NonConcurrentTask(TimerTrigger trigger) {
        super(trigger);
    }

    public NonConcurrentTask(TimerTrigger trigger, String name) {
        super(trigger, name);
    }

    @Override
    final public void run(long runtime) {
        Thread localThread = thread;
        if (localThread != null) {
            LOG.warn("NonConcurrentTask run aborted because another run has not yet completed: " + getClass().getName());
            return;
        }

        try {
            thread = Thread.currentThread();
            runNonConcurrent(runtime);
        }
        finally {
            thread = null;
        }
    }

    abstract public void runNonConcurrent(long runtime);
}
