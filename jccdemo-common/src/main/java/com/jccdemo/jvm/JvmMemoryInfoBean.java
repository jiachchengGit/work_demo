package com.jccdemo.jvm;

import java.io.Serializable;

/**
 * Created by chenjiacheng on 2017-07-04.
 */
public class JvmMemoryInfoBean implements Serializable {
    private long maxMemory; //kb
    private long totalMemory;//kb
    private long freeMemory;//kb
    private long usedMemory;//kb

    private long nonHeapMemoryUsed;//kb
    private long nonHeapMemoryMax;//kb
    private long nonHeapMemoryInit;//kb

    private long usedPermGen;//kb
    private long maxPermGen;//kb

    @Override
    public String toString() {
        return "JvmMemoryInfoBean{" +
                "maxMemory=" + maxMemory +
                ", totalMemory=" + totalMemory +
                ", freeMemory=" + freeMemory +
                ", usedMemory=" + usedMemory +
                ", nonHeapMemoryUsed=" + nonHeapMemoryUsed +
                ", nonHeapMemoryMax=" + nonHeapMemoryMax +
                ", nonHeapMemoryInit=" + nonHeapMemoryInit +
                ", usedPermGen=" + usedPermGen +
                ", maxPermGen=" + maxPermGen +
                '}';
    }

    public long getUsedPermGen() {
        return usedPermGen;
    }

    public void setUsedPermGen(long usedPermGen) {
        this.usedPermGen = usedPermGen;
    }

    public long getMaxPermGen() {
        return maxPermGen;
    }

    public void setMaxPermGen(long maxPermGen) {
        this.maxPermGen = maxPermGen;
    }

    public long getNonHeapMemoryUsed() {
        return nonHeapMemoryUsed;
    }

    public void setNonHeapMemoryUsed(long nonHeapMemoryUsed) {
        this.nonHeapMemoryUsed = nonHeapMemoryUsed;
    }

    public long getNonHeapMemoryMax() {
        return nonHeapMemoryMax;
    }

    public void setNonHeapMemoryMax(long nonHeapMemoryMax) {
        this.nonHeapMemoryMax = nonHeapMemoryMax;
    }

    public long getNonHeapMemoryInit() {
        return nonHeapMemoryInit;
    }

    public void setNonHeapMemoryInit(long nonHeapMemoryInit) {
        this.nonHeapMemoryInit = nonHeapMemoryInit;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(long usedMemory) {
        this.usedMemory = usedMemory;
    }
}
