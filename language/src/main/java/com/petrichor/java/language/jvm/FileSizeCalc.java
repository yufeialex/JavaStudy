package com.petrichor.java.language.jvm;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池设置为CPU核心数+1个，这个运行结果是大象在工作电脑(CPU：G630 内存：4G JDK1.7.0_51)上跑出来的。如果在这里把线程池加大，
 * 比如调到100，你会发现所用时间变多了，大象这里最多的消耗时间是0.297秒，与之前最少的一次0.218之间相差0.079秒，也即79毫秒。
 * 当然这多出来的时间在我们看来好像不算什么，只有零点零几秒，但是对于CPU来说可是相当长的，因为CPU里面是以纳秒为计算单位，
 * 1毫秒=1000000纳秒。所以加大线程池会增加CPU上下文的切换成本，有时程序的优化就是从这些微小的地方积累起来的。
 * Created by XinYufei on 2018/1/11.
 */
public class FileSizeCalc {
    static class SubDirsAndSize {
        final long size;
        final List<File> subDirs;

        SubDirsAndSize(long size, List<File> subDirs) {
            this.size = size;
            this.subDirs = Collections.unmodifiableList(subDirs);
        }
    }

    private SubDirsAndSize getSubDirsAndSize(File file) {
        long total = 0;
        List<File> subDirs = new ArrayList<File>();
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (child.isFile())
                        total += child.length();
                    else
                        subDirs.add(child);
                }
            }
        }
        return new SubDirsAndSize(total, subDirs);
    }

    private long getFileSize(File file) throws Exception {
        final int cpuCore = Runtime.getRuntime().availableProcessors();
        final int poolSize = cpuCore + 1;
        ExecutorService service = Executors.newFixedThreadPool(poolSize);
        long total = 0;
        List<File> directories = new ArrayList<File>();
        directories.add(file);
        SubDirsAndSize subDirsAndSize = null;
        try {
            while (!directories.isEmpty()) {
                List<Future<SubDirsAndSize>> partialResults = new ArrayList<Future<SubDirsAndSize>>();
                for (final File directory : directories) {
                    partialResults.add(service.submit(new Callable<SubDirsAndSize>() {
                        @Override
                        public SubDirsAndSize call() throws Exception {
                            return getSubDirsAndSize(directory);
                        }
                    }));
                }
                directories.clear();
                for (Future<SubDirsAndSize> partialResultFuture : partialResults) {
                    subDirsAndSize = partialResultFuture.get(100, TimeUnit.SECONDS);
                    total += subDirsAndSize.size;
                    directories.addAll(subDirsAndSize.subDirs);
                }
            }
            return total;
        } finally {
            service.shutdown();
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            final long start = System.currentTimeMillis();
            long total = new FileSizeCalc().getFileSize(new File("e:/m2"));
            final long end = System.currentTimeMillis();
            System.out.format("文件夹大小: %dMB%n", total / (1024 * 1024));
            System.out.format("所用时间: %.3fs%n", (end - start) / 1.0e3);
        }
    }
}
