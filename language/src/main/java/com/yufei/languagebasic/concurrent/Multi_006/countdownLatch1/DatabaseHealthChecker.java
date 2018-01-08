package com.yufei.languagebasic.concurrent.Multi_006.countdownLatch1;

import java.util.concurrent.CountDownLatch;

/**
 * Created by XinYufei on 2018/1/8.
 */
public class DatabaseHealthChecker extends BaseHealthChecker{
    public DatabaseHealthChecker (CountDownLatch latch)  {
        super("Database Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(9000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
