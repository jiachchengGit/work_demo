package com.jccdemo.jvm;

import java.io.Serializable;

/**
 * Created by chenjiacheng on 2017-07-04.
 */
public class JvmThreadInfoBean implements Serializable {

    private long threadCount;
    private long daemonThreadCount;
    private long peakThreadCount;
    private int deadlockedThreads;
    private int monitorDeadlockedThreads;

    @Override
    public String toString() {
        return "JvmThreadInfoBean{" +
                "threadCount=" + threadCount +
                ", daemonThreadCount=" + daemonThreadCount +
                ", peakThreadCount=" + peakThreadCount +
                ", deadlockedThreads=" + deadlockedThreads +
                ", monitorDeadlockedThreads=" + monitorDeadlockedThreads +
                '}';
    }

    public int getDeadlockedThreads() {
        return deadlockedThreads;
    }

    public void setDeadlockedThreads(int deadlockedThreads) {
        this.deadlockedThreads = deadlockedThreads;
    }

    public int getMonitorDeadlockedThreads() {
        return monitorDeadlockedThreads;
    }

    public void setMonitorDeadlockedThreads(int monitorDeadlockedThreads) {
        this.monitorDeadlockedThreads = monitorDeadlockedThreads;
    }

    public long getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(long threadCount) {
        this.threadCount = threadCount;
    }

    public long getDaemonThreadCount() {
        return daemonThreadCount;
    }

    public void setDaemonThreadCount(long daemonThreadCount) {
        this.daemonThreadCount = daemonThreadCount;
    }

    public long getPeakThreadCount() {
        return peakThreadCount;
    }

    public void setPeakThreadCount(long peakThreadCount) {
        this.peakThreadCount = peakThreadCount;
    }
}
