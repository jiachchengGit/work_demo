package com.jccdemo.jvm;

import java.io.Serializable;

/**
 * Created by chenjiacheng on 2017-07-04.
 */
public class PhysicalMemoryInfoBean implements Serializable {
    private long freePhysicalMemorySize; //kb
    private long totalPhysicalMemorySize; //kb
    private long usedPhysicalMemorySize; //kb

    private long freeSwapSpaceSize; //kb
    private long totalSwapSpaceSize; //kb
    private long usedSwapSpaceSize; //kb

    @Override
    public String toString() {
        return "PhysicalMemoryInfoBean{" +
                "freePhysicalMemorySize=" + freePhysicalMemorySize +
                ", totalPhysicalMemorySize=" + totalPhysicalMemorySize +
                ", usedPhysicalMemorySize=" + usedPhysicalMemorySize +
                ", freeSwapSpaceSize=" + freeSwapSpaceSize +
                ", totalSwapSpaceSize=" + totalSwapSpaceSize +
                ", usedSwapSpaceSize=" + usedSwapSpaceSize +
                '}';
    }

    public long getFreePhysicalMemorySize() {
        return freePhysicalMemorySize;
    }

    public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
        this.freePhysicalMemorySize = freePhysicalMemorySize;
    }

    public long getTotalPhysicalMemorySize() {
        return totalPhysicalMemorySize;
    }

    public void setTotalPhysicalMemorySize(long totalPhysicalMemorySize) {
        this.totalPhysicalMemorySize = totalPhysicalMemorySize;
    }

    public long getUsedPhysicalMemorySize() {
        return usedPhysicalMemorySize;
    }

    public void setUsedPhysicalMemorySize(long usedPhysicalMemorySize) {
        this.usedPhysicalMemorySize = usedPhysicalMemorySize;
    }

    public long getFreeSwapSpaceSize() {
        return freeSwapSpaceSize;
    }

    public void setFreeSwapSpaceSize(long freeSwapSpaceSize) {
        this.freeSwapSpaceSize = freeSwapSpaceSize;
    }

    public long getTotalSwapSpaceSize() {
        return totalSwapSpaceSize;
    }

    public void setTotalSwapSpaceSize(long totalSwapSpaceSize) {
        this.totalSwapSpaceSize = totalSwapSpaceSize;
    }

    public long getUsedSwapSpaceSize() {
        return usedSwapSpaceSize;
    }

    public void setUsedSwapSpaceSize(long usedSwapSpaceSize) {
        this.usedSwapSpaceSize = usedSwapSpaceSize;
    }
}
