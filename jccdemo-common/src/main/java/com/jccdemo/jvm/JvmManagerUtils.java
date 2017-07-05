package com.jccdemo.jvm;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.StringTokenizer;
import com.sun.management.OperatingSystemMXBean;
/**
 * Created by chenjiacheng on 2017-07-04.
 */
public class JvmManagerUtils {

    private static final int FAULTLENGTH = 10;
    private static final int CPUTIME = 30;
    private static final int PERCENT = 100;
    private static final File versionFile = new File("/proc/version");
    private static String linuxVersion = null;
    private static long KB=1024*1024;

    /**
     * 获取线程信息
     * @return
     */
    public static JvmThreadInfoBean getThreadInfo(){
        JvmThreadInfoBean bean = new JvmThreadInfoBean();
        int threadCount = ManagementFactory.getThreadMXBean().getThreadCount();
        int daemonThreadCount = ManagementFactory.getThreadMXBean().getDaemonThreadCount();
        int peakThreadCount = ManagementFactory.getThreadMXBean().getPeakThreadCount();
        long[] deadlockedThreads = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
        long[] monitorDeadlockedThreads = ManagementFactory.getThreadMXBean().findMonitorDeadlockedThreads();
        bean.setThreadCount(threadCount);
        bean.setDaemonThreadCount(daemonThreadCount);
        bean.setPeakThreadCount(peakThreadCount);
        bean.setDeadlockedThreads(deadlockedThreads == null?0:deadlockedThreads.length);
        bean.setMonitorDeadlockedThreads(monitorDeadlockedThreads==null?0:monitorDeadlockedThreads.length);
        return bean;
    }

    /**
     * 获取机器内存信息
     * @return
     */
    public static PhysicalMemoryInfoBean getPhysicalMemory(){
        PhysicalMemoryInfoBean bean = new PhysicalMemoryInfoBean();
        OperatingSystemMXBean os = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        long freePhysicalMemorySize = os.getFreePhysicalMemorySize();
        long totalPhysicalMemorySize = os.getTotalPhysicalMemorySize();
        bean.setFreePhysicalMemorySize(freePhysicalMemorySize /KB);
        bean.setTotalPhysicalMemorySize(totalPhysicalMemorySize /KB);
        long freeSwapSpaceSize = os.getFreeSwapSpaceSize();
        long totalSwapSpaceSize = os.getTotalSwapSpaceSize();
        bean.setFreeSwapSpaceSize(freeSwapSpaceSize /KB);
        bean.setTotalSwapSpaceSize(totalSwapSpaceSize /KB);
        bean.setUsedPhysicalMemorySize((totalPhysicalMemorySize-freePhysicalMemorySize)/KB);
        bean.setUsedSwapSpaceSize((totalSwapSpaceSize-freeSwapSpaceSize)/KB);
        return bean;
    }

    /**
     * 获取JVM内存信息
     * @return
     */
    public static JvmMemoryInfoBean getJvmMemory(){
        JvmMemoryInfoBean bean = new JvmMemoryInfoBean();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        bean.setFreeMemory(freeMemory/KB);
        bean.setMaxMemory(maxMemory/KB);
        bean.setTotalMemory(totalMemory/KB);
        bean.setUsedMemory((totalMemory-freeMemory)/KB);

        long nonHeapMemoryUsed = (ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed());
        long nonHeapMemoryMax = (ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getMax());
        long nonHeapMemoryInit = (ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getInit());
        bean.setNonHeapMemoryInit(nonHeapMemoryInit/KB);
        bean.setNonHeapMemoryMax(nonHeapMemoryMax/KB);
        bean.setNonHeapMemoryUsed(nonHeapMemoryUsed/KB);

        final MemoryPoolMXBean permGenMemoryPool = getPermGenMemoryPool();
        if (permGenMemoryPool != null) {
            final MemoryUsage usage = permGenMemoryPool.getUsage();
            long usedPermGen = usage.getUsed();
            long maxPermGen = usage.getMax();
            bean.setMaxPermGen(maxPermGen/KB);
            bean.setUsedPermGen(usedPermGen/KB);
        }
        return bean;
    }

    /**
     * 获取CPU信息
     * @return
     */
    public static CpuInfoBean getCpuInfo(){
        CpuInfoBean bean = new CpuInfoBean();
        // 操作系统
        String osName = System.getProperty("os.name");
        double cpuRatio = 0;
        if (osName.toLowerCase().startsWith("windows")) {
            cpuRatio = getCpuRatioForWindows();
        } else {
            cpuRatio = getCpuRateForLinux();
        }

        bean.setOsName(osName);
        bean.setCpuRatio(cpuRatio);
        return bean;
    }

