package com.yufei.languagebasic.concurrent.executors.useExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledJob extends Thread {
    public void run() {
        System.out.println("run");
    }

    public static void main(String args[]) throws Exception {

        ScheduledJob command = new ScheduledJob();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 5, 1, TimeUnit.SECONDS);
    }
}