package utils;

//import java.lang.management.ThreadMXBean;
//import java.lang.management.ManagementFactory;
//import java.lang.management.MemoryMXBean;
//import java.lang.management.MemoryUsage;
//import java.lang.management.OperatingSystemMXBean;
//import java.lang.management.RuntimeMXBean;

public class Profiler {
//
//    private static void println(String text) { System.out.println(text); }
//
//    public static void printTitle(String title) { println("====== " + title + " ======"); }
//​
//    public static void printSubtitle(String sub) { println("   --- " + sub + " ---"); }
//​
//    public static void printInfo(String label, Object text) { println("      " + label + ": " + text); }
//
//    public static void printBullet(String text) { println("       -" + text); }
//​
//    private static final long MILLISECOND = 1000000L;
//    private static final long MEGABYTE = 1024L * 1024L;
//​
//    private static long nanoToMillis(long ns) { return ns / MILLISECOND; }
//​
//    private static String nanoToString(long ns) {
//        long ms = nanoToMillis(ns);
//        return ms + "." + (ns - (ms * MILLISECOND)) + "ms";
//    }
//​
//    private static String millisToString(long ms) { return ms + "ms"; }
//​
//    private static long bytesToMegabytes(long bytes) { return bytes / MEGABYTE; }
//​
//    private static String memoryToString(long memory) { return bytesToMegabytes(memory) + "MB"; }
//​
//    private static void printMemoryUsage(MemoryUsage mu, String kind) {
//        long cb = mu.getCommitted();
//        long ib = mu.getInit();
//        long mb = mu.getMax();
//        long ub = mu.getUsed();
//​
//        printSubtitle(kind + " Memory Usage");
//        if (ib > 0)
//        {
//            printInfo("Init", memoryToString(ib));
//        }
//        if (ub > 0)
//        {
//            printInfo("Used", memoryToString(ub));
//        }
//        if (cb > 0)
//        {
//            printInfo("Committed", memoryToString(cb));
//        }
//        if (mb > 0)
//        {
//            printInfo("Max", memoryToString(mb));
//        }
//    }
//​
//    private Runtime runtime;
//    private MemoryMXBean memoryBean;
//​
//    private ThreadMXBean threadBean;
//    private OperatingSystemMXBean osBean;
//    private RuntimeMXBean runtimeBean;
//​
//    private Hashtable<String, Long> stopwatch;
//​
//    public Profiler() {
//        threadBean = ManagementFactory.getThreadMXBean();
//        osBean = ManagementFactory.getOperatingSystemMXBean();
//        runtimeBean = ManagementFactory.getRuntimeMXBean();
//        memoryBean = ManagementFactory.getMemoryMXBean();
//        runtime = Runtime.getRuntime();
//​
//        stopwatch = new Hashtable<>();
//    }
//​
//    public void profile() {
//        printTitle("PROFILE");
//        cpuDump();
//        memoryDump();
//    }
//​
//    public void cpuDump() {
//        printSubtitle("CPU");
//
//        if (!threadBean.isThreadCpuTimeSupported())
//        {
//            Log.error("CPU Time is not supported");
//​
//        }
//        else
//        {
//            printInfo("Total CPU Time", nanoToString(threadBean.getCurrentThreadCpuTime()));
//            printInfo("User CPU Time", nanoToString(threadBean.getCurrentThreadUserTime()));
//        }
//​
//        printInfo("JVM Uptime", millisToString(runtimeBean.getUptime()));
//        printInfo("Thread Count", threadBean.getTotalStartedThreadCount());
//        printInfo("Operating System", osBean.getName() + " " + osBean.getVersion() + " " + osBean.getArch());
//        printInfo("Average Load", osBean.getSystemLoadAverage());
//        printInfo("Processors", osBean.getAvailableProcessors());
//    }
//​
//    public void memoryDump() {
//        printSubtitle("Memory");
//        printInfo("Max Memory", memoryToString(runtime.maxMemory()));
//        printInfo("Total Memory", memoryToString(runtime.totalMemory()));
//        printInfo("Free Memory", memoryToString(runtime.freeMemory()));
//
//        printMemoryUsage(memoryBean.getHeapMemoryUsage(), "Heap");
//        printMemoryUsage(memoryBean.getNonHeapMemoryUsage(), "Non Heap");
//    }
//​
//    private void cleanMemory() { runtime.gc(); }
//​
//    public void start(String name) {
//        printTitle("Starting Stopwatch " + name);
//        stopwatch.put(name, System.nanoTime());
//    }
//​
//    public void stop(String name) {
//        long temp = System.nanoTime() - stopwatch.get(name);
//        printTitle("Stopping Stopwatch " + name + ": " + nanoToString(temp));
//    }

}