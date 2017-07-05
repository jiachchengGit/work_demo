package com.jccdemo.jvm;


/**
 * Created by chenjiacheng on 2017-07-04.
 */
public class TestJVMMain {

    public static void main(String[] args) {
        JvmMemoryInfoBean jvmMemory = JvmManagerUtils.getJvmMemory();
        System.out.println(jvmMemory.toString());
        PhysicalMemoryInfoBean physicalMemory = JvmManagerUtils.getPhysicalMemory();
        System.out.println(physicalMemory.toString());
        JvmThreadInfoBean threadInfo = JvmManagerUtils.getThreadInfo();
        System.out.println(threadInfo.toString());
        CpuInfoBean cpuInfo = JvmManagerUtils.getCpuInfo();
        System.out.println(cpuInfo.toString());
    }
}
