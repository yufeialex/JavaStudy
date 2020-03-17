package com.petrichor.java.utillearn.acommons;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author xinyufei
 * @date 2019/12/11
 */
public class ConcurrentUtilDemo {
    /**
     * 线程名
     */
    private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2,
            new BasicThreadFactory.Builder().namingPattern("PriKeyContext-schedule-pool-%d").daemon(true).build());

    public static void main(String[] args) {

    }
}
