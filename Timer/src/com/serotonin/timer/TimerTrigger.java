package com.serotonin.timer;

import java.util.Date;

abstract public class TimerTrigger {
    protected AbstractTimer timer;

    void setTimer(AbstractTimer timer) {
        this.timer = timer;
    }

    AbstractTimer getTimer() {
        return timer;
    }

    /**
     * Next execution time for this task in the format returned by System.currentTimeMillis, assuming this task is
     * scheduled for execution. For repeating tasks, this field is updated prior to each task execution.
     */
    long nextExecutionTime;

    /**
     * Returns the <i>scheduled</i> execution time of the most recent <i>actual</i> execution of this task. (If this
     * method is invoked while task execution is in progress, the return value is the scheduled execution time of the
     * ongoing task execution.)
     * 
     * <p>
     * This method is typically invoked from within a task's run method, to determine whether the current execution of
     * the task is sufficiently timely to warrant performing the scheduled activity:
     * 
     * <pre>
     * public void run() {
     *     if (System.currentTimeMillis() - scheduledExecutionTime() &gt;= MAX_TARDINESS)
     *         return; // Too late; skip this execution.
     *     // Perform the task
     * }
     * </pre>
     * 
     * This method is typically <i>not</i> used in conjunction with <i>fixed-delay execution</i> repeating tasks, as
     * their scheduled execution times are allowed to drift over time, and so are not terribly significant.
     * 
     * @return the time at which the most recent execution of this task was scheduled to occur, in the format returned
     *         by Date.getTime(). The return value is undefined if the task has yet to commence its first execution.
     * @see Date#getTime()
     */
    abstract public long mostRecentExecutionTime();

    abstract protected long getFirstExecutionTime();

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    /**
     * Return the time of the next execution, or -1 if there isn't one.
     * 
     * @return
     */
    abstract protected long calculateNextExecutionTime();
}