    private static MemoryPoolMXBean getPermGenMemoryPool() {
        for (final MemoryPoolMXBean memoryPool : ManagementFactory.getMemoryPoolMXBeans()) {
            // name est "Perm Gen" ou "PS Perm Gen" (32 vs 64 bits ?)
            if (memoryPool.getName().endsWith("Perm Gen")) {
                return memoryPool;
            }
        }
        return null;
    }

    private static void freeResource(InputStream is, InputStreamReader isr, BufferedReader br) {
        try {
            if (is != null)
                is.close();
            if (isr != null)
                isr.close();
            if (br != null)
                br.close();
        } catch (IOException ioe) {
        }
    }

    private static double getCpuRateForLinux() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader brStat = null;
        StringTokenizer tokenStat = null;
        try {
            System.out.println("Get usage rate of CUP , linux version: " + linuxVersion);
            Process process = Runtime.getRuntime().exec("top -b -n 1");
            is = process.getInputStream();
            isr = new InputStreamReader(is);
            brStat = new BufferedReader(isr);

            if (linuxVersion !=null && linuxVersion.equals("2.4")) {
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();
                brStat.readLine();

                tokenStat = new StringTokenizer(brStat.readLine());
                tokenStat.nextToken();
                tokenStat.nextToken();
                String user = tokenStat.nextToken();
                tokenStat.nextToken();
                String system = tokenStat.nextToken();
                tokenStat.nextToken();
                String nice = tokenStat.nextToken();

                System.out.println(user + " , " + system + " , " + nice);

                user = user.substring(0, user.indexOf("%"));
                system = system.substring(0, system.indexOf("%"));
                nice = nice.substring(0, nice.indexOf("%"));

                float userUsage = new Float(user).floatValue();
                float systemUsage = new Float(system).floatValue();
                float niceUsage = new Float(nice).floatValue();

                return (userUsage + systemUsage + niceUsage) / 100;
            } else {
                brStat.readLine();
                brStat.readLine();

                tokenStat = new StringTokenizer(brStat.readLine());
                tokenStat.nextToken();
                tokenStat.nextToken();
                tokenStat.nextToken();
                tokenStat.nextToken();
                tokenStat.nextToken();
                tokenStat.nextToken();
                tokenStat.nextToken();
                String cpuUsage = tokenStat.nextToken();

                System.out.println("CPU idle : " + cpuUsage);
                Float usage = new Float(cpuUsage.substring(0, cpuUsage.indexOf("%")));

                return (1 - usage.floatValue() / 100);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            freeResource(is, isr, brStat);
            return 1;
        } finally {
            freeResource(is, isr, brStat);
        }
    }
    /**
     * 获得CPU使用率.
     *
     * @return 返回cpu使用率
     * @author GuoHuang
     */
    private static double getCpuRatioForWindows() {
        try {
            String procCmd = System.getenv("windir")
                    + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"
                    + "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
            // 取进程信息
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
            Thread.sleep(CPUTIME);
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
            if (c0 != null && c1 != null) {
                long idletime = c1[0] - c0[0];
                long busytime = c1[1] - c0[1];
                return Double.valueOf(PERCENT * (busytime) / (busytime + idletime))
                        .doubleValue();
            } else {
                return 0.0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0.0;
        }
    }

    /**
     * 读取CPU信息.
     *
     * @param proc
     * @return
     * @author GuoHuang
     */
    private static long[] readCpu(final Process proc) {
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine();
            if (line == null || line.length() < FAULTLENGTH) {
                return null;
            }
            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int rocidx = line.indexOf("ReadOperationCount");
            int umtidx = line.indexOf("UserModeTime");
            int kmtidx = line.indexOf("KernelModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            long idletime = 0;
            long kneltime = 0;
            long usertime = 0;
            while ((line = input.readLine()) != null) {
                if (line.length() < wocidx) {
                    continue;
                }
                // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
                // ThreadCount,UserModeTime,WriteOperation
                String caption = Bytes.substring(line, capidx, cmdidx - 1).trim();
                String cmd = Bytes.substring(line, cmdidx, kmtidx - 1).trim();
                if (cmd.indexOf("wmic.exe") >= 0) {
                    continue;
                }
                // log.info("line="+line);
                if (caption.equals("System Idle Process")
                        || caption.equals("System")) {
                    idletime += Long.valueOf(Bytes.substring(line, kmtidx, rocidx - 1).trim()).longValue();
                    idletime += Long.valueOf(Bytes.substring(line, umtidx, wocidx - 1).trim()).longValue();
                    continue;
                }

                kneltime += Long.valueOf( Bytes.substring(line, kmtidx, rocidx - 1).trim()).longValue();
                usertime += Long.valueOf(Bytes.substring(line, umtidx, wocidx - 1).trim()).longValue();
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
