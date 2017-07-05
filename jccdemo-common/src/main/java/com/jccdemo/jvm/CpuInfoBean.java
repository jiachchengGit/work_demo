package com.jccdemo.jvm;

import java.io.Serializable;

/**
 * Created by chenjiacheng on 2017-07-04.
 */
public class CpuInfoBean implements Serializable{

    private String osName;
    private double cpuRatio;

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public double getCpuRatio() {
        return cpuRatio;
    }

    public void setCpuRatio(double cpuRatio) {
        this.cpuRatio = cpuRatio;
    }

    @Override
    public String toString() {
        return "CpuInfoBean{" +
                "osName='" + osName + '\'' +
                ", cpuRatio=" + cpuRatio +
                '}';
    }
}
